package cn.finance.model;

import cn.finance.model.base.PersistentObject;

/**
 *  资源表
 */
public class Resource extends PersistentObject {

	private static final long serialVersionUID = 1L;
	
	private Integer type;    
	
	private String operation;
	
	private String menu;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}
	
}
