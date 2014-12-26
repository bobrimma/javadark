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
import domain.AnswerInstance;
import domain.QuestionInstance;
import domain.SurveyInstance;
import domain.UserInstance;

public class AnswerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AnswerController() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String formName = request.getParameter("formname");
		if (formName.equals("newanswer")) {
			createAnswer(request, response);
		}

	}

	protected void createAnswer(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Retrievable ret = JDInstanceService.getInstance();
		QuestionInstance question = ret.getQuestion(Integer.parseInt(request
				.getParameter("qwid")));
		AnswerInstance answer = new AnswerInstance(question);
		answer.setAnswerDescription(request.getParameter("description"));
		boolean isCorrect = false;
		if (request.getParameter("type").equals("correct")) {
			isCorrect = true;
		}
		if ((!question.isAllowMultipleAnswers())
				&& (ret.getCorAnswers(question.getId()) > 0)&&isCorrect) {
			System.out.println(question.isAllowMultipleAnswers());
			System.out.println(ret.getCorAnswers(question.getId()));

			request.setAttribute("errorMessage", "This question can have only one correct answer!");
			request.getRequestDispatcher("/answer-new.jsp?qwid=" + question.getId()).forward(request,
					response);
		} else {
			answer.setCorrect(isCorrect);
			JDInstanceDAO.saveIntoDB(answer);
			JDInstanceDAO.updateInDB(question);
			request.setAttribute("successMessage", "Answer was added!");
			request.getRequestDispatcher(
					"/question-info.jsp?qwid=" + question.getId()).forward(
					request, response);
		}
	}

}