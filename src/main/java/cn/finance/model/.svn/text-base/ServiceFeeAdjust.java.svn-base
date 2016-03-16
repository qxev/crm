package cn.finance.model;

import java.math.BigDecimal;
import java.util.Date;

import cn.finance.model.base.PersistentObject;

/**
 * 服务费调整历史表
 */
public class ServiceFeeAdjust extends PersistentObject {

	private static final long serialVersionUID = 1L;

	/**
	 * 项目
	 */
	private Project project;    

	/**
	 * 渠道
	 */
	private Channel channel;    
	
	/**
	 * 服务调整费
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
	
	/**
	 * 服务费类型 1：月服务费  2：短期服务费
	 */
	private Integer type;

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public void setValueStr(String valueStr){
		if (valueStr==null){
			this.value = new BigDecimal(0);
		} else {
			this.value = new BigDecimal(valueStr);
		}
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

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
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
