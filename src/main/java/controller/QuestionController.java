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
import domain.QuestionInstance;
import domain.SurveyInstance;
import domain.UserInstance;

/**
 * Servlet implementation class RegistrationController
 */
@WebServlet("/QuestionController")
public class QuestionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QuestionController() {
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
		String formName = request.getParameter("formname");
		if (formName.equals("newquestion")) {
			createQuestion(request, response);
		} 
		request.getRequestDispatcher("/newquestion.jsp").forward(request, response);

	}
	protected void createQuestion(HttpServletRequest request,
			HttpServletResponse response) {
		Retrievable ret = JDInstanceService.getInstance();
		SurveyInstance surv=ret.getSurvey(Integer.parseInt(request.getParameter("survId")));
		QuestionInstance question = new QuestionInstance(surv);
		question.setName(request.getParameter("name"));
		question.setDescription(request.getParameter("description"));
		boolean isMultianswer=false;
		if(request.getParameter("questiontype").equals("multi")){
			isMultianswer=true;
			question.isAllowMultipleAnswers();
		}
		
		JDInstanceDAO.saveIntoDB(question);
		JDInstanceDAO.updateInDB(surv);
		request.setAttribute("idSurv", surv.getId());
		request.setAttribute("createdQuestionId", question.getId());

	}
	

}