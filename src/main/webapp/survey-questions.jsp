<%@page import="domain.SurveyInstance"%>
<%@page import="domain.QuestionInstance"%>
<%@page import="service.JDInstanceService"%>
<%@page import="service.Retrievable"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@ include file="/jspf/link.jspf"%>
<title>Survey</title>
</head>
<style type="text/css">
body {
	padding-top: 10px;
	padding-bottom: 40px;
	background-color: #f5f5f5;
}

.row {
	padding-top: 10px;
	padding-bottom: 10px;
}

.button-wide {
	width: 205px;
}

textarea {
	width: 270px;
}
</style>
<body>
	<div class="container">
		<div class="row">
			<div class="span12">
				<%@ include file="/jspf/navbar.jspf"%>
			</div>
		</div>

		<div class="row">
			<div class="span2">
				<ul class="nav nav-tabs nav-stacked">
					<li class="active"><a href="/OpinionPoll/survey-info.jsp">General
							info</a></li>
					<li><a href="#">Questions</a></li>
				</ul>
			</div>
			<div class="span10">

				<%
					Retrievable ret = JDInstanceService.getInstance();
					SurveyInstance survey;
					Integer id=null;
					if (request.getParameter("id") != null) {
						id = Integer.parseInt(request.getParameter("id"));
						survey = ret.getSurvey(id);
					} else {
						survey = (SurveyInstance) request.getAttribute("survey");
					}
				%>
				<table class="table table-hover table-condensed">
					<%
						
						List<QuestionInstance> questionsList = ret.getQuestions(id);
					%>
					<thead>
						<tr>
							<th>Name</th>
							<th>Description</th>
							<th>Type</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<%
							for (QuestionInstance question : questionsList) {
						%>
						<tr>
							<td><%=question.getName()%></td>
							<td><%=question.getDescription()%></td>
							<td>
								<%
									boolean multi = question.isAllowMultipleAnswers();
										if (multi)
											out.print("multianswer");
										else
											out.print("one answer");
								%>
							</td>
							<td><a href="#">delete</a></td>
						</tr>
						<%
							}
						%>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>
