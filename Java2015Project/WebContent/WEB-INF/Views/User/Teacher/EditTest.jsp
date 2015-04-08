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
			<form action="/Java2015Project/Teacher/EditTest" method="post" class="form-horizontal">
				<input type="hidden" name="id" value="${Test.getId() }"/>
				<div class="form-group">
				    <label for="title" class="col-sm-2 control-label">Titre </label>
				    <div class="col-sm-10">
				      	<input type="text" value="${Test.getTitle()}" name="title" class="form-control"/>
				    </div>
				</div>
				
				<div class="form-group">
				    <label for="subject" class="col-sm-2 control-label">Subjet </label>
				    <div class="col-sm-10">
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
				    </div>
				</div>
			
				<div class="form-group">
				    <label for="startDate" class="col-sm-2 control-label">Debut </label>
				    <div class="col-sm-10">
				      	<input type="date" value="<fmt:formatDate value="${Test.getStartDate()}" pattern="yyyy-MM-dd" />" name="startDate" class="form-control" />
				    </div>
				</div>
			
				<div class="form-group">
				    <label for="endDate" class="col-sm-2 control-label">Fin </label>
				    <div class="col-sm-10">
				      	<input type="date" value="<fmt:formatDate value="${Test.getEndDate()}" pattern="yyyy-MM-dd" />" name="endDate" class="form-control" />
				    </div>
				</div>
			
				<div class="form-group">
				    <label for="duration" class="col-sm-2 control-label">Durée </label>
				    <div class="col-sm-10">
				      	<input type="text" value="${Test.getDuration() }" name="duration" class="form-control" />
				    </div>
				</div>
				
				<div class="form-group">
				    <label for="" class="col-sm-2 control-label">Questions </label>
				    <div class="col-sm-10">
				      	<div style="margin:auto;">
							<c:forEach items="${Questions}" var="q">
								<c:choose>
								<c:when test="${Tools.TestQuestionsContains(QuestionsTest,q)}">
									<input type="checkbox" checked value="${q.getId() }" >&nbsp;&nbsp;${ q.getContent()}<br/>
								</c:when>
								<c:otherwise>
									<input type="checkbox" value="${q.getId() }" >&nbsp;&nbsp;${ q.getContent()}<br/>
								</c:otherwise>			
							</c:choose>	
							</c:forEach>
						</div>
				    </div>
				</div>
				<input type="hidden" name="questions" id="questions" value=""/>
				<hr/>
			  	<div class="form-group">
			  		<div class="col-md-2 col-md-offset-8">
			  			<a class="btn btn-default" role="button" href="/Java2015Project/Teacher" style="width:100%!important;">Retours</a>
			  		</div>
			  		<div class="col-md-2">
			  			<input class="btn btn-primary" type="submit" value="Enregistrer" style="width:100%!important;"/>
			  		</div>
			  	</div>					
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