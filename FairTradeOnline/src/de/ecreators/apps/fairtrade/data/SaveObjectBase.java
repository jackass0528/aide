package de.ecreators.apps.fairtrade.data;

import de.ecreators.apps.fairtrade.*;
import de.ecreators.apps.fairtrade.basic.*;
import de.ecreators.apps.fairtrade.data.model.*;

public abstract class SaveObjectBase extends NotifyModel
 {
	private boolean isSaved;
	private boolean delete;

	protected abstract Iterable<ColumnValueMapper> getPkValues();
	
	public String getPks(String delimiter)
	{
		return StringUtils.join(delimiter, getPkValues(), Converters.ColumnStringConverter);
	}

	public void delete()
	{
		this.delete = true;
	}

	public boolean isDeleted()
	{
		return delete;
	}

	public void setIsSaved(boolean isSaved) {
		this.isSaved = isSaved;
	}
	public boolean isSaved() {
		return isSaved;
	}
	
	public abstract boolean readColumnValue(String column, ColumnResult e);
}
