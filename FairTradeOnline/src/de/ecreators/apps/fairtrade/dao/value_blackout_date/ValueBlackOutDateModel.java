package de.ecreators.apps.fairtrade.dao.value_blackout_date;

import de.ecreators.apps.fairtrade.basic.*;
import java.util.*;

public class ValueBlackOutDateModel extends SaveObjectBase
{
	private ShortDate start;
	private int durationInMonths;
	private ShortDate end;
	private String comment;
	private UUID id;
	private UUID valueIdFK;
	private Double alternateValue;
	private Boolean alternateEnabled;

	// Konstruktor
	public ValueBlackOutDateModel()
	{
		id = UUID.randomUUID();
	}

	public void setAlternateEnabled(Boolean alternateEnabled)
	{
		this.alternateEnabled = alternateEnabled;
	}

	public Boolean getAlternateEnabled()
	{
		return alternateEnabled;
	}

	public void setAlternateValue(Double alternateValue)
	{
		this.alternateValue = alternateValue;
	}

	public Double getAlternateValue()
	{
		return alternateValue;
	}

	public void setValueIdFK(UUID valueIdFK)
	{
		this.valueIdFK = valueIdFK;
	}

	public UUID getValueIdFK()
	{
		return valueIdFK;
	}

	public void setId(UUID id)
	{
		this.id = id;
	}

	public UUID getId()
	{
		return id;
	}

	public void setComment(String comment)
	{
		this.comment = comment;
	}

	public String getComment()
	{
		return comment;
	}

	public void setStart(ShortDate dt)
	{
		start = dt;
	}

	public ShortDate getStart()
	{
		return start;
	}

	public int getDurationInMonths()
	{
		return durationInMonths;
	}

	// Legt fest, wann die Änderung endet.
	public void setEnd(ShortDate end)
	{
		this.end = end;
		durationInMonths = getDurationInMonths(start, end);
	}

	// Liefert das Ender der Änderung.
	public ShortDate getEnd()
	{
		return end;
	}

	// Legt das Ende fest, anhand einer Laufzeit.
	public ValueBlackOutDateModel setDurationInMonths(int months)
	{
		durationInMonths = months;
		end = getShortDateByRuntime(start.getDate(), months);
		return this;
	}

	@Override
	protected Iterable<ColumnValueMapper> getPkValues()
	{
		return new ArrayList<ColumnValueMapper>() {{
				add(new ColumnValueMapper(ValuesBlackOutTableDAO.Columns.BlackoutDateId, getId().toString()));
			}};
	}

	@Override
	public boolean readColumnValue(String column, ColumnResult e)
	{
		// TODO: Implement this method
		return false;
	}

	private static int getDurationInMonths(ShortDate start, ShortDate end)
	{
		assert start != null && end != null : "beide Werte dürfen nicht null sein!";

		// 07/2014 - 01/2015
		// 15-14 = 1*12 + 1-7 = -6 = 12-6 = 6
		return (end.getDate().getYear() - start.getDate().getYear()) * 12 +
			(end.getDate().getMonth() - start.getDate().getMonth());
	}

	private static ShortDate getShortDateByRuntime(Date date, int months)
	{
		Date result = date;
		for (int i = 0; i < months; i++)
		{
			int month = (result.getMonth() + 1) % 12;
			int year = result.getYear();
			if (month == 0)
			{
				year ++;
				month = 1;
			}

			result = new Date(year, month, 1);
		}
		return new ShortDate(result.getMonth(), result.getYear());
	}
}
