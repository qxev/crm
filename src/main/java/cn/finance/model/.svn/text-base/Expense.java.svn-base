package cn.finance.model;

import java.math.BigDecimal;
import java.util.Date;

import cn.finance.model.base.PersistentObject;

/**
 * 账户每日花费表
 */
public class Expense extends PersistentObject {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 账户
	 */
	private Account account;
	
	/**
	 * 管理媒体费
	 */
	
	private BigDecimal originalManageMedia;

	/**
	 * 网盟消费
	 */
	private BigDecimal originalNetAff;
	
	/**
	 * 管理媒体费
	 */
	private BigDecimal manageMedia;

	/**
	 * 网盟消费
	 */
	private BigDecimal netAff;

	/**
	 * 花费
	 */
	private BigDecimal cost;

	/**
	 * 达闻成本
	 */
	private BigDecimal darwinCost;
	
	/**
	 * 折扣
	 */
	private BigDecimal discount;

	/**
	 * 服务费
	 */
	private BigDecimal serviceFee;
	
	/**
	 * 服务费
	 */
	private BigDecimal serviceFeeAdjust;
	
	/**
	 * 收入
	 */
	private BigDecimal revenue;
	
	/**
	 * 毛利
	 */
	private BigDecimal grossProfit;
	
	/**
	 * 昨天余额
	 */
	private BigDecimal lastAmount;
	
	/**
	 * 扣除后的余额
	 */
	private BigDecimal currentAmount;
	
	/**
	 * 花费日期
	 */
	private Date expenseDate;

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public BigDecimal getLastAmount() {
		return lastAmount;
	}

	public void setLastAmount(BigDecimal lastAmount) {
		this.lastAmount = lastAmount;
	}

	public BigDecimal getCurrentAmount() {
		return currentAmount;
	}

	public void setCurrentAmount(BigDecimal currentAmount) {
		this.currentAmount = currentAmount;
	}

	public Date getExpenseDate() {
		return expenseDate;
	}

	public void setExpenseDate(Date expenseDate) {
		this.expenseDate = expenseDate;
	}

	public BigDecimal getManageMedia() {
		return manageMedia;
	}
	
	public BigDecimal getTotalManageMedia() {
		BigDecimal temp = manageMedia;
		if (temp==null)
			temp = new BigDecimal(0);
		BigDecimal temp1 = netAff;
		if (temp1==null)
			temp1 = new BigDecimal(0);
		return temp.add(temp1);
	}

	public void setManageMedia(BigDecimal manageMedia) {
		this.manageMedia = manageMedia;
	}

	public BigDecimal getDarwinCost() {
		return darwinCost;
	}

	public void setDarwinCost(BigDecimal darwinCost) {
		this.darwinCost = darwinCost;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public BigDecimal getServiceFee() {
		return serviceFee;
	}

	public void setServiceFee(BigDecimal serviceFee) {
		this.serviceFee = serviceFee;
	}

	public BigDecimal getRevenue() {
		return revenue;
	}

	public void setRevenue(BigDecimal revenue) {
		this.revenue = revenue;
	}

	public BigDecimal getGrossProfit() {
		return grossProfit;
	}

	public void setGrossProfit(BigDecimal grossProfit) {
		this.grossProfit = grossProfit;
	}

	public BigDecimal getServiceFeeAdjust() {
		return serviceFeeAdjust;
	}

	public void setServiceFeeAdjust(BigDecimal serviceFeeAdjust) {
		this.serviceFeeAdjust = serviceFeeAdjust;
	}
	
	public BigDecimal getServiceFeeTotal() {
		BigDecimal temp = serviceFeeAdjust;
		if (temp==null)
			temp = new BigDecimal(0);
		BigDecimal temp1 = serviceFee;
		if (temp1==null)
			temp1 = new BigDecimal(0);
		return temp1.add(temp);
	}

	public BigDecimal getNetAff() {
		if (netAff == null){
			return new BigDecimal(0);
		} else {
			return netAff;
		}
	}

	public void setNetAff(BigDecimal netAff) {
		this.netAff = netAff;
	}

	public BigDecimal getOriginalManageMedia() {
		return originalManageMedia;
	}

	public void setOriginalManageMedia(BigDecimal originalManageMedia) {
		this.originalManageMedia = originalManageMedia;
	}

	public BigDecimal getOriginalNetAff() {
		if (originalNetAff == null){
			return new BigDecimal(0);
		} else {
			return originalNetAff;
		}
	}

	public void setOriginalNetAff(BigDecimal originalNetAff) {
		this.originalNetAff = originalNetAff;
	}
	
}
