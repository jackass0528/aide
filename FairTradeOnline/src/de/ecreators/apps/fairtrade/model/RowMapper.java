package de.ecreators.apps.fairtrade.model;

import android.database.*;

public abstract interface RowMapper<T>
{
	public abstract T map(Cursor row, int columnIndex);
}
