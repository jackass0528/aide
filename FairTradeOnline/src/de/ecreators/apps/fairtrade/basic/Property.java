package de.ecreators.apps.fairtrade.basic;

import de.ecreators.apps.fairtrade.basic.model.*;
import de.ecreators.apps.fairtrade.basic.prototypes.*;
import java.security.*;

public class Property<T extends Object> extends NotifyModel
{
	private T value;
	private final Object owner;
	private IValueConverter<T, Object> converter;
	private final String propertyName;

	private boolean isReadOnly;

	public Property(String name, Object owner, T initValue)
	{
		this.owner = owner;
		value = initValue;
		this.propertyName = name;
	}

	public void setReadOnly()
	{
		this.isReadOnly = true;
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
		if (isReadOnly)
		{
			try
			{
				throw new PrivilegedActionException(new Exception(String.format("Diese Property ist ReadOnly (set %s.%s)", 
																				owner.getClass().getName(),
																				getPropertyName())));
			}
			catch (PrivilegedActionException e)
			{
				e.printStackTrace();
				return;
			}
		}

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

	public T getValue()
	{
		return value;
	}

	public Object getConvertedValue()
	{
		return converter == null ? value : converter.convert(value);
	}
}
