package cn.finance.web.action.system;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import cn.finance.model.User;
import cn.finance.service.UserService;
import cn.finance.util.Security;
import cn.finance.util.Struts2Utils;
import cn.finance.web.action.base.BaseAction;

public class UserAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private UserService userService;

	private String language;
	
	private String password;
	
	private String newPassword;
	
	private String rePassword;
	

	// 基本属性
	private User user;

	public String languageSet() throws Exception {
		Struts2Utils.getRequest().setAttribute("nav", "5");
		Struts2Utils.getRequest().setAttribute("subNav", "1");
		user=userService.findUser(this.getUserSession().getUserId());
		user.setLanguage(language);
		user.setUpdateAt(new Date());
		userService.saveUser(user);
		this.addActionMessage("语言修改成功！");
		return SUCCESS;
	}
	
	public String languageInit() throws Exception {
		Struts2Utils.getRequest().setAttribute("nav", "5");
		Struts2Utils.getRequest().setAttribute("subNav", "1");
		user=userService.findUser(this.getUserSession().getUserId());
		return SUCCESS;
	}
	
	public String passwordInit() throws Exception{
		Struts2Utils.getRequest().setAttribute("nav", "5");
		Struts2Utils.getRequest().setAttribute("subNav", "2");
		return SUCCESS;
	}
	
	public String passwordSet() throws Exception{
		Struts2Utils.getRequest().setAttribute("nav", "5");
		Struts2Utils.getRequest().setAttribute("subNav", "2");
		user.setPassword( Security.MD5(newPassword));
		userService.saveUser(user);
		this.addActionMessage("修改密码成功！");
		return SUCCESS;
	}
	
	public void validatePasswordSet(){
		Struts2Utils.getRequest().setAttribute("nav", "5");
		Struts2Utils.getRequest().setAttribute("subNav", "2");
		user=userService.findUser(this.getUserSession().getUserId());
		if (StringUtils.isBlank(password)) {
			this.addActionError("请输入原密码");
		}else if(!StringUtils.equals(user.getPassword(), Security.MD5(password))){
			this.addActionError("输入的原密码有误");
		}
		
		if (StringUtils.isBlank(newPassword)) {
			this.addActionError("请输入新密码");
		}
		
		if (StringUtils.isBlank(rePassword)) {
			this.addActionError("请输入确认密码");
		}else if (StringUtils.isNotBlank(newPassword)&&!StringUtils.equals(newPassword, rePassword)) {
			this.addActionError("新密码与确认密码不相符，请重新输入");
		}
	}
	
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}

	public String getLanguage() {
		return language;
	}
	
	public void setLanguage(String language) {
		this.language = language;
	}
	
	public UserService getUserService() {
		return userService;
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

}