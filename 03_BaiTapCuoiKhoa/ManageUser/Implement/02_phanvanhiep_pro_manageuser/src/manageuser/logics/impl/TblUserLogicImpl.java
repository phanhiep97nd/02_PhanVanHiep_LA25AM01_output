/**
 * Copyright(C) 2020  Luvina Software
 * TblUserLogicImpl.java, Jul 14, 2020 Phan Văn Hiệp
 */
package manageuser.logics.impl;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

import manageuser.dao.TblUserDao;
import manageuser.dao.impl.TblUserDaoImpl;
import manageuser.entities.TblUserEntity;
import manageuser.entities.UserInfoEntity;
import manageuser.logics.TblUserLogic;
import manageuser.utils.Common;
import manageuser.utils.MessageProperties;

/**
 * Thực thi các hàm của TblUserLogic
 * 
 * @author Phan Van Hiep
 */
public class TblUserLogicImpl implements TblUserLogic {
	/**
	 * Kiểm tra xem loginName và pass người dùng nhập có tồn tại trong DB hay khoong
	 * 
	 * @param loginName login_name người dùng nhập
	 * @param pass      password người dùng nhập
	 * @return trả về kết quả là giá trị boolean có tồn tại trong DB không
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws NoSuchAlgorithmException
	 */
	@Override
	public Boolean checkExistLogin(String loginName, String pass)
			throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		try {
			// Khởi tạo một TblUserDao
			TblUserDao userDaoImpl = new TblUserDaoImpl();
			// Khái báo một đối tượng TblUserEntity và gán bằng giá trị lấy về từ hàm
			// getTblUserByLoginName
			TblUserEntity user = userDaoImpl.getTblUserByLoginName(loginName);
			// Khởi tạo một String lấy giá trị trả về từ hàm enscryptPassword
			String passEnscrypt = Common.enscryptPassword(pass, user.getSalt());
			// Khởi tạo một biến boolean lấy giá trị trả về từ hàm ompareString
			boolean check = Common.compareString(passEnscrypt, user.getPass());
			// Trả về biến check
			return check;
		} catch (ClassNotFoundException | SQLException | NoSuchAlgorithmException e) {
			System.out.println("Error : TblUserLogicImpl.checkExistLogin " + e.getMessage());
			throw e;
		}

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
		// Khởi tạo đối tượng userDaoImpl
		TblUserDao userDaoImpl = new TblUserDaoImpl();
		// khai báo listUserInfo là danh sách các userinfo
		ArrayList<UserInfoEntity> listUserInfo = new ArrayList<UserInfoEntity>();
		try {
			// gán bằng giá trị trả về của hàm getListUser
			listUserInfo = userDaoImpl.getListUser(offset, limit, groupId, fullName, sortType, sortByFullName,
					sortByCodeLevel, sortByEndDate);
		} catch (ClassNotFoundException | SQLException e) {
			// Thông báo lỗi ở màn hình console
			System.out.println("Error: TblUserLogicImpl.getListUser " + e.getMessage());
			// Throw lỗi
			throw e;
		}
		return listUserInfo;
	}
	
	/**
	 * Đếm tổng số User tìm được
	 * @param groupId là nhóm được chọn trong selectbox
	 * @param fullName là tên tìm kiếm được nhập từ textbox
	 * @return trả về số bản ghi có trong bảng thỏa mãn điều kiện tìm kiếm
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Override
	public int getTotalUsers(int groupId, String fullName) throws ClassNotFoundException, SQLException {
		// khai báo số bản khi
		int countUser = 0;
		// khởi tạo đối tượng TblUserDaoImpl
		TblUserDao tblUserImpl = new TblUserDaoImpl();
		try {
			// Gọi đến hàm getTotalUsers ở lớp TblUserDaoImpl
			countUser = tblUserImpl.getTotalUsers(groupId, fullName);
		} catch (ClassNotFoundException | SQLException e) {
			// Thông báo lỗi ở màn hình console
			System.out.println("Error : TblUserLogicImpl.getTotalUsers " + e.getMessage());
			// Throw lỗi
			throw e;
		}

		return countUser;
	}
}
