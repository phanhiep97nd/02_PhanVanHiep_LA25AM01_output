/**
 * Copyright(C) 2020  Luvina Software
 *CDDatabase.java, 11:08:51 PM Phan Văn Hiệp
 */
package bai2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Tạo các hàm nghiệp vụ
 * 
 * @author Phan Văn Hiệp
 */
public class CDDatabase {
	/**
	 * Thêm CD
	 * 
	 * @param cd Truyền vào cd muốn thêm
	 * @return có thêm thành công hay không!
	 * @throws SQLException
	 */
	public boolean insertCD(CD cd) throws SQLException {
		Connection conn = null;
		PreparedStatement pstm = null;
		String artistInput = cd.getArtist();
		String titleInput = cd.getTitle();
		boolean check = true;
		try {
			conn = ConnectionDB.openConnection();
			pstm = conn.prepareStatement("insert into cds values(?,?);");
			int parameterIndex = 1;
			pstm.setString(parameterIndex++, cd.getArtist());
			pstm.setString(parameterIndex++, cd.getTitle());
			int status = pstm.executeUpdate();
			if (status == 0) {
				check = false;
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			ConnectionDB.closeConnection(conn, pstm);
			return check;
		}
	}

	/**
	 * Xóa CD
	 * 
	 * @param cd Truyền vào cd muốn xóa
	 * @return có xóa thành công hay không!
	 * @throws SQLException
	 */
	public boolean removeCD(CD cd) throws SQLException {
		Connection conn = null;
		PreparedStatement pstm = null;
		boolean check = true;
		boolean isExis = false;
		try {
			for (CD cdGetAll : getAll()) {
				if (cd.getArtist().equals(cdGetAll.getArtist()) && cd.getTitle().equals(cdGetAll.getTitle())) {
					isExis = true;
				}
			}
			if (isExis) {
				conn = ConnectionDB.openConnection();
				if (conn != null) {
					pstm = conn.prepareStatement("DELETE FROM cds WHERE artist=? AND title = ?;");
					int parameterIndex = 1;
					pstm.setString(parameterIndex++, cd.getArtist());
					pstm.setString(parameterIndex++, cd.getTitle());
					int status = pstm.executeUpdate();
					if (status == 0) {
						check = false;
					}
				}
			} else {
				System.out.println("Không tồn tại CD này!");
				check = false;
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			ConnectionDB.closeConnection(conn, pstm);
			return check;
		}

	}

	/**
	 * Tìm CD theo title
	 * 
	 * @param input Truyền vào title muốn tìm
	 * @return Trả về danh sách tìm kiếm được!
	 * @throws Exception
	 */
	public ArrayList<CD> findByTitle(String input, String sortField) throws SQLException, NullPointerException {
		ArrayList<CD> lstCD = new ArrayList<CD>();
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = ConnectionDB.openConnection();
			if (conn != null && getColumnSort().contains(sortField)) {
				String SQL = "SELECT * FROM cds WHERE title LIKE ? ESCAPE '!'";
				SQL += " ORDER BY " + sortField +" DESC;";
				pstm = conn.prepareStatement(SQL);
				input.replace("%", "!%");
				input = "%" + input + "%";
				pstm.setString(1, input);
				ResultSet rs = pstm.executeQuery();
				while (rs.next()) {
					CD cd = new CD();
					cd.setArtist(rs.getString("artist"));
					cd.setTitle(rs.getString("title"));
					lstCD.add(cd);
				}
			}
			return lstCD;
		} catch (SQLException | NullPointerException e) {
			throw e;
		} finally {
			ConnectionDB.closeConnection(conn, pstm);
			return lstCD;
		}
	}

	/**
	 * Tìm CD theo artist
	 * 
	 * @param input Truyền vào artist muốn tìm
	 * @return Trả về danh sách tìm kiếm được!
	 * @throws SQLException
	 */
	public ArrayList<CD> findByArtist(String input) throws SQLException, NullPointerException {
		ArrayList<CD> lstCD = new ArrayList<CD>();
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = ConnectionDB.openConnection();
			if (conn != null) {
				pstm = conn.prepareStatement("SELECT * FROM cds WHERE artist=?;");
				pstm.setString(1, input);
				ResultSet rs = pstm.executeQuery();
				while (rs.next()) {
					CD cd = new CD();
					cd.setArtist(rs.getString("artist"));
					cd.setTitle(rs.getString("title"));
					lstCD.add(cd);
				}
			}
			return lstCD;
		} catch (SQLException | NullPointerException e) {
			throw e;
		} finally {
			ConnectionDB.closeConnection(conn, pstm);
			return lstCD;
		}
	}

	/**
	 * Lấy ra tất cả CD
	 * 
	 * @return Trả về danh sách tìm kiếm được!
	 * @throws SQLException
	 */
	public ArrayList<CD> getAll() throws SQLException {
		PreparedStatement pstm = null;
		Connection conn = null;
		ArrayList<CD> lstCD = new ArrayList<CD>();
		try {
			conn = ConnectionDB.openConnection();
			if (conn != null) {
				String SQL = "select artist,title from cds";
				pstm = (PreparedStatement) conn.prepareStatement(SQL);
				ResultSet rs = pstm.executeQuery(SQL);
				while (rs.next()) {
					CD cd = new CD();
					cd.setArtist(rs.getString("artist"));
					cd.setTitle(rs.getString("title"));
					lstCD.add(cd);
				}
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			ConnectionDB.closeConnection(conn, pstm);
			return lstCD;
		}
	}

/**
	 * Lấy ra danh sách tất cả các trường trong bảng
	 * 
	 * @return danh sách tất cả các trường trong bảng
	 */
	public static ArrayList<String> getColumnSort() throws Exception {
		ArrayList<String> columnSort = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = ConnectionDB.openConnection();
			if (conn != null) {
				String SQL = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = ?";
				pstm = conn.prepareStatement(SQL);
				pstm.setString(1, "cds");
				ResultSet rs = pstm.executeQuery();
				while (rs.next()) {
					columnSort.add(rs.getString(1));

				}
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			ConnectionDB.closeConnection(conn, pstm);
		}
		return columnSort;
	}

}

