<%@page import="domain.AnswerInstance"%>
<%@page import="domain.QuestionInstance"%>
<%@page import="domain.SurveyInstance"%>
<%@page import="service.Retrievable"%>
<%@page import="service.JDInstanceService"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="/jspf/link.jspf"%>
<title>Question list</title>
</head>
<style type="text/css">
body {
	padding-top: 10px;
	padding-bottom: 40px;
}

.button-wide {
	width: 205px;
}

textarea {
	width: 500px;
}
</style>
<body>
<div class="container">
<a href="newquestion.jsp?idSurv=<%=Integer.parseInt(request.getParameter("idSurv"))%>" class="ntSaveFormsSubmit">add new</a>
						
	<table class="table table-hover table-condensed">
		<%	Retrievable ret = JDInstanceService.getInstance();
			List<QuestionInstance> questionsList = ret
					.getQuestions(Integer.parseInt(request.getParameter("idSurv")));
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
	<a href="surveys.jsp" class="ntSaveFormsSubmit">go back</a>
	
	</div>
</body>
</html>