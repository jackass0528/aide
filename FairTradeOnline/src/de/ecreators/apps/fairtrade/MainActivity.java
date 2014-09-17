package de.ecreators.apps.fairtrade;

import android.app.*;
import android.os.*;
import de.ecreators.apps.fairtrade.basic.*;

public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		MySqlDb db = new MySqlDb();
		db.test();
    }
}
