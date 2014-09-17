package de.ecreators.apps.fairtrade.basic.model;

import de.ecreators.apps.fairtrade.basic.*;
import de.ecreators.apps.fairtrade.basic.prototypes.*;

public interface INotifyPropertyCaller
{
	CallerEvent<PropertyChangedEventHandler, PropertyChangedArgs> getPropertyChangedCall();
}
