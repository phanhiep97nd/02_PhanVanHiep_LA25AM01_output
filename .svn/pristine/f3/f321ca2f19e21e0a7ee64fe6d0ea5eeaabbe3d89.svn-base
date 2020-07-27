/**
 * Copyright(C) 2020  Luvina Software
 * SystemErrorController.java, Jul 26, 2020 Phan Văn Hiệp
 */
package manageuser.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manageuser.utils.Constant;
import manageuser.utils.MessageProperties;

/**
 * Xử lí khi có lỗi
 * @author Phan Van Hiep
 */
public class SystemErrorController extends HttpServlet{
	/**
	 * Xử lí khi có lỗi
	 * 
	 * @param req  request
	 * @param resp response
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String ER015 = MessageProperties.getValueByKey(Constant.ER015);
		req.setAttribute("ER015", ER015);
		req.getServletContext().getRequestDispatcher(Constant.PATH_SYSTEM_ERROR).forward(req, resp);
	}
}
