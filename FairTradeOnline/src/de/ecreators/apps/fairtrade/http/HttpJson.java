package de.ecreators.apps.fairtrade.http;

import android.util.*;
import java.io.*;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.*;
import org.json.*;

public class HttpJson
{
	private HttpJson() {
		
	}
	
	public static JSONObject getJSONfromURL(String url)
	{
		// Download JSON data from URL
		InputStream stream = requestUrlInputStream(url);

		// Convert response to string
		String json = readJsonResponse(stream);

		JSONObject jArray = null;
		try
		{
			jArray = new JSONObject(json);
		}
		catch (Exception e)
		{
			Log.e(HttpJson.class.getName(), "Error parsing data: " + e.toString());
		}

		return jArray;
	}

	private static String readJsonResponse(InputStream stream)
	{
		try
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "utf-8"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;

			while ((line = reader.readLine()) != null)
			{
				sb.append(line + "\n");
			}
			stream.close();
			return sb.toString();
		}
		catch (Exception e)
		{
			Log.e(HttpJson.class.getName(), "Error converting result " + e.toString());
		}
		return "";
	}

	private static InputStream requestUrlInputStream(String url)
	{
		try
		{
			Log.i(HttpJson.class.getName(), "call to " + url);
			HttpPost httppost = new HttpPost(url);
			return new DefaultHttpClient().execute(httppost).getEntity().getContent();
		}
		catch (Exception e)
		{
			Log.e(HttpJson.class.getName(), "Error in http connection: " + e.toString());
		}
		return null;
	}
}
