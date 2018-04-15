<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="card">
	<div class="card-header text-white bg-secondary">
		<h5 class="card-title">${tvseries.name}</h5>
	</div>
	<div class="card-body">
		<div class="row">
			<div class="col">
				<div class="card">
					<div class="card-body">
						<div class="card-title">Description</div>
						<div class="card-text">${tvseries.description}</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col">
				<div class="card">
					<div class="card-header">
						<h6>Studio</h6>
					</div>			
					<div class="card-body">
						<div class="card-title">${tvseries.studio.name}, ${tvseries.studio.location}</div>	
						<div class="card-text">${tvseries.studio.description}</div>										
					</div>
				</div>				
			</div>
		</div>		
		<div class="row">
			<div class="col">
				<div class="card">
					<div class="card-header">
						<h6>Director</h6>
					</div>						
					<div class="card-body">
						<div class="card-title">${tvseries.director.name}</div>
						<div class="card-text"><c:out value="${tvseries.director.biography}" escapeXml="false"/></div>							
					</div>
				</div>				
			</div>
		</div>			
		<div class="row">
			<div class="col">
				<div class="card">
					<div class="card-header">
						<h6 class="card-title">Casts</h6>
					</div>					
					<div class="card-body">
						<c:forEach var="season" items="${tvseries.casts}">
						</c:forEach>
						<div class="card-title">Director</div>
						<div class="card-text">${tvseries.director.name}</div>
						<div class="card-text"><c:out value="${tvseries.director.biography}" escapeXml="false"/></div>							
					</div>
				</div>				
			</div>
		</div>									
		<div class="row">
			<div class="col">
				<div class="card">
					<div class="card-header">
						<h6 class="card-title">Seasons: </h6>
						<c:forEach var="season" items="${tvseries.seasons}">
						</c:forEach>
					</div>					
					<div class="card-body">
						<div class="card-title">Director</div>
						<div class="card-text">${tvseries.director.name}</div>
						<div class="card-text"><c:out value="${tvseries.director.biography}" escapeXml="false"/></div>							
					</div>
				</div>				
			</div>
		</div>								
	</div>
</div>

