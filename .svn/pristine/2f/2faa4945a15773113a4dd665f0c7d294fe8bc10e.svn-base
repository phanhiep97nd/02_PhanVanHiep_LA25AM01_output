package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

public class UserDatabase {
	/**
	 * Láº¥y ra táº¥t cáº£ CD
	 * 
	 * @return Tráº£ vá»� danh sÃ¡ch tÃ¬m kiáº¿m Ä‘Æ°á»£c!
	 * @throws Exception 
	 */
	public ArrayList<User> getAll() throws Exception {
		PreparedStatement pstm = null;
		Connection conn = null;
		ArrayList<User> lstUser = new ArrayList<User>();
		try {
			conn = ConnectionDB.openConnection();
			if (conn != null) {
				String SQL = "select * from user";
				pstm = (PreparedStatement) conn.prepareStatement(SQL);
				ResultSet rs = pstm.executeQuery(SQL);
				while (rs.next()) {
					User user = new User();
					user.setId(Integer.parseInt(rs.getString("id")));
					user.setName(rs.getString("name"));
					user.setBirthday(rs.getString("birthday"));
					user.setBirthPlace(rs.getString("birthplace"));
					lstUser.add(user);
				}
			}
		} catch (SQLException | NullPointerException e) {
			throw e;
		} finally {
			ConnectionDB.closeConnection(conn, pstm);			
		}
		return lstUser;
	}
}
