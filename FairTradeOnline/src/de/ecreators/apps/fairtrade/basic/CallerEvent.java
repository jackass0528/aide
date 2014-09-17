package de.ecreators.apps.fairtrade.basic;

import de.ecreators.apps.fairtrade.model.*;
import java.util.*;

public class CallerEvent<T extends EventHandler<E>, E extends EventArgs> implements IDisposable
{
	private final String eventName;
	private final Object owner;
	
	public CallerEvent(String event, Object owner) {
		this.eventName = event;
		this.owner = owner;
	}

	public Object getOwner()
	{
		return owner;
	}

	public String getEventName()
	{
		return eventName;
	}
	
	@Override
	public void dispose()
	{
		handlers.clear();
	}
	
	private final ArrayList<T> handlers = new ArrayList<>();

	private boolean inCall;

	public void addHandler(T handler)
	{
		if (handler != null)
		{
			handlers.add(handler);
		}
	}

	public void removeHandler(T handler)
	{
		if (handler != null && handlers.contains(handler) && !handler.isSealed())
		{
			handlers.remove(handler);
		}
	}

	public void raise(Object sender, E args)
	{
		if (!inCall)
		{
			inCall = true;
			for (T h : handlers)
			{
				h.onEventHandled(sender, args);
			}
			inCall = false;
		}
	}
}
