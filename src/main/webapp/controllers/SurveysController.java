package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.JDInstanceService;
import service.Retrievable;
import utils.HibernateUtils;

@WebServlet("/SurveysController")
public class SurveysController extends HttpServlet {
//	private Retrievable ret;
	
	@Override
	public void init() throws ServletException {
		//ret=JDInstanceService.getInstance();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/surveys.jsp").forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

}
