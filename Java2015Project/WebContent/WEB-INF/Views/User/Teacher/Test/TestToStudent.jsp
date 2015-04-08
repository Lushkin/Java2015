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
<title>Teacher Index</title>
</head>
<body>
	<div class="container">
		<div class="header row">
			<div class="col-md-10">
				<h3>Espace enseignant > Attribution de test aux élèves</h3>
			</div>
			<div class="col-md-2" style="text-align:right;">
				<a class="logout" role="button" href="/Java2015Project/">Déconnexion</a>
			</div>
		</div>
		<hr/>
		<div class="row">
			<input type="hidden" value="${Test.getId()}" id="ActualTest"/>
			<div class="col-md-2" style="font-weight:bold;">
				Nom du test
			</div>
			<div class="col-md-4">
				${Test.getTitle()}
			</div>
			<div class="col-md-2" style="font-weight:bold;">
				Début
			</div>
			<div class="col-md-4">
				<fmt:formatDate pattern="dd/MM/yyyy"
								value="${Test.getStartDate()}" />
			</div>
		</div>
		<div class="row">
			<div class="col-md-2" style="font-weight:bold;">
				Sujet
			</div>
			<div class="col-md-4">
				${Test.getSubjects().getName()}
			</div>
			<div class="col-md-2" style="font-weight:bold;">
				Fin
			</div>
			<div class="col-md-4">
				<fmt:formatDate pattern="dd/MM/yyyy"
								value="${Test.getEndDate()}" />
			</div>
		</div>
		<div class="row">
			<div class="col-md-2" style="font-weight:bold;">
				Durée
			</div>
			<div class="col-md-4">
				${Test.getDuration()} h
			</div>
		</div>
		<hr/>
		<div class="row">
		    <label class="col-md-3" for="Promotion">Choisir une promotion</label>
		    <div class="col-md-9">
		    	<select id="Promotion" name="Promotion" class="form-control">
		    		<option value="0">Selectionner une promotion</option>
			    	<c:forEach items="${Promotions}" var="p">
			    		<option value="${p.getId()}" ${ActualPromo != null && ActualPromo.getId() == p.getId() ? "selected" : "" }>${p.getName()}</option> 
			    		<%-- <option value="${p.getId()}">${p.getName()}</option> --%>
					</c:forEach>
			    </select>
		    </div>
	  	</div>
	  	<div class="row">
	  		<div class="loader col-md-2 col-md-offset-5" id="loader" hidden>
		  		<!-- <img src="http://www.hepfu.vn/public/images/loader_7.gif" alt="loader" /> -->
		  	</div>
	  	</div>
	  	<br/>
	  	<div><label>Liste d'élèves</label></div>
	  	<form action="/Java2015Project/Teacher/SaveTestToStudent" method="POST"
			class="form-horizontal">
		  	<div style="border: solid 1px #ddd;">
			  	<table class="table table-condensed">
				  		<tr>
				  			<th class="col-md-1">Ajouter</th>
							<th class="col-md-2">Prénom</th>
							<th class="col-md-2">Nom</th>
							<th class="col-md-3">Email</th>
							<th class="col-md-2">Date de naissance</th>
							<th class="col-md-2">Promotion</th>
						</tr>
				</table>
			  	<div class="student-table">
			  		<table class="table table-condensed" id="Students">
						<c:forEach items="${Students}" var="s" varStatus="i">
							<tr id="StudentLine">
	<%-- 						${Tools.UserTestContains(s.getUserTestses(),Test) ? "checked" : "" } --%>
								<td class="col-md-1"><input type="checkbox" ${Tools.UserTestContains(s,Test) ? "checked" : "" }></td>
								<td class="col-md-2">${s.getFirstName()}</td>
								<td class="col-md-2">${s.getLastName()}</td>
								<td class="col-md-3">${s.getEmail()}</td>
								<td class="col-md-2"><fmt:formatDate pattern="dd/MM/yyyy" value="${s.getBirthDate()}"/></td>
								<td class="col-md-2">
								<c:choose>
									<c:when test="${s.getPromotions().getId() != null}">
										<span id="PromotionName" name="PromotionName"> ${s.getPromotions().getName()}</span>
									</c:when>
									<c:otherwise>
										<span id="PromotionName" name="PromotionName">N/A</span>
									</c:otherwise>			
								</c:choose>				
									<%-- <input type="hidden" id="PromotionId" name="PromotionId" value="${s.getPromotions().getId()}"/> --%>
									<input type="hidden" id="UserTestId" name="UserTestId_${i.index}"  value="${Tools.UserTestContains(s,Test) ? Test.getId() : null}"/>
									<input type="hidden" id="StudentId" name="StudentId_${i.index}" value="${s.getId()}">
									<input type="hidden" id="ActualTestId" name="ActualTestId"  value="${Test.getId()}"/>
								</td>
							</tr>
						</c:forEach>
				  	</table>
			  	</div>
		  	</div>
		  	<hr/>
		  	<div class="row">
		  		<div class="col-md-2 col-md-offset-8">
		  			<a class="btn btn-default" role="button" href="/Java2015Project/Teacher" style="width:100%!important;">Retours</a>
		  		</div>
		  		<div class="col-md-2">
		  			<button type="submit" class="btn btn-primary" style="width:100%!important;" onclick="SaveStudents();" id="submitbtn">Enregistrer</button>
		  		</div>
		  	</div>
	  	</form>
	</div>
</body>
</html>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script>

$("#Promotion").change(function(){
	GetStudents();
});

function GetStudents(){
    var actualPromotion = $("#Promotion").val();
    var actualTest = $("#ActualTest").val();
    
    location.href = "http://" + location.host + "/Java2015Project/Teacher/TestToStudent?id="+actualTest+"&promotionId="+actualPromotion;
	
}


$(":checkbox").change(function UpdateChecks(){
	 var actualTest = $("#ActualTest").val();
	
	var testId = $(this.parentElement.parentElement).find('td input[type=hidden]#UserTestId');
	//var promoName = $(this.parentElement.parentElement).find('td span#PromotionName');
	
	if(this.checked){
		$(testId).val(actualTest);
		//$(promoName).text($("#Promotion option:selected").text());
	}
	else{
		$(testId).val(null);
		//$(promoName).text("N/A");
	}
});


/* 

$(":checkbox").change(function UpdateChecks(){
	var promo = $("#Promotion").val();
	
	var promoId = $(this.parentElement.parentElement).find('td input[type=hidden]#PromotionId');
	var promoName = $(this.parentElement.parentElement).find('td span#PromotionName');
	
	if(this.checked){
		$(promoId).val(promo);
		$(promoName).text($("#Promotion option:selected").text());
	}
	else{
		$(promoId).val(0);
		$(promoName).text("N/A");
	}
});

function SaveStudents(){
    var studentsnew = "";
    
    $("#loader").show("slow");
    $("#submitbtn").attr('disabled', true);
    
    var rows = $("#Students").find("tr#StudentLine");
    rows.each(function(){
   
    	var col = $(this).find('td');
    	col.each(function(){
    		var p = $(this).find('#PromotionId').val();
        	var s = $(this).find('#StudentId').val();
    		if(p){
        		studentsnew += "|"+p+":"+s;
    		}
    	});
    });

    $.ajax("http://" + location.host + "/Java2015Project/Admin/SaveStudentsToPromotion", {
        type: "POST",
        data: {
        	students : JSON.stringify(studentsnew)
        }
    }).success(function (d) {
        //alert("Ok");
        location.reload();
    }).fail(function () {
        alert("Fail");
    });
	
} */
</script>