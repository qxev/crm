package cn.finance.web.interceptor;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.finance.util.Constant;
import cn.finance.web.servlet.UserSession;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class UserSessionInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;
	
	private static final Log logger = LogFactory.getLog(UserSessionInterceptor.class);

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext ac = invocation.getInvocationContext();
		Object action = invocation.getAction();
		
		if(action instanceof UserSessionAware) {
			ServletContext servletContext = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);
			WebApplicationContext wc = WebApplicationContextUtils.getWebApplicationContext(servletContext);
			
			if (wc == null) {
				logger.error("ApplicationContext could not be found.");
			} else {
				UserSession us = (UserSession) ac.getSession().get(Constant.USER_SESSION_KEY);
				((UserSessionAware) action).setUserSession(us);
			}
		}
		return invocation.invoke();
	}
}
