package de.ecreators.apps.fairtrade.utils;

public abstract interface IForAction<T>
{
	void doItem(T item, int index);
}
