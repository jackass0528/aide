package de.ecreators.apps.fairtrade.http.commands;

import de.ecreators.apps.fairtrade.basic.*;
import de.ecreators.apps.fairtrade.basic.model.*;
import de.ecreators.apps.fairtrade.http.*;

public class AppIdCommand extends HttpCommand
{
	public AppIdCommand()
	{
		super("get_app_id_by_name",
			  new KeyValue("name", null),
			  new KeyValue("db", null));
	}
	
	public void create(String appName, String dbName) {
		reset();
		super.setParameter(0, appName);
		super.setParameter(1, dbName);
	}
}
