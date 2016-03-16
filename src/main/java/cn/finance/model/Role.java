package cn.finance.model;

import java.util.HashSet;
import java.util.Set;

import cn.finance.model.base.PersistentObject;

/**
 *  角色表
 */
public class Role extends PersistentObject {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 角色名
	 */
	private String name;   
	
	/**
	 * 权限
	 */
	private Set<Resource>resources = new HashSet<Resource>();
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Set<Resource> getResources() {
		return resources;
	}

	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}

}
