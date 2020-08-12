/**
 * Copyright(C) 2020  Luvina Software
 * HelloWord.java, Jun 16, 2020 Phan Van Hiep
 */
package hello;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description:  
 * @author Phan Van Hiep
 */
public class HelloWord extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html");
		
		PrintWriter out=response.getWriter();
		out.println("<html><head><title>Hello Servlet</title></head><body>");
		out.println("<h1>Hello Servlet</h1>");
		out.println("<p>Welcome to JSP course</p>");
		out.println("</body></html>");}

}
