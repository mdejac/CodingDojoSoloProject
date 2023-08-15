<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="user" required="true" type="com.michaeld.baggers.models.User" %>
<div class="row profile-card border-black rounded mb-3 p-2">
	<div class="col-3 d-flex align-items-center justify-content-center">
		<img class="user-image" alt="Profile Picture" src="/${user.profilePictureFileUrl}">		
	</div>
	<div class="col">
		<p class="m-0"><a href="/profile/${user.id}/view"><c:out value="${user.lastName}"/>, <c:out value="${user.firstName}"/></a></p>
		<p class="m-0"><c:out value="${user.clubName}"/></p>		
	</div>	
</div>
