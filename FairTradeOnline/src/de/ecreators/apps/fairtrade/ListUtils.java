package de.ecreators.apps.fairtrade;

import java.util.*;

public class ListUtils
{
	public static Iterable<Object> casting(Collection<String> src)	{
		return new ArrayList<Object>(src);
	}
	
	public static <T> List<T> toList(T... list) {
		return (List<T>)ListUtils.<T>asList(list);
	}
	
	public static <T> Collection<T> asList(T[] enumerator) {
		ArrayList<T> result = new ArrayList<T>();
		for(T i : enumerator) {
			result.add(i);
		}
		return result;
	}
}
