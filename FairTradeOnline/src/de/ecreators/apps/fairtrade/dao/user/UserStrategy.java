package de.ecreators.apps.fairtrade.dao.user;

/**
 Steuert das Speichern und Laden von Benutzern.
 */
import de.ecreators.apps.fairtrade.basic.*;
import de.ecreators.apps.fairtrade.dao.model.*;
import java.util.*;
import de.ecreators.apps.fairtrade.basic.model.*;

public class UserStrategy implements IItemStrategy<UserModel>
{
	private static final Lazy<UserTableDAO> db = new Lazy<UserTableDAO>(new LazyBuilder<UserTableDAO>()
		{
			@Override
			public UserTableDAO create(List<Object> e)
			{
				return new UserTableDAO();
			}
		});
	
	@Override
	public UserModel filterSingle(ColumnValueMapper... conditions)
	{
		return db.get().getAll(mysqliteDb);
	}

	@Override
	public List<UserModel> filterMany(List<ColumnValueMapper>... conditions)
	{
		// TODO: Implement this method
		return null;
	}

	@Override
	public List<UserModel> select()
	{
		// TODO: Implement this method
		return null;
	}

	@Override
	public void save(UserModel itemToSave)
	{
		// TODO: Implement this method
	}

	@Override
	public void save(List<UserModel> itemsToSave)
	{
		// TODO: Implement this method
	}

	@Override
	public int count()
	{
		// TODO: Implement this method
		return 0;
	}
}
