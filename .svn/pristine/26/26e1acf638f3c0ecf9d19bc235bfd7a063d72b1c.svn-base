/**
 * Copyright(C) 2020  Luvina software
 * Login.java, Jun 16, 2020 HuyHDQ
 */
package login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Admin
 *
 */
public class Login extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String Uname = request.getParameter("Uname");
		String Upass = request.getParameter("Upass");
		String result = "";
		if (Uname.equals("Luvina") && Upass.equals("Luvina")) {
			result = "Welcome";
		} else {
			result = "Invalid username or password";
		}
		out.println("<html><head><title>Login Servlet</title></head><body>");
		out.println("<h1>" + result + "</h1>");
		out.println("</body></html>");
	}

}
