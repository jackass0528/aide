package de.ecreators.apps.fairtrade.basic.model;

public abstract interface ITaskAction<T>
{
	public abstract void run(ITask<T> sender);
}
