package controller;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.JDInstanceService;

import com.mysql.jdbc.Connection;

import domain.UserInstance;

public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection conn;
	ResultSet res;

	public LoginController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String inputUsername = request.getParameter("inputUsername");
		String inputPassword = request.getParameter("inputPassword");

		HttpSession session = request.getSession(false);
		if (session != null)
			session.setAttribute("inputUsername", inputUsername);
		
		UserInstance user = new UserInstance();
		
		user.setLogin(inputUsername);
		user.setPassword(inputPassword.toCharArray());
		
		JDInstanceService service = new JDInstanceService();
		
		if (service.validateUser(user)){
			request.getRequestDispatcher("/surveys-published.jsp").forward(request,
					response);
		}

		else{
			request.setAttribute("errorMessage", "Incorrect login or password");
			request.getRequestDispatcher("/index.jsp").forward(request,
					response);
		}		
	}
}
