package de.ecreators.apps.fairtrade.model;

public abstract interface IConverter<T,R extends Object>
{
	R convert(T item);
}
