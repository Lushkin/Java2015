<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin index</title>
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<link rel="stylesheet" href="Content/css/site.css">
</head>
<body>
	<div class="container">
		<div>
			<ul class="nav nav-tabs">
				<li role="presentation" class="active"><a href="/Teachers">Profs</a></li>
				<li role="presentation"><a href="/Promotions">Promotions</a></li>
				<li role="presentation"><a href="/Students">Etudiants</a></li>
			</ul>
		</div>
		<div>
			<table class="table table-hover">
				<tr>
					<th>Prénom</th>
					<th>Nom</th>
					<th>Email</th>
					<th>Date de naissance</th>
				</tr>
				<c:forEach items="${Teachers}" var="t">
					<tr>
						<td>${t.getFirstName()}</td>
						<td>${t.getLastName()}</td>
						<td>${t.getEmail()}</td>
						<td><fmt:formatDate pattern="dd/MM/yyyy" value="${t.getBirthDate()}"/></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</body>
</html>