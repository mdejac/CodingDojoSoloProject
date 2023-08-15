<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="friend" required="true" type="com.michaeld.baggers.models.Friend" %>
<div class="row profile-card border-black rounded mb-3 p-2">
	<c:set var="user" value="${userId.equals(friend.recipient.id) ? friend.requestor : friend.recipient}"/>
	<div class="col-3 d-flex align-items-center justify-content-center">
		<img class="user-image" alt="Profile Picture" src="/${user.profilePictureFileUrl}">		
	</div>
	<div class="col">
		<p class="m-0"><a href="/profile/${user.id}/view"><c:out value="${user.lastName}"/>, <c:out value="${user.firstName}"/></a></p>
		<p class="m-0"><c:out value="${user.clubName}"/></p>		
	</div>
	<div class="col-2 d-flex flex-column justify-content-center gap-1">
		<c:choose>
			<c:when test="${friend.status == 'PENDING' }">
				<div class="row">
					<a class="btn btn-sm btn-success" href="/friends/${friend.id}/accept">Accept</a>
				</div>
				<div class="row">
					<a class="btn btn-sm btn-danger" href="/friends/${friend.id}/deny">Deny</a>			
				</div>
			</c:when>
			<c:otherwise>
				<div class="row">
					<a class="btn btn-sm btn-danger" href="/friends/${friend.id}/deny">Remove</a>			
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</div>
