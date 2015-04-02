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
			<form action="/Java2015Project" method="post">
				<label>Titre</label>
				<input type="text" value="${Test.getTitle()}" class="form-control"/>
				<label>Subjet</label>
				<input type="text" value="${Test.getSubjects().getName()}" class="form-control"/>
				<label>Debut</label>
				<input type="date" value="<fmt:formatDate value="${Test.getStartDate()}" pattern="yyyy-MM-dd" />" class="form-control" />
				<label>Fin</label>
				<input type="date" value="<fmt:formatDate value="${Test.getEndDate()}" pattern="yyyy-MM-dd" />" class="form-control" />
				<input type="submit" value="Sauvegarder"/>
			</form>
	</div>
</body>
</html>