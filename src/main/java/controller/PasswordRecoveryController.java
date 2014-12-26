package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import domain.UserInstance;
import service.JDInstanceService;
import utils.StringUtilities;

/**
 * Servlet implementation class PasswordRecoveryController
 * @author Kapitoha
 */

public class PasswordRecoveryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PasswordRecoveryController() 
    {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
	    String email = request.getParameter("email");
	    JDInstanceService jis = JDInstanceService.getInstance();
	    UserInstance user = null;
	    String newPass = null;
	    List<UserInstance>uList = jis.getUsers(null, null, null, email);
	    if (!uList.isEmpty())
	    {
		user = uList.get(0);
		newPass = StringUtilities.randomString(10);
		user.setPassword(newPass.toCharArray());
	    }
	    if (null != user && jis.updateInstance(user))
	    {
		jis.sendEmail(user.getEmail(), "Password recovety", "Your password was changed to: " + newPass, null);
	    }
//	    response.getOutputStream().print("New password will send on your email if it valid");
	    request.setAttribute("submitMessage",
		    "New password will send on your email if it valid");
	    request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

}
