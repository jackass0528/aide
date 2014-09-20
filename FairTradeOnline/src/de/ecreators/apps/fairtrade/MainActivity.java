package de.ecreators.apps.fairtrade;

import android.app.*;
import android.os.*;
import de.ecreators.apps.fairtrade.basic.*;
import de.ecreators.apps.fairtrade.dao.model.*;

public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

		// Legt den Kontext der Datenbank fest.
		FairTradeDb.init(this);
		
//		try 
//		{
//			MySqlDb db = new MySqlDb();
//			db.test();
//		}
//		catch (Exception ex)
//		{
//			ex.printStackTrace();
//		}
    }
}
