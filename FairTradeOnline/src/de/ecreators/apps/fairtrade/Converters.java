package de.ecreators.apps.fairtrade;

import de.ecreators.apps.fairtrade.basic.*;
import de.ecreators.apps.fairtrade.basic.model.*;

public class Converters
{
	private Converters() {
		
	}
	
	public static final IConverter<ColumnValueMapper, Object> ColumnStringConverter = new IConverter<ColumnValueMapper, Object>() {

		@Override
		public Object convert(ColumnValueMapper item)
		{
			return item.toString();
		}
	};
}
