package de.ecreators.apps.fairtrade.model;

public abstract interface IValueConverter<T extends Object, R extends Object>
{
	R convert(T source);
	T convertBack(R result);
}
