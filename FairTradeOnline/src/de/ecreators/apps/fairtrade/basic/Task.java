package de.ecreators.apps.fairtrade.basic;

import de.ecreators.apps.fairtrade.basic.model.*;

public class Task<T> extends Thread implements ITask<T>
{
	private ITaskCallback<T> onEnd;
	private boolean isRunning;
	private boolean abort;
	private T dataContext;
	private final ITaskAction<T> action;

	private Task(ITaskAction<T> action)
	{
		this.action = action;
	}

	public static <T> ITask<T> newTask(ITaskAction<T> action)
	{
		return new Task<T>(action);
	}

	public ITask<T> setOnEndListener(ITaskCallback<T> callback)
	{
		onEnd = callback;
		return this;
	}

	@Override
	public void run()
	{
		this.action.run(this);
		onCompletion();
	}
	
	public T getDataContext()
	{
		return dataContext;
	}
	
	public ITask<T> setDataContext(T dataContext)
	{
		this.dataContext = dataContext;
		return this;
	}

	private void onCompletion()
	{
		ITaskCallback<T> handler = onEnd;
		if (handler != null)
		{
			handler.onEnd(this);
		}
	}

	public boolean begin(T dataContext)
	{
		boolean result = !isRunning;
		if (!isRunning)
		{
			isRunning = true;
			abort = false;
			this.dataContext = dataContext;
			start();
		}
		
		return result;
	}
	
	public boolean isRunning()
	{
		return isRunning;
	}
	
	public boolean isAborted()
	{
		return abort;
	}

	public ITask<T> abort()
	{
		if (isRunning)
		{
			abort = true;
		}
		
		return this;
	}
}
