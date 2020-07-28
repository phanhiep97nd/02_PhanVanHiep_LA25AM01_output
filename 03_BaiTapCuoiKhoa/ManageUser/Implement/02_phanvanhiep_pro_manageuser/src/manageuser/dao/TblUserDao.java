/**
 * Copyright(C) 2020  Luvina Software
TblUserDao.java, Jul 13, 2020 Phan Van Hiep
 */
package manageuser.dao;

import java.awt.List;
import java.sql.SQLException;
import java.util.ArrayList;

import manageuser.entities.TblUserEntity;
import manageuser.entities.UserInfoEntity;

/**
 * Interface Xử lý thao tác với DB Thao tác với DB bảng tbl_user
 * 
 * @author Phan Van Hiep
 */
public interface TblUserDao extends BaseDao {
	/**
	 * Lấy ra user trong bảng tbl_user bằng loginName
	 * 
	 * @param loginName loginName người dùng nhập vào
	 * @return trả về một user tìm được trong DB
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	TblUserEntity getTblUserByLoginName(String loginName) throws SQLException, ClassNotFoundException;

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
	ArrayList<UserInfoEntity> getListUser(int offset, int limit, int groupId, String fullName, String sortType,
			String sortByFullName, String sortByCodeLevel, String sortByEndDate)
			throws ClassNotFoundException, SQLException;

	/**
	 * Đếm tổng số bảng ghi kết quả tìm được
	 * 
	 * @param groupId  là nhóm được chọn trong selectbox
	 * @param fullName là tên tìm kiếm được nhập từ textbox
	 * @return trả về số bản ghi tìm được theo điều kiện tìm kiếm
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	int getTotalUsers(int groupId, String fullName) throws ClassNotFoundException, SQLException;

}
