package cn.finance.model;

import cn.finance.model.base.PersistentObject;

/**
 *  行业表
 */
public class Industry extends PersistentObject {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 行业名
	 */
	private String name;    
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
