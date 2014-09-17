package de.ecreators.apps.fairtrade.dao.user_value;

import de.ecreators.apps.fairtrade.basic.*;
import de.ecreators.apps.fairtrade.dao.value_blackout_date.*;
import java.util.*;

public class UserValueModel extends SaveObjectBase
{
	private UUID id;
	private UUID userIdFk;
	private Double value;
	private String description;
	private Boolean enabled;
	private UUID intervalFk;
	private String createDate;
	private String endDate;
	private UUID destUserFk;
	private int payDay;
	private final ArrayList<ValueBlackOutDateModel> blackouts = new ArrayList<ValueBlackOutDateModel>();
	
	public UserValueModel() {
		id = UUID.randomUUID();
	}
	
	public void add(ValueBlackOutDateModel b) {
		blackouts.add(b);
	}
	
	public void add(Collection<ValueBlackOutDateModel> b) {
		blackouts.addAll(b);
	}
	
	public void remove(ValueBlackOutDateModel b) {
		blackouts.remove(b);
	}
	
	public void clear() {
		blackouts.clear();
	}
	
	public boolean isCompleted() {
		Date today = new Date();
		for(ValueBlackOutDateModel b : blackouts) {
			if(b.getEnd() != null && b.getEnd().isMonthBefore(today)) {
				
			}
		}
		return false;
	}
	
	public void setPayDay(int payDay)
	{
		this.payDay = payDay;
	}

	public int getPayDay()
	{
		return payDay;
	}

	public void setDestUserFk(UUID destUserFk)
	{
		this.destUserFk = destUserFk;
	}

	public UUID getDestUserFk()
	{
		return destUserFk;
	}

	public void setEndDate(String endDate)
	{
		this.endDate = endDate;
	}

	public String getEndDate()
	{
		return endDate;
	}

	public void setCreateDate(String createDate)
	{
		this.createDate = createDate;
	}

	public String getCreateDate()
	{
		return createDate;
	}

	public void setIntervalFk(UUID intervalFk)
	{
		this.intervalFk = intervalFk;
	}

	public UUID getIntervalFk()
	{
		return intervalFk;
	}

	public void setEnabled(Boolean enabled)
	{
		this.enabled = enabled;
	}

	public Boolean getEnabled()
	{
		return enabled;
	}

	public void setDescription(String describtion)
	{
		this.description = describtion;
	}

	public String getDescription()
	{
		return description;
	}

	public void setValue(Double value)
	{
		this.value = value;
	}

	public Double getValue()
	{
		return value;
	}

	public void setUserIdFk(UUID userIdFk)
	{
		this.userIdFk = userIdFk;
	}

	public UUID getUserIdFk()
	{
		return userIdFk;
	}

	public void setId(UUID id)
	{
		this.id = id;
	}

	public UUID getId()
	{
		return id;
	}
	@Override
	protected Iterable<ColumnValueMapper> getPkValues()
	{
		return new ArrayList<ColumnValueMapper>() {{
				add(new ColumnValueMapper(UserValuesTableDAO.Columns.ValueId, getId()));
			}};
	}

	@Override
	public boolean readColumnValue(String column, ColumnResult e)
	{
		// TODO: Implement this method
		return false;
	}
}
