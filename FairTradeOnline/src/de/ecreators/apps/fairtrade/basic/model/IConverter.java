package de.ecreators.apps.fairtrade.basic.model;

public abstract interface IConverter<T,R extends Object>
{
	R convert(T item);
}
