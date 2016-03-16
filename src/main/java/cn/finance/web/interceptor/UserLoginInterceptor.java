package cn.finance.web.interceptor;

import cn.finance.util.Constant;
import cn.finance.web.servlet.UserSession;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class UserLoginInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext ac = invocation.getInvocationContext();
		UserSession userSession = (UserSession) ac.getSession().get(Constant.USER_SESSION_KEY);
		if (userSession == null) {
			return "index";
		}
		return invocation.invoke();
	}

}
