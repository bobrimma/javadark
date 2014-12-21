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
		    request.getRequestDispatcher("/newquestion.jsp").forward(request, response);

	}


	

}