package de.ecreators.apps.fairtrade.data;
import java.util.*;
import android.database.sqlite.*;

public final class ValuesBlackOutTableDAO extends Tables.DAO
{
	@Override
	public void save(SQLiteDatabase db, Collection items)
	{
		// TODO: Implement this method
	}

	@Override
	public Collection getAll(SQLiteDatabase db)
	{
		// TODO: Implement this method
		return null;
	}

	@Override
	public <T extends SaveObjectBase> void save(SQLiteDatabase db, Collection<T> items)
	{
		// TODO: Implement this method
	}

	@Override
	public <T extends SaveObjectBase> Collection<T> getAll(SQLiteDatabase db)
	{
		// TODO: Implement this method
		return null;
	}
	
	@Override
	public String getCreateStatement()
	{
		// TODO: Implement this method
		return null;
	}
	
	// Verwaltung der Spalten der Tabelle
	public static class Columns
	{
		private Columns()
		{
		}

		public static final String UserName = "UserName";

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
