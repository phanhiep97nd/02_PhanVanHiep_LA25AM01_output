/**
 * Copyright(C) 2020  Luvina Software
 * SuccessController.java, Aug 4, 2020 Phan Văn Hiệp
 */
package manageuser.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manageuser.utils.Common;
import manageuser.utils.Constant;

/**
 * Xử lí khi thực hiện add hoặc edit thành công
 * 
 * @author Phan Van Hiep
 */
public class SuccessController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String type = req.getParameter(Constant.REQUEST_TYPE);
		String message = "";
		if (Common.compareString(type, Constant.TYPE_ADD_SUCCESS)) {
			message = Constant.MSG001;
		}
		req.setAttribute("message", message);
		req.getServletContext().getRequestDispatcher(Constant.PATH_ADM006).forward(req, resp);
	}
}
