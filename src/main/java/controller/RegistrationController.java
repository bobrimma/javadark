package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.JDInstanceService;
import domain.UserInstance;

/**
 * Servlet implementation class RegistrationController
 */
//Юля, извини, за то что затер твою работу. Возобновил как было.
public class RegistrationController extends HttpServlet {
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
	    JDInstanceService service = JDInstanceService.getInstance();

		UserInstance user = new UserInstance();

		String firstname = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password1 = request.getParameter("password1");

		// Checking data entered in 'First name' field
		if (firstname.matches("\\s+")) {
			firstname = "anonym";
		}

		// Checking data entered in 'Last name' field
		if (lastName.matches("\\s+")) {
			lastName = "anonym";
		}

		// Checking data entered in 'E-mail' field
		if (email.matches("\\s+")) {
			request.setAttribute("errorMessage",
					"Field 'E-mail' can't be empty");
			request.getRequestDispatcher("/registration.jsp").forward(request,
					response);
			return;
		}
		// if entered data is an e-mail
		else if (!email.matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")) {
			request.setAttribute("errorMessage",
					"Please enter correct e-mail address");
			request.getRequestDispatcher("/registration.jsp").forward(request,
					response);
			return;
		}

		// Checking data entered in 'Username' field
		if (username.matches("\\s+")) {
			request.setAttribute("errorMessage",
					"Field 'Username' can't be empty");
			request.getRequestDispatcher("/registration.jsp").forward(request,
					response);
			return;
		}

		// Checking data entered in "Passwords" fields
		if (password1.matches("\\s+")) {
			request.setAttribute("errorMessage",
					"Field 'Password' can't be empty");
			request.getRequestDispatcher("/registration.jsp").forward(request,
					response);
			return;
		} else if (password1.length() < 8) {
			request.setAttribute("errorMessage",
					"Password must be at least 8 charactes");
			request.getRequestDispatcher("/registration.jsp").forward(request,
					response);
			return;
		}
		// If message contains "space"
		else if (password1.contains("\\s+")) {
			request.setAttribute("errorMessage",
					"Password cannot contain space");
			request.getRequestDispatcher("/registration.jsp").forward(request,
					response);
			return;
		}
		List<UserInstance> uMail = service.getUsers(null, null, null, email);
		List<UserInstance> uLog = service.getUsers(username, null, null, null);
		if (null != uMail && !uMail.isEmpty() && uMail.get(0) != null) {

			request.setAttribute("errorMessage",
					"This e-mail is already exist. Please choose another.");
			request.getRequestDispatcher("/registration.jsp").forward(request,
					response);
			return;

		} else if (null != uLog && !uLog.isEmpty() && uLog.get(0) != null) {

			request.setAttribute("errorMessage",
					"This Username is already exist. Please choose another.");
			request.getRequestDispatcher("/registration.jsp").forward(request,
					response);
			return;

		}

		// Adding data to User
		user.setFirstName(firstname);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setLogin(username);
		user.setPassword(password1.toCharArray());

		// Adding data to DB and forward to index.jsp
		if (JDInstanceService.getInstance().saveInstance(user))
		{
        		request.setAttribute("submitMessage",
        				"Congratulations your registration was successful!");
        		request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
		else
		{
		    request.setAttribute("errorMessage",
				"Registration failed");
		request.getRequestDispatcher("/registration.jsp").forward(request, response);
		}

	}

}