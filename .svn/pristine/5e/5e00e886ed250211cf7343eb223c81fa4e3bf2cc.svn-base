/**
 * Copyright(C) 2020  Luvina Software
 * TblUserLogicImpl.java, Jul 14, 2020 Phan Văn Hiệp
 */
package manageuser.logics.impl;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import manageuser.dao.impl.TblUserDaoImpl;
import manageuser.entities.TblUserEntity;
import manageuser.logics.TblUserLogic;
import manageuser.utils.Common;
import manageuser.utils.MessageProperties;

/**
 * Thực thi các hàm của TblUserLogic
 * 
 * @author Phan Van Hiep
 */
public class TblUserLogicImpl implements TblUserLogic {
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
	@Override
	public Boolean checkExistLogin(String loginName, String pass)
			throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		try {
			TblUserDaoImpl userDao = new TblUserDaoImpl();
			TblUserEntity user = userDao.getTblUserByLoginName(loginName);
			String passEnscrypt = Common.enscryptPassword(pass, user.getSalt());
			boolean check = Common.compareString(passEnscrypt, user.getPass());
			return check;
		} catch (ClassNotFoundException | SQLException | NoSuchAlgorithmException e) {
			System.out.println("Error : TblUserLogicImpl.checkExistLogin " + e.getMessage());
			throw e;
		}

	}
}
