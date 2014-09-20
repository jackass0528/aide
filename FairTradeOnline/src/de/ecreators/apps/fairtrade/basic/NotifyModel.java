package de.ecreators.apps.fairtrade.basic;

import de.ecreators.apps.fairtrade.basic.model.*;
import de.ecreators.apps.fairtrade.basic.prototypes.*;
import java.lang.reflect.*;
import java.util.*;

public abstract class NotifyModel implements INotifyPropertyCaller
{
	public static final String PROPERTY_CHANGED_NAME = "PropertyChanged";
	private final CallerEvent<PropertyChangedEventHandler, PropertyChangedArgs> callerPropertyChanged;

	private Map<String, Property> properties;

	protected NotifyModel()
	{
		callerPropertyChanged = new CallerEvent<PropertyChangedEventHandler, PropertyChangedArgs>(PROPERTY_CHANGED_NAME, this);
		callerPropertyChanged.addHandler(new PropertyChangedEventHandler() 
			{
				{
					// kann nicht gelöscht werden
					setSealed(true);
				}
				
				@Override
				public void onEventHandled(Object sender, PropertyChangedArgs e)
				{
					onPropertyChanged(e);
				}
			});
	}

	@Override
	public CallerEvent<PropertyChangedEventHandler, PropertyChangedArgs> getPropertyChangedCall()
	{
		return callerPropertyChanged;
	}

	protected void onPropertyChanged(PropertyChangedArgs e)
	{
		// zum Überschreiben gedacht.
	}
	
	public Map<String, Property> getProperties()
	{
		if (this.properties == null)
		{
			Map<String, Property> result = new HashMap<String, Property>();
			List<Property> properties = findPropertiesDirect();
			for (Property p : properties)
			{
				result.put(p.getPropertyName(), p);
			}
			this.properties = result;
		}

		return this.properties;
	}

	private List<Property> findPropertiesDirect()
	{
		List<Property> result = new ArrayList<Property>();
		Field[] fields = getClass().getFields();
		for (Field f : fields)
		{
			try
			{
				Object value = f.get(this);
				if (value instanceof Property)
				{
					Property p = (Property)value;
					if (p != null)
					{
						result.add(p);
					}
				}
			}
			catch (IllegalArgumentException e)
			{}
			catch (IllegalAccessException e)
			{}
		}
		return result;
	}
}
