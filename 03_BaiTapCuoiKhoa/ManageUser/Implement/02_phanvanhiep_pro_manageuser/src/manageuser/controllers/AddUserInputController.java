/**
 * Copyright(C) 2020  Luvina Software
 * AddUserController.java, Jul 27, 2020 Phan Văn Hiệp
 */
package manageuser.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manageuser.utils.Constant;

/**
 * Controller để xử lý cho màn hình ADM003 truong hợp Add
 * @author Phan Van Hiep
 */
public class AddUserInputController extends HttpServlet{
	/**
	 * Xử lý khi click vào button Add của ADM002
	 * 
	 * @param req  request
	 * @param resp response
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getServletContext().getRequestDispatcher(Constant.PATH_ADM003).forward(req, resp);
	}
}
