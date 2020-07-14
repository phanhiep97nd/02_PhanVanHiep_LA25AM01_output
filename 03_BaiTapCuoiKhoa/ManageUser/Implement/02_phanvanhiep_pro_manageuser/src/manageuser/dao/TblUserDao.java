/**
 * Copyright(C) 2020  Luvina Software
TblUserDao.java, Jul 13, 2020 Phan Van Hiep
 */
package manageuser.dao;

import java.sql.SQLException;

import manageuser.entities.TblUserEntity;

/**
 * Description 
 * @author Phan Van Hiep
 */
public interface TblUserDao extends BaseDao{
	/**
	 * Lấy ra user trong bảng tbl_user bằng loginName
	 * @param loginName loginName người dùng nhập vào
	 * @return trả về một user tìm được trong DB
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	TblUserEntity getTblUserByLoginName(String loginName) throws SQLException, ClassNotFoundException;
}
