/**
 * Copyright(C) 2020  Luvina Software
EditUserConfirmController.java, Aug 11, 2020 Phan Van Hiep
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
 * Controller xử lí hiển thị thông tin để confirm edit
 * 
 * @author Phan Van Hiep
 */
public class EditUserConfirmController extends HttpServlet {
	/*
	 * xử lí khi nhấn xác nhận ở màn hình ADM003
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// khai báo session
			HttpSession session = req.getSession();
			// nếu từ màn ADM003 click xác nhận(Sesion confirmAdDM003 khác null)
			if (session.getAttribute(Constant.SESSION_CONFIRM_ADM003) != null) {
				// Xóa session confirmAdDM003
				session.removeAttribute(Constant.SESSION_CONFIRM_ADM003);
				// Lấy key từ req
				String key = req.getParameter(Constant.REQUEST_KEY);
				// lấy userInfoEntity từ session
				UserInfoEntity userInfoEntity = (UserInfoEntity) session
						.getAttribute(Constant.SESSION_EDITUSER_CONFIRM + key);
				// truyền userInfoEntity vào request
				req.setAttribute(Constant.REQUEST_USERINFORENTITY, userInfoEntity);
				// truyền key lên req
				req.setAttribute(Constant.REQUEST_KEY, key);
				// forward
				req.getServletContext().getRequestDispatcher(Constant.PATH_ADM004).forward(req, resp);

				// không phải là từ click xác nhận ở màn hình ADM003
			} else {
				// hiển thị ra màn hình ADM002
				resp.sendRedirect(Constant.URL_SYSTEMERROR);
			}
		} catch (Exception e) {
			// Hiển thị ở console lỗi
			System.out.println("Error : AddUserConfirmController.doGet " + e.getMessage());
			// Chuyển đến màn hình System_Error
			resp.sendRedirect(Constant.URL_SYSTEMERROR);
		}
	}
	
	/* Xử lí khi nhấn OK ở màn hình ADM004
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}