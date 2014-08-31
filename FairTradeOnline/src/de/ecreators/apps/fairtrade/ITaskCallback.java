package de.ecreators.apps.fairtrade;

public abstract interface ITaskCallback<T>
{
	public abstract void onEnd(ITask<T> sender);
}
