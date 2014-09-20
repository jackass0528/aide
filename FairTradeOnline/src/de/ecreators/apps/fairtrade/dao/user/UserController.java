package de.ecreators.apps.fairtrade.dao.user;

import de.ecreators.apps.fairtrade.basic.*;
import de.ecreators.apps.fairtrade.basic.model.*;
import java.util.*;
import de.ecreators.apps.fairtrade.dao.model.*;
import android.database.sqlite.*;

public class UserController
{
	private static final Lazy<UserController> lazyInstance = new Lazy<UserController>(new LazyBuilder<UserController>() {

			@Override
			public UserController create(List<Object> e)
			{
				return new UserController();
			}
		});

	public static UserController get()
	{
		return lazyInstance.get();
	}

	private final Property<UserDatabaseStrategy> strategyProperty;
	
	private UserController()
	{
		// Vor diesem Aufruf muss zu allererst FairTradeDb.init() aufgerufen werden.
		// Siehe dazu MainActivity.java
		SQLiteDatabase db = FairTradeDb.get().getWritableDatabase();
		
		// Init Property
		strategyProperty = createPropertyStrategy(db);
		strategyProperty.setReadOnly();
	}

	private Property<UserDatabaseStrategy> createPropertyStrategy(SQLiteDatabase db)
	{
		return new Property<UserDatabaseStrategy>("Strategy", 
												  this, 
												  new UserDatabaseStrategy(db));
	}

	public UserModel getUserById(UUID userId)
	{
		try
		{
			return getStrategy().filterSingle(userIdMapper(userId));
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
	}

	private static KeyValue userIdMapper(UUID userId)
	{
		return new KeyValue(UserTableDAO.Columns.UserId, userId.toString());
	}

	public void save(UserModel... users)
	{
		// Übertragt die Werte in die Datenbank per DAO
		getStrategy().save(Arrays.asList(users));
	}

	private UserDatabaseStrategy getStrategy()
	{
		return strategyProperty.getValue();
	}
}
