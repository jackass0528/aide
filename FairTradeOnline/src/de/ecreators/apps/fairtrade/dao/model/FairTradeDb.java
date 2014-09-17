package de.ecreators.apps.fairtrade.dao.model;

import android.content.*;
import android.database.sqlite.*;
import de.ecreators.apps.fairtrade.*;

public class FairTradeDb extends SQLiteOpenHelper
{
	public FairTradeDb(Context c)
	{
		super(c, FairTradeDb.class.getName(), null, 1);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db)
	{
		Tables.create(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		// TODO: Implement this method
	}
}
