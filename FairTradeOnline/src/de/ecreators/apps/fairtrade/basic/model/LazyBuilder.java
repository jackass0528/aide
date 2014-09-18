package de.ecreators.apps.fairtrade.basic.model;

import de.ecreators.apps.fairtrade.basic.*;
import java.util.*;

public abstract class LazyBuilder<T>
{
	private final Property<List<Object>> parametersProperty;
	
	{
		// Konstruktor:
		parametersProperty = new Property<List<Object>>("Parameters",this, new ArrayList<Object>());
	}
	
	protected void setParameters(Object... p) {
		List<Object> value = getParams();
		value.clear();
		value.addAll(Arrays.asList(p));
	}

	public List<Object> getParams()
	{
		List<Object> value = (ArrayList<Object>) parametersProperty.getValue();
		return value;
	}

	public abstract T create(List<Object> e);
}
