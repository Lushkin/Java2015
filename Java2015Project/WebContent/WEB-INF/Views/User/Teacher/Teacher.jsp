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
<title>Teacher Index</title>
</head>
<body>
	<div class="content" id="teacher-content">
			<table class="table table-hover">
				<tr>
					<th>Titre</th>
					<th>Subjet</th>
					<th>Debut</th>
					<th>Fin</th>
					<th>Attribuer</th>
					<th>Modifier</th>
					<th>Supprimer</th>
				</tr>
				<c:forEach items="${Tests}" var="t">
					<tr>
						<td>${t.getTitle()}</td>
						<td>${t.getSubjects().getName()}</td>
						<td><fmt:formatDate pattern="dd/MM/yyyy"
								value="${t.getStartDate()}" /></td>
						<td><fmt:formatDate pattern="dd/MM/yyyy"
								value="${t.getEndDate()}" /></td>
						<td><a role="button" class="btn btn-default"
							href="/Java2015Project/Teacher/TestToStudent?id=${t.getId()}"> <span class="glyphicon glyphicon-user"
								aria-hidden="true"></span>
						</a></td>
						<td><a role="button" class="btn btn-default"
							href="/Java2015Project/Teacher/EditTest?id=${t.getId()}"> <span class="glyphicon glyphicon-pencil"
								aria-hidden="true"></span>
						</a></td>
						<td><a role="button" class="btn btn-default"
							href="/Java2015Project/Teacher/DeleteTest?id=${t.getId()}"> <span class="glyphicon glyphicon-trash"
								aria-hidden="true"></span>
						</a></td>
					</tr>
				</c:forEach>
			</table>
			<a class="btn btn-default" role="button" href="/Java2015Project/Teacher/CreateTest">Ajouter un test</a>
			<a class="btn btn-default" role="button" href="/Java2015Project/Teacher/Questions">Questions</a>
		</div>
</body>
</html>