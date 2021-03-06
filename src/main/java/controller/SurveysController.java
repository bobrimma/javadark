package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.Actionable;
import service.JDInstanceService;
import service.Retrievable;
import domain.SurveyInstance;

public class SurveysController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private  Actionable action = JDInstanceService.getInstance();


	public SurveysController() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String formName = request.getParameter("formname");
		if (formName.equals("search")) {
			doSearch(request, response);
		} else if (formName.equals("edit")) {
			doEdit(request, response);
		} else if (formName.equals("new")) {
			createSurvey(request, response);
		}
	}

	protected void doSearch(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Retrievable ret = JDInstanceService.getInstance();
		String keyword = request.getParameter("keyword");
		if ((keyword != null) && (!(keyword.trim()).equals(""))) {
			List<SurveyInstance> findedSurveys = ret.getSurveys(keyword);
			request.setAttribute("findedSurveys", findedSurveys);
		}
		request.getRequestDispatcher("/surveys-search.jsp").forward(request,
				response);
	}

	protected void doEdit(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Retrievable ret = JDInstanceService.getInstance();
		Integer id = Integer.parseInt(request.getParameter("id"));
		SurveyInstance editedSurvey = ret.getSurvey(id);
		editedSurvey.setName(request.getParameter("name"));
		editedSurvey.setDescription(request.getParameter("description"));
		String status = request.getParameter("status");
		if (status.equals("published")) {
			editedSurvey.setPublished(true);
		} else {
			editedSurvey.setPublished(false);
		}
		action.updateInstance(editedSurvey);
		request.setAttribute("successMessage", "Survey was edited!");
			request.getRequestDispatcher("/survey-info.jsp")
			.forward(request, response);
		
	}

	protected void createSurvey(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		if ((name == null) || (name.trim().equals(""))) {
			request.setAttribute("errorMessage", "Field 'Name' can't be empty");
			request.getRequestDispatcher("/survey-new.jsp").forward(request,
					response);
		} else if ((description == null) || (description.trim().equals(""))) {
			request.setAttribute("errorMessage",
					"Field 'Description' can't be empty");
			request.getRequestDispatcher("/survey-new.jsp").forward(request,
					response);

		} else {
			SurveyInstance survey = new SurveyInstance();
			survey.setName(request.getParameter("name"));
			survey.setDescription(request.getParameter("description"));
			boolean isPublished = false;
			if (request.getParameter("status").equals("published")) {
				isPublished = true;
			}
			survey.setPublished(isPublished);
			action.saveInstance(survey);
			request.setAttribute("survey", survey);
			request.setAttribute("successMessage", "New survey is created!");
			request.getRequestDispatcher("/survey-info.jsp").forward(request,
					response);
		}

	}

	protected void addQuestion(HttpServletRequest request,
			HttpServletResponse response) {

	}

	protected void addAnswer(HttpServletRequest request,
			HttpServletResponse response) {

	}
}