package de.ecreators.apps.fairtrade.data;

import android.database.*;
import android.database.sqlite.*;
import de.ecreators.apps.fairtrade.*;
import de.ecreators.apps.fairtrade.data.value_blackout_date.*;
import de.ecreators.apps.fairtrade.model.*;
import java.util.*;

public final class ValuesBlackOutTableDAO extends Tables.DAO
{
	@Override
	public void save(SQLiteDatabase db, Collection<ValueBlackOutDateModel> items)
	{
		StringBuilder sb = new StringBuilder("begin;");
		for (ValueBlackOutDateModel item : items)
		{
			sb.append("\n").append(getInsertOrReplaceStatement(item, Columns.all, TableName));
		}
		sb.append("\ncommit;");
		db.execSQL(sb.toString());
	}

	@Override
	public Collection<ValueBlackOutDateModel> getAll(SQLiteDatabase db)
	{
		String sql = "select * from %s;";
		Cursor result = db.rawQuery(String.format(sql, TableName), null);

		return readRows(result, Columns.ValueBlackoutDateRowMapper);
	}

	private Collection<String> getColumnDefinitions()
	{
		return new ArrayList<String>() {{
				add(Columns.BlackoutDateId + " varchar(38) primary key");
				add(Columns.ValueIdFk + " varchar(38) not null");
				add(Columns.Comment + " varchar(255)");
				add(Columns.StartDate + " varchar(7) not null");
				add(Columns.EndDate + " varchar(7)");
				add(Columns.AlternateEnabled + " int(1)");
				add(Columns.AlternateValue + " double");
			}};
	}

	@Override
	public String getCreateStatement()
	{
		return String.format("create table %s(%s);", TableName, StringUtils.join(", ", ListUtils.casting(getColumnDefinitions())));
	}

	private static final String TableName = "tblValueBlackoutDates";

	// Verwaltung der Spalten der Tabelle
	public static class Columns
	{
		private Columns()
		{
		}

		public static final String BlackoutDateId = "id_pk";
		public static final String ValueIdFk = "value_id_fk";
		public static final String StartDate = "startDate";
		public static final String EndDate = "endDate";
		public static final String AlternateValue = "alernateValue";
		public static final String AlternateEnabled = "alternateEnabled";
		public static final String Comment = "commentText";

		// Alle Spalten zusammengefasst
		public static Iterable<String> all = new ArrayList<String>() {{
				add(BlackoutDateId);
				add(ValueIdFk);
				add(StartDate);
				add(EndDate);
				add(AlternateValue);
				add(AlternateEnabled);
				add(Comment);
			}};

		// Liest eine Zeile Aus.
		public static RowMapper<ValueBlackOutDateModel> ValueBlackoutDateRowMapper = new RowMapper<ValueBlackOutDateModel>() {

			@Override
			public ValueBlackOutDateModel map(final Cursor row, int columnIndex)
			{
				ValueBlackOutDateModel res = new ValueBlackOutDateModel() {{
						setId(asUUID(row, Columns.BlackoutDateId));
						setStart(asDate(row, Columns.StartDate));
						setEnd(asDate(row, Columns.EndDate));
						setValueIdFK(asUUID(row, Columns.ValueIdFk));
						setComment(asString(row, Columns.Comment));
						setAlternateValue(asDouble(row, Columns.AlternateValue));
						setAlternateEnabled(asBool(row, Columns.AlternateEnabled));
					}};
				return res;
			}
		};
	}
}
