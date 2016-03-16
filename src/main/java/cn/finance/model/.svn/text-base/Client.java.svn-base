package cn.finance.model;

import java.util.HashSet;
import java.util.Set;

import cn.finance.model.base.PersistentObject;

/**
 * 客户表
 */
public class Client extends PersistentObject {

	private static final long serialVersionUID = 1L;
	
	/**
	 * sv3 ID
	 */
	private Integer sv3Id;
	
	/**
	 * 客户名
	 */
	private String name;    
	
	/**
	 * 客户类型
	 */
	private Integer type; 
	
	/**
	 * 客户状态  1有效 2无效
	 */
	private Integer status;
	
	/**
	 * 所属的项目
	 */
	private Set<Project> projects = new HashSet<Project>();

	public Integer getSv3Id() {
		return sv3Id;
	}

	public void setSv3Id(Integer sv3Id) {
		this.sv3Id = sv3Id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getCutName() {
		if (name !=null && name.length()>8){
			return name.substring(0, 8);
		}
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	public String getStatusDisplay() {
		if (status == 1){
			return "有效";
		} else if(status == 2) {
			return "无效";
		} else {
			return "";
		}
	}
	
	public String getTypeDisplay() {
		if (type == 1){
			return "直接客户";
		} else if(type == 2) {
			return "代理客户";
		} else {
			return "";
		}
	}
	
}
