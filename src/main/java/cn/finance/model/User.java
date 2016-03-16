package cn.finance.model;

import cn.finance.model.base.PersistentObject;

/**
 *  用户表
 */
public class User extends PersistentObject {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 用户名
	 */
	private String name;    
	
	/**
	 * 语言类型 1:中文 ;2:英文
	 */
	private String language;
	
	/**
	 * 用户类型
	 * 1. 超级用户
	 * 2. bd普通用户
	 * 3. bd主管用户
	 * 4. sem主管用户
	 * 5. seo主管用户
	 * 6. aff主管用户
	 * 7. wom主管用户
	 * 8. 财务主管用户
	 * 9. CEO用户
	 */
	private Role role;
	
	private Role agentRole;

	/**
	 * Email
	 */
	private String email;
	
	/**
	 * 0: 上海
	 * 1: 北京
	 */
	private Integer areaId;

	/**
	 * 
	 */
	private Integer pmId;

	/**
	 * 密码
	 */
	private String password;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getAgentRole() {
		return agentRole;
	}

	public void setAgentRole(Role agentRole) {
		this.agentRole = agentRole;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public Integer getPmId() {
		return pmId;
	}

	public void setPmId(Integer pmId) {
		this.pmId = pmId;
	}
	
}
