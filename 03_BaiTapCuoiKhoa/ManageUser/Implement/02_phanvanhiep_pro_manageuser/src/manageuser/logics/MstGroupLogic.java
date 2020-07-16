/**
 * Copyright(C) 2020  Luvina Software
 * MstGroupLogic.java, Jul 16, 2020 Phan Văn Hiệp
 */
package manageuser.logics;

import java.sql.SQLException;
import java.util.ArrayList;

import manageuser.entities.MstGroupEntity;

/**
 * Description của class là làm gì 
 * @author Phan Van Hiep
 */
public interface MstGroupLogic{
	
	/**
	 * getAllMstGroup lấy tất cả các group trong bảng mst_group
	 * 
	 * @param
	 * @return list group trong bảng mst_group
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	ArrayList<MstGroupEntity> getAllMstGroup() throws ClassNotFoundException, SQLException;
}
