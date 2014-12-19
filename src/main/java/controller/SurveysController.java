package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.JDInstanceService;
import service.Retrievable;
import dao.JDInstanceDAO;
import domain.SurveyInstance;
import domain.UserInstance;

/**
 * Servlet implementation class RegistrationController
 */
@WebServlet("/SurveysController")
public class SurveysController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SurveysController() {
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
		Retrievable ret = JDInstanceService.getInstance();
		String keyword = request.getParameter("keyword");
		if ((keyword != null)&&(!(keyword.trim()).equals(""))) {
			List<SurveyInstance> findedSurveys = ret.getSurveys(keyword);
			request.setAttribute("findedSurveys", findedSurveys);
		}
		request.getRequestDispatcher("/surveys.jsp").forward(request, response);

	}

}