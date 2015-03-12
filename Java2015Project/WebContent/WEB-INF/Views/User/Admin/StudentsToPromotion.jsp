<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Student to promotion</title>
	<link rel="stylesheet"
		href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="../Content/css/site.css">
</head>
<body>
	<div class="container">
		<div class="header">
			<h2>Ajout d'élèves dans une promotion</h2>
		</div>
		<hr/>
		<div class="row">
		    <label class="col-md-3" for="Promotion">Choisir une promotion</label>
		    <div class="col-md-9">
		    	<select id="Promotion" name="Promotion" class="form-control">
		    		<option value="0">Selectionner une promotion</option>
			    	<c:forEach items="${Promotions}" var="p">
			    		<option value="${p.getId()}">${p.getName()}</option>
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
					<c:forEach items="${Students}" var="s">
						<tr id="StudentLine">
							<td class="col-md-1"><input type="checkbox" disabled></td>
							<td class="col-md-2">${s.getFirstName()}</td>
							<td class="col-md-2">${s.getLastName()}</td>
							<td class="col-md-3">${s.getEmail()}</td>
							<td class="col-md-2"><fmt:formatDate pattern="dd/MM/yyyy" value="${s.getBirthDate()}"/></td>
							<td class="col-md-2">
								<span id="PromotionName" name="PromotionName"> ${(s.getPromotion().getName()) != null ? s.getPromotion().getName() : "N/A"}</span>
								<input type="hidden" id="PromotionId" name="PromotionId" value="${s.getPromotion().getId()}"/>
								<input type="hidden" id="StudentId" name="StudentId" value="${s.getId()}">
							</td>
						</tr>
					</c:forEach>
			  	</table>
		  	</div>
	  	</div>
	  	<br/>
	  	<div class="row">
	  		<div class="col-md-2 col-md-offset-10">
	  			<button type="button" class="btn btn-primary" style="width:100%!important;" onclick="SaveStudents();" id="submitbtn">Enregistrer</button>
	  		</div>
	  	</div>
	</div>
</body>
</html>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script>
$("#Promotion").change(function(){
	var promo = $("#Promotion").val();
	var rows = $("#Students").find("tr#StudentLine");
	
	rows.each(function(){
		$(this).removeClass("unable-row");
		$(this).find('td input[type=checkbox]').attr('disabled', false);
		$(this).find('td input[type=checkbox]').prop('checked', false);
		
		if(promo > 0){
			var promotionId = $(this).find('td input#PromotionId');
			if($(promotionId).val() == promo){
				$(this).find('td input[type=checkbox]').prop('checked', true);
			}
			else if($(promotionId).val() != 0){
				$(this).addClass("unable-row");
				$(this).find('td input[type=checkbox]').attr('disabled', true);
			}
		}
		else{
			$(this).find('td input[type=checkbox]').attr('disabled', true);
			
			$(this).find('td input[type=checkbox]').change(function() {
			    if(this.checked) {
			        alert("Ok");
			    }
			});
		}
	});
});

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
   
	
}
</script>