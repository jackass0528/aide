package de.ecreators.apps.fairtrade.data;

import android.database.sqlite.*;
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
	
	public static abstract class DAO {
		
		public abstract String getCreateStatement();
	}
}
