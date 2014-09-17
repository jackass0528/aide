package de.ecreators.apps.fairtrade.basic;

import de.ecreators.apps.fairtrade.basic.model.*;
import de.ecreators.apps.fairtrade.basic.prototypes.*;

public abstract class NotifyModel implements INotifyPropertyCaller
{
	public static final String PROPERTY_CHANGED_NAME = "PropertyChanged";
	private final CallerEvent<PropertyChangedEventHandler, PropertyChangedArgs> callerPropertyChanged;

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
}
