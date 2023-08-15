<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ attribute name="action" required="true" type="String" %>
<%@ attribute name="method" required="true" type="String" %>

<form:form class="border-black rounded p-5 align-middle" action="${action}" method="${method}" modelAttribute="tournament">
	<div class="row mb-3">
		<div class="col-2">
			<form:label path="eventName" class="col-form-label">Event Name : </form:label>
		</div>
		<div class="col">
			<form:errors path="eventName" class="text-danger"/>
			<form:input path="eventName" class="form-control" type="text" required="true"/>		
		</div>
	</div>
					
	<div class="row mb-3">
		<div class="col-2">
			<form:label path="eventLocation" class="col-form-label" >Location : </form:label>
		</div>
		<div class="col">
			<form:errors path="eventLocation" class="text-danger"/>
			<form:input path="eventLocation" class="form-control" type="text" id="addressInput" required="true"/>
		</div>
	</div>	
					
	<div class="row mb-3">
		<div class="col-2">
			<form:label path="eventDate" class="col-form-label">Date :</form:label>
		</div>
		<div class="col">
			<form:errors path="eventDate" class="text-danger"/>
			<form:input path="eventDate" class="form-control" type="date" required="true"></form:input>
		</div>
	</div>
				
	<div class="row mb-3">
		<div class="col-2">
			<form:label path="eventTime" class="col-form-label">Time :</form:label>
		</div>
		<div class="col">
			<form:errors path="eventTime" class="text-danger"/>
			<form:input path="eventTime" class="form-control" type="time" value="${tournament.eventTime}" required="true"></form:input>
		</div>
	</div>
					
	<div class="row mb-3">
		<div class="col">
			<div class="row">
				<div class="col-4">
					<form:label path="eventFormat" class="col-form-label">Format :</form:label>
				</div>
				<div class="col-5">
					<form:errors path="eventFormat" class="text-danger"/>
					<form:select path="eventFormat" class="form-select">
						<form:option value="" disabled="true" selected="true" hidden="true">Choose an option...</form:option>
						<form:option value="Round Robin">Round Robin</form:option>
						<form:option value="Single Elimination">Single Elimination</form:option>
						<form:option value="Double Elimination">Double Elimination</form:option>
					</form:select>
				</div>
			</div>
		</div>
		<div class="col">
			<div class="row">
				<div class="col-4">
					<form:label path="eventTeamType" class="col-form-label">Team Type :</form:label>
				</div>
				<div class="col-5">
					<form:errors path="eventTeamType" class="text-danger"/>
					<form:select path="eventTeamType" class="form-select">
						<form:option value="" disabled="true" selected="true" hidden="true">Choose an option...</form:option>
						<form:option value="Singles">Singles</form:option>
						<form:option value="Blind Draw">Blind Draw</form:option>
						<form:option value="Set Teams">Set Teams</form:option>
					</form:select>
				</div>
			</div>
		</div>
	</div>
							
	<div class="row mb-3">
		<div class="col">
			<div class="row">
				<div class="col-4">
					<form:label path="eventRoundCount" class="col-form-label"># Rounds :</form:label>	
				</div>
				<div class="col-5">
					<form:errors path="eventRoundCount" class="text-danger"/>
					<form:input path="eventRoundCount" class="form-control" type="number" min="1" step="1" required="true"></form:input>
				</div>
			</div>
		</div>
		<div class="col">
			<div class="row">
				<div class="col-4">
					<form:label path="eventBoardCount" class="col-form-label"># Boards :</form:label>	
				</div>
				<div class="col-5">
					<form:errors path="eventBoardCount" class="text-danger"/>
					<form:input path="eventBoardCount" class="form-control" type="number" min="1" step="1" required="true"></form:input>
				</div>
			</div>
		</div>
		<div class="col">
			<div class="row">
				<div class="col-4">
					<form:label path="eventMaxPlayerCount" class="col-form-label">Max Players :</form:label>	
				</div>
				<div class="col-5">
					<form:errors path="eventMaxPlayerCount" class="text-danger"/>
					<form:input path="eventMaxPlayerCount" class="form-control" type="number" min="2" step="1" required="true"></form:input>
				</div>
			</div>
		</div>
					
	</div>
		
	<div class="mt-5 text-end">
		<input class="btn btn-primary" type="submit" value="Submit" />
		<c:if test="${method eq 'put'}">
			<a class="btn btn-warning" href="/tournaments/${tournament.id}/view">Cancel</a>
			<a class="btn btn-danger" href="/tournaments/${tournament.id}/delete">Delete</a>
		</c:if>
	</div>		
</form:form>