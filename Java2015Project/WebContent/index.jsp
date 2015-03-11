<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<link rel="stylesheet" href="Content/css/site.css">
<title>JAVA!</title>
</head>
<body>

	<div class="container">
		<h1>Bienvenue !</h1>
		<hr/>
		<h2>Merci de vous connecter</h2>
		<div>
			<p><%= request.getSession().getAttribute("errorMsg") == null ? "" : request.getSession().getAttribute("errorMsg") %></p>
			<div class="index-log-container">
				<form role="form" action="User/Login" method="POST">
					<input type="text" class="form-control index-input" id="EmailInput" name="email" placeholder="firstname.lastname@y-nov.com">
					<input type="password" class="form-control index-input" id="PassInput" name="password" placeholder="Mot de passe">
					<input type="submit" value="Valider" class="btn btn-primary index-log-in-button"/>
				</form>
			</div>
		</div>
	</div>

</body>
</html>