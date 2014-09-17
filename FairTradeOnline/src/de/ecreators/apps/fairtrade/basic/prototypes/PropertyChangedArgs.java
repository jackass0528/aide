package de.ecreators.apps.fairtrade.basic.prototypes;

import de.ecreators.apps.fairtrade.basic.*;

public class PropertyChangedArgs extends EventArgs
{
	private final Property property;
	
	public PropertyChangedArgs(Property p) {
		this.property = p;
	}

	public Property getProperty()
	{
		return property;
	}
}
