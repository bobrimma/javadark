package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.JDInstanceDAO;
import domain.UserInstance;

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

	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		UserInstance user = new UserInstance();

		String firstname = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");

		// Checking data entered in 'First name' field
		if (firstname.equals("")) {
			request.setAttribute("errorMessage",
					"Field 'First name' can't be empty");
			request.getRequestDispatcher("/registration.jsp").forward(request,
					response);
		}
	

		// Checking data entered in 'Last name' field
		if (lastName.equals("")) {
			request.setAttribute("errorMessage",
					"Field 'Last name' can't be empty");
			request.getRequestDispatcher("/registration.jsp").forward(request,
					response);
		}

		// Checking data entered in 'E-mail' field
		if (email.equals("")) {
			request.setAttribute("errorMessage",
					"Field 'E-mail' can't be empty");
			request.getRequestDispatcher("/registration.jsp").forward(request,
					response);
		}
		// if entered data is an e-mail
		else if (!email.matches("\\w+@\\w+\\.\\w+")) {
			request.setAttribute("errorMessage",
					"Please enter correct e-mail address");
			request.getRequestDispatcher("/registration.jsp").forward(request,
					response);
		}

		// Checking data entered in 'Username' field
		if (username.equals("")) {
			request.setAttribute("errorMessage",
					"Field 'Username' can't be empty");
			request.getRequestDispatcher("/registration.jsp").forward(request,
					response);
		}

		// Checking data entered in "Passwords" fields
		if (password1.equals("")) {
			request.setAttribute("errorMessage",
					"Field 'Password' can't be empty");
			request.getRequestDispatcher("/registration.jsp").forward(request,
					response);
		} else if (password1.length() < 8) {
			request.setAttribute("errorMessage",
					"Password must be at least 8 charactes");
			request.getRequestDispatcher("/registration.jsp").forward(request,
					response);
		}
		// If message contains "space"
		else if (password1.matches("\\w*\\s+\\w*")) {
			request.setAttribute("errorMessage",
					"Password cannot contain space");
			request.getRequestDispatcher("/registration.jsp").forward(request,
					response);
		} else if (!password1.equals(password2)) {
			request.setAttribute("errorMessage", "Entered passwords not match");
			request.getRequestDispatcher("/registration.jsp").forward(request,
					response);
		}

		// Adding data to User
		user.setFirstName(firstname);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setLogin(username);
		user.setPassword(password1.toCharArray());

		// Adding data to DB and forward to index.jsp
		JDInstanceDAO.saveIntoDB(user);
		request.setAttribute("submitMessage",
				"Congratulations your registration was successful!");
		request.getRequestDispatcher("/index.jsp").forward(request, response);

	}

}