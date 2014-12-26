<%@page import="domain.QuestionInstance"%>
<%@page import="domain.SurveyInstance"%>
<%@page import="service.Retrievable"%>
<%@page import="service.JDInstanceService"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/jspf/link.jspf"%>
<title>Surveys</title>
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

.bordered {
	padding-top: 10px;
	padding-bottom: 10px;
	border: 1px solid #dddddd;
	border-collapse: separate;
	*border-collapse: collapse;
	-webkit-border-radius: 4px;
	-moz-border-radius: 4px;
	border-radius: 4px;
}

form {
	margin: 0 0 0;
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
					<li class="active"><a href="#">Published</a></li>
					<li><a href="/OpinionPoll/surveys-unpublished.jsp">Unpublished</a></li>
				</ul>
			</div>
			<div class="span10">
				<%
					Retrievable ret = JDInstanceService.getInstance();
				%>
				<%
					List<SurveyInstance> surveysList = ret.getSurveys(true);
					if (surveysList != null) {
						if (0 == surveysList.size()) {
				%>
				<div class="alert alert-block">
					<h4>Sorry!</h4>
					There're no any published surveys yet.
				</div>
				<%
					} else {
				%>
				<table class="table table-hover table-condensed">
					<thead>
						<tr>
							<th>Name</th>
							<th>Description</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<%
							for (SurveyInstance survey : surveysList) {
						%>
						<tr>
							<td><%=survey.getName()%></td>
							<td>
								<%
									String desc = survey.getDescription();
												if (desc != null)
													out.print(desc);
												else
													out.print("no description");
								%>
							</td>
							<td><form method="post"
									action="/OpinionPoll/survey-info.jsp">
									<input type="hidden" name="from" value="published"> <input
										type="hidden" name="id" value=<%=survey.getId()%>>
									<button type="submit" class="btn btn-link">edit</button>
								</form></td>
						</tr>
						<%
							}
						%>
					</tbody>
				</table>
				<%
					}
					}
				%>
			</div>
		</div>
	</div>
</body>
</html>