package cn.finance.model;

import cn.finance.model.base.PersistentObject;

/**
 *  合同模板表
 */
public class ContractTemplate extends PersistentObject {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 模板类型
	 */
	private BusinessType businessType;
	
	/**
	 * 内容
	 */
	private String context;

	public String getContext() {
		return context;
	}
	
	public String getContextCut() {
		if (context !=null && context.length()>20){
			return context.substring(0, 20);
		}
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public BusinessType getBusinessType() {
		return businessType;
	}

	public void setBusinessType(BusinessType businessType) {
		this.businessType = businessType;
	}  
	
}
