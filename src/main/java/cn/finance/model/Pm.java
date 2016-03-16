package cn.finance.model;

import cn.finance.model.base.PersistentObject;

/**
 *  pm表
 */
public class Pm extends PersistentObject {

	private static final long serialVersionUID = 1L;
	
	/**
	 * SEM分析师名
	 */
	private String name;  
	
	/**
	 * pm类型
	 */
	private BusinessType businessType;
	
	/**
	 * 账户状态 0有效 1无效
	 */
	private Integer status;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public BusinessType getBusinessType() {
		return businessType;
	}

	public void setBusinessType(BusinessType businessType) {
		this.businessType = businessType;
	}
	
}
