package de.ecreators.apps.fairtrade.http.model;

import java.util.*;
import org.json.*;
import java.text.*;
import android.util.*;

public class JResponse
{
	private static final String METHODE_STRING = "Method";
	private static final String UDP_BOOL = "UDP";
	private static final String SESSION_STRING = "Session";
	private static final String SESSIONDATE_STRING = "SessionDate";
	private static final String LICENCE_STRING = "License";
	private static final String RECEIVED_BOOL = "Received";
	private static final String ACCESS_BOOL = "Access";
	private static final String DATA_ARRAY = "Data";

	private String method;
	private Boolean isUdp;
	private String session;
	private Date sessionDate;
	private String license;
	private Boolean received;
	private Boolean access;
	private Object[] data;

	/*
	 Array('Method'=>$request->getMethodName(),
	 'UDP'=>$request->getIsUDP(),
	 'Session'=>$request->getSession(),
	 'SessionDate'=>$request->getSessionDate(),
	 'License'=>$request->getLicense(),
	 'Received'=>true,
	 'Access'=>$access,
	 'Data'=> $data);
	 */

	public JResponse(JSONObject response)
	{
		read(response);
	}

	private void read(JSONObject response)
	{
		if (response != null)
		{
			// Mögliche Fehler sind
			// Nicht-in-Array-Fehler oder Castfehler
			// In beiden Fällen hat sich der Service in php geändert.

			try
			{
				method = (String)response.get(METHODE_STRING);
			}
			catch (JSONException e)
			{
				e.printStackTrace();
			}

			try
			{
				isUdp = (Boolean)response.get(UDP_BOOL);
			}
			catch (JSONException e)
			{
				e.printStackTrace();
			}

			try
			{
				session = (String)response.get(SESSION_STRING);
			}
			catch (JSONException e)
			{
				e.printStackTrace();
			}

			try
			{
				sessionDate = DateFormat.getInstance().parse((String)response.get(SESSIONDATE_STRING));
			}
			catch (JSONException e)
			{
				e.printStackTrace();
			}
			catch (ParseException e)
			{
				e.printStackTrace();
			}

			try
			{
				license = (String)response.get(LICENCE_STRING);
			}
			catch (JSONException e)
			{
				e.printStackTrace();
			}

			try
			{
				received = (Boolean)response.get(RECEIVED_BOOL);
			}
			catch (JSONException e)
			{
				e.printStackTrace();
			}

			try
			{
				access = (Boolean)response.get(ACCESS_BOOL);
			}
			catch (JSONException e)
			{
				e.printStackTrace();
			}

			try
			{
				JSONArray array = response.getJSONArray(DATA_ARRAY);
				data = array == null ? null : toArray(array);
			}
			catch (JSONException e)
			{
				e.printStackTrace();
			}
		}
	}

	private Object[] toArray(JSONArray array)
	{
		try
		{
			Object[] result = new Object[array.length()];
			for (int i = 0; i < result.length; i++)
			{
				result[i] = array.get(i);
			}
			return result;
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}

	public Object[] getData()
	{
		return data;
	}

	public Boolean getAccess()
	{
		return access;
	}

	public Boolean getReceived()
	{
		return received;
	}

	public String getLicense()
	{
		return license;
	}

	public Date getSessionDate()
	{
		return sessionDate;
	}

	public String getSession()
	{
		return session;
	}

	public Boolean getIsUdp()
	{
		return isUdp;
	}

	public String getMethod()
	{
		return method;
	}
}
