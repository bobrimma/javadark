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
</style>
<body>
	<div class="container">
		<%
			String action = request.getParameter("formname");

			Integer createdSurveyId = (Integer) request
					.getAttribute("createdSurveyId");

			//		out.print(action);
		%>


		<div class="row">
			<div class="span12">
				<%@ include file="/jspf/navbar.jspf"%>
			</div>
		</div>
		<div class="row">
			<div class="span12">
				<div class="tabbable tabs-left">
					<ul class="nav nav-tabs">
						<li <%if ("search".equals(action) || (action == null)) {%>
							class="active" <%}%>><a href="#tab1" data-toggle="tab">Search</a></li>
						<li><a href="#tab2" data-toggle="tab">Published</a></li>
						<li><a href="#tab3" data-toggle="tab">Unpublished</a></li>
						<li
							<%if (("new".equals(action)) || ("newquestion".equals(action))
					|| ("clear".equals(action))) {%>
							class="active" <%}%>><a href="#tab4" data-toggle="tab">Create
								new</a></li>
					</ul>
					<div class="tab-content">
						<%
							Retrievable ret = JDInstanceService.getInstance();
						%>
						<div
							class="tab-pane  <%if ("search".equals(action) || (action == null)) {%>active<%}%>"
							id="tab1">
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
										<td>
											<%
												String status = null;
															if (survey.isPublished())
																status = "published";
															else
																status = "unpublished";
															out.print(status);
											%>
										</td>
										<td><a href="surveyinfo.jsp?id=<%=survey.getId()%>">edit</a></td>
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
						<div class="tab-pane" id="tab2">
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
										<td><a href="surveyinfo.jsp?id=<%=survey.getId()%>">edit</a></td>
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
						<div class="tab-pane" id="tab3">
							<%
								surveysList = ret.getSurveys(false);
								if (surveysList != null) {
									if (0 == surveysList.size()) {
							%>
							<div class="alert alert-block">
								<h4>Sorry!</h4>
								There're no any unpublished surveys yet.
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
										<td><a href="surveyinfo.jsp?id=<%=survey.getId()%>">edit</a></td>
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
						<div
							class="tab-pane  <%if (("new".equals(action)) || ("newquestion".equals(action))
					|| ("clear".equals(action))) {%>active<%}%>"
							id="tab4">

							<form action="/OpinionPoll/SurveysController" method="post"
								class="form">
								<legend> New survey</legend>
								<div class="control-group">
									<label class="control-label" for="name">Name</label>
									<div class="controls">
										<textarea rows="1" name="name" placeholder="survey name"
											class="ntSaveForms"></textarea>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="description">Description</label>
									<div class="controls">
										<textarea rows="3" name="description"
											placeholder="enter description here..." class="ntSaveForms"></textarea>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="status">Status</label>
									<div class="controls">
										<label class="radio inline"> <input type="radio"
											name="status" value="unpublished"
											<%if ((createdSurveyId == null)
					|| (!(ret.getSurvey(createdSurveyId)).isPublished())) {%>
											checked <%}%>> Unpublished
										</label> <label class="radio inline"> <input type="radio"
											name="status" value="published"
											<%if ((createdSurveyId != null)
					&& ((ret.getSurvey(createdSurveyId)).isPublished())) {%>
											checked <%}%>>Published
										</label>
									</div>
								</div>
								<input type="hidden" name="formname" value="new">
								<div class="control-group">
									<div class="controls">
										<button class="btn  button-wide btn-success" type="submit"
											<%if (createdSurveyId != null) {%> disabled="disabled" <%}%>>Save</button>
											<button href="surveys.jsp"
									class="btn  btn-info ntSaveFormsSubmit" type="reset"
									style="width: 100px;">Clear</button>
									</div>
								</div>
							</form>
						
							<%
								if (createdSurveyId != null) {
							%>
							<div class="alert alert-error">Question list is empty.
							<a href="questions.jsp?idSurv=<%=createdSurveyId%>" class="ntSaveFormsSubmit">Add questions</a>
								</div>
							<%
								} 
							%>
							

						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
	<script type="text/javascript"
		src="/OpinionPoll/resources/js/ntsaveforms.js"></script>
</body>
</html>