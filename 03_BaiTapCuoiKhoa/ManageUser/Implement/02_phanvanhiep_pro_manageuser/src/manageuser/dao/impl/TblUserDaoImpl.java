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
		// Khởi taho một đối tượng TblUserEntity
		TblUserEntity user = new TblUserEntity();
		try {
			// Mở kết nối
			openConnection();
			// Check nếu mở kết nối thành công
			if (conn != null) {
				// Câu lệnh SQL
				String sql = "SELECT password,salt FROM tbl_user WHERE login_name = ? AND rule = ?";
				// Gọi đến prepareStatement truyền vào câu lệnh SQL
				pstm = conn.prepareStatement(sql);
				// Gán giá trị tương ứng vào câu truy vấn
				int index = 1;
				pstm.setString(index++, loginName);
				pstm.setInt(index++, 0);
				// Khởi tạo một đối tượng ResultSet để nhận kết quả trả về từ câu Query
				ResultSet rs = pstm.executeQuery();
				// Chạy từng kết quả của ResultSet
				while (rs.next()) {
					// gái các giá trị vào đối tượng user
					user.setPass(rs.getString("password"));
					user.setSalt(rs.getString("salt"));
				}
			}
		} catch (SQLException | NullPointerException e) {
			throw e;
		} finally {
			// đóng kết nối
			closeConnection();
		}
		// Trả về đối tượng user
		return user;
	}
	
}
