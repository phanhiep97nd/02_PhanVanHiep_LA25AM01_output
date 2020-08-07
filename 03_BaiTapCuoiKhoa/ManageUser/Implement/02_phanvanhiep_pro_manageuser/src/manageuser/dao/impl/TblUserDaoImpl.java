﻿/**
 * Copyright(C) 2020  Luvina Software
 * TblUserDaoImpl.java, Jul 14, 2020 Phan Văn Hiệp
 */
package manageuser.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import manageuser.dao.TblUserDao;
import manageuser.entities.TblUserEntity;
import manageuser.entities.UserInfoEntity;
import manageuser.utils.Common;
import manageuser.utils.Constant;

/**
 * Implement UserDao để Xử lý Thao tác với DB bảng tbl_user
 * 
 * @author Phan Van Hiep
 */
public class TblUserDaoImpl extends BaseDaoImpl implements TblUserDao {
	/**
	 * Lấy ra user trong bảng tbl_user bằng loginName
	 * 
	 * @param loginName
	 *            loginName người dùng nhập vào
	 * @return trả về một user tìm được trong DB
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@Override
	public TblUserEntity getTblUserByLoginId(String loginName) throws SQLException, ClassNotFoundException {
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
			// Thông báo lỗi ở màn hình console
			System.out.println("Error: TblUserDaoImpl.getTblUserByLoginId " + e.getMessage());
			throw e;
		} finally {
			// đóng kết nối
			closeConnection();
		}
		// Trả về đối tượng user
		return user;
	}

	/**
	 * lấy các thông tin chi tiết của user từ bảng tbl_user, mst_group,
	 * mst_japan, tbl_detail_user_japan
	 * 
	 * @param offset
	 *            vị trí bắt đầu lấy
	 * @param limit
	 *            số bản ghi tối đa trên 1 page
	 * @param groupId
	 *            là id của nhóm được chọn trong pulldown
	 * @param fullName
	 *            là fullname tìm kiếm nhập vào từ textbox
	 * @param sortType
	 *            là loại sắp xếp theo fullName, codeLevel hay endDate
	 * @param sortByFullName
	 *            giá trị sắp xếp (ASC/DESC) cột fullName
	 * @param sortByCodeLevel
	 *            giá trị sắp xếp (ASC/DESC) cột codelevel
	 * @param sortByEndDate
	 *            giá trị sắp xếp (ASC/DESC) cột endDate
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
		// Khai đối tượng SimpleDateFormat chuyển định dạng ngày tháng năm về
		// YYYY/MM/DD
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		try {
			// lấy danh sách tên các cột
			listColumn = getListColumn();
			// mở kết nối đến cơ sở dữ liệu
			openConnection();
			// kiểm tra xem đã kết nối thành công?
			if (conn != null) {
				// Xử lí kí tự widecard
				fullName = Common.replaceWildCard(fullName);

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
				// textbox fullName có giá trị thì thêm điều kiện tìm kiếm với
				// full_name
				if (!"".equals(fullName)) {
					// thêm câu lệnh vào sql
					sql.append(" AND u.full_name LIKE ? ");
				}
				// pulldown group khác giá trị mặc định thì thêm điều kiện tìm
				// kiếm theo group
				if (groupId != 0) {
					// thêm câu lệnh vào sql
					sql.append(" AND u.group_id = ? ");
				}

				// Kiểm tra các table có tồn tại trong DB
				if (listColumn.containsKey("tbl_user_full_name") && listColumn.containsKey("mst_japan_name_level")
						&& listColumn.containsKey("tbl_detail_user_japan_end_date")) {
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
					} else {
						if (Constant.SORT_TYPE_FULLNAME.equals(sortType)) {
							// sort theo full_name
							sql.append(" ORDER BY u.full_name ");
							sql.append(sortByFullName);
							// sort name_level
							sql.append(", j.name_level ");
							sql.append(sortByCodeLevel);
							// sort end_date
							sql.append(", d.end_date ");
							sql.append(sortByEndDate);
						} else if (Constant.SORT_TYPE_CODELEVEL.equals(sortType)) {
							// sort name_level
							sql.append(" ORDER BY j.name_level ");
							sql.append(sortByCodeLevel);
							// sort theo full_name
							sql.append(", u.full_name ");
							sql.append(sortByFullName);
							// sort end_date
							sql.append(", d.end_date ");
							sql.append(sortByEndDate);
						} else if (Constant.SORT_TYPE_ENDDATE.equals(sortType)) {
							// sort end_date
							sql.append(" ORDER BY d.end_date ");
							sql.append(sortByEndDate);
							// sort theo full_name
							sql.append(", u.full_name ");
							sql.append(sortByFullName);
							// sort name_level
							sql.append(", j.name_level ");
							sql.append(sortByCodeLevel);
						}
					}
				}

				// Điều kiện lấy các bản ghi từ vị trí offset và lấy tiếp limit
				// bản ghi
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

				// Khai báo đối tượng ResulSet để lưu các giá trị lấy về được từ
				// DB
				ResultSet rs = pstm.executeQuery();
				// Chạy từng bản ghi từ rs
				while (rs.next()) {
					// Khởi tạo đối tượng userInfoEntity để lấy các giá trị từ
					// câu truy vấn
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
					userInfo.setTotal(rs.getString("total"));

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.dao.TblUserDao#getTotalUsers(int, java.lang.String)
	 */
	@Override
	public int getTotalUsers(int groupId, String fullName) throws ClassNotFoundException, SQLException {
		// Tạo biến đếm số User tìm được ban đầu bằng 0
		int countUser = 0;
		try {
			openConnection();
			if (conn != null) {
				// Xử lí kí tự widecard
				fullName = Common.replaceWildCard(fullName);

				// Khởi tạo chuỗi sql chứa câu lệnh truy vấn
				StringBuilder sql = new StringBuilder();
				sql.append("SELECT COUNT(*)");
				sql.append(" FROM tbl_user u");
				sql.append(" INNER JOIN mst_group g");
				sql.append(" ON u.group_id = g.group_id");
				sql.append(" LEFT JOIN (tbl_detail_user_japan d");
				sql.append(" INNER JOIN mst_japan j");
				sql.append(" ON d.code_level = j.code_level)");
				sql.append(" ON u.user_id = d.user_id");
				sql.append(" WHERE u.Rule = ?");

				// textbox fullName có giá trị thì thêm điều kiện tìm kiếm với
				// full_name
				if (!"".equals(fullName)) {
					// thêm câu lệnh vào sql
					sql.append(" AND u.full_name LIKE ? ");
				}
				// pulldown group khác giá trị mặc định thì thêm điều kiện tìm
				// kiếm theo group
				if (groupId != 0) {
					// thêm câu lệnh vào sql
					sql.append(" AND u.group_id = ? ");
				}

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

				// Khai báo đối tượng ResulSet để lưu các giá trị lấy về được từ
				// DB
				ResultSet rs = pstm.executeQuery();
				while (rs.next()) {
					countUser = rs.getInt(1);
				}
			}
			return countUser;
		} catch (ClassNotFoundException | SQLException e) {
			// Thông báo lỗi ở màn hình console
			System.out.println("Error: TblUserDaoImpl.getTotalUsers " + e.getMessage());
			// Throw lỗi
			throw e;
		} finally {
			// đóng kết nối
			closeConnection();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.dao.TblUserDao#getTblUserByLoginName(java.lang.String)
	 */
	@Override
	public TblUserEntity getUserByLoginName(String loginName) throws SQLException, ClassNotFoundException {
		// Khởi taho một đối tượng TblUserEntity
		TblUserEntity user = new TblUserEntity();
		try {
			// Mở kết nối
			openConnection();
			// Check nếu mở kết nối thành công
			if (conn != null) {
				// Câu lệnh SQL
				String sql = "SELECT * FROM tbl_user WHERE login_name = ?";
				// Gọi đến prepareStatement truyền vào câu lệnh SQL
				pstm = conn.prepareStatement(sql);
				// Gán giá trị tương ứng vào câu truy vấn
				int index = 1;
				pstm.setString(index++, loginName);
				// Khởi tạo một đối tượng ResultSet để nhận kết quả trả về từ
				// câu Query
				ResultSet rs = pstm.executeQuery();
				// Chạy từng kết quả của ResultSet
				while (rs.next()) {
					user.setLoginName(rs.getString("login_name"));
				}
			}
		} catch (SQLException | NullPointerException e) {
			// Thông báo lỗi ở màn hình console
			System.out.println("Error: TblUserDaoImpl.getTblUserByLoginName " + e.getMessage());
			throw e;
		} finally {
			// đóng kết nối
			closeConnection();
		}
		// Trả về đối tượng user
		return user;
	}

	@Override
	public TblUserEntity getUserByEmail(int userId, String email) throws SQLException, ClassNotFoundException {
		try {
			// Khởi taho một đối tượng TblUserEntity
			TblUserEntity tblUserEntity = new TblUserEntity();
			// Mở kết nối DB
			openConnection();
			// nếu kết nối với DB thành công
			if (conn != null) {
				// câu lệnh truy vấn
				StringBuilder sql = new StringBuilder("SELECT * FROM tbl_user WHERE email = ?");
				// nếu userid khác 0(Trường hợp edit)
				if (userId != 0) {
					sql.append(" AND user_id != ?");
				}
				// thực hiện truy vấn
				pstm = conn.prepareStatement(sql.toString());
				// Gán giá trị tương ứng vào câu truy vấn
				int index = 1;
				pstm.setString(index++, email);
				// nếu userid khác 0(Trường hợp edit)
				if (userId != 0) {
					// set giá trị cho userId
					pstm.setInt(index++, userId);
				}
				// Khởi tạo một đối tượng ResultSet để nhận kết quả trả về từ
				// câu Query
				ResultSet rs = pstm.executeQuery();
				while (rs.next()) {
					tblUserEntity = new TblUserEntity();
					// gán giá trị cho thuộc tính email
					tblUserEntity.setEmail(rs.getString("email"));
				}
			}
			// trả về đối tượng tblUserEntity
			return tblUserEntity;
		} catch (SQLException | ClassNotFoundException e) {
			// Thông báo lỗi ở màn hình consolei
			System.out.println("Error : TblUserDaoImpl.getUserByEmail " + e.getMessage());
			// throw lỗi
			throw e;
		} finally {
			// đóng kết nối db
			closeConnection();

		}
	}

	/**
	 * Lấy về Connection
	 * 
	 * @return đối tượng Connection
	 */
	@Override
	public Connection getConnection() {
		return conn;
	}

	/**
	 * quyết định có tự động ghi vào DB hay ko
	 * 
	 * @param isAutoCommit
	 *            có tự động commit hay là ko
	 * @throws SQLException
	 */
	@Override
	public void setDisableCommit(boolean isAutoCommit) throws SQLException {
		try {
			if (conn != null) {
				conn.setAutoCommit(isAutoCommit);
			}
		} catch (SQLException e) {
			// thông báo lỗi
			System.out.println("Error : TblUserDaoImpl.setAutoCommit " + e.getMessage());
			// gửi lỗi
			throw e;
		}

	}

	/**
	 * ghi các thược tính của các đối tượng tblUserEntity vào DB
	 * 
	 * @param tblUserEntity
	 *            giá trị cần ghi vào DB
	 * @return userId
	 * @throws SQLException
	 */
	@Override
	public int insertUser(TblUserEntity tblUserEntity) throws SQLException {
		try {
			// userId trả về
			int userId = 0;
			if (conn != null) {
				// tạo câu truy vấn
				StringBuilder sql = new StringBuilder(
						"INSERT INTO tbl_user (group_id, login_name, password, full_name, full_name_kana, email, tel, birthday, rule, salt)");
				sql.append(" VALUES(?,?,?,?,?,?,?,?,?,?)");
				// Gọi đến hàm prepareStatement có khả năng truy suất đến các
				// trường được tạo tự động
				// Hằng số truyền vào cho biết trình điều khiển có nên tạo khóa
				// tự động có sẵn để truy xuất hay không.(Nó sẽ được bỏ qua khi
				// ko phải là câu lẹnh insert)
				pstm = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
				// khai báo vị trí tham số
				int index = 1;
				// set các giá trị cho các tham số
				pstm.setInt(index++, tblUserEntity.getGroupId());
				pstm.setString(index++, tblUserEntity.getLoginName());
				pstm.setString(index++, tblUserEntity.getPass());
				pstm.setString(index++, tblUserEntity.getFullName());
				pstm.setString(index++, tblUserEntity.getFullNameKana());
				pstm.setString(index++, tblUserEntity.getEmail());
				pstm.setString(index++, tblUserEntity.getTel());
				pstm.setString(index++, tblUserEntity.getBirthday());
				pstm.setInt(index++, tblUserEntity.getRule());
				pstm.setString(index++, tblUserEntity.getSalt());

				// thực thi truy vấn
				pstm.execute();
				// Lấy về kết quả từ trường tự tăng
				ResultSet rs = pstm.getGeneratedKeys();
				while (rs.next()) {
					// lấy ra userID
					userId = rs.getInt(1);
				}
			}
			// trả về userid
			return userId;
		} catch (SQLException e) {
			// thông báo lỗi ở màn hình console
			System.out.println("Error : TblUserDaoImpl.insertUser " + e.getMessage());
			// throw lỗi
			throw e;

		}
	}

	/**
	 * Thực hiện insert
	 * 
	 * @throws SQLException
	 */
	@Override
	public void commitData() throws SQLException {
		try {
			if (conn != null) {
				// bắt đầu thực hiện thao tác vào DB
				conn.commit();
			}
		} catch (SQLException e) {
			// thông báo lỗi
			System.out.println("Error : TblUserDaoImpl.commitData " + e.getMessage());
			// gửi lỗi
			throw e;
		}
	}

	/**
	 * Trả lại dữ liệu về trạng thái chưa insert
	 * 
	 * @throws SQLException
	 */
	@Override
	public void rollBack() throws SQLException {
		try {
			if (conn != null) {
				// lấy lại dữ liệu ban đầu
				conn.rollback();
			}
		} catch (SQLException e) {
			// thông báo lỗi
			System.out.println("Error: TblUserDaoImpl.rollBack " + e.getMessage());
			// throw lỗi
			throw e;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.dao.TblUserDao#getUserByUserId(int)
	 */
	@Override
	public UserInfoEntity getUserInfoByUserId(int userId) throws SQLException, ClassNotFoundException {
		// Khai báo đối tượng UserInfoEntity
		UserInfoEntity userInfoEntity = null;
		// Khai đối tượng SimpleDateFormat chuyển định dạng ngày tháng năm về
		// YYYY/MM/DD
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		try {
			// mở kết nối đến cơ sở dữ liệu
			openConnection();
			// kiểm tra xem đã kết nối thành công?
			if (conn != null) {
				// khởi tạo chuỗi câu truy vẫn sql
				StringBuilder sql = new StringBuilder();
				sql.append(
						" SELECT u.user_id,u.login_name, u.full_name, u.full_name_kana, u.birthday, u.email, u.tel, g.group_id, g.group_name,d.code_level, j.name_level, d.start_date, d.end_date, d.total");
				sql.append(" FROM tbl_user u");
				sql.append(" INNER JOIN mst_group g");
				sql.append(" ON u.group_id = g.group_id");
				sql.append(" LEFT JOIN (tbl_detail_user_japan d");
				sql.append(" INNER JOIN mst_japan j");
				sql.append(" ON d.code_level = j.code_level)");
				sql.append(" ON u.user_id = d.user_id");
				sql.append(" WHERE u.Rule = ?");
				sql.append(" AND u.user_id = ?");

				// gọi đến prepareStatement và truyền vào câu truy vấn sql
				pstm = conn.prepareStatement(sql.toString());
				// Khai báo index = 1
				int index = 1;
				// set giá trị vào câu truy vấn
				pstm.setInt(index++, Constant.RULE_USER);
				pstm.setInt(index++, userId);
				// Khai báo đối tượng ResulSet để lưu các giá trị lấy về được từ
				// DB
				ResultSet rs = pstm.executeQuery();
				// Chạy từng bản ghi từ rs
				while (rs.next()) {
					// Khởi tạo (Khởi tạo trong đây để nếu không tồn tại id thì
					// hàm sẽ trả về null)
					userInfoEntity = new UserInfoEntity();
					// set giá trị cho các thuộc tính của đối tượng userInfo
					userInfoEntity.setUserId(rs.getInt("user_id"));
					userInfoEntity.setLoginName(rs.getString("login_name"));
					userInfoEntity.setFullName(rs.getString("full_name"));
					userInfoEntity.setFullNameKatana(rs.getString("full_name_kana"));
					if (rs.getDate("birthday") == null) {
						userInfoEntity.setBirthday("");
					} else {
						userInfoEntity.setBirthday(dateFormat.format(rs.getDate("birthday")));
					}

					userInfoEntity.setEmail(rs.getString("email"));
					userInfoEntity.setTel(rs.getString("tel"));
					userInfoEntity.setGroupId(rs.getInt("group_id"));
					userInfoEntity.setGroupName(rs.getString("group_name"));
					userInfoEntity.setCodeLevel(rs.getString("code_level"));
					userInfoEntity.setNameLevel(rs.getString("name_level"));
					if (rs.getDate("start_date") == null) {
						userInfoEntity.setStartDate("");
					} else {
						userInfoEntity.setStartDate(dateFormat.format(rs.getDate("start_date")));
					}
					if (rs.getDate("end_date") == null) {
						userInfoEntity.setEndDate("");
					} else {
						userInfoEntity.setEndDate(dateFormat.format(rs.getDate("end_date")));
					}
					userInfoEntity.setTotal(rs.getString("total"));
				}
			}
			// trả về đối tượng userInfoEntity
			return userInfoEntity;
		} catch (ClassNotFoundException | SQLException e) {
			// Thông báo lỗi ở màn hình console
			System.out.println("Error: TblUserDaoImpl.getUserInfoByUserId " + e.getMessage());
			// Throw lỗi
			throw e;
		} finally {
			// đóng kết nối
			closeConnection();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see manageuser.dao.TblUserDao#getUserById(int)
	 */
	@Override
	public TblUserEntity getTblUserById(int userId) throws SQLException, ClassNotFoundException {
		// Khởi taho một đối tượng TblUserEntity
		TblUserEntity user = new TblUserEntity();
		try {
			// Mở kết nối
			openConnection();
			// Check nếu mở kết nối thành công
			if (conn != null) {
				// Câu lệnh SQL
				String sql = "SELECT * FROM tbl_user WHERE user_id = ?";
				// Gọi đến prepareStatement truyền vào câu lệnh SQL
				pstm = conn.prepareStatement(sql);
				// Gán giá trị tương ứng vào câu truy vấn
				int index = 1;
				pstm.setInt(index++, userId);
				// Khởi tạo một đối tượng ResultSet để nhận kết quả trả về từ
				// câu Query
				ResultSet rs = pstm.executeQuery();
				// Chạy từng kết quả của ResultSet
				while (rs.next()) {
					user.setLoginName(rs.getString("login_name"));
				}
			}
		} catch (SQLException | NullPointerException e) {
			// Thông báo lỗi ở màn hình console
			System.out.println("Error: TblUserDaoImpl.getTblUserByLoginName " + e.getMessage());
			throw e;
		} finally {
			// đóng kết nối
			closeConnection();
		}
		// Trả về đối tượng user
		return user;
	}
}
