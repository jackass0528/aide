package de.ecreators.apps.fairtrade.dao.model;

import java.util.*;
import de.ecreators.apps.fairtrade.basic.*;

public interface IItemStrategy<T extends SaveObjectBase>
{
	T filterSingle(KeyValue... conditions);
	List<T> filterMany(Iterable<KeyValue>... conditions);
	List<T> select();
	void save(T itemToSave);
	void save(List<T> itemsToSave);
	int count();
}
