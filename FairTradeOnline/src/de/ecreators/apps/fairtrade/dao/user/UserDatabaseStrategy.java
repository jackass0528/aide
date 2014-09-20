package de.ecreators.apps.fairtrade.dao.user;

/**
 Steuert das Speichern und Laden von Benutzern.
 */
import android.database.sqlite.*;
import de.ecreators.apps.fairtrade.basic.*;
import de.ecreators.apps.fairtrade.basic.model.*;
import de.ecreators.apps.fairtrade.dao.model.*;
import java.util.*;
import de.ecreators.apps.fairtrade.utils.*;

public class UserDatabaseStrategy implements IItemStrategy<UserModel>
{
	/*
	 * Der UserController holt such diese Strategie und verwendet diese.
	 * Nicht umgekehrt!
	 */
	 
	// 
	private static final Lazy<UserTableDAO> lazyDAOManager = new Lazy<UserTableDAO>(new UserDAOBuilder());

	private static class UserDAOBuilder extends LazyBuilder<UserTableDAO>
	{
		// Initialisiert den DAO Manager
		@Override
		public UserTableDAO create(List<Object> e)
		{
			return new UserTableDAO();
		}
	}

	private SQLiteDatabase mysqliteDb;

	public UserDatabaseStrategy(SQLiteDatabase db)
	{
		mysqliteDb = db;
	}

	@Override
	public UserModel filterSingle(KeyValue... conditions)
	{
		return ListUtils.firstOrNull(FilterUtils.filter(select(), conditions));
	}

	@Override
	public List<UserModel> filterMany(Iterable<KeyValue>... conditions)
	{
		Collection<UserModel> all = select();
		List<UserModel> result = new ArrayList<UserModel>();

		for (Iterable<KeyValue> conditionAnd : conditions)
		{
			result.addAll(FilterUtils.filter(all, conditionAnd));
		}

		FilterUtils.distinct(result);

		return result;
	}

	@Override
	public List<UserModel> select()
	{
		return (List<UserModel>)lazyDAOManager.get().getAll(mysqliteDb);
	}

	@Override
	public void save(UserModel itemToSave)
	{
		lazyDAOManager.get().save(mysqliteDb, ListUtils.asList(itemToSave));
	}

	@Override
	public void save(List<UserModel> itemsToSave)
	{
		lazyDAOManager.get().save(mysqliteDb, itemsToSave);
	}

	@Override
	public int count()
	{
		return lazyDAOManager.get().countAll(mysqliteDb);
	}
}
