<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="/css/style.css">
	<link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Ubuntu&display=swap" rel="stylesheet">
    <script src="https://polyfill.io/v3/polyfill.min.js?features=default"></script>
	<script src="/js/mapScript.js"></script>
    <script src="https://maps.googleapis.com/maps/api/js?key=API_KEY_HERE&callback=initMap&libraries=&v=weekly"></script>
	<script>
		const eventLocation = "${tournament.eventLocation}";
		initalizeMap(eventLocation);
	</script>
	<title>4Baggers</title>
</head>
<body>
	<div class="container">	    
		<custom:header/>
		<div class="row mt-3">
			<div class="col-6">
				<div class="card border-black">
					<div class="card-header">
						<h3 class="card-title"><c:out value="${tournament.eventName}"/></h3>
					</div>
					<div class="card-body">
						<div class="row">
							<div class="col-3">
								<p>Address :</p>							
							</div>
							<div class="col">
								<c:out value="${tournament.eventLocation}"/>
							</div>
						</div>
						<div class="row">
							<div class="col-3">
								<p>Date/Time :</p>							
							</div>
							<div class="col">
									<fmt:formatDate value="${tournament.eventDate}" pattern="MM.dd.YY"/> - 
									<c:set var="hour" value="${tournament.eventTime.getHour()}" />
									<c:out value="${hour <= 12 ? hour : hour-12}"/>:<c:out value="${String.format('%02d', tournament.eventTime.getMinute())}"/><c:out value="${hour < 12 ? 'AM' : 'PM'}"/>
							</div>
						</div>
						<div class="row">
							<div class="col-2">
								<p>Format :</p>							
							</div>
							<div class="col-4">
								<c:out value="${tournament.eventFormat}"/>
							</div>
							<div class="col-3">
								<p>Team Type :</p>							
							</div>
							<div class="col-3">
								<c:out value="${tournament.eventTeamType}"/>
							</div>
						</div>
						<div class="row">
							<div class="col-2">
								<p>Rounds :</p>							
							</div>
							<div class="col-4">
								<c:out value="${tournament.eventRoundCount}"/>
							</div>
							<div class="col-3">
								<p># Boards :</p>							
							</div>
							<div class="col-3">
								<c:out value="${tournament.eventBoardCount}"/>
							</div>
						</div>
						<div class="row">
							<div class="col-3">
								<p>Registered :</p>							
							</div>
							<div class="col">
								<p><c:out value="${tournament.eventRegisteredPlayersCount}"/>/<c:out value="${tournament.eventMaxPlayerCount}"/></p>
							</div>
						</div>
						<div class="row">
							<div class="col-3">
								<p>Organizer :</p>							
							</div>
							<div class="col">
								<c:choose>
									<c:when test="${userId.equals(tournament.createdBy.id)}">
										<p>You</p>
									</c:when>
									<c:otherwise>
										<a href="/profile/${tournament.createdBy.id}/view"><c:out value="${tournament.createdBy.firstName}"/> <c:out value="${tournament.createdBy.lastName}"/></a>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
					<div class="card-footer text-end">
						<c:set var="isRegistered" value="false"/>
						<c:forEach var="user" items="${tournament.eventRegisteredPlayers}">
						    <c:if test="${user.id eq userId}">
						        <c:set var="isUserRegistered" value="true" />
						    </c:if>
						</c:forEach>
						
						<c:choose>
						    <c:when test="${isUserRegistered}">
						        <a class="btn btn-success" href="/tournaments/${tournament.id}/unregister">Unregister</a>
						    </c:when>
						    <c:otherwise>
						        <c:if test="${tournament.eventRegisteredPlayersCount < tournament.eventMaxPlayerCount}">
						        	<a class="btn btn-success" href="/tournaments/${tournament.id}/register">Register</a>
						   		</c:if>
						   		<c:if test="${tournament.eventRegisteredPlayersCount eq tournament.eventMaxPlayerCount}">
						        	<button class="btn btn-success" disabled>Registration Full</button>
						   		</c:if>
						    </c:otherwise>
						</c:choose>
						<c:if test="${userId.equals(tournament.createdBy.id)}">
							<a class="btn btn-warning" href="/tournaments/${tournament.id}/edit">Edit</a>
							<a class="btn btn-danger" href="/tournaments/${tournament.id}/delete">Delete</a>
						</c:if>
					</div>
				</div>
			</div>
			<div class="col-6">
				<div class="card border-black" id="mapContainer">
					<div class="card-header">
						<h3 class="card-title">Location Details</h3>
					</div>
					<div class="card-body" id="gmp-map"></div>
					<div class="card-footer">
					
					</div>
				</div>
			</div>
		</div>
		<div class="container mt-3 p-5 border-black rounded scroll-box">
			<c:forEach var="user" items="${tournament.eventRegisteredPlayers}">
					<custom:userCard user="${user}"/>
			</c:forEach>
		</div>
	</div>			
</body>
</html>