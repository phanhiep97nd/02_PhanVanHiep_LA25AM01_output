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

/**
 * Thực thi các hàm của TblUserLogic
 * @author Phan Van Hiep
 */
public class TblUserLogicImpl implements TblUserLogic{

	@Override
	public Boolean checkExistLogin(String loginName, String pass) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		TblUserDaoImpl userDao = new TblUserDaoImpl();
		TblUserEntity user = userDao.getTblUserByLoginName(loginName);
		String passEnscrypt = Common.enscryptPassword(pass, user.getSalt());
		boolean check = Common.compareString(passEnscrypt, user.getPass());
		return check;
	}
}