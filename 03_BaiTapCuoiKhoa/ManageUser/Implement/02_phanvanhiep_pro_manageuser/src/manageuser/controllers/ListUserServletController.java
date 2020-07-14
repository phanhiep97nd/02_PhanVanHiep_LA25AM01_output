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
 * Controller để xử lý cho màn hình ADM002
 * 
 * @author Phan Van Hiep
 */
public class ListUserServletController extends HttpServlet {

	/**
	 * Xử lí khi đăng nhập thành công
	 * 
	 * @param req  request
	 * @param resp response
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			HttpSession session = req.getSession();
			req.getServletContext().getRequestDispatcher(Constant.PATH_ADM002).forward(req, resp);
			// resp.sendRedirect("./Views/jsp/ADM002.jsp");
		} catch (Exception e) {
			System.out.println("Error : ListUserServletController.doPost " + e.getMessage());
			resp.sendRedirect(Constant.PATH_SYSTEM_ERROR);
		}

	}
}
