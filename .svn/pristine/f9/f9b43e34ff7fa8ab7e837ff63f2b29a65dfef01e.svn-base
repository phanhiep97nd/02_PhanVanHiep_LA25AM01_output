/**
 * Copyright(C) 2020  Luvina Software
 * BaseDao.java, Jul 14, 2020 Phan Văn Hiệp
 */
package manageuser.dao;

import java.sql.SQLException;

import com.mysql.jdbc.Connection;

/**
 * Tạo ra các phương thức openConnection, closeConnection
 * 
 * @author Phan Van Hiep
 */
public interface BaseDao {
	/**
	 * Mở kết nối đến Database
	 * 
	 * @return conn
	 * @throws SQLException, ClassNotFoundException
	 */
	Connection openConnection() throws SQLException, ClassNotFoundException;

	/**
	 * closeDatabase đóng kết nối đến database
	 */
	void closeConnection() throws SQLException;
}
