/**
 * Copyright(C) 2020  Luvina Software
 * ValidateUser.java, Jul 14, 2020 Phan Văn Hiệp
 */
package manageuser.validates;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import manageuser.entities.UserInfoEntity;
import manageuser.logics.impl.TblUserLogicImpl;
import manageuser.utils.Common;
import manageuser.utils.Constant;
import manageuser.utils.MessageProperties;

/**
 * Xử lý validate thông tin nhập vào từ màn hình
 * 
 * @author Phan Van Hiep
 */
public class ValidateUser {

	/**
	 * hàm validate các thông tin nhập từ màn hình login
	 * 
	 * @param loginName login_name nhập từ màn hình
	 * @param pass      pass nhập từ màn hình
	 * @return list lỗi
	 */
	public static List<String> validateLogin(String loginName, String pass)
			throws ClassNotFoundException, NoSuchAlgorithmException, SQLException {
		List<String> lstError = new ArrayList<String>();
		TblUserLogicImpl userLogic = new TblUserLogicImpl();
		try {
			if ("".equals(loginName)) {
				lstError.add(MessageProperties.getValueByKey((Constant.ER001_USERNAME)));
			}
			if ("".equals(pass)) {
				lstError.add(MessageProperties.getValueByKey((Constant.ER001_PASS)));
			}
			if (!"".equals(loginName) && !"".equals(pass)) {
				if (!userLogic.checkExistLogin(loginName, pass)) {
					lstError.add(MessageProperties.getValueByKey((Constant.ER016)));
				}
			}

		} catch (ClassNotFoundException | NoSuchAlgorithmException | SQLException e) {
			// Hiển thị ở console lỗi
			System.out.println("Error : ValidateUser.validateLogin " + e.getMessage());
			throw e;
		}
		return lstError;
	}

	/**
	 * hàm validate các thông tin nhập từ màn hình ADM003
	 * 
	 * @param userInfor đối tượng userInfor để validate các thuộc tính
	 * @return list lỗi
	 */
	public static List<String> validateUserInfor(UserInfoEntity userInforEntity) {
		List<String> listError = new ArrayList<>();
		// validate loginName
		String errorLoginName = validateLoginName(userInforEntity.getLoginName());
		// nếu có lỗi
		if (!"".equals(errorLoginName)) {
			// thêm lỗi vào trong danh sách lỗi
			listError.add(errorLoginName);
		}
		return listError;
	}

	/**
	 * validate giá trị của trường loginName
	 * 
	 * @param loginName giá trị cần validate
	 * @return lỗi nếu có
	 */
	private static String validateLoginName(String loginName) {
		// Khởi tạo biến chứa lỗi
		String errLoginName = "";
		// Nếu rỗng thì gán mã lỗi Ẻ001
		if (Common.checkEmpty(loginName)) {
			errLoginName = Constant.ER001_LOGINNAME;
		}
		return errLoginName;
	}

}
