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
				<input type="submit" value="Sauvegarder"/>
			</form>
	</div>
</body>
</html>