package de.ecreators.apps.fairtrade.basic;

import de.ecreators.apps.fairtrade.basic.model.*;

public class Lazy<T>
{
	private final LazyBuilder<T> builder;

	private T value;
	
	public Lazy(LazyBuilder<T> builder) {
		this.builder = builder;
	}
	
	public T get() {
		if(value == null) {
			value = builder.create(builder.getParams());
		}
		return value;
	}
}
