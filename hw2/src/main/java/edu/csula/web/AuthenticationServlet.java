package edu.csula.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.csula.storage.servlet.UsersDAOImpl;
import edu.csula.storage.UsersDAO;
import edu.csula.models.User;

@WebServlet("/admin/auth")
public class AuthenticationServlet extends HttpServlet {
	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String html = "<link rel='stylesheet' type='text/css\' href='" + request.getContextPath() + "/app.css' />";
		html += "<h1>Incremental Game Framework</h1>";
		html += "<form method='POST'>";
		html += "<label for='username'>Username:</label><br>";
		html += "<input name='username' id='username' type='text' /><br>";
		html += "<label for='password'>Password:</label><br>";
		html += "<input name='password' id='password' type='text' /><br>";
		html += "<button>Log in</button></form>";

		out.println(html);
	}

	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO: handle login
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		UsersDAO dao = new UsersDAOImpl(request.getSession());

		if (dao.authenticate(username, password)) {
			response.sendRedirect("generators");
		} else {	
			response.sendRedirect("auth");

		}

	}

    @Override
    public void doDelete( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: handle logout
        UsersDAO dao = new UsersDAOImpl(request.getSession());

        dao.logout();
    }
}
