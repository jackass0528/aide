package de.ecreators.apps.fairtrade.utils;

import java.util.*;
import de.ecreators.apps.fairtrade.dao.user.*;

public class ListUtils
{
	public static <T> void forEach(T[] items, IForAction<T> doItem) {
		forEach(asList(items), doItem);
	}
	
	public static <T> void forEach(Iterable<T> items, IForAction<T> doItem)
	{
		if (doItem != null)
		{
			int i = 0;
			for (T item : items)
			{
				doItem.doItem(item, i);
				i++;
			}
		}
	}

	public static Iterable<Object> casting(Collection<String> src)
	{
		return new ArrayList<Object>(src);
	}

	public static <T> List<T> toList(T... list)
	{
		return asList(list);
	}

	public static <T> List<T> asList(T enumerator)
	{
		ArrayList<T> result = new ArrayList<T>();
		result.add(enumerator);
		return result;
	}

	public static <T> List<T> asList(T[] enumerator)
	{
		ArrayList<T> result = new ArrayList<T>();
		for (T i : enumerator)
		{
			result.add(i);
		}
		return result;
	}

	public static <T> T firstOrNull(Iterable<T> collection)
	{
		T result = null;
		for (T i: collection)
		{
			result = i;
			break;
		}
		return result;
	}
}
