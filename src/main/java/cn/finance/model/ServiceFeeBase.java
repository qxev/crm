package cn.finance.model;

import java.math.BigDecimal;
import java.util.Date;

import cn.finance.model.base.PersistentObject;

/**
 * 服务费调整基数表
 */
public class ServiceFeeBase extends PersistentObject {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 客户
	 */
	private Account account;    
	
	/**
	 * 服务费调整基数
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
}
