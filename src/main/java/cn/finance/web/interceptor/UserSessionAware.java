package cn.finance.web.interceptor;

import cn.finance.web.servlet.UserSession;

public interface UserSessionAware {
	public void setUserSession(UserSession userSession);
}
