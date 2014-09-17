package de.ecreators.apps.fairtrade.model;

public abstract class EventHandler<T extends EventArgs>
{
	private boolean sealed;

	public void setSealed(boolean sealed)
	{
		this.sealed = sealed;
	}

	public boolean isSealed()
	{
		return sealed;
	}
	
	public abstract void onEventHandled(Object sender, T e);
}
