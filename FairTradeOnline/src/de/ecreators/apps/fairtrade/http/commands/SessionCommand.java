package de.ecreators.apps.fairtrade.http.commands;

import de.ecreators.apps.fairtrade.http.*;
import de.ecreators.apps.fairtrade.basic.*;

public class SessionCommand extends HttpCommand
{
	public SessionCommand()
	{
		super("get_session", 
			  new KeyValue("id", null));
	}

	public void create(String appId)
	{
		reset();
		setParameter(0, appId);
	}
}
