package de.ecreators.apps.fairtrade.basic;
import de.ecreators.apps.fairtrade.utils.*;
import android.util.*;

public class AppVersion
{
	// Auf Länge erweitern
	private static final int L = 3;

	private final int releaseVersion;
	private final int featureVersion;
	private final int bugfixVersion;
	private final int dbVersion;

	public AppVersion(int releaseVersion, int featureVersion, int bugfixVersion, int dbVersion)
	{
		this.releaseVersion = releaseVersion;
		this.featureVersion = featureVersion;
		this.bugfixVersion = bugfixVersion;
		this.dbVersion = dbVersion;
	}

	public int getInt()
	{
		// Splitter
		final char S = '9';

		// Füllzeichen
		final char Z = '0';

		String r = StringUtils.alignRight("" + releaseVersion, L, Z);
		String f = StringUtils.alignRight("" + featureVersion, L, Z);
		String b = StringUtils.alignRight("" + bugfixVersion, L, Z);
		String d = StringUtils.alignRight("" + dbVersion, L, Z);

		// beginnt mit 1
		int version = Integer.parseInt("1" + r + S + f + S + b + S + d);
		
		//10001900019000090000
		return version;
	}

	public static void printVersionBuilt(int number)
	{
		//10001900019000090000
		//0001900019000090000
		String nb = ("" + number).substring(1);
		
		//0001900019000090000
		//0001
		String rs = nb.substring(0, L);
		
		//0001900019000090000
		//00019000090000
		nb = nb.substring(L + 1);

		//00019000090000
		//0001
		String fs = nb.substring(0, L);
		
		//00019000090000
		//000090000
		nb = nb.substring(L + 1);
		
		//000090000
		//0000
		String bs = nb.substring(0, L);
		
		//000090000
		//0000
		nb = nb.substring(L + 1);
		
		//== Auswerten

		//0001 = 1
		int r = Integer.parseInt(rs);
		
		//0001 = 1
		int f = Integer.parseInt(fs);
		
		//0000 = 0
		int b = Integer.parseInt(bs);
		
		//0000 = 0
		int d = Integer.parseInt(nb);
		
		//== Bewerten

		AppVersion v = new AppVersion(r, f, b, d);
		Log.i(v.getClass().getName(), v.toString());
	}

	@Override
	public String toString()
	{
		return String.format("Version: %d.%d.%d.%d (%d)",
							 releaseVersion, 
							 featureVersion,
							 bugfixVersion, 
							 dbVersion,
							 getInt());
	}
}
