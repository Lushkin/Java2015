<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<title>Questions</title>
</head>
<body>
	<table class="table table-hover">
	</table>
	<tr>
		<th>Question</th>
		<th>Catégorie</th>
		<th>Points</th>
		<th>Modifier</th>
		<th>Supprimer</th>
	</tr>
	<c:forEach items="${Questions}" var="q">
		<tr>
			<td>${q.getContent()}</td>
			<td>${q.getCatgory()}</td>
			<td>${q.getPonderation()}</td>
			<td><a role="button" class="btn btn-default"
				href="/Java2015Project/Teacher/EditQuestion?id=${q.getId()}"> <span
					class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
			</a></td>
			<td><a role="button" class="btn btn-default"
				href="/Java2015Project/Teacher/DeleteQuestion?id=${q.getId()}">
					<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
			</a></td>
		</tr>
	</c:forEach>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</body>
</html>