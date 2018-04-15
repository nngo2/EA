<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:forEach var="tvseries" items="${tvseriesList}">
	<div class="card">
		<div class="card-header text-white bg-secondary">
			<h5 class="card-title"><a href="series/${tvseries.id}">${tvseries.name}</a></h5>
			<div class="card-text">Seasons: ${tvseries.seasonCount()} | Episodes: ${tvseries.episodeCount()} </div>
			<div class="card-text">Director: ${tvseries.director.name}</div>
			<div class="card-text">Studio: ${tvseries.studio.name}</div>			
		</div>
	</div>
</c:forEach>
