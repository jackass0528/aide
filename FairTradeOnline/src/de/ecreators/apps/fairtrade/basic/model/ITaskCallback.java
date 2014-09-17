package de.ecreators.apps.fairtrade.basic.model;

public abstract interface ITaskCallback<T>
{
	public abstract void onEnd(ITask<T> sender);
}
