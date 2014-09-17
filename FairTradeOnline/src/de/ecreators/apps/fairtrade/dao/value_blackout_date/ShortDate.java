package de.ecreators.apps.fairtrade.dao.value_blackout_date;

import de.ecreators.apps.fairtrade.utils.*;
import java.util.*;

public class ShortDate
{
	private final Date date;

	public ShortDate(int month, int year)
	{
		this.date = new Date(year, month, 1);
	}

	public boolean isMonthBefore(Date dt)
	{
		return DateUtils.isWithinDates(dt, 
		DateUtils.setStartOfMonth(dt), 
		DateUtils.setEndOfMonth(dt));
	}

	public Date getDate()
	{
		return date;
	}

	@Override
	public int hashCode()
	{
		return date.getMonth() ^ date.getYear();
	}

	@Override
	public boolean equals(Object o)
	{
		if (o == null)
		{
			return false;
		}

		if (o instanceof Date)
		{
			Date another = (Date)o;
			return another.getMonth() == date.getMonth() && another.getYear() == date.getYear();
		}
		else if (o instanceof ShortDate)
		{
			ShortDate another = (ShortDate) o;
			return another.date.getMonth() == date.getMonth() && another.date.getYear() == date.getYear();
		}

		return super.equals(o);
	}
}
