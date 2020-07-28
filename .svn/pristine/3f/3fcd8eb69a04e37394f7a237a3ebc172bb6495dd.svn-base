/**
 * Copyright(C) 2020  Luvina Software
 * MstGroupDaoImpl.java, Jul 16, 2020 Phan Văn Hiệp
 */
package manageuser.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import manageuser.dao.MstGroupDao;
import manageuser.entities.MstGroupEntity;

/**
 * Implement MstGroupDao để Xử lý Thao tác với DB bảng mst_group
 * 
 * @author Phan Van Hiep
 */
public class MstGroupDaoImpl extends BaseDaoImpl implements MstGroupDao {

	/**
	 * getAllMstGroup lấy tất cả các group trong bảng mst_group
	 * 
	 * @param
	 * @return list group trong bảng mst_group
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@Override
	public ArrayList<MstGroupEntity> getAllMstGroup() throws ClassNotFoundException, SQLException {
		// Khai báo listGroup để chứa các đối tượng MstGroupEntity lấy được
		ArrayList<MstGroupEntity> listGroup = new ArrayList<MstGroupEntity>();
		try {
			// Mở kết nối với DB
			openConnection();
			// Nếu mở thành công kết nối
			if (conn != null) {
				// Câu truy vấn DB
				String sql = "SELECT group_id, group_name FROM mst_group ORDER BY group_id ASC";
				// Gọi đến prepareStatement truyền vào câu lệnh SQL
				pstm = conn.prepareStatement(sql);
				// Khởi tạo ResultSet để chứa kết quả từ executeQuery()
				ResultSet rs = pstm.executeQuery();
				// Chạy từng kết quả của ResultSet
				while (rs.next()) {
					// Khởi tạo đối tượng MstGroupEntity
					MstGroupEntity group = new MstGroupEntity();
					// Gán giá trị cho đối tượng group từ kết quả lấy từ ResultSet
					group.setGroupId(rs.getInt("group_id"));
					group.setGroupName(rs.getString("group_name"));
					// Thêm vào list group
					listGroup.add(group);
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			// Thông báo lỗi ở màn hình console
			System.out.println("Error: MstGroupDaoImpl.getAllMstGroup " + e.getMessage());
			// Throw lỗi
			throw e;
		} finally {
			// đóng kết nối
			closeConnection();
		}
		return listGroup;
	}

}
