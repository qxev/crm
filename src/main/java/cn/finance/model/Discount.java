package cn.finance.model;

import java.math.BigDecimal;
import java.util.Date;

import cn.finance.model.base.PersistentObject;

/**
 * 折扣率表
 */
public class Discount extends PersistentObject {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 客户
	 */
	private Account account;    
	
	/**
	 * 达闻折扣率
	 */
	private BigDecimal darwinDiscount;
	
	/**
	 * 项目折扣率
	 */
	private BigDecimal projectDiscount;
	   
	/**
	 * 奖励折扣率
	 */
	private BigDecimal bonusDiscount;
	
	/**
	 * 开始日期
	 */
	private Date startDate;

	/**
	 * 结束日期
	 */
	private Date endDate;
	
	/**
	 * 服务费类型 1搜索 2网盟
	 */
	private Integer type;

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public BigDecimal getDarwinDiscount() {
		return darwinDiscount;
	}

	public void setDarwinDiscount(BigDecimal darwinDiscount) {
		this.darwinDiscount = darwinDiscount;
	}

	public BigDecimal getProjectDiscount() {
		return projectDiscount;
	}

	public void setProjectDiscount(BigDecimal projectDiscount) {
		this.projectDiscount = projectDiscount;
	}

	public BigDecimal getBonusDiscount() {
		return bonusDiscount;
	}

	public void setBonusDiscount(BigDecimal bonusDiscount) {
		this.bonusDiscount = bonusDiscount;
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
	
	public BigDecimal getTotalDiscount(){
		return darwinDiscount.add(projectDiscount).add(bonusDiscount);
	}
	
	@SuppressWarnings("deprecation")
	public Date getEndDateNotNull() {
		if (endDate==null || endDate.compareTo(new Date("3000/01/01"))==0) {
			return null;
		} else {
			return endDate;
		}
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
