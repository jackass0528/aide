package de.ecreators.apps.fairtrade;

public abstract interface ITaskAction<T>
{
	public abstract void run(ITask<T> sender);
}
