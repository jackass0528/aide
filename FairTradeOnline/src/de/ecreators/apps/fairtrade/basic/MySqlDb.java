package de.ecreators.apps.fairtrade.basic;

import android.util.*;
import de.ecreators.apps.fairtrade.basic.model.*;
import java.sql.*;

public class MySqlDb
{
	public MySqlDb()
	{
	}

	private static final class MySqlConnection 
	{
		private static final String url = "jdbc:mysql://mysql.lima-city.de:3306/"; 
		private static final String dbName = "db_299576_1";
		private static final String driver = "com.mysql.jdbc.Driver"; 
		private static final String userName = "USER299576"; 

		public Connection open() 
		{
			try
			{
				Class.forName(driver).newInstance(); 
				Connection conn = DriverManager.getConnection(url + dbName, userName, "Bjoern4700");
				return conn;
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}

			return null;
		}
	}

	public Connection open()
	{
		return new MySqlConnection().open();
	}

	public void test()
	{
		Task
			.newTask(testConnectionCmd)
			.setOnEndListener(testConnectionCmdCallback)
			.begin(true);
	}

	private static final ITaskAction<Boolean> testConnectionCmd = new ITaskAction<Boolean>()
	{
		@Override
		public void run(ITask<Boolean> sender)
		{
			boolean success = true;
			try
			{
				new MySqlConnection().open().close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
				success = false;
			} 

			sender.setDataContext(success);
		}
	};

	private ITaskCallback<Boolean> testConnectionCmdCallback = new ITaskCallback<Boolean>()
	{
		@Override
		public void onEnd(ITask<Boolean> sender)
		{
			Log.i(getClass().getName(), String.format("Die Sqlverbindung konnte %shergestellt werden.", (sender.getDataContext() ? "" : "nicht ")));
		}
	};
}
