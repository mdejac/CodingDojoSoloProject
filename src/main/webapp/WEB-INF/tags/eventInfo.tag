<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<c:forEach var="tournament" items="${allTournaments}">
	<div class="row border-black rounded event-card mb-3 p-2">
		<div class="col-10">
			<p class="m-0"> <c:out value="${tournament.eventName}"/></p>
			<p class="m-0 d-flex"> 
				<c:out value="${tournament.eventLocation}"/> - 
				<fmt:formatDate value="${tournament.eventDate}" pattern="MM.dd.YY"/> - 
				<c:set var="hour" value="${tournament.eventTime.getHour()}" />
				<c:out value="${hour <= 12 ? hour : hour-12}"/>:<c:out value="${String.format('%02d', tournament.eventTime.getMinute())}"/><c:out value="${hour < 12 ? 'AM' : 'PM'}"/>
			</p>
			<p class="m-0">Registered :  <c:out value="${tournament.eventRegisteredPlayersCount}"/>/<c:out value="${tournament.eventMaxPlayerCount}"/></p>
			<p class="m-0"> <c:out value="${tournament.eventFormat}"/> - <c:out value="${tournament.eventTeamType}"/> - <c:out value="${tournament.eventRoundCount}"/> Rounds</p>					
		</div>
		<div class="col-2 d-flex align-items-center justify-content-center">
			<a href="/tournaments/${tournament.id}/view">View</a>
		</div>
	</div>
</c:forEach>