package de.ecreators.apps.fairtrade.basic.model;

public interface ITask<T>
{
	public boolean isRunning();
	public boolean begin(T dataContext)
	public boolean isAborted();
	public T getDataContext();
	public ITask<T> setDataContext(T dataContext);
	
	public ITask<T> setOnEndListener(ITaskCallback<T> callback)
	public ITask<T> abort();
}
