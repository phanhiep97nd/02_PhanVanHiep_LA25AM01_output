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
		String loginName = req.getParameter("loginId");
		String pass = req.getParameter("password");
		try {
			List<String> listErr = ValidateUser.validateLogin(loginName, pass);
			HttpSession session = req.getSession();
			if (listErr.size() == 0) {
				resp.sendRedirect("listUser.do");
				// resp.sendRedirect("./Views/jsp/ADM002.jsp");
				// req.getRequestDispatcher("./Views/jsp/ADM002.html").forward(req, resp);
				session.setAttribute("loginName", loginName);
			} else {
				req.setAttribute("listErr", listErr);
				req.setAttribute("loginName", loginName);
				req.getServletContext().getRequestDispatcher(Constant.PATH_ADM001).forward(req, resp);
			}
		} catch (ClassNotFoundException | NoSuchAlgorithmException | SQLException e) {
			System.out.println("Error : LoginServletController.doPost " + e.getMessage());
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
			req.getServletContext().getRequestDispatcher(Constant.PATH_ADM001).forward(req, resp);
		} catch (Exception e) {
			System.out.println("Error : LoginServletController.doPost " + e.getMessage());
			resp.sendRedirect(Constant.PATH_SYSTEM_ERROR);
		}

	}
}
