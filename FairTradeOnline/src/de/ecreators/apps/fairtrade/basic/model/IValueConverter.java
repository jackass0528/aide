package de.ecreators.apps.fairtrade.basic.model;

public abstract interface IValueConverter<T extends Object, R extends Object>
{
	R convert(T source);
	T convertBack(R result);
}
