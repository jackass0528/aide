package de.ecreators.apps.fairtrade.data.model;
import java.util.*;

public class ColumnResult
{
	private Object value;
	
	public ColumnResult() {
		
	}

	public Object getValue()
	{
		return value;
	}

	public void setValue(Object value)
	{
		this.value = value;
		
		if(!(value instanceof Number) && this.value != null) {
			this.value = String.format("'%s'", value);
		}
		
		if(this.value == null) {
			this.value = "null";
		}
	}
}
