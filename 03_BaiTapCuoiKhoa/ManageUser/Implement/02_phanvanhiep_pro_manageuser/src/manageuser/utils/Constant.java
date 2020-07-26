/**
 * Copyright(C) 2020  Luvina Software
 * Constant.java, Jul 14, 2020 Phan Văn Hiệp
 */
package manageuser.utils;

/**
 * Chứa các constant của dự án
 * 
 * @author Phan Van Hiep
 */
public class Constant {
	// constant database va message
	public static final String URL = "URL";
	public static final String DRIVER = "DRIVER";
	public static final String USER_NAME = "USER_NAME";
	public static final String PASS = "PASS";
	public static final String ER001_USERNAME = "ER001_USERNAME";
	public static final String ER001_PASS = "ER001_PASS";
	public static final String ER016 = "ER016";
	public static final String MSG005 = "MSG005";
	public static final String ER015 = "ER015";

	// constant path properties
	public static final String PROPERTIES_MESSAGE_PATH = "//message.properties";
	public static final String PROPERTIES_DATABASE_PATH = "//database.properties";
	public static final String CONFIG_MESSAGE_PATH = "//config.properties";

	// constant path màn hình
	public static final String PATH_ADM001 = "/Views/jsp/ADM001.jsp";
	public static final String PATH_ADM002 = "/Views/jsp/ADM002.jsp";
	public static final String PATH_ADM003 = "/Views/jsp/ADM003.jsp";
	public static final String PATH_ADM005 = "/Views/jsp/ADM005.jsp";
	public static final String PATH_SYSTEM_ERROR = "/Views/jsp/System_Error.jsp";

	// constant URL
	public static final String URL_LOGIN = "login.do";
	public static final String URL_OUT = "logout.do";
	public static final String URL_LISTUSER = "listUser.do";

	// constant session
	public static final String SESSION_LOGINNAME = "loginName";
	public static final String SESSION_SEARCH = "search";
	public static final String SESSION_SORT_TYPE = "sortType";
	public static final String SESSION_SORT_LIKE = "sortLike";
	public static final String SESSION_CURRENTPAGE = "currentPage";

	// constant các hạng mục ở MH listUser
	public static final int RULE_ADMIN = 0;
	public static final int RULE_USER = 1;
	public static final int GROUPID_DEFAULT = 0;
	public static final String FULLNAME_DEFAULT = "";
	public static final String SORTTYPE_DEFAULT = "";
	public static final String SORTLIKE_DEFAULT = "";
	public static final int CURRENTPAGE_DEFAULT = 1;
	public static final String ASC = "ASC";
	public static final String DESC = "DESC";
	public static final String TYPE_DEFAULT = "default";
	public static final String TYPE_SEARCH = "search";
	public static final String TYPE_BACK = "back";
	public static final String TYPE_SORT = "sort";
	public static final String TYPE_PAGING = "paging";
	public static final int LIMIT_DEFAULT = 5;
	public static final int OFFSET_DEFAULT = 0;
	public static final int LIMITPAGE_DEFAULT = 3;
	public static final String SORT_TYPE_FULLNAME = "sortFullName";
	public static final String SORT_TYPE_CODELEVEL = "sortCodeLevel";
	public static final String SORT_TYPE_ENDDATE = "sortEndDate";

	// constant URL type
	public static final String URL_TYPE_DEFAULT = "?type=default";

	// constant config
	public static final String LIMIT = "LIMIT";
	public static final String LIMIT_PAGE = "LIMIT_PAGE";
	
	

}
