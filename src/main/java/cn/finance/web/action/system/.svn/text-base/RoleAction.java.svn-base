package cn.finance.web.action.system;

import org.springframework.beans.factory.annotation.Autowired;

import cn.finance.model.User;
import cn.finance.service.RoleService;
import cn.finance.service.UserService;
import cn.finance.util.Constant;
import cn.finance.util.Struts2Utils;
import cn.finance.web.action.base.BaseAction;
import cn.finance.web.model.RoleBean;
import cn.finance.web.servlet.UserSession;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class RoleAction extends BaseAction implements ModelDriven<RoleBean>{ 

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;
	
	private RoleBean model;
	
	public String list() throws Exception{
		Struts2Utils.getRequest().setAttribute("nav", "5");
		Struts2Utils.getRequest().setAttribute("subNav", "9");
		model = roleService.initRole(model);
		return SUCCESS;
	}

	public String save() throws Exception{
		Struts2Utils.getRequest().setAttribute("nav", "5");
		Struts2Utils.getRequest().setAttribute("subNav", "9");
		roleService.saveRole(model);
		this.addActionMessage("修改权限成功！");
		return "save";
	}

	public String initRoleSet() throws Exception{
		Struts2Utils.getRequest().setAttribute("nav", "5");
		Struts2Utils.getRequest().setAttribute("subNav", "10");
		UserSession userSession = (UserSession) ActionContext.getContext().getSession().get(Constant.USER_SESSION_KEY);
		User user = userService.findUser(userSession.getUserId());
		Integer roleId = user.getRole().getId();
		init(roleId);
		return "roleSet";
	}

	private void init(Integer roleId) {
		if (roleId==3){//bd主管
			model.setUsers(userService.getUserByType(2));
			model.setUserId(userService.getAgentIdByRole(2, roleId));
		}
		if (roleId==4){//sem主管
			model.setUsers(userService.getUserByType(11));
			model.setUserId(userService.getAgentIdByRole(11, roleId));
		}
		if (roleId==5){//seo主管
			model.setUsers(userService.getUserByType(12));
			model.setUserId(userService.getAgentIdByRole(12, roleId));
		}
		if (roleId==6){//aff主管
			model.setUsers(userService.getUserByType(13));
			model.setUserId(userService.getAgentIdByRole(13, roleId));
		}
		if (roleId==7){//wom主管
			model.setUsers(userService.getUserByType(14));
			model.setUserId(userService.getAgentIdByRole(14, roleId));
		}
	}
	
	public String roleSet() throws Exception{
		Struts2Utils.getRequest().setAttribute("nav", "5");
		Struts2Utils.getRequest().setAttribute("subNav", "10");
		UserSession userSession = (UserSession) ActionContext.getContext().getSession().get(Constant.USER_SESSION_KEY);
		User user = userService.findUser(userSession.getUserId());
		Integer roleId = user.getRole().getId();
		if (roleId==3)
			userService.setAgent(2,roleId,model.getUserId());
		if (roleId==4)
			userService.setAgent(10,roleId,model.getUserId());
		if (roleId==5)
			userService.setAgent(11,roleId,model.getUserId());
		if (roleId==6)
			userService.setAgent(12,roleId,model.getUserId());
		if (roleId==7)
			userService.setAgent(13,roleId,model.getUserId());
		init(roleId);
		if (model.getUserId()==0){
			this.addActionMessage("代理人取消成功！");
		} else {
			this.addActionMessage("代理人设置成功！");
		}
		return "roleSet";
	}
	
	@Override
	public RoleBean getModel() {
		if (model==null) {
			model=new RoleBean();
		}
		return model;
	}
	
}