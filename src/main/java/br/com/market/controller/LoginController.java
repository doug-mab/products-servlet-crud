package br.com.market.controller;

import java.io.IOException;
import java.security.InvalidParameterException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.market.model.User;
import br.com.market.service.AuthenticationService;
import br.com.market.service.DataBase;
import br.com.market.service.Session;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Session<User> db = DataBase.connect("users");

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("WEB-INF/view/loginForm.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		User loggedUser = null;

		if (username.isBlank() || username == null || password.isBlank() || password == null) {
			req.getSession().setAttribute("exceptionMessage", "Invalid credentials.");
			resp.sendRedirect(req.getHeader("Referer"));
		}

		if (action.equals("register")) {
			try {
				if (db.findFirstWhere(user -> user.getUsername().equals(username)) != null)
					throw new InvalidParameterException("This user already exists.");
				loggedUser = new User(username, password);
				db.create(loggedUser);

				req.getSession().removeAttribute("exceptionMessage");
			} catch (InvalidParameterException e) {
				req.getSession().setAttribute("exceptionMessage", e.getMessage());
				resp.sendRedirect(req.getHeader("Referer"));
			}
		} else if (action.equals("login")) {
			try {
				AuthenticationService auth = new AuthenticationService();
				if (!auth.isUserValid(username, password))
					throw new InvalidParameterException("Invalid credentials.");

				loggedUser = auth.getLastValidatedUser();

				req.getSession().removeAttribute("exceptionMessage");
			} catch (InvalidParameterException e) {
				req.getSession().setAttribute("exceptionMessage", e.getMessage());
				resp.sendRedirect(req.getHeader("Referer"));
			}
		}

		if (loggedUser != null) {
			System.out.println("Logged User: " + loggedUser.getUsername());
			req.getSession().setAttribute("loggedUser", loggedUser);
			resp.sendRedirect("product?action=list");
		}
	}

}
