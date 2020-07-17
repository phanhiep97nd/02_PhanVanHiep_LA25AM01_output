/**
 * Copyright(C) 2020  Luvina Software
 * TblUserDaoImpl.java, Jul 14, 2020 Phan Văn Hiệp
 */
package manageuser.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import manageuser.dao.TblUserDao;
import manageuser.entities.TblUserEntity;
import manageuser.entities.UserInfoEntity;
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
				// Khởi tạo một đối tượng ResultSet để nhận kết quả trả về từ
				// câu Query
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

	/**
	 * lấy các thông tin chi tiết của user từ bảng tbl_user, mst_group, mst_japan,
	 * tbl_detail_user_japan
	 * 
	 * @param offset          vị trí bắt đầu lấy
	 * @param limit           số bản ghi tối đa trên 1 page
	 * @param groupId         là id của nhóm được chọn trong pulldown
	 * @param fullName        là fullname tìm kiếm nhập vào từ textbox
	 * @param sortType        là loại sắp xếp theo fullName, codeLevel hay endDate
	 * @param sortByFullName  giá trị sắp xếp (ASC/DESC) cột fullName
	 * @param sortByCodeLevel giá trị sắp xếp (ASC/DESC) cột codelevel
	 * @param sortByEndDate   giá trị sắp xếp (ASC/DESC) cột endDate
	 * @return trả về 1 list danh sách các UserInfo
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@Override
	public ArrayList<UserInfoEntity> getListUser(int offset, int limit, int groupId, String fullName, String sortType,
			String sortByFullName, String sortByCodeLevel, String sortByEndDate)
			throws ClassNotFoundException, SQLException {
		// khai báo listUserInfo danh sách các userinfo
		ArrayList<UserInfoEntity> listUserInfo = new ArrayList<>();
		// Khai đối tượng SimpleDateFormat chuyển định dạng ngày tháng năm về YYYY/MM/DD
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

		try {
			// mở kết nối đến cơ sở dữ liệu
			openConnection();
			// kiểm tra xem đã kết nối thành công?
			if (conn != null) {
				// khởi tạo chuỗi câu truy vẫn sql
				StringBuilder sql = new StringBuilder();
				sql.append(
						" SELECT u.user_id, u.full_name, u.birthday, u.email, u.tel, g.group_id, g.group_name, j.name_level, d.end_date, d.total");
				sql.append(" FROM tbl_user u");
				sql.append(" INNER JOIN mst_group g");
				sql.append(" ON u.group_id = g.group_id");
				sql.append(" LEFT JOIN (tbl_detail_user_japan d");
				sql.append(" INNER JOIN mst_japan j");
				sql.append(" ON d.code_level = j.code_level)");
				sql.append(" ON u.user_id = d.user_id");
				sql.append(" WHERE u.Rule = ?");
				// textbox fullName có giá trị thì thêm điều kiện tìm kiếm với full_name
				if (!"".equals(fullName)) {
					// thêm câu lệnh vào sql
					sql.append(" AND u.full_name LIKE ? ");
				}
				// pulldown group khác giá trị mặc định thì thêm điều kiện tìm kiếm theo group
				if (groupId != 0) {
					// thêm câu lệnh vào sql
					sql.append(" AND u.group_id = ? ");
				}

				// default chưa có điều kiện sort thì sort theo mặc định
				if ("".equals(sortType)) {
					// sort theo full_name
					sql.append(" ORDER BY u.full_name ");
					sql.append(sortByFullName);
					// sort name_level
					sql.append(", j.name_level ");
					sql.append(sortByCodeLevel);
					// sort end_date
					sql.append(", d.end_date ");
					sql.append(sortByEndDate);
				}

				// Điều kiện lấy các bản ghi từ vị trí offset và lấy tiếp limit bản ghi
				sql.append(" LIMIT " + offset + ", " + limit);

				// gọi đến prepareStatement và truyền vào câu truy vấn sql
				pstm = conn.prepareStatement(sql.toString());
				// Khai báo index = 1
				int index = 1;
				// set giá trị vào câu truy vấn
				pstm.setInt(index++, Constant.RULE_USER);
				// Nếu người dùng có nhập fullname
				if (!"".equals(fullName)) {
					// set giá trị vào câu truy vấn
					pstm.setString(index++, "%" + fullName + "%");
				}
				// Nếu người dùng có chọn group
				if (groupId != 0) {
					// set giá trị vào câu truy vấn
					pstm.setInt(index++, groupId);
				}

				// Khai báo đối tượng ResulSet để lưu các giá trị lấy về được từ DB
				ResultSet rs = pstm.executeQuery();
				// Chạy từng bản ghi từ rs
				while (rs.next()) {
					// Khởi tạo đối tượng userInfoEntity để lấy các giá trị từ câu truy vấn
					UserInfoEntity userInfo = new UserInfoEntity();
					// set giá trị cho các thuộc tính của đối tượng userInfo
					userInfo.setUserId(rs.getInt("user_id"));
					userInfo.setFullName(rs.getString("full_name"));
					if (rs.getDate("birthday") == null) {
						userInfo.setBirthday("");
					} else {
						userInfo.setBirthday(dateFormat.format(rs.getDate("birthday")));
					}

					userInfo.setEmail(rs.getString("email"));
					userInfo.setTel(rs.getString("tel"));
					userInfo.setGroupName(rs.getString("group_name"));
					userInfo.setNameLevel(rs.getString("name_level"));
					if (rs.getDate("end_date") == null) {
						userInfo.setEndDate("");
					} else {
						userInfo.setEndDate(dateFormat.format(rs.getDate("end_date")));
					}
					userInfo.setTotal(rs.getInt("total"));

					// thêm userInfo vào danh sách listUserInfo
					listUserInfo.add(userInfo);
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			// Thông báo lỗi ở màn hình console
			System.out.println("Error: TblUserDaoImpl.getListUser " + e.getMessage());
			// Throw lỗi
			throw e;
		} finally {
			// đóng kết nối
			closeConnection();
		}
		return listUserInfo;
	}
}