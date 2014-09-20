package de.ecreators.apps.fairtrade;

import android.database.*;
import android.database.sqlite.*;
import de.ecreators.apps.fairtrade.basic.*;
import de.ecreators.apps.fairtrade.dao.user.*;
import de.ecreators.apps.fairtrade.dao.user_value.*;
import de.ecreators.apps.fairtrade.dao.value_blackout_date.*;
import java.util.*;
import java.util.regex.*;
import android.util.*;
import java.io.*;

public class Tables
{
	private Tables()
	{	}

	private static final ArrayList<DAO<? extends SaveObjectBase>> tables = new ArrayList<DAO<? extends SaveObjectBase>>() {{
			add(new UserTableDAO());
			add(new UserValuesTableDAO());
			add(new ValuesBlackOutTableDAO());
		}};

	public static <T extends DAO<? extends SaveObjectBase>> T getDAO(Class<T> clazz)
	{
		for (DAO<? extends SaveObjectBase> dao : tables)
		{
			if (dao.getClass() == clazz &&
				dao instanceof T)
			{
				return (T)dao;
			}
		}
		return null;
	}

	public static void create(SQLiteDatabase db)
	{
		for (DAO table : tables)
		{
			String sql = table.getCreateStatement();
			Log.i(Tables.class.getName(), "sql: " + sql);
			db.execSQL(sql);
		}
	}

	public static abstract class DAO<T extends SaveObjectBase>
	{
		public abstract String getCreateStatement();
		public abstract void save(SQLiteDatabase db, Collection<T> items);
		public abstract Collection<T> getAll(SQLiteDatabase db);

		protected static final RowMapper<Integer> IntMapper = new RowMapper<Integer>() { @Override		public Integer map(Cursor row, int columnIndex)
			{ return !row.isNull(columnIndex) ? ((Float)row.getFloat(columnIndex)).intValue() : -1; }	};
		protected static final RowMapper<Boolean> BoolMapper = new RowMapper<Boolean>() { @Override	public Boolean map(Cursor row, int columnIndex)
			{ return !row.isNull(columnIndex) ? ((Float)row.getFloat(columnIndex)).intValue() == 1 : false; }	};
		protected static final RowMapper<String> StringMapper = new RowMapper<String>() { @Override	public String map(Cursor row, int columnIndex)
			{ return !row.isNull(columnIndex) ? row.getString(columnIndex) : null; }	};
		protected static final RowMapper<UUID> UUIDMapper = new RowMapper<UUID>() {		 @Override	public UUID map(Cursor row, int columnIndex)
			{ return !row.isNull(columnIndex) ? UUID.fromString(row.getString(columnIndex)) : null; }	};
		protected static final RowMapper<Double> DoubleMapper = new RowMapper<Double>() { @Override	public Double map(Cursor row, int columnIndex)
			{ return !row.isNull(columnIndex) ? ((Float)row.getFloat(columnIndex)).doubleValue() : -1.0; }	};
		protected static final RowMapper<Byte[]> DataMapper = new RowMapper<Byte[]>() { @Override	public Byte[] map(Cursor row, int columnIndex)
			{ return !row.isNull(columnIndex) ? asBytes(row.getBlob(columnIndex)) : null; }	};

		private static Byte[] asBytes(byte[] d)
		{
			Byte[] res = new Byte[d.length];
			int i = 0;
			for (byte b : d)
			{
				res[i++] = b;
			}
			return res;
		}

		protected static final RowMapper<ShortDate> ShortDateMapper = new RowMapper<ShortDate>() { 
			@Override	
			public ShortDate map(Cursor row, int columnIndex)
			{ 
				return !row.isNull(columnIndex) ? getShortDateFromString(row.getString(columnIndex)) : null; 
			}

			private final Pattern pattern = Pattern.compile("\\d{1,2}/\\d{4}");

			private ShortDate getShortDateFromString(String str)
			{
				if (pattern.matcher(str).find())
				{
					String[] m = str.split("/");
					return new ShortDate(Integer.parseInt(m[0]), Integer.parseInt(m[1]));
				}
				return null;
			}
		};

		protected static <T extends SaveObjectBase> Collection<T> readRows(Cursor result, RowMapper<T> rowMapper)
		{
			ArrayList<T> rows = new ArrayList<T>();
			if (result.moveToFirst())
			{
				do {
					rows.add(rowMapper.map(result, -1));
				} 
				while(result.moveToNext());
			}
			return rows;
		}

		protected static <V> V field(Cursor row, String fieldName, V fallback, RowMapper<V> mapper)
		{
			V result = fallback;
			int index = getFieldIndex(row, fieldName);
			if (index >= 0)
			{
				return mapper.map(row, index);
			}
			return result;
		}

		protected static int getFieldIndex(Cursor row, String fieldName)
		{
			// Sucht eine Spalte
			int l = row.getColumnCount();
			for (int i = 0; i < l; i++)
			{
				if (row.getColumnName(i).equalsIgnoreCase(fieldName))
				{
					return i;
				}
			}
			return -1;
		}

		protected static UUID asUUID(Cursor row, String nameOfField)
		{
			return field(row, nameOfField, (UUID)null, UUIDMapper);
		}

		protected static String asString(Cursor row, String nameOfField)
		{
			return field(row, nameOfField, (String)null, StringMapper);
		}

		protected static Boolean asBool(Cursor row, String nameOfField)
		{
			return field(row, nameOfField, false, BoolMapper);
		}

		protected static Integer asInt(Cursor row, String nameOfField)
		{
			return field(row, nameOfField, -1, IntMapper);
		}

		protected static Double asDouble(Cursor row, String nameOfField)
		{
			return field(row, nameOfField, -1.0, DoubleMapper);
		}

		protected static ShortDate asDate(Cursor row, String nameOfField)
		{
			return field(row, nameOfField, null, ShortDateMapper);
		}

		protected static Byte[] asByte(Cursor row, String nameOfField)
		{
			return field(row, nameOfField, null, DataMapper);
		}

		protected static <T extends SaveObjectBase> String getInsertUpdateOrDeleteStatement(T item, Iterable<String> allColumns, String table)
		{
			if (item.isDeleted())
			{
				String sql = String.format("delete from %s where (%s)", table, item.getPks(" and "));
				return sql;
			}

			StringBuilder sb = new StringBuilder("insert or replace into (");
			int i = 0;
			List<Object> m = new ArrayList<Object>();
			for (String column : allColumns)
			{
				DbValueResult e = new DbValueResult();
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

		public int countAll(SQLiteDatabase mysqliteDb)
		{
			String sql = String.format("select count(*) CountAll from %s;", getTableName());
			Log.i(getClass().getName(), "sql: " + sql);
			return mysqliteDb.rawQuery(sql, null).getInt(0);
		}

		protected <T extends SaveObjectBase> StringBuilder getTransactionSaveMultipleObjects(Collection<T> items, Iterable<String> allColumns)
		{
			StringBuilder sb = new StringBuilder("begin;");
			for (T item : items)
			{
				sb.append("\n").append(getInsertUpdateOrDeleteStatement(item, allColumns, getTableName()));
			}
			sb.append("\ncommit;");
			return sb;
		}

		protected abstract String getTableName();
	}
}
