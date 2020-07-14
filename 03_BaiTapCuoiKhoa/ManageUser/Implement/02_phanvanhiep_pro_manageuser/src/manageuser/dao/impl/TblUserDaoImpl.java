/**
 * Copyright(C) 2020  Luvina Software
 * TblUserDaoImpl.java, Jul 14, 2020 Phan Văn Hiệp
 */
package manageuser.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import manageuser.dao.TblUserDao;
import manageuser.entities.TblUserEntity;
import manageuser.utils.Constant;

/**
 * Description của class là làm gì
 * 
 * @author Phan Van Hiep
 */
public class TblUserDaoImpl extends BaseDaoImpl implements TblUserDao {

	@Override
	public TblUserEntity getTblUserByLoginName(String loginName) throws SQLException, ClassNotFoundException {
		TblUserEntity user = new TblUserEntity();
		try {
			openConnection();
			if (conn != null) {
				String sql = "SELECT password,salt FROM tbl_user WHERE login_name = ? AND rule = ?";
				pstm = conn.prepareStatement(sql);
				int index = 1;
				pstm.setString(index++, loginName);
				pstm.setInt(index++, 0);
				ResultSet rs = pstm.executeQuery();
				while (rs.next()) {
					user.setPass(rs.getString("password"));
					user.setSalt(rs.getString("salt"));
				}
			}
		} catch (SQLException | NullPointerException e) {
			throw e;
		} finally {
			closeConnection();
		}
		return user;
	}
}
