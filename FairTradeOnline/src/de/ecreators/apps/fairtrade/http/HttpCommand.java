package de.ecreators.apps.fairtrade.http;

import de.ecreators.apps.fairtrade.basic.*;
import de.ecreators.apps.fairtrade.basic.model.*;
import de.ecreators.apps.fairtrade.basic.prototypes.*;
import de.ecreators.apps.fairtrade.utils.*;
import java.net.*;
import java.util.*;

public abstract class HttpCommand
{
	private static final String URL = "http://ecreators.lima-city.de/service.php?m=";

	private static final IValueConverter<String, Object> urlConverter = new IValueConverter<String, Object>() {

		@Override
		public Object convert(String source)
		{
			return URL + source;
		}

		@Override
		public String convertBack(Object result)
		{
			try
			{
				throw new NotImplementedMethod();
			}
			catch (NotImplementedMethod e)
			{ }
			return "" + result;
		}
	};
	private final Property<String> urlProperty;
	private final Property<LockableList<KeyValue>> parametersProperty;
	private ArrayList<Object> defaults;
	private IForAction<KeyValue> doItem;

	protected HttpCommand(String url, KeyValue... parameters)
	{
		urlProperty = new Property<String>("Url", this, url);
		urlProperty.setReadOnly();
		urlProperty.setConverter(urlConverter);

		parametersProperty = new Property<LockableList<KeyValue>>("Parameters", this, new LockableList<KeyValue>());
		parametersProperty.setReadOnly();
		parametersProperty.getValue().addAll(ListUtils.asList(parameters));
		parametersProperty.getValue().setLocked(true);

		stash(ListUtils.asList(parameters));
	}

	private void stash(Iterable<KeyValue> parameters)
	{
		defaults = new ArrayList<Object>();
		doItem = new IForAction<KeyValue>()
		{
			@Override
			public void doItem(KeyValue item, int index)
			{
				defaults.add(cloneOrDefault(item.getValue()));
			}

			private Object cloneOrDefault(Object value)
			{
				// Fehler: Cloneable enthielt die Methode clone nicht.
				return value;
			}
		};
		ListUtils.forEach(parameters, doItem);
	}

	protected void reset()
	{
		int i = 0;
		for (KeyValue v : parametersProperty.getValue())
		{
			v.setValue(defaults.get(i++));
		}
	}
	
	protected void setParameter(int parameterIndex, Object value)
	{
		parametersProperty.getValue().get(parameterIndex).setValue(value);
	}
	
	public String getUrlFinal()
	{
		// Bugfix: 2014-09-20, Muss erst in String casten!
		StringBuilder sb = new StringBuilder((String)urlProperty.getConvertedValue());
		
		int i = 0;
		for(KeyValue v : parametersProperty.getValue()) {
			if(i++ > 0) {
				sb.append("&");
			}
			sb.append(v.getKey()).append("=").append(v.getValue());
		}
		return sb.toString();
	}
}
