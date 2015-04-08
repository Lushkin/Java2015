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
<link rel="stylesheet" href="Content/css/site.css">
<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<title>Teacher Index</title>
</head>
<body>
	<div class="content" id="teacher-content">
			<form action="/Java2015Project/Teacher/CreateTest" method="post">
				<input type="hidden" name="id" value=""/>
				<label>Titre</label>
				<input type="text" value="" name="title" class="form-control"/>
				<label>Subjet</label>
				<select name="subject" class="form-control">
				<c:forEach items="${Subjects}" var="s">
					<option value="${s.getId() }" >${ s.getName()}</option>			
				</c:forEach>
				</select>
				<label>Debut</label>
				<input type="date" value="" name="startDate" class="form-control" />
				<label>Fin</label>
				<input type="date" value="" name="endDate" class="form-control" />
				<label>Duration</label>
				<input type="text" value="" name="duration" class="form-control" />
				<div style="margin:auto;">
					<c:forEach items="${Questions}" var="q">
						<input type="checkbox" value="${q.getId() }" >${ q.getContent()}<br/>	
					</c:forEach>
				</div>
				<input type="hidden" name="questions" id="questions" value=""/>
				<input type="submit" value="Sauvegarder"/>
			</form>
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