/**
 * Copyright(C) 2020  Luvina Software
Abc.java, Jul 13, 2020 Phan Van Hiep
 */
package manageuser.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manageuser.utils.Common;
import manageuser.utils.Constant;

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
			// Nếu checkLogin trả về false (Chưa đăng nhập)
			if (!Common.checkLogin(session)) {
				// gọi đến URL login.do để về màn hình login
				resp.sendRedirect(Constant.URL_LOGIN);
				// Ngược lại nếu checkLogin trả về true (Đã đăng nhập thành công)
			} else {
				// Chuyển hướng sang trang ADM002
				req.getServletContext().getRequestDispatcher(Constant.PATH_ADM002).forward(req, resp);
			}
		} catch (Exception e) {
			// Hiển thị ở console lỗi
			System.out.println("Error : ListUserServletController.doPost " + e.getMessage());
			// Chuyển đến màn hình System_Error
			resp.sendRedirect(Constant.PATH_SYSTEM_ERROR);
		}

	}
}
