package de.ecreators.apps.fairtrade.data.model;

public class ColumnValueMapper extends ColumnResult
{
	private final String column;
	
	public ColumnValueMapper(String column, Object v) {
		this.column = column;
		setValue(v);
	}

	public String getColumn()
	{
		return column;
	}

	@Override
	public String toString()
	{
		return String.format("%s=%s", column, getValue());
	}
}
