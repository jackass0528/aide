package de.ecreators.apps.fairtrade.model;
import de.ecreators.apps.fairtrade.basic.*;

public interface INotifyPropertyCaller
{
	CallerEvent<PropertyChangedEventHandler, PropertyChangedArgs> getPropertyChangedCall();
}
