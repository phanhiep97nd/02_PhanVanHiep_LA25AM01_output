/**
 * Copyright(C) 2020  Luvina Software
 * ValidateUser.java, Jul 14, 2020 Phan Văn Hiệp
 */
package manageuser.validates;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import manageuser.logics.impl.TblUserLogicImpl;
import manageuser.utils.Constant;
import manageuser.utils.MessageProperties;

/**
 * Xử lý validate thông tin nhập vào từ màn hình
 * @author Phan Van Hiep
 */
public class ValidateUser {
	
	/**
	 * hàm calidate các thông tin nhập từ màn hình
	 * @param loginName login_name nhập từ màn hình
	 * @param pass pass nhập từ màn hình
	 * @return list lỗi
	 */
	public static List<String> validateLogin(String loginName, String pass) throws ClassNotFoundException, NoSuchAlgorithmException, SQLException{
		List<String> lstError = new ArrayList<String>();
		TblUserLogicImpl userLogic = new TblUserLogicImpl();
		if("".equals(loginName)) {
			lstError.add(MessageProperties.readFileProperties(Constant.ER001_USERNAME));
		}
		if("".equals(pass)) {
			lstError.add(MessageProperties.readFileProperties(Constant.ER001_PASS));
		}
		if(!"".equals(loginName) && !"".equals(pass)) {
			if(!userLogic.checkExistLogin(loginName, pass)) {
				lstError.add(MessageProperties.readFileProperties(Constant.ER016));
			}
		}
		return lstError;
	}

}
