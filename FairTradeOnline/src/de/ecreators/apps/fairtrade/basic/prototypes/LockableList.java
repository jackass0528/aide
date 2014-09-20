package de.ecreators.apps.fairtrade.basic.prototypes;

import java.util.*;
import java.security.*;

public class LockableList<T> implements Collection<T>
{
	private final ArrayList<T> items = new ArrayList<T>();
	private boolean locked;
	
	public T get(int index) {
		return items.get(index);
	}

	public void setLocked(boolean locked)
	{
		this.locked = locked;
	}

	public boolean isLocked()
	{
		return locked;
	}

	@Override
	public boolean add(T item)
	{
		checkAccess();
		return items.add(item);
	}
	
	@Override
	public boolean addAll(Collection<? extends T> itemsToAdd)
	{
		checkAccess();
		return items.addAll(itemsToAdd);
	}

	@Override
	public void clear()
	{
		checkAccess();
		items.clear();
	}

	private void checkAccess()
	{
		if (!locked)
		{
			throw new AccessControlException("Der Zugriff ist nicht erlaubt.");
		}
	}

	@Override
	public boolean contains(Object item)
	{
		return items.contains(item);
	}

	@Override
	public boolean containsAll(Collection<?> testItems)
	{
		return items.containsAll(testItems);
	}

	@Override
	public boolean isEmpty()
	{
		return items.isEmpty();
	}

	@Override
	public Iterator<T> iterator()
	{
		return items.iterator();
	}

	@Override
	public boolean remove(Object item)
	{
		checkAccess();
		return items.remove(item);
	}

	@Override
	public boolean removeAll(Collection<?> items)
	{
		checkAccess();
		return this.items.removeAll(items);
	}

	@Override
	public boolean retainAll(Collection<?> items)
	{
		checkAccess();
		return this.items.retainAll(items);
	}

	@Override
	public int size()
	{
		return items.size();
	}

	@Override
	public Object[] toArray()
	{
		return items.toArray();
	}

	@Override
	public <T extends Object> T[] toArray(T[] items)
	{
		return this.items.toArray(items);
	}
}
