package de.ecreators.apps.fairtrade.basic.prototypes;

import java.util.*;

public class Dic<K,V> extends HashMap<K, V>
{
	public boolean tryGet(K key, V[] value) {
		value[0] = get(key);
		return containsKey(key);
	}
}
