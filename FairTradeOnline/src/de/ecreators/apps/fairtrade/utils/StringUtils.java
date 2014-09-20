package de.ecreators.apps.fairtrade.utils;

import de.ecreators.apps.fairtrade.basic.model.*;
import android.view.Surface.*;

public class StringUtils
{
	private StringUtils()
	{}

	public static String alignRight(String textToAlign, int length, char fill)
	{
		if (textToAlign.length() > length)
		{
			throw new OutOfResourcesException(String.format("Die Länge von %d ist kürzer als die Textlänge von '%s'.", length, textToAlign.length()));
		}

		StringBuilder sb = new StringBuilder(textToAlign);

		while (sb.length() < length)
		{
			sb.insert(0, fill);
		}
		return sb.toString();
	}
	
	public static String join(String delimitter, Iterable<Object> data)
	{
		StringBuilder sb = new StringBuilder();
		for (Object obj : data)
		{
			if (sb.length() > 0)
			{
				sb.append(delimitter);
			}
			sb.append(obj.toString());
		}
		return sb.toString();
	}

	public static <T> String join(String delimitter, Iterable<T> data, IConverter<T,Object> converter)
	{
		StringBuilder sb = new StringBuilder();
		for (T obj : data)
		{
			if (sb.length() > 0)
			{
				sb.append(delimitter);
			}
			sb.append("" + (converter == null ? obj : converter.convert(obj)));
		}
		return sb.toString();
	}
}
