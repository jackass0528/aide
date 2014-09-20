package de.ecreators.apps.fairtrade;

import de.ecreators.apps.fairtrade.basic.*;
import de.ecreators.apps.fairtrade.basic.model.*;

public class Converters
{
	private Converters() {
		
	}
	
	public static final IConverter<KeyValue, Object> ColumnStringConverter = new IConverter<KeyValue, Object>() {

		@Override
		public Object convert(KeyValue item)
		{
			return item.toString();
		}
	};
}
