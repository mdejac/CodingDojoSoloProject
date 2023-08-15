<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="/css/style.css">
	<link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Ubuntu&display=swap" rel="stylesheet">
    <script src="/js/addressScript.js" defer></script>
    <script src="https://maps.googleapis.com/maps/api/js?key=API_KEY_HERE&callback=initAutocomplete&libraries=places" defer></script>
	<title>4Baggers</title>
</head>
<body>
	<div class="container">	
	<custom:header/>
		<div class="row justify-content-center mt-5">
			<div class="col-md-12">
				<div class="card border-black">
					<div class="card-header">
						<h3 class="card-title">Edit Tournament</h3>
					</div>
					
					<div class="card-body">
						<custom:tournamentForm action="/tournaments/${tournament.id}/edit" method="put"/>		
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>