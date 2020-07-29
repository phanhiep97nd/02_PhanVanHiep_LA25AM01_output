/**
 * Copyright(C) 2020  Luvina Software
AddUserConfirmController.java, Jul 29, 2020 Phan Van Hiep
 */
package manageuser.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manageuser.utils.Constant;

/**
 * Controller xử lí hiển thị thông tin để confirm add
@author Phan Van Hiep
 */
public class AddUserConfirmController extends HttpServlet{
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getServletContext().getRequestDispatcher(Constant.PATH_ADM004).forward(req, resp);
	}
}
