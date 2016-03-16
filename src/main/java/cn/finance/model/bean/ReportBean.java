package cn.finance.model.bean;

import java.math.BigDecimal;

/**
 * 
 */
public class ReportBean {

	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String name;
	
	private BigDecimal manageMedia;

	private BigDecimal netAff;

	private BigDecimal originalManageMedia;

	private BigDecimal originalNetAff;

	private BigDecimal cost;

	private BigDecimal darwinCost;

	private BigDecimal discount;

	private BigDecimal serviceFee;

	private BigDecimal serviceFeeAdjust;

	private BigDecimal revenue;

	private BigDecimal grossProfit;

	public ReportBean(){
		
	}
	
	public ReportBean(Integer id, String name, BigDecimal originalManageMedia, BigDecimal originalNetAff, 
			BigDecimal manageMedia, BigDecimal netAff, BigDecimal cost,
			BigDecimal darwinCost, BigDecimal discount, BigDecimal serviceFee,BigDecimal serviceFeeAdjust,
			BigDecimal revenue, BigDecimal grossProfit) {
		super();
		this.id = id;
		this.name = name;
		this.originalManageMedia = originalManageMedia;
		this.originalNetAff = originalNetAff;
		this.manageMedia = manageMedia;
		this.netAff = netAff;
		this.cost = cost;
		this.darwinCost = darwinCost;
		this.discount = discount;
		this.serviceFee = serviceFee;
		this.serviceFeeAdjust = serviceFeeAdjust;
		this.revenue = revenue;
		this.grossProfit = grossProfit;
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

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getNetAff() {
		return netAff;
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
		return originalNetAff;
	}

	public void setOriginalNetAff(BigDecimal originalNetAff) {
		this.originalNetAff = originalNetAff;
	}
}
