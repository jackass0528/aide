package de.ecreators.apps.fairtrade.data;

import android.database.*;
import android.database.sqlite.*;
import de.ecreators.apps.fairtrade.model.*;
import de.ecreators.apps.fairtrade.data.model.*;
import java.util.*;

public class Tables
{
	private Tables()
	{	}
	
	private static final ArrayList<DAO> tables = new ArrayList<DAO>() {{
		add(new UserTableDAO());
		add(new UserValuesTableDAO());
		add(new ValuesBlackOutTableDAO());
	}};
	
	public static void create(SQLiteDatabase db)
	{
		for(DAO table : tables) {
			db.execSQL(table.getCreateStatement());
		}
	}
	
	public static abstract class DAO<T extends SaveObjectBase> {
		public abstract String getCreateStatement();
		public abstract void save(SQLiteDatabase db, Collection<T> items);
		public abstract Collection<T> getAll(SQLiteDatabase db);
		
		protected static final RowMapper IntMapper = new RowMapper<Integer>() { @Override		public Integer map(Cursor row, int columnIndex) { return !row.isNull(columnIndex) ? ((Float)row.getFloat(columnIndex)).intValue() : -1; }	};
		protected static final RowMapper BoolMapper = new RowMapper<Boolean>() { @Override	public Boolean map(Cursor row, int columnIndex) { return !row.isNull(columnIndex) ? ((Float)row.getFloat(columnIndex)).intValue() == 1 : false; }	};
		protected static final RowMapper StringMapper = new RowMapper<String>() { @Override	public String map(Cursor row, int columnIndex) { return !row.isNull(columnIndex) ? row.getString(columnIndex) : null; }	};
		protected static final RowMapper UUIDMapper = new RowMapper<UUID>() {		 @Override	public UUID map(Cursor row, int columnIndex) { return !row.isNull(columnIndex) ? UUID.fromString(row.getString(columnIndex)) : null; }	};
		protected static final RowMapper DoubleMapper = new RowMapper<Double>() { @Override	public Double map(Cursor row, int columnIndex) { return !row.isNull(columnIndex) ? ((Float)row.getFloat(columnIndex)).doubleValue() : -1.0; }	};
		protected static final RowMapper DataMapper = new RowMapper<byte[]>() { @Override	public byte[] map(Cursor row, int columnIndex) { return !row.isNull(columnIndex) ? row.getBlob(columnIndex) : null; }	};
		
		protected static <T extends SaveObjectBase> Collection<T> readRows(Cursor result, RowMapper<T> rowMapper)
		{
			ArrayList<T> rows = new ArrayList<T>();
			if(result.moveToFirst()) {
				do {
					rows.add(rowMapper.map(result, -1));
				} 
				while(result.moveToNext());
			}
			return rows;
		}
		
		protected static <V> V field(Cursor row, String fieldName, V fallback, RowMapper<V> mapper) {
			V result = fallback;
			int index = getFieldIndex(row, fieldName);
			if(index >= 0) {
				return mapper.map(row, index);
			}
			return result;
		}

		protected static int getFieldIndex(Cursor row, String fieldName) {
			// Sucht eine Spalte
			int l = row.getColumnCount();
			for(int i = 0; i < l; i++) {
				if(row.getColumnName(i).equalsIgnoreCase(fieldName)) {
					return i;
				}
			}
			return -1;
		}
		
		protected static UUID asUUID(Cursor row, String nameOfField) {
			return field(row, nameOfField, (UUID)null, UUIDMapper);
		}

		protected static String asString(Cursor row, String nameOfField) {
			return field(row, nameOfField, (String)null, StringMapper);
		}

		protected static Boolean asBool(Cursor row, String nameOfField) {
			return field(row, nameOfField, false, BoolMapper);
		}

		protected static Integer asInt(Cursor row, String nameOfField) {
			return field(row, nameOfField, -1, IntMapper);
		}

		protected static Double asDouble(Cursor row, String nameOfField) {
			return field(row, nameOfField, -1.0, DoubleMapper);
		}

		protected static Byte[] asByte(Cursor row, String nameOfField) {
			return field(row, nameOfField, null, DataMapper);
		}
		
		protected static <T extends SaveObjectBase> String getInsertOrReplaceStatement(T item, Iterable<String> allColumns, String table)
		{
			if(item.isDeleted()) {
				String sql = String.format("delete from %s where (%s)", table, item.getPks(" and "));
				return sql;
			}
			
			StringBuilder sb = new StringBuilder("insert or replace into (");
			int i = 0;
			List<Object> m = new ArrayList<Object>();
			for (String column : allColumns)
			{
				ColumnResult e = new ColumnResult();
				if (item.readColumnValue(column, e))
				{
					m.add(e.getValue());
					if (i > 0)
					{
						sb.append(",");
					}
					sb.append(column);
					i++;
				}
			}
			sb.append(" values (");
			i = 0;
			for (Object v : m)
			{
				if (i > 0)
				{
					sb.append(",");
				}
				sb.append(v);
				i++;
			}
			sb.append(");");

			return sb.toString();
		}
	}
}
