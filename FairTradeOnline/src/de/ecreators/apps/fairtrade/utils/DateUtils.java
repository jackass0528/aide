package de.ecreators.apps.fairtrade.utils;

import java.util.*;

public class DateUtils
{
	private DateUtils()
	{}

	public static Date setStartOfMonth(Date dt)
	{
		Calendar c = asCalendar(dt);
		c.set(Calendar.DAY_OF_MONTH, 1);
		setStartOfDay(c);
		return null;
	}

	private static void setStartOfDay(Calendar c)
	{
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
	}
	
	public static Date setEndOfMonth(Date dt) {
		Calendar c = asCalendar(dt);
		setEndOfDay(c);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		return c.getTime();
	}

	private static Calendar asCalendar(Date dt)
	{
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		return c;
	}

	private static void setEndOfDay(Calendar c)
	{
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
	}
	
	public static boolean isWithinDates(Date dt, Date from, Date to)
	{
		assert dt != null && from != null && to != null && (to.after(from) || to.equals(from)) :
		"to must be equals or after from date!";

		return
			from.getMonth() <= dt.getMonth() && from.getYear() <= dt.getYear() &&
			to.getMonth() >= dt.getMonth() &&	to.getYear() >= dt.getYear();
	}
}
