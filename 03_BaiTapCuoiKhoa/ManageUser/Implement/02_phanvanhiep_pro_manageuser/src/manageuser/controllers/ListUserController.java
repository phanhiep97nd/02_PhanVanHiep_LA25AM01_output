/**
 * Copyright(C) 2020  Luvina Software
Abc.java, Jul 13, 2020 Phan Van Hiep
 */
package manageuser.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manageuser.entities.MstGroupEntity;
import manageuser.entities.UserInfoEntity;
import manageuser.logics.MstGroupLogic;
import manageuser.logics.TblUserLogic;
import manageuser.logics.impl.MstGroupLogicImpl;
import manageuser.logics.impl.TblUserLogicImpl;
import manageuser.utils.Common;
import manageuser.utils.ConfigProperties;
import manageuser.utils.Constant;
import manageuser.utils.MessageProperties;

/**
 * Controller để xử lý cho màn hình ADM002
 * 
 * @author Phan Van Hiep
 */
public class ListUserController extends HttpServlet {

	/**
	 * Xử lí khi đăng nhập thành công
	 * 
	 * @param req  request
	 * @param resp response
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// Khởi tạo session từ request
			HttpSession session = req.getSession();

			// Khởi tạo đối tượng groupLogicImpl
			MstGroupLogic groupLogicImpl = new MstGroupLogicImpl();
			// khởi tạo đối tượng userLogicImpl
			TblUserLogic userLogicImpl = new TblUserLogicImpl();

			// Khởi tạo listUserInfo để chứa kết qẩu trả về hàm
			ArrayList<UserInfoEntity> listUserInfo = new ArrayList<UserInfoEntity>();

			// khai báo giá trị default cho các biến
			int limit = Integer.parseInt(ConfigProperties.getValueByKey(Constant.LIMIT));
			int offset = 0;
			int groupId = 0;
			String fullName = "";
			String sortType = "";
			String sortByFullName = Constant.ASC;
			String sortByCodeLevel = Constant.ASC;
			String sortByEndDate = Constant.DESC;

			// Nếu checkLogin trả về true (Đã đăng nhập)
			if (Common.checkLogin(session)) {
				// Khởi tạo listUser chứa kết quả trả về hàm getAllMstGroup()
				ArrayList<MstGroupEntity> listGroup = groupLogicImpl.getAllMstGroup();
				// gán danh sách group lên request
				req.setAttribute("listGroup", listGroup);
				// Lấy giá trị của type trên action
				String type = req.getParameter("type");
				if ("default".equals(type)) {
				}
				if ("search".equals(type)) {
					// Lấy fullNam và groupId người dùng nhập ở request
					fullName = req.getParameter("name");
					groupId = Integer.parseInt(req.getParameter("group_id"));
				}

				// Gán kết quả trả về hàm getListUser vào listUserInfo
				listUserInfo = userLogicImpl.getListUser(offset, limit, groupId, fullName, sortType, sortByFullName,
						sortByCodeLevel, sortByEndDate);
				// Nếu không tìm thấy user nào thì in ra câu thông báo, ngược lại nếu có kết quả
				// thì gửi lên trang jsp
				if (listUserInfo.size() == 0) {
					String notiMSG005 = MessageProperties.getValueByKey(Constant.MSG005);
					req.setAttribute("notiMSG005", notiMSG005);
				} else {
					// gán danh sách userInfor lên request
					req.setAttribute("listUserInfo", listUserInfo);
				}

				// truyền fullname tìm kiếm vào request
				req.setAttribute("fullName", fullName);
				// truyền groupID tìm kiếm vào request
				req.setAttribute("groupId", groupId);
				// Chuyển hướng sang trang ADM002
				req.getServletContext().getRequestDispatcher(Constant.PATH_ADM002).forward(req, resp);
			} else {
				// gọi đến URL login.do để về màn hình login
				resp.sendRedirect(Constant.URL_LOGIN);
			}
		} catch (Exception e) {
			// Hiển thị ở console lỗi
			System.out.println("Error : ListUserServletController.doPost " + e.getMessage());
			// Chuyển đến màn hình System_Error
			resp.sendRedirect(Constant.PATH_SYSTEM_ERROR);
		}

	}
}
