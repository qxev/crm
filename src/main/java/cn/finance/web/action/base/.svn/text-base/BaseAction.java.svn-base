package cn.finance.web.action.base;

import cn.finance.web.interceptor.UserSessionAware;
import cn.finance.web.servlet.UserSession;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements UserSessionAware {

	private static final long serialVersionUID = 1L;

	public static final String RELOAD = "reload";

	public static final String EDIT = "edit";

	/**
	 * 一级菜单
	 */
	private String nav;
	
	/**
	 * 二级菜单
	 */
	private String subNav;

	private Integer pageSize = 30;
	
	private UserSession userSession;

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	public UserSession getUserSession() {
		return userSession;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getNav() {
		return nav;
	}

	public void setNav(String nav) {
		this.nav = nav;
	}

	public String getSubNav() {
		return subNav;
	}

	public void setSubNav(String subNav) {
		this.subNav = subNav;
	}
}

