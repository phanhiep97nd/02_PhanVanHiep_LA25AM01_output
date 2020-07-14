/**
 * Copyright(C) 2020  Luvina Software
 * TblUserLogic.java, Jul 14, 2020 Phan Văn Hiệp
 */
package manageuser.logics;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

/**
 * Interface Xử lý logic cho các chức năng login + list/search user
 * 
 * @author Phan Van Hiep
 */
public interface TblUserLogic {

	/**
	 * Kiểm tra xem loginName và pass người dùng nhập có tồn tại trong DB hay khoong
	 * 
	 * @param loginName login_name người dùng nhập
	 * @param pass      password người dùng nhập
	 * @return trả về kết quả là giá trị boolean có tồn tại trong DB không
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws NoSuchAlgorithmException
	 */
	Boolean checkExistLogin(String loginName, String pass)
			throws ClassNotFoundException, SQLException, NoSuchAlgorithmException;
}
