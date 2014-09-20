package de.ecreators.apps.fairtrade.http;

import de.ecreators.apps.fairtrade.*;
import de.ecreators.apps.fairtrade.basic.*;
import de.ecreators.apps.fairtrade.basic.model.*;
import de.ecreators.apps.fairtrade.http.commands.*;
import java.util.*;
import org.json.*;
import android.util.*;
import de.ecreators.apps.fairtrade.http.model.*;

public class ServiceConnection
{
	private static final LazyBuilder<ServiceConnection> serviceBuilder;
	private static final Lazy<ServiceConnection> lazyConnection;

	static
	{
		serviceBuilder = new LazyBuilder<ServiceConnection>() 
		{
			@Override
			public ServiceConnection create(List<Object> e)
			{
				return new ServiceConnection();
			}
		};
		lazyConnection = new Lazy<ServiceConnection>(serviceBuilder);
	}

	public static ServiceConnection get()
	{
		return lazyConnection.get();
	}

	private static final AppIdCommand cmdGetAppId = new AppIdCommand();

	private ServiceConnection()
	{ }

	public String registerAppStart() 
	{
		//http://ecreators.lima-city.de/service.php?m=
		//get_app_id_by_name&
		//name=de.ecreators-productions.apps.ffxiv-map-editor&
		//db=db_299576_1

		cmdGetAppId.create(Program.APP_NAME, Program.DB);
		JSONObject json = HttpJson.getJSONfromURL(cmdGetAppId.getUrlFinal());
		if (json != null)
		{
			Log.i(getClass().getName(), json.toString());
			try
			{
				JResponse r = new JResponse(json);
				return (String)r.getData()[0];
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return null;
	}
}
