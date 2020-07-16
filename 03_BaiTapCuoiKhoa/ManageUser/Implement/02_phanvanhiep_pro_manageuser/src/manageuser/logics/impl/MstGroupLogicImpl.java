/**
 * Copyright(C) 2020  Luvina Software
 * MstGroupLogicImpl.java, Jul 16, 2020 Phan Văn Hiệp
 */
package manageuser.logics.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import manageuser.dao.MstGroupDao;
import manageuser.dao.impl.MstGroupDaoImpl;
import manageuser.entities.MstGroupEntity;
import manageuser.logics.MstGroupLogic;

/**
 * Description của class là làm gì
 * 
 * @author Phan Van Hiep
 */
public class MstGroupLogicImpl implements MstGroupLogic {

	/**
	 * getAllMstGroup lấy tất cả các group 
	 * 
	 * @param
	 * @return list group trong bảng mst_group
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@Override
	public ArrayList<MstGroupEntity> getAllMstGroup() throws ClassNotFoundException, SQLException {
		// Khởi tạo listGroup để lưu tất cả các group lấy được
		ArrayList<MstGroupEntity> listGroup = new ArrayList<>();
		// Khởi tạo đối tượng mstGroupImpl
		MstGroupDao mstGroupImpl = new MstGroupDaoImpl();
		try {
			// gán giá trị listGroup bằng kết quả trả về từ hàm getAllMstGroup() ở lớp MstGroupDaoImpl
			listGroup = mstGroupImpl.getAllMstGroup();
		} catch (ClassNotFoundException | SQLException e) {
			// Thông báo lỗi ở màn hình console
			System.out.println("Error: MstGroupLogicImpl.getAllMstGroup " + e.getMessage());
			// Throw lỗi
			throw e;
		}
		return listGroup;
	}
}