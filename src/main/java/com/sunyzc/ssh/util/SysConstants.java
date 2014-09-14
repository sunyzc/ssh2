package com.sunyzc.ssh.util;

public interface SysConstants {
	/** application(ServletContext)中所有session的Set集合 */
	String ALL_SESSIONS = "allSessions";
	/** application(ServletContext)中所有的Resource对象map，key为actionPath */
	String ALL_RESOURCE_MAP = "allResourceMap";
	/** application(ServletContext)中所有顶级权限对象Authority的List集合 */
	String ALL_TOP_AUTHORITIES = "allTopAuthorities";
	/** application(ServletContext)中存储的所有数据库表中的actionPath的List集合 */
	String ALL_ACTION_PATHS = "allActionPaths";

	/** session中保存的登录用户User对象 */
	String USER_IN_SESSION = "user";
	/** 登录用户授权的所有actionPath的Set集合 */
	String ALL_AUTHORISED_ACTION_PATHS = "allAuthorisedActionPaths";
	/** session中存储的String格式的验证码 */
	String SESSION_SECURITY_CODE = "security_code";

	/** 登录相关的（如登录、登出、获取验证码等）action的前缀 */
	String ACTION_LOGIN_PREFIX = "/login";
	/** 登录的action */
	String ACTION_PATH_LOGIN = "/login!login";
	/** 登出的action */
	String ACTION_PATH_LOGOUT = "/login!logout";

	/** cookie中保存的用户登录信息 */
	String COOKIE_FOR_LOGIN_INFO = "userinfo";
}
