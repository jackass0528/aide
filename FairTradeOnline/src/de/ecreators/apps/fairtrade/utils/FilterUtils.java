package de.ecreators.apps.fairtrade.utils;

import de.ecreators.apps.fairtrade.basic.*;
import de.ecreators.apps.fairtrade.dao.model.*;
import java.util.*;
import de.ecreators.apps.fairtrade.dao.user.*;

public class FilterUtils
{
	private FilterUtils()
	{}

	// reduziert die Liste auf eindeutige Werte.
	public static <T> void distinct(List<T> result)
	{
		for (int i = 0; i < result.size(); i++)
		{
			// Suche den Index für den Eintrag an Index i
			int j = result.indexOf(result.get(i));

			// Wenn dieser Eintrag weiter vorn in der Liste nochmal existiert,
			// entferne diesen Eintrag an i und korrigiere den Index i um 1 zurück.
			if (betweenExclusive(j, -1, i))
			{
				result.remove(i--);
			}
		}
	}

	public static boolean betweenExclusive(int value, int min, int max)
	{
		return value > min && value < max;
	}
	
	public static boolean betweenInclusive(int value, int min, int max)
	{
		return value >= min && value <= max;
	}

	public static <T extends SaveObjectBase> List<T> filter(T[] collection, KeyValue[] conditions)
	{
		return filter(ListUtils.asList(collection), ListUtils.asList(conditions));
	}

	public static <T extends SaveObjectBase> List<T> filter(Iterable<T> collection, KeyValue[] conditions)
	{
		return filter(collection, ListUtils.asList(conditions));
	}

	public static <T extends SaveObjectBase> List<T> filter(Iterable<T> collection, Iterable<KeyValue> conditions)
	{
		// mach eine neue Liste mit dem Typen aus T
		List<T> result = new ArrayList<T>();

		// Gehe alle Einträge durch. Jeder Eintrag ist nach und nach in entry. 
		for (T entry : collection)
		{
			// Wird Wahr aus isMatch zurückgegeben,
			// dann füge den Entrag dem Ergebnis hinzu.
			if (isMatch(entry, conditions))
			{
				result.add(entry);
			}
		}

		return result;
	}

	public static <T extends SaveObjectBase> boolean isMatch(T item, KeyValue[] conditions)
	{
		return isMatch(item, ListUtils.asList(conditions));
	}

	// Analysiere einen Eintrag mit mindestens einer Bedingung.
	public static <T extends SaveObjectBase> boolean isMatch(T item, Iterable<KeyValue> conditions)
	{
		boolean result = true;

		// Geh alle Bedingungen durch
		for (KeyValue condition : conditions)
		{
			// Info:
			// Bedingung hat einen Wertnamen und
			// einen zu erwartenden/gesuchten Wert.

			// Suche alle Werte
			// hole daraus den Wert mit dem Wertnamen (value kann auch nichts sein)
			Object value = item.getProperties().get(condition.getKey());

			// Guck nochmal, ob der Wertname drin war
			// und wenn ja, vergleiche den erhaltenen Wert mit dem Wert in der Bedingung
			result = item.getProperties().containsKey(condition.getKey()) && value == condition.getOrdinalValue() ;

			// ist die Bedingung nicht erfüllt oder
			// ist der Wertname nicht enthalten, brich ab und gib Falsch zurück
			if (!result)
			{
				result = false;
				break;
			}
		} 

		return result;
	}
}
