package de.ecreators.apps.fairtrade.dao.model;

import java.util.*;
import de.ecreators.apps.fairtrade.basic.*;

public interface IItemStrategy<T extends SaveObjectBase>
{
	T filterSingle(ColumnValueMapper... conditions);
	List<T> filterMany(List<ColumnValueMapper>... conditions);
	List<T> select();
	void save(T itemToSave);
	void save(List<T> itemsToSave);
	int count();
}
