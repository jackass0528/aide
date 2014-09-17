package de.ecreators.apps.fairtrade.data;
import android.database.*;
import android.database.sqlite.*;
import de.ecreators.apps.fairtrade.*;
import de.ecreators.apps.fairtrade.data.user_value.*;
import de.ecreators.apps.fairtrade.model.*;
import java.util.*;

public final class UserValuesTableDAO extends Tables.DAO
{
	@Override
	public void save(SQLiteDatabase db, Collection<UserValueModel> items)
	{
		StringBuilder sb = new StringBuilder("begin;");
		for (UserValueModel item : items)
		{
			sb.append("\n").append(getInsertOrReplaceStatement(item, Columns.all, TableName));
		}
		sb.append("\ncommit;");
		db.execSQL(sb.toString());
	}

	@Override
	public Collection getAll(SQLiteDatabase db)
	{
		String sql = "select * from %s;";
		Cursor result = db.rawQuery(String.format(sql, TableName), null);

		return readRows(result, Columns.UserValueRowMapper);
	}

	@Override
	public String getCreateStatement()
	{
		return String.format("create table %s(%s);", TableName, StringUtils.join(", ", ListUtils.casting(getColumnDefinitions())));
	}
	
	private Collection<String> getColumnDefinitions()
	{
		return new ArrayList<String>() {{
				add(Columns.ValueId + " varchar(38) primary key");
				add(Columns.UserIdFk + " varchar(38)");
				add(Columns.IntervalFk + " varchar(38)");
				add(Columns.DestUserFk + " varchar(38)");
				add(Columns.Enabled + " int(1) default 1");
				add(Columns.CreationDate + " date");
				add(Columns.EndDate + " date");
				add(Columns.Description + " varchar(256)");
				add(Columns.PayDay + " integer");
				add(Columns.Value + " double");
			}};
	}
	
	// Name der Tabelle
	public static final String TableName = "tbl" + UserValuesTableDAO.class.getName().replace("DAO", null);

	// Verwaltung der Spalten der Tabelle
	public static class Columns
	{
		private Columns()
		{
		}

		public static final String UserIdFk = "userIdFk";
		public static final String ValueId = "valueId";
		public static final String Value = "value";
		public static final String Enabled = "isEnabled";
		public static final String IntervalFk = "intervalFk";
		public static final String CreationDate = "creationDate";
		public static final String EndDate = "endDate";
		public static final String DestUserFk = "destUserIdFk";
		public static final String PayDay = "payDay";
		public static final String Description = "description";

		// Alle Spalten zusammengefasst
		public static Iterable<String> all = new ArrayList<String>() {{
				add(UserIdFk);
				add(ValueId);
				add(Value);
				add(Enabled);
				add(IntervalFk);
				add(CreationDate);
				add(EndDate);
				add(DestUserFk);
				add(PayDay);
			}};

		// Liest eine Zeile Aus.
		public static RowMapper<UserValueModel> UserValueRowMapper = new RowMapper<UserValueModel>() {

			@Override
			public UserValueModel map(final Cursor row, int columnIndex)
			{
				UserValueModel res = new UserValueModel() {{
						setId(asUUID(row, Columns.ValueId));
						setUserIdFk(asUUID(row, Columns.UserIdFk));
						setValue(asDouble(row, Columns.Value));
						setDescription(asString(row, Columns.Description));
						setEnabled(asBool(row, Columns.Enabled));
						setIntervalFk(asUUID(row, Columns.IntervalFk));
						setCreateDate(asString(row, Columns.CreationDate));
						setEndDate(asString(row, Columns.EndDate));
						setDestUserFk(asUUID(row, Columns.DestUserFk));
						setPayDay(asInt(row, Columns.PayDay));
					}};
				return res;
			}
		};
	}
}
