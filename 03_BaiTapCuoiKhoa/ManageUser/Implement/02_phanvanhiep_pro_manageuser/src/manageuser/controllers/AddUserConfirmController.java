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
import javax.servlet.http.HttpSession;

import manageuser.entities.UserInfoEntity;
import manageuser.utils.Constant;

/**
 * Controller xử lí hiển thị thông tin để confirm add
 * 
 * @author Phan Van Hiep
 */
public class AddUserConfirmController extends HttpServlet {
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// khai báo session
			HttpSession session = req.getSession();

			// lấy userInfoEntity từ session
			UserInfoEntity userInfoEntity = (UserInfoEntity) session.getAttribute(Constant.SESSION_ADDUSER_CONFIRM);

			// truyền userInfoEntity vào request
			req.setAttribute(Constant.REQUEST_USERINFORENTITY, userInfoEntity);
		} catch (Exception e) {
			// Hiển thị ở console lỗi
			System.out.println("Error : AddUserConfirmController.doGet " + e.getMessage());
			// Chuyển đến màn hình System_Error
			resp.sendRedirect(Constant.URL_SYSTEMERROR);
		}
		req.getServletContext().getRequestDispatcher(Constant.PATH_ADM004).forward(req, resp);
	}
}
