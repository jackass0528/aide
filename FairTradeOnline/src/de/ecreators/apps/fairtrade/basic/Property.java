package de.ecreators.apps.fairtrade.basic;

import de.ecreators.apps.fairtrade.basic.model.*;
import de.ecreators.apps.fairtrade.basic.prototypes.*;

public class Property<T extends Object> extends NotifyModel
{
	private T value;
	private final Object owner;
	private IValueConverter<T, Object> converter;
	private final String propertyName;
	
	public Property(String name, Object owner, T initValue) {
		this.owner = owner;
		value = initValue;
		this.propertyName = name;
	}

	public String getPropertyName()
	{
		return propertyName;
	}

	public void setConverter(IValueConverter<T, Object> converter)
	{
		this.converter = converter;
	}

	public IValueConverter<T, Object> getConverter()
	{
		return converter;
	}

	public void setValue(T value)
	{
		if (onChange(this.value, value))
		{
			this.value = value;
			super.getPropertyChangedCall().raise(owner, new PropertyChangedArgs(this));
		}
	}

	private boolean onChange(T oldValue, T newValue)
	{
		return oldValue != newValue;
	}

	public Object getValue()
	{
		return converter == null ? value : converter.convert(value);
	}
}
