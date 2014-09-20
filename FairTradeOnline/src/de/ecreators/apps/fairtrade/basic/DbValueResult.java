package de.ecreators.apps.fairtrade.basic;

import java.util.*;

public class DbValueResult
{
	private Object value;
	private Object ordinalValue;
	
	public DbValueResult() {
		
	}

	public Object getValue()
	{
		return value;
	}
	
	public Object getOrdinalValue() {
		return ordinalValue;
	}

	public void setValue(Object value)
	{
		this.value = value;
		this.ordinalValue = value;
		
		if(!(value instanceof Number) && this.value != null) {
			this.value = String.format("'%s'", value.toString());
		}
		
		if(this.value == null) {
			this.value = "null";
		}
	}
}
