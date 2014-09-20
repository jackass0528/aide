package de.ecreators.apps.fairtrade.dao.model;

import android.content.*;
import android.database.sqlite.*;
import de.ecreators.apps.fairtrade.*;
import de.ecreators.apps.fairtrade.basic.*;
import android.util.*;
import de.ecreators.apps.fairtrade.basic.model.*;
import java.util.*;

public class FairTradeDb extends SQLiteOpenHelper
{
	private static final AppVersion version = new AppVersion(000, 001, 000, 001);

	public FairTradeDb(Context c)
	{
		super(c, FairTradeDb.class.getName(), null, version.getInt());
		printVersion();
	}

	private static final LazyBuilder<FairTradeDb> fairDbBuilder = new LazyBuilder<FairTradeDb>() 
	{
		@Override
		public FairTradeDb create(List<Object> e)
		{
			return new FairTradeDb((Context)e.get(0));
		}
	};
	private static final Lazy<FairTradeDb> lazyDb = new Lazy<FairTradeDb>(fairDbBuilder);

	public static void init(Context c)
	{
		fairDbBuilder.getParams().clear();
		fairDbBuilder.getParams().add(c);
	}

	public static FairTradeDb get()
	{
		return lazyDb.get();
	}

	private void printVersion()
	{
		Log.i(getClass().getName(), version.toString());
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
