/**
 * Copyright(C) 2020  Luvina Software
 * BaseDaoImpl.java, Jul 14, 2020 Phan Văn Hiệp
 */
package manageuser.dao.impl;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

import manageuser.dao.BaseDao;
import manageuser.utils.Constant;
import manageuser.utils.DatabaseProperties;

/**
 * Thực hiện các phương thức openConnection, closeConnection
 * 
 * @author Phan Van Hiep
 */
public class BaseDaoImpl implements BaseDao {
	protected Connection conn = null;
	protected PreparedStatement pstm = null;

	/**
	 * Mở kết nối đến Database
	 * 
	 * @return conn
	 * @throws SQLException, ClassNotFoundException
	 */
	@Override
	public Connection openConnection() throws SQLException, ClassNotFoundException {
		// Lấy từ file properties và gán các giá trị driver, userName, pass, url
		String driver = DatabaseProperties.getValueByKey((Constant.DRIVER));
		String userName = DatabaseProperties.getValueByKey((Constant.USER_NAME));
		String pass = DatabaseProperties.getValueByKey((Constant.PASS));
		String url = DatabaseProperties.getValueByKey((Constant.URL));
		try {
			// Thực hiện kết nối đến DB
			Class.forName(driver);
			conn = (Connection) DriverManager.getConnection(url, userName, pass);
		} catch (SQLException | ClassNotFoundException e) {
			// Hiển thị lỗi ở console
			System.out.println("Error : BaseDaoImpl.openConnection " + e.getMessage());
			// Throw lỗi đi
			throw e;
		}
		// trả về một đối tượng connection
		return conn;
	}

	/**
	 * Đóng kết nối đến DB
	 */
	@Override
	public void closeConnection() throws SQLException {
		try {
			// Nếu đang tồn tại đối tượng PrepareStatement thì đóng PrepareStatement
			if (pstm != null) {
				pstm.close();
			}
			// Nếu đang tồn tại đối tượng Connection và chưa đóng thì đóng Connection
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			// Hiển thị lỗi ở console
			System.out.println("Error : BaseDaoImpl.closeConnection " + e.getMessage());
			// Throw lỗi đi
			throw e;
		}

	}

}
