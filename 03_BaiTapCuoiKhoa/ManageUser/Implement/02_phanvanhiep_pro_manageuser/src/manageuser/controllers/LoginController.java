/**
 * Copyright(C) 2020  Luvina Software
Abc.java, Jul 13, 2020 Phan Van Hiep
 */
package manageuser.controllers;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manageuser.utils.Constant;
import manageuser.validates.ValidateUser;

/**
 * Controller để xử lý cho màn hình ADM001
 * 
 * @author Phan Van Hiep
 */
public class LoginController extends HttpServlet {

	/**
	 * Xử lí khi người dùng click button đăng nhập
	 * 
	 * @param req  request
	 * @param resp response
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Lấy giá trị từ request mà người dùng nhập
		String loginName = req.getParameter("loginId");
		String pass = req.getParameter("password");
		try {
			// Khai báo 1 list để lấy kết quả trả về từ hàm validateLogin
			List<String> listErr = ValidateUser.validateLogin(loginName, pass);
			// Khởi tạo session
			HttpSession session = req.getSession();
			// Nếu danh sách lỗi rỗng(Tồn tại loginName và pass trong DB)
			if (listErr.size() == 0) {
				// chuyển hướng đến URL listuser.do
				resp.sendRedirect(Constant.URL_LISTUSER + Constant.URL_TYPE_DEFAULT);
				// gán loginName lên session
				session.setAttribute("loginName", loginName);
				// Nếu có lỗi xảy ra
			} else {
				// gán danh sách lỗi lên Request
				req.setAttribute("listErr", listErr);
				// gán loginName lên request để giữ lại giá trị loginName trên textbox
				req.setAttribute("loginName", loginName);
				// forward đến trang ADM001.jsp
				req.getServletContext().getRequestDispatcher(Constant.PATH_ADM001).forward(req, resp);
			}
			// Nếu có lỗi
		} catch (Exception e) {
			// Hiển thị ở console lỗi
			System.out.println("Error : LoginServletController.doPost " + e.getMessage());
			// Chuyển đến màn hình System_Error
			resp.sendRedirect(Constant.PATH_SYSTEM_ERROR);
		}
	}

	/**
	 * Xử lí khi người dùng vào web và khi logout
	 * 
	 * @param req  request
	 * @param resp response
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// Di chuyển về màn hình ADM001
			req.getServletContext().getRequestDispatcher(Constant.PATH_ADM001).forward(req, resp);
			// Nếu có lỗi
		} catch (Exception e) {
			// Hiển thị lỗi
			System.out.println("Error : LoginServletController.doGet " + e.getMessage());
			// Chuyển đến màn hình System_Error
			resp.sendRedirect(Constant.PATH_SYSTEM_ERROR);
		}

	}
}
