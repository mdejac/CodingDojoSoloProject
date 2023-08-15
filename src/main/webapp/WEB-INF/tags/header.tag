<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
	<div class="row mt-5 border-black rounded header">
		<div class="col-11 p-4">
			<h1>4BAGGERS</h1>
			<p>Find, Create, and Play your Tournament.</p>
		</div>
		<c:if test="${userId != null}">
			<div class="col-1 d-flex align-items-center justify-content-center">
				<a href="/users/logout">Logout</a>
			</div>
		</c:if>		
	</div>
	<c:if test="${userId != null}">
		<div class="row my-2">
			<div class="col mx-2 rounded d-flex align-items-center justify-content-center border-black ${selected == 'profile' ? ' selected-nav-option' : 'unselected-nav-option'}">
				<a href="/profile" class="my-1">Profile</a>
			</div>
			<div class="col mx-2 rounded d-flex align-items-center justify-content-center border-black ${selected == 'findTournament' ? 'selected-nav-option' : 'unselected-nav-option'}">
				<a href="/tournaments" class="my-1">Find Tournament</a>
			</div>
			<div class="col mx-2 rounded d-flex align-items-center justify-content-center border-black ${selected == 'createTournament' ? 'selected-nav-option' : 'unselected-nav-option'}">
				<a href="/tournaments/create" class="my-1">Create Tournament</a>
			</div>
		</div>
	</c:if>	
</div>
