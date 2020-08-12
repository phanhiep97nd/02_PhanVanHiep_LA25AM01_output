/**
 * Copyright(C) 2020  Luvina Software
 *ConnectionDB.java, 10:08:31 PM Phan Văn Hiệp
 */
package bai2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Tạo connection đến DB
 * 
 * @author Phan Văn Hiệp
 */
public class ConnectionDB {
	private static String DRIVER = "com.mysql.jdbc.Driver";
	private static String URL = "jdbc:mysql://localhost:3306/02_phanvanhiep_cds";
	private static String USER_NAME = "root";
	private static String PASS = "0000";

	/**
	 * Mở kết nối đến Database
	 * 
	 * @return conn
	 * @throws Exception
	 */
	public static Connection openConnection() throws Exception {
		Connection conn = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER_NAME, PASS);
		} catch (SQLException | ClassNotFoundException e) {
			throw e;
		} finally {
			return conn;
		}
	}
	
	/**
	 * Đóng kết nối đến Database
	 * @param conn Truyền vào đối tượng Connection cần đóng kết nối
	 * @param pstm Truyền vào đối tượng PreparedStatement cần đóng kết nối
	 * @throws SQLException
	 */
	public static void closeConnection(Connection conn, PreparedStatement pstm) throws SQLException {
		try {
			if(conn!=null && !conn.isClosed()) {
				conn.close();
			}
			if(pstm != null) {
				pstm.close();
			}
		} catch (SQLException e) {
			throw e;
		}
	}
}
