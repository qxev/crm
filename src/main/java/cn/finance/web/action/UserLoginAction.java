package cn.finance.web.action;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;

import cn.finance.model.Resource;
import cn.finance.model.Role;
import cn.finance.model.User;
import cn.finance.service.RoleService;
import cn.finance.service.UserService;
import cn.finance.util.Constant;
import cn.finance.util.Struts2Utils;
import cn.finance.web.action.base.BaseAction;
import cn.finance.web.model.UserLoginView;
import cn.finance.web.servlet.UserSession;

import com.opensymphony.xwork2.ActionContext;

public class UserLoginAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private UserService userService;

	private RoleService roleService;

	private UserLoginView userLoginView;

	public UserLoginView getUserLoginView() {
		return userLoginView;
	}

	public void setUserLoginView(UserLoginView userLoginView) {
		this.userLoginView = userLoginView;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public String show() throws Exception {
		return INPUT;
	}

	public String logout() {
		Struts2Utils.getSession().invalidate();
		return SUCCESS;
	}

	public String execute() throws Exception {
		UserLoginView userView = getUserLoginView();
		String password = userView.getPassword();
		String username = userView.getUsername();
		User user = userService.getUserByNameAndPassword(username, DigestUtils.md5Hex(password));

		if (user != null) {
			Role role = roleService.findRoleById(user.getRole().getId());
			Map<String, String> maps = setRole(user, role);
			ActionContext.getContext().getSession().put("role", maps);
			UserSession userSession = new UserSession(user.getId(), user.getName());
			ActionContext.getContext().getSession().put(Constant.USER_SESSION_KEY, userSession);
			if (role.getId() == 8 && userView.getContractId() != null) {
				return "finance";
			}
			if (role.getId() > 9 || role.getId() == 2) {
				if (user.getAgentRole().getId() != 0) {
					maps.put("2.8.1", "查看合同列表");
					maps.put("2", "have");
					maps.put("type", user.getAgentRole().getId().toString());
					maps.put("agent", "1");
				}
			}
			return SUCCESS;
		} else {
			this.addActionError("用户名或者密码错误");
			return ERROR;
		}
	}

	private Map<String, String> setRole(User user, Role role) {
		Set<Resource> resources = role.getResources();
		Map<String, String> maps = new HashMap<String, String>();
		for (Resource resource : resources) {
			maps.put(resource.getMenu(), resource.getOperation());
		}
		if ((maps.get("1.1") != null) || (maps.get("1.2.1") != null)) {
			maps.put("1", "have");
		}
		if ((maps.get("2.1.1") != null) || (maps.get("2.2.1") != null) || (maps.get("2.3.1") != null) || (maps.get("2.4.1") != null)
				|| (maps.get("2.5.1") != null)) {
			maps.put("2", "have");
		}
		if ((maps.get("3.1.1") != null) || (maps.get("3.2.1") != null) || (maps.get("3.8.2") != null)) {
			maps.put("3", "have");
		}
		if ((maps.get("4.1.2") != null) || (maps.get("4.2.1") != null)) {
			maps.put("4", "have");
		}
		maps.put("5", "have");
		maps.put("6", "have");
		maps.put("type", role.getId().toString());
		return maps;
	}

}