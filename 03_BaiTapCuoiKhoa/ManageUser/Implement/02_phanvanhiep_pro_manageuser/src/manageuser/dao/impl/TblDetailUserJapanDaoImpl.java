/**
 * Copyright(C) 2020  Luvina Software
 * TblDetailUserJapanDaoImpl.java, Aug 4, 2020 Phan Văn Hiệp
 */
package manageuser.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import manageuser.dao.TblDetailUserJapanDao;
import manageuser.entities.TblDetailUserJapanEntity;

/**
 * Implement TblDeatilUserJapanDao để Xử lý Thao tác với DB bảng
 * tbl_detail_user_japan
 * 
 * @author Phan Van Hiep
 */
public class TblDetailUserJapanDaoImpl extends BaseDaoImpl implements TblDetailUserJapanDao {

	/**
	 * set Connection
	 * 
	 * @param conn đối tượng Connection
	 * @return
	 */
	@Override
	public void setConn(Connection connection) {
		this.conn = (com.mysql.jdbc.Connection) connection;
	}

	@Override
	public void insertTblDetailUserJapan(TblDetailUserJapanEntity tblDetailUserJapanEntity) throws SQLException {
		try {
			if (conn != null) {
				// tạo câu truy vấn
				StringBuilder sql = new StringBuilder(
						"INSERT INTO tbl_detail_user_japan (user_id, code_level, start_date, end_date, total)");
				sql.append(" VALUES(?,?,?,?,?)");
				// Thực hiện câu truy vấn
				pstm = conn.prepareStatement(sql.toString());
				// khai báo vị trí tham số
				int index = 1;
				// set các giá trị cho các tham số
				pstm.setInt(index++, tblDetailUserJapanEntity.getUserId());
				pstm.setString(index++, tblDetailUserJapanEntity.getCodeLevel());
				pstm.setString(index++, tblDetailUserJapanEntity.getStartDate());
				pstm.setString(index++, tblDetailUserJapanEntity.getEndDate());
				pstm.setString(index++, tblDetailUserJapanEntity.getTotal());

				// thực thi truy vấn
				pstm.execute();
			}
		} catch (SQLException e) {
			// thông báo lỗi ở màn hình console
			System.out.println("Error : TblDetailUserJapanDaoImpl.insertTblDetailUserJapan " + e.getMessage());
			// throw lỗi
			throw e;

		}
	}

}
