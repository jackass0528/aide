package de.ecreators.apps.fairtrade.basic;

public class KeyValue extends DbValueResult
{
	private final String keyOrColumn;

	public KeyValue(String columnOrKey, Object value)
	{
		this.keyOrColumn = columnOrKey;
		setValue(value);
	}

	public String getKey()
	{
		return keyOrColumn;
	}

	@Override
	public String toString()
	{
		return String.format("%s=%s", keyOrColumn, getValue());
	}

	@Override
	public boolean equals(Object o)
	{
		if (o instanceof KeyValue)
		{
			KeyValue kv = (KeyValue) o;
			return kv != null && kv.hashCode() == hashCode();
		}
		return false;
	}

	@Override
	public int hashCode()
	{
		int hashCode = (getKey() == null ? 0 : getKey().hashCode()) ^
			(getOrdinalValue() == null ? 0 : getOrdinalValue().hashCode());
		return hashCode == 0 ? super.hashCode() : hashCode;
	}
}
