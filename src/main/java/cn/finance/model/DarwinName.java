package cn.finance.model;

import cn.finance.model.base.PersistentObject;

/**
 *  达闻公司名
 */
public class DarwinName extends PersistentObject {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 公司名
	 */
	private String name;    
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
