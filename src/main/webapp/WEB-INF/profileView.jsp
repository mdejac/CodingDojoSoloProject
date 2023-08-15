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
	<title>4Baggers</title>
</head>
<body>
	<div class="container">	    
		<custom:header/>
		
		<div class="container p-2">
			<div class="row profile-card border-black rounded my-3">
				<div class="col-3 d-flex align-items-center justify-content-center">
					<img class="user-profile-image" alt="User Profile Pic" src="/${user.profilePictureFileUrl}">
				</div>
				
				<div class="col py-3">
					<h2><c:out value="${user.lastName}"/>, <c:out value="${user.firstName}"/></h2>
					<h5><c:out value="${user.clubName}"/></h5>		
				</div>
				
				<div class="col-1 d-flex align-items-center justify-content-center">
						<c:set var="status" value=""/>
						<c:set var="friendId" value="${0}"/>
						<c:set var="isRecipient" value="${false}"/>
						<c:forEach var="friend" items="${allFriends}">
						    <c:if test="${friend.requestor.id eq userId || friend.recipient.id eq userId }">
						        <c:set var="status" value="${friend.status}" />
								<c:set var="friendId" value="${friend.id}"/>
								<c:if test="${friend.recipient.id eq userId }">
									<c:set var="isRecipient" value="${true}"/>
								</c:if>
						    </c:if>
						</c:forEach>
					<c:choose>
						<c:when test="${status eq 'PENDING' }">
							<c:choose>
								<c:when test="${isRecipient}">
									<a class="btn btn-success" href="/friends/${friendId}/accept">Accept Friend Request</a>
								</c:when>
								<c:otherwise>
									<a class="btn btn-secondary disabled" href="#">Request Pending</a>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:when test="${status eq 'ACCEPT' }">
							<a class="btn btn-danger" href="/friends/${friendId}/deny">Remove Friend</a>
						</c:when>
						<c:otherwise>
							<a class="btn btn-primary" href="/friends/${user.id}/new">Send friend Request</a>
						</c:otherwise>
						
					</c:choose>
				</div>	
			</div>
		</div>
		
		<div class="row">
			<div class="col scroll-box border-black rounded mx-2 p-4">
				<custom:eventInfo/>
				<!-- Placeholders to demonstrate scroll pane. -->
				<div class="row border-black rounded mb-3 p-2">
					<p class="m-0"> Scroll Demo Placeholder</p>
					<p class="m-0"> Scroll Demo Placeholder</p>
					<p class="m-0"> Scroll Demo Placeholder</p>
					<p class="m-0"> Scroll Demo Placeholder</p>
				</div>
				<div class="row border-black rounded mb-3 p-2">
					<p class="m-0"> Scroll Demo Placeholder</p>
					<p class="m-0"> Scroll Demo Placeholder</p>
					<p class="m-0"> Scroll Demo Placeholder</p>
					<p class="m-0"> Scroll Demo Placeholder</p>
				</div>
				<div class="row border-black rounded mb-3 p-2">
					<p class="m-0"> Scroll Demo Placeholder</p>
					<p class="m-0"> Scroll Demo Placeholder</p>
					<p class="m-0"> Scroll Demo Placeholder</p>
					<p class="m-0"> Scroll Demo Placeholder</p>
				</div>
				<div class="row border-black rounded mb-3 p-2">
					<p class="m-0"> Scroll Demo Placeholder</p>
					<p class="m-0"> Scroll Demo Placeholder</p>
					<p class="m-0"> Scroll Demo Placeholder</p>
					<p class="m-0"> Scroll Demo Placeholder</p>
				</div>
				<div class="row border-black rounded mb-3 p-2">
					<p class="m-0"> Scroll Demo Placeholder</p>
					<p class="m-0"> Scroll Demo Placeholder</p>
					<p class="m-0"> Scroll Demo Placeholder</p>
					<p class="m-0"> Scroll Demo Placeholder</p>
				</div>
				<div class="row border-black rounded mb-3 p-2">
					<p class="m-0"> Scroll Demo Placeholder</p>
					<p class="m-0"> Scroll Demo Placeholder</p>
					<p class="m-0"> Scroll Demo Placeholder</p>
					<p class="m-0"> Scroll Demo Placeholder</p>
				</div>
				<div class="row border-black rounded mb-3 p-2">
					<p class="m-0"> Scroll Demo Placeholder</p>
					<p class="m-0"> Scroll Demo Placeholder</p>
					<p class="m-0"> Scroll Demo Placeholder</p>
					<p class="m-0"> Scroll Demo Placeholder</p>
				</div>
				<div class="row border-black rounded mb-3 p-2">
					<p class="m-0"> Scroll Demo Placeholder</p>
					<p class="m-0"> Scroll Demo Placeholder</p>
					<p class="m-0"> Scroll Demo Placeholder</p>
					<p class="m-0"> Scroll Demo Placeholder</p>
				</div>
				<div class="row border-black rounded mb-3 p-2">
					<p class="m-0"> Scroll Demo Placeholder</p>
					<p class="m-0"> Scroll Demo Placeholder</p>
					<p class="m-0"> Scroll Demo Placeholder</p>
					<p class="m-0"> Scroll Demo Placeholder</p>
				</div>
			</div>
		</div>
	</div>
</body>
</html>