package de.ecreators.apps.fairtrade.dao.user;

import de.ecreators.apps.fairtrade.basic.*;
import de.ecreators.apps.fairtrade.basic.model.*;
import java.util.*;

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

	private final Property<UserStrategy> strategy = new Property<UserStrategy>("Strategy", this, new UserStrategy());

	private UserController()
	{	}

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

	private static ColumnValueMapper userIdMapper(UUID userId)
	{
		return new ColumnValueMapper(UserTableDAO.Columns.UserId, userId.toString());
	}

	public void save(UserModel... users)
	{
		boolean success = true;
		try
		{
			getStrategy().save(Arrays.asList(users));
		}
		catch (Exception ex)
		{
			success = false;
			ex.printStackTrace();
		}

		for (SaveObjectBase save : users)
		{
			if (save == null) continue;
			save.setIsSaved(success);
		}
	}

	private UserStrategy getStrategy()
	{
		return (UserStrategy) strategy.getValue();
	}
}
