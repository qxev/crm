package cn.finance.model;

import java.math.BigDecimal;
import java.util.Date;

import cn.finance.model.base.PersistentObject;
import cn.finance.util.DateUtil;

/**
 *  充值历史表
 */
public class SupplementHistory extends PersistentObject {

	private static final long serialVersionUID = 1L;
	
	private Account account;    
	
	/**
	 * 充值日期
	 */
	private Date supplementDate;
	
	/**
	 * 付款金额
	 */
	private BigDecimal payAmount; 
	
	/**
	 * 返点
	 */
	private BigDecimal counterPoint;
	
	/**
	 * 充值总额
	 */
	private BigDecimal totalAmount; 
	
	/**
	 * 还款金额
	 */
	private BigDecimal repayAmount; 
	

	/**
	 * 冲值类型 1:达闻直充 ;2:渠道垫付 ;3渠道反点
	 */
	private Integer type; 
	
	/**
	 * 下次还款日期
	 */
	private Date nextSupplementDate;
	
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Date getSupplementDate() {
		return supplementDate;
	}

	public void setSupplementDate(Date supplementDate) {
		this.supplementDate = supplementDate;
	}

	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	public String getPayAmountStr(){
		return payAmount.toString();
	}
	
	public String getPayAmountStr2(){
		return totalAmount.subtract(counterPoint).toString();
	}

	public String getCounterPointStr(){
		return counterPoint.toString();
	}

	public String getTotalAmountStr(){
		return totalAmount.toString();
	}
	
	public String getNextSupplementDateStr(){
		return DateUtil.dateToString(nextSupplementDate);
	}

	public String getSupplementDateStr(){
		return DateUtil.dateToString(supplementDate);
	}

	public void setNextSupplementDateStr(String nextSupplementDate){
		this.nextSupplementDate = DateUtil.stringToDate(nextSupplementDate);
	}
	
	public void setSupplementDateStr(String supplementDate){
		this.supplementDate = DateUtil.stringToDate(supplementDate);
	}
	
	public void setPayAmountStr(String payAmountStr){
		if (payAmountStr==null){
			this.payAmount = new BigDecimal(0);
		} else {
			this.payAmount = new BigDecimal(payAmountStr);
		}
	}
	
	public void setPayAmountStr2(String payAmountStr){
		setPayAmountStr(payAmountStr);
	}

	public void setCounterPointStr(String counterPointStr){
		if (counterPointStr==null){
			this.counterPoint = new BigDecimal(0);
		} else {
			this.counterPoint = new BigDecimal(counterPointStr);
		}
	}

	public void setTotalAmountStr(String totalAmountStr){
		if (totalAmountStr==null){
			this.totalAmount = new BigDecimal(0);
		} else {
			this.totalAmount = new BigDecimal(totalAmountStr);
		}
	}

	public BigDecimal getCounterPoint() {
		return counterPoint;
	}

	public void setCounterPoint(BigDecimal counterPoint) {
		this.counterPoint = counterPoint;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getNextSupplementDate() {
		return nextSupplementDate;
	}

	public void setNextSupplementDate(Date nextSupplementDate) {
		this.nextSupplementDate = nextSupplementDate;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public BigDecimal getRepayAmount() {
		return repayAmount;
	}

	public void setRepayAmount(BigDecimal repayAmount) {
		this.repayAmount = repayAmount;
	}
	
	public long getRemainDays() {
		if (nextSupplementDate!=null){
			Date today = new Date();
			if (nextSupplementDate.getTime()-today.getTime()<0)
				return 0;
			return (nextSupplementDate.getTime()-today.getTime())/(1000*60*60*24)+1;
		} else {
			return 0;
		}
	}
}
