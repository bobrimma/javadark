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

textarea {
	width: 500px;
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
			<div class="span12">
				<form class="form-search" method="post"
					action="/OpinionPoll/SurveysController">
					<div class="input-append">
						<input type="text" class="span2 search-query" name="keyword">
						<input type="hidden" name="formname" value="search">
						<button type="submit" class="btn">GO</button>
					</div>
				</form>
				<%
					List<SurveyInstance> findedSurveys = (List<SurveyInstance>) request
							.getAttribute("findedSurveys");
					if (findedSurveys != null) {
						if (0 == findedSurveys.size()) {
				%>
				<div class="alert alert-block">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					<h4>Sorry!</h4>
					We didn't find any like this.
				</div>
				<%
					} else {
				%>
				<table class="table table-hover table-condensed">
					<thead>
						<tr>
							<th>Name</th>
							<th>Description</th>
							<th>Status</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<%
							for (SurveyInstance survey : findedSurveys) {
										String status = null;
										if (survey.isPublished())
											status = "published";
										else
											status = "unpublished";
						%>
						<tr <%if (status.equals("published")) {%> class="success"
							<%} else {%> class="error" <%}%>>
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
							<td><%=status%></td>
							<td><form method="post"
									action="/OpinionPoll/survey-info.jsp">
									<input type="hidden" name="from" value="search"> <input
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