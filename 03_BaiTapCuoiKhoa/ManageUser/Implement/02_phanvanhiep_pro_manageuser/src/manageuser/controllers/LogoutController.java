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

import manageuser.utils.Constant;

/**
 * Controller để xử lý Logout
 * 
 * @author Phan Van Hiep
 */
public class LogoutController extends HttpServlet {

	/**
	 * Xử lí khi người dùng click link logout
	 * 
	 * @param req  request
	 * @param resp response
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// Khai báo và khởi tạo session từ request
			HttpSession session = req.getSession();
			// Hủy session
			session.invalidate();
			// chuyển hướng về trang login
			resp.sendRedirect("login.do");
		} catch (Exception e) {
			System.out.println("Error : LoginServletController.doGet " + e.getMessage());
			resp.sendRedirect(Constant.PATH_SYSTEM_ERROR);
		}
	}
}
