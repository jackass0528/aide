package de.ecreators.apps.fairtrade.dao;

import android.util.*;
import de.ecreators.apps.fairtrade.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import org.apache.http.*;
import org.apache.http.client.*;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.*;
import org.apache.http.protocol.*;
import org.json.*;

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
				Connection conn = DriverManager.getConnection(url + dbName, userName, "Arnold47004700");
				return conn;
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}

			return null;
		}
	}
	
	public class Json {
		public JSONObject getJSONfromURL(String url) {
			InputStream is = null;
			String result = "";
			JSONObject jArray = null;

			// Download JSON data from URL
			try {
				HttpClient httpclient = new DefaultHttpClient();
				HttpPost httppost = new HttpPost(url);
				HttpResponse response = httpclient.execute(httppost);
				HttpEntity entity = response.getEntity();
				is = entity.getContent();

			} catch (Exception e) {
				Log.e("log_tag", "Error in http connection " + e.toString());
			}

			// Convert response to string
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(
															   is, "utf-8"), 8);
				StringBuilder sb = new StringBuilder();
				String line = null;
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}
				is.close();
				result = sb.toString();
			} catch (Exception e) {
				Log.e("log_tag", "Error converting result " + e.toString());
			}

			try {

				jArray = new JSONObject(result);
			} catch (JSONException e) {
				Log.e("log_tag", "Error parsing data " + e.toString());
			}

			return jArray;
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
			Log.i("", String.format("Die Sqlverbindung konnte %shergestellt werden.", (sender.getDataContext() ? "" : "nicht ")));
		}
	};
}
