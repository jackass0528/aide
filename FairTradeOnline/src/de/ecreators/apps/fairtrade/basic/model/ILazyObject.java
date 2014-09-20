package de.ecreators.apps.fairtrade.basic.model;

public interface ILazyObject<T>
{
	LazyBuilder<T> getBuilder();
}
