package de.ecreators.apps.fairtrade.dao.model;

import de.ecreators.apps.fairtrade.*;
import de.ecreators.apps.fairtrade.basic.*;

public interface IDAOObject<T extends SaveObjectBase>
{
	Tables.DAO<T> getDAO();
}
