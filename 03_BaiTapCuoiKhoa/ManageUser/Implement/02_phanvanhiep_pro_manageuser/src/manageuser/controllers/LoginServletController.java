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

import manageuser.validates.ValidateUser;

/**
 * Description 
@author Phan Van Hiep
 */
public class LoginServletController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String loginName = req.getParameter("loginId");
		String pass = req.getParameter("password");
		try {
			List<String> listErr = ValidateUser.validateLogin(loginName, pass);
			if(listErr == null) {
				resp.sendRedirect("/Views/jsp/ADM002.html");
			}else {
				req.setAttribute("listErr", listErr);
				req.getRequestDispatcher("/Views/jsp/ADM001.jsp").forward(req, resp);
			}
		} catch (ClassNotFoundException | NoSuchAlgorithmException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
}}
