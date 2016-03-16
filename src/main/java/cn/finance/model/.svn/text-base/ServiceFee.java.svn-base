package cn.finance.model;

import java.math.BigDecimal;
import java.util.Date;

import cn.finance.model.base.PersistentObject;

/**
 * 服务费表
 */
public class ServiceFee extends PersistentObject {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 客户
	 */
	private Account account;    
	
	/**
	 * 先前的服务费
	 */
	private BigDecimal preValue;

	/**
	 * 服务费类型 1搜索 2网盟
	 */
	private Integer type;
	
	/**
	 * 服务费
	 */
	private BigDecimal value;
	
	/**
	 * 开始日期
	 */
	private Date startDate;

	/**
	 * 结束日期
	 */
	private Date endDate;

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@SuppressWarnings("deprecation")
	public Date getEndDateNotNull() {
		if (endDate==null || endDate.compareTo(new Date("3000/01/01"))==0) {
			return null;
		} else {
			return endDate;
		}
	}

	public BigDecimal getPreValue() {
		return preValue;
	}

	public void setPreValue(BigDecimal preValue) {
		this.preValue = preValue;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
}
