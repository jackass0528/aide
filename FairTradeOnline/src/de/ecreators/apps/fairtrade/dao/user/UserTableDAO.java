package de.ecreators.apps.fairtrade.dao.user;

import android.database.*;
import android.database.sqlite.*;
import de.ecreators.apps.fairtrade.*;
import de.ecreators.apps.fairtrade.basic.*;
import de.ecreators.apps.fairtrade.dao.user.*;
import de.ecreators.apps.fairtrade.utils.*;
import java.util.*;

public final class UserTableDAO extends Tables.DAO<UserModel>
{
	@Override
	public void save(SQLiteDatabase db, Collection<UserModel> items)
	{
		StringBuilder sb = new StringBuilder("begin;");
		for (UserModel item : items)
		{
			sb.append("\n").append(getInsertOrReplaceStatement(item, Columns.all, TableName));
		}
		sb.append("\ncommit;");
		db.execSQL(sb.toString());
	}

	@Override
	public Collection<UserModel> getAll(SQLiteDatabase db)
	{
		String sql = "select * from %s;";
		Cursor result = db.rawQuery(String.format(sql, TableName), null);

		return readRows(result, Columns.UserRowMapper);
	}

	@Override
	public String getCreateStatement()
	{
		return String.format("create table %s(%s);", TableName, StringUtils.join(", ", ListUtils.casting(getColumnDefinitions())));
	}

	private Collection<String> getColumnDefinitions()
	{
		return new ArrayList<String>() {{
				add(Columns.UserId + " varchar(38) primary key");
				add(Columns.UserName + " varchar(100)");
				add(Columns.PasswordHash + " varchar(255)");
				add(Columns.VisibleName + " varchar(255)");
				add(Columns.IsLeftSide + " int(1)");
			}};
	}
	
	// Name der Tabelle
	public static final String TableName = "tbl" + UserTableDAO.class.getName().replace("DAO", null);

	// Verwaltung der Spalten der Tabelle
	public static class Columns
	{
		private Columns()
		{
		}

		public static final String UserName = "UserName";
		public static final String PasswordHash = "PWHash";
		public static final String UserId = "UserId";
		public static final String VisibleName = "VisibleName";
		public static final String IsLeftSide = "IsLeft";

		// Alle Spalten zusammengefasst
		public static Iterable<String> all = new ArrayList<String>() {{
				add(UserId);
				add(UserName);
				add(PasswordHash);
				add(VisibleName);
				add(IsLeftSide);
			}};
		
		// Liest eine Zeile Aus.
		public static RowMapper<UserModel> UserRowMapper = new RowMapper<UserModel>() {

			@Override
			public UserModel map(final Cursor row, int columnIndex)
			{
				UserModel res = new UserModel() {{
						setId(asUUID(row, Columns.UserId));
						setName(asString(row, Columns.UserName));
						setVisName(asString(row, Columns.VisibleName));
						setPwHash(asString(row, Columns.PasswordHash));
						setIsLeft(asBool(row, Columns.IsLeftSide));
					}};
				return res;
			}
		};
	}
}
