/**
 * Copyright(C) 2020  Luvina Software
 * ShowUserController.java, Jul 26, 2020 Phan Văn Hiệp
 */
package manageuser.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manageuser.utils.Constant;

/**
 * Hiển thị thông tin chi tiết User
 * @author Phan Van Hiep
 */
public class ShowUserController extends HttpServlet{
	/**
	 * Xử lí phương thức doGet
	 * 
	 * @param req  request
	 * @param resp response
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Chuyển hướng sang trang ADM002
		req.getServletContext().getRequestDispatcher(Constant.PATH_ADM005).forward(req, resp);
	}
}
