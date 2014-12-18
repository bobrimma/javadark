package main.java.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.dao.JDInstanceDAO;
import main.java.domain.UserInstance;



/**
 * Servlet implementation class RegistrationController
 */
@WebServlet("/RegistrationController")
public class RegistrationController extends AbstractServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistrationController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		//String action = request.getParameter("action");
		UserInstance user = new UserInstance();
		/*
		 * if (action == null) {
		 * request.getRequestDispatcher("/index.jsp").forward(request,
		 * response); } else if (action.equals("doregister")) {
		 */
		user.setFirstName(request.getParameter("firstName"));
		user.setLastName(request.getParameter("lastName"));
		user.setEmail(request.getParameter("email"));
		user.setLogin(request.getParameter("username"));

		if (request.getParameter("password1").equals(
				request.getParameter("password2"))) {
			user.setPassword(request.getParameter("password1").toCharArray());
		}

		JDInstanceDAO.saveIntoDB(user);
		request.getRequestDispatcher("/index.jsp").forward(
				request, response);

	}

}