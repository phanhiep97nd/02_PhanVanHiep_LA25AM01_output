/**
 * Copyright(C) 2020  Luvina Software
 * MstJapanLogicImpl.java, Jul 28, 2020 Phan Văn Hiệp
 */
package manageuser.logics.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import manageuser.dao.MstGroupDao;
import manageuser.dao.MstJapanDao;
import manageuser.dao.impl.MstGroupDaoImpl;
import manageuser.dao.impl.MstJapanDaoImpl;
import manageuser.entities.MstGroupEntity;
import manageuser.entities.MstJapanEntity;
import manageuser.logics.MstJapanLogic;

/**
 * Implement MstJapanLogic để Xử lý logic cho các chức năng liên quan đến
 * mst_japan
 * 
 * @author Phan Van Hiep
 */
public class MstJapanLogicImpl implements MstJapanLogic {

	/**
	 * Lấy tất cả các nhóm trong bảng mst_japan
	 * 
	 * @return trả về danh sách tất cả các nhóm trong bảng mst_japan
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@Override
	public List<MstJapanEntity> getAllMstJapan() throws ClassNotFoundException, SQLException {
		// Khởi tạo listMstJapan để lưu tất cả các group lấy được
		List<MstJapanEntity> listMstJapan = new ArrayList<>();
		// Khởi tạo đối tượng mstJapanImpl
		MstJapanDao mstJapanImpl = new MstJapanDaoImpl();
		try {
			// gán giá trị listMstJapan bằng kết quả trả về từ hàm getAllMstJapan() ở lớp
			// MstJapanDaoImpl
			listMstJapan = mstJapanImpl.getAllMstJapan();
		} catch (ClassNotFoundException | SQLException e) {
			// Thông báo lỗi ở màn hình console
			System.out.println("Error: MstJapanLogicImpl.getAllMstJapan " + e.getMessage());
			// Throw lỗi
			throw e;
		}
		return listMstJapan;
	}

}