package de.ecreators.apps.fairtrade.dao.user;

import de.ecreators.apps.fairtrade.basic.*;
import de.ecreators.apps.fairtrade.utils.*;
import java.util.*;

// Datenobjekt einer Datenzeile der Tabelle.
public class UserModel extends SaveObjectBase
{
	private final Property<UUID> idProperty;
	private final Property<String> nameProperty;
	private final Property<String> pwHashProperty;
	private final Property<Boolean> isLeftProperty;
	private final Property<String> visNameProperty;
	
	public UserModel()
	{
		idProperty = new Property<UUID>(UserTableDAO.Columns.UserId, this, UUID.randomUUID());
		nameProperty = new Property<String>(UserTableDAO.Columns.UserName, this, null);
		pwHashProperty = new Property<String>(UserTableDAO.Columns.PasswordHash, this, null);
		isLeftProperty = new Property<Boolean>(UserTableDAO.Columns.IsLeftSide, this, true);
		visNameProperty = new Property<String>(UserTableDAO.Columns.VisibleName, this, null);
	}

	public void setVisName(String visName)
	{
		this.visNameProperty.setValue(visName);
	}

	public String getVisName()
	{
		return visNameProperty.getValue();
	}

	public void setIsLeft(Boolean isLeft)
	{
		this.isLeftProperty.setValue(isLeft);
	}

	public Boolean getIsLeft()
	{
		return isLeftProperty.getValue();
	}

	public void setPassword(String pw)
	{
		this.pwHashProperty.setValue("" + (pw == null ? 0 : pw.hashCode()));
	}

	public void setPwHash(String pwHash)
	{
		this.pwHashProperty.setValue(pwHash);
	}

	public String getPwHash()
	{
		return pwHashProperty.getValue();
	}

	public void setName(String name)
	{
		this.nameProperty.setValue(name);
	}

	public String getName()
	{
		return nameProperty.getValue();
	}

	public void setId(UUID id)
	{
		this.idProperty.setValue(id);
	}
	public UUID getId()
	{
		return idProperty.getValue();
	}

	//==================

	@Override
	protected List<KeyValue> getPkValues()
	{
		return ListUtils.<KeyValue>asList(new KeyValue(UserTableDAO.Columns.UserId, getId()));
	}

	@Override
	public boolean readColumnValue(String column, DbValueResult e)
	{
		switch (column) 
		{
			case UserTableDAO.Columns.UserId:
				e.setValue(getId());
				return true;
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
		if (o == null || !(o instanceof UserModel))
		{
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
