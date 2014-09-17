package de.ecreators.apps.fairtrade.dao.user;

import de.ecreators.apps.fairtrade.basic.*;
import de.ecreators.apps.fairtrade.utils.*;
import java.util.*;

// Datenobjekt einer Datenzeile der Tabelle.
public class UserModel extends SaveObjectBase
{
	private UUID id = UUID.randomUUID();
	private String name;
	private String pwHash;
	private Boolean isLeft;
	private String visName;

	public void setVisName(String visName)
	{
		this.visName = visName;
	}

	public String getVisName()
	{
		return visName;
	}

	public void setIsLeft(Boolean isLeft)
	{
		this.isLeft = isLeft;
	}

	public Boolean getIsLeft()
	{
		return isLeft;
	}

	public void setPassword(String pw)
	{
		this.pwHash = "" + (pw == null ? 0 : pw.hashCode());
	}

	public void setPwHash(String pwHash)
	{
		this.pwHash = pwHash;
	}

	public String getPwHash()
	{
		return pwHash;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void setId(UUID id)
	{
		this.id = id;
	}
	public UUID getId()
	{
		return id;
	}

	//==================

	@Override
	protected List<ColumnValueMapper> getPkValues()
	{
		return ListUtils.<ColumnValueMapper>toList(new ColumnValueMapper(UserTableDAO.Columns.UserId, getId()));
	}

	@Override
	public boolean readColumnValue(String column, ColumnResult e)
	{
		switch (column) 
		{
			case UserTableDAO.Columns.UserId:
				e.setValue(getId());
				return true;
				
				// ToDo andere Spalten ...
		}
		return false;
	}

	@Override
	public boolean equals(Object o)
	{
		if(o == null || !(o instanceof UserModel)) {
			return false;
		}
		return ((UserModel)o).getId().equals(getId());
	}

	@Override
	public int hashCode()
	{
		return getId() == null ? 0 : getId().hashCode();
	}
}
