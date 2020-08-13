/**
 * Copyright(C) 2020  Luvina Software
 * DeleteUserController.java, Aug 13, 2020 Phan Văn Hiệp
 */
package manageuser.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manageuser.logics.TblUserLogic;
import manageuser.logics.impl.TblUserLogicImpl;
import manageuser.utils.Common;
import manageuser.utils.Constant;

/**
 * Controller để xử lý cho khi click button delete
 * 
 * @author Phan Van Hiep
 */
public class DeleteUserController extends HttpServlet {

	/**
	 * Xử lý khi click vào button delete ở màn hình ADM005
	 * 
	 * @param req  request
	 * @param resp response
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// khai báo session
			HttpSession session = req.getSession();
			// Kiểm tra đăng nhập thành công
			if (Common.checkLogin(session)) {
				// khởi tạo đối tượng tblUserLogicImpl
				TblUserLogic tblUserLogicImpl = new TblUserLogicImpl();
				// Lấy id từ request
				int userId = Common.convertStringToInt(req.getParameter(Constant.REQUEST_ID), Constant.USER_ID_DEFAULT);
				// kiểm tra tồn tại user
				if (tblUserLogicImpl.checkExistUserById(userId)) {
					// gọi đến hàm deleteUser
					boolean checkDelete = tblUserLogicImpl.deleteUser(userId);
					// Nếu delete thành công
					if (checkDelete) {
						// gọi đến URL Success
						resp.sendRedirect(Constant.URL_SUCCESS + Constant.TYPE_DELETE_SUCCESS);
					} else {
						// Chuyển đến màn hình System_Error
						resp.sendRedirect(Constant.URL_SYSTEMERROR);
					}
				} else {
					// Thông báo lỗi không tồn tại Id
					resp.sendRedirect(Constant.URL_SYSTEMERROR + Constant.URL_ERROR_NOTEXIST_ID);
				}
			} else {
				// gọi đến URL login.do để về màn hình login
				resp.sendRedirect(Constant.URL_LOGIN);
			}
		} catch (Exception e) {
			// Hiển thị ở console lỗi
			System.out.println("Error : DeleteUserController.doGet " + e.getMessage());
			// Chuyển đến màn hình System_Error
			resp.sendRedirect(Constant.URL_SYSTEMERROR);
		}
	}
}
