package de.ecreators.apps.fairtrade;

import de.ecreators.apps.fairtrade.model.*;

public class StringUtils
{
	private StringUtils() {}
	
	public static String join (String delimitter, Iterable<Object> data) {
		StringBuilder sb = new StringBuilder();
		for(Object obj : data) {
			if(sb.length() > 0) {
				sb.append(delimitter);
			}
			sb.append(obj.toString());
		}
		return sb.toString();
	}
	
	public static <T> String join (String delimitter, Iterable<T> data, IConverter<T,Object> converter) {
		StringBuilder sb = new StringBuilder();
		for(T obj : data) {
			if(sb.length() > 0) {
				sb.append(delimitter);
			}
			sb.append("" + (converter == null ? obj : converter.convert(obj)));
		}
		return sb.toString();
	}
}
