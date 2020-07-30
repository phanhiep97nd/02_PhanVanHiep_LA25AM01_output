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
import manageuser.logics.MstGroupLogic;
import manageuser.logics.TblUserLogic;
import manageuser.logics.impl.MstGroupLogicImpl;
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
	 * @param loginName
	 *            login_name nhập từ màn hình
	 * @param pass
	 *            pass nhập từ màn hình
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
	 * @param userInfor
	 *            đối tượng userInfor để validate các thuộc tính
	 * @return list lỗi
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static List<String> validateUserInfor(UserInfoEntity userInforEntity)
			throws ClassNotFoundException, SQLException {
		List<String> listError = new ArrayList<>();
		// validate loginName
		String errorLoginName = validateLoginName(userInforEntity.getLoginName());
		String errorGroup = validateGroup(userInforEntity.getGroupId());
		String errorFullName = validateFullName(userInforEntity.getFullName());
		String errorFullNameKatana = validateFullNameKatana(userInforEntity.getFullNameKatana());
		// nếu có lỗi
		if (!"".equals(errorLoginName)) {
			// thêm lỗi vào trong danh sách lỗi
			listError.add(errorLoginName);
		}
		if (!"".equals(errorGroup)) {
			listError.add(errorGroup);
		}
		if (!"".equals(errorFullName)) {
			listError.add(errorFullName);
		}
		if (!"".equals(errorFullNameKatana)) {
			listError.add(errorFullNameKatana);
		}
		return listError;
	}

	/**
	 * validate selectbox group
	 * 
	 * @param groupId
	 *            groupId cần kiểm tra
	 * @return lỗi nếu có
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	private static String validateGroup(int groupId) throws ClassNotFoundException, SQLException {
		// Khởi tạo biến chứa lỗi
		String errGroup = "";
		// Khởi tạo đối tượng mstGroupLogicImpl
		MstGroupLogic mstGroupLogicImpl = new MstGroupLogicImpl();
		// Kiểm tra chưa chọn
		if (Constant.GROUPID_DEFAULT == groupId) {
			errGroup = Constant.ER002_GROUPID;
			// Kiểm tra tồn tại groupId
		} else if (!mstGroupLogicImpl.checkExistGroup(groupId)) {
			errGroup = Constant.ER004_GROUPID;
		}
		return errGroup;
	}

	/**
	 * Validate trường Fullname Kana
	 * 
	 * @param fullNameKatana
	 *            tên kana cần validate
	 * @return lỗi nếu có
	 */
	private static String validateFullNameKatana(String fullNameKatana) {
		// Khởi tạo biến chứa lỗi
		String errFullNameKatana = "";
		// Kiểm tra kí tư Kana
		if (!Common.isKatakana(fullNameKatana)) {
			errFullNameKatana = Constant.ER009_FULLNAMEKANA;
			// Kiểm tra max kí tự
		} else if (!Common.checkLength(fullNameKatana, 0, 255)) {
			errFullNameKatana = Constant.ER006_FULLNAMEKANA;
		}
		return errFullNameKatana;
	}

	/**
	 * Validate trường fullName
	 * 
	 * @param fullName
	 *            giá trị cần validate
	 * @return lỗi
	 */
	private static String validateFullName(String fullName) {
		// Khởi tạo biến chữa lỗi
		String errFullName = "";
		// Kiểm tra rỗng
		if (Common.checkEmpty(fullName)) {
			errFullName = Constant.ER001_FULLNAME;
			// Kiểm tra max kí tự
		} else if (!Common.checkLength(fullName, 0, 255)) {
			errFullName = Constant.ER006_FULLNAME;
		}
		return errFullName;
	}

	/**
	 * validate giá trị của trường loginName
	 * 
	 * @param loginName
	 *            giá trị cần validate
	 * @return lỗi nếu có
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	private static String validateLoginName(String loginName) throws ClassNotFoundException, SQLException {
		// Khởi tạo biến chứa lỗi
		String errLoginName = "";
		// Khởi tạo đối tượng tblUserLogic
		TblUserLogic tblUserLogic = new TblUserLogicImpl();
		// Nếu rỗng thì gán mã lỗi ER001
		if (Common.checkEmpty(loginName)) {
			errLoginName = Constant.ER001_LOGINNAME;
			// Kiểm tra format
		} else if (!Common.checkFormat(loginName, Constant.FORMAT_LOGINNAME)) {
			errLoginName = Constant.ER0019_LOGINNAME;
			// Kiểm tra độ dài khoảng
		} else if (!Common.checkLength(loginName, Constant.MIN_LENGTH_LOGINNAME, Constant.MAX_LENGTH_LOGINNAME)) {
			errLoginName = Constant.ER007_LOGINNAME;
			// Check tồn tại trong DB
		} else if (tblUserLogic.checkExistedLoginName(loginName)) {
			errLoginName = Constant.ER003_LOGINNAME;
		}
		// trả về lỗi
		return errLoginName;
	}

}
