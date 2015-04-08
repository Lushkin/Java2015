<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat,java.text.DateFormat;" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<link rel="stylesheet" href="../Content/css/site.css">
<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<title>Teacher Index</title>
</head>
<body>
<div class="container">
		<div class="header row">
		<div class="col-md-10">
				<h3>Espace enseignant > Edition d'un Test</h3>
			</div>
			<div class="col-md-2" style="text-align: right;">
				<a class="logout" role="button" href="/Java2015Project/">Déconnexion</a>
			</div>
		</div>
		<hr/>
	<div class="content" id="teacher-content">
			<form action="/Java2015Project/Teacher/EditTest" method="post">
				<input type="hidden" name="id" value="${Test.getId() }"/>
				<label>Titre</label>
				<input type="text" value="${Test.getTitle()}" name="title" class="form-control"/>
				<label>Subjet</label>
				<select name="subject" class="form-control">
				<c:forEach items="${Subjects}" var="s">
					<c:choose>
						<c:when test="${s.getId() == Test.getSubjects().getId()}">
							<option value="${s.getId() }" selected>${ s.getName()}</option>
						</c:when>
						<c:otherwise>
							<option value="${s.getId() }" >${ s.getName()}</option>
						</c:otherwise>			
					</c:choose>	
				</c:forEach>
				</select>
				<label>Debut</label>
				<input type="date" value="<fmt:formatDate value="${Test.getStartDate()}" pattern="yyyy-MM-dd" />" name="startDate" class="form-control" />
				<label>Fin</label>
				<input type="date" value="<fmt:formatDate value="${Test.getEndDate()}" pattern="yyyy-MM-dd" />" name="endDate" class="form-control" />
				<label>Duration</label>
				<input type="text" value="${Test.getDuration() }" name="duration" class="form-control" />
				<label>Questions</label>
				<div style="margin:auto;">
					<c:forEach items="${Questions}" var="q">
						<c:choose>
						<c:when test="${Tools.TestQuestionsContains(QuestionsTest,q)}">
							<input type="checkbox" checked value="${q.getId() }" >${ q.getContent()}<br/>
						</c:when>
						<c:otherwise>
							<input type="checkbox" value="${q.getId() }" >${ q.getContent()}<br/>
						</c:otherwise>			
					</c:choose>	
					</c:forEach>
				</div><br/>
				<input type="hidden" name="questions" id="questions" value=""/>
				<input class="btn btn-default" type="submit" value="Sauvegarder"/>
			</form>
	</div>
	</div>
	<script>
	$(function(){
		$("input[type=checkbox]").change(function(){
			var q = "";
			$('input[type=checkbox]:checked').each(function() {
			       q+=$(this).val() + '|';
		     });
			$("#questions").val(q);
			console.log("changed");
		});
	});
	</script>
</body>
</html>