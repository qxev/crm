package cn.finance.util;

import java.util.Date;

@SuppressWarnings("deprecation")
public class Constant {
	public static final String USER_SESSION_KEY = "user_session";

	public static final String USER_TOKEN = "79ca7392da678dfae3dccc9b560d9e75";

	public final static String WEBAPP = "";

	public final static String BAIDU_ACCOUNT_REPORT = WEBAPP + "/reports/baidu/account/";
	
	public final static String GOOGLE_ACCOUNT_REPORT = WEBAPP + "/reports/google/account/";
	
	public final static Date DEFAULT_DATE = new Date("3000/01/01");
	
	public final static Integer STATUS_ACTIVE = 1;

	public final static Integer STATUS_INVALID = 2;

}
