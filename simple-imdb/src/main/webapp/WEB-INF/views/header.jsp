<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarToggler" aria-controls="navbarToggler"
		aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<a class="navbar-brand" href='<c:url value="/"/>'>Demo Simple IMDB</a>

	<div class="collapse navbar-collapse" id="navbarToggler">
		<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
			<li class="nav-item active"><a class="nav-link" href='<c:url value="/"/>'>TV
					Series<span class="sr-only">(current)</span>
			</a></li>
		</ul>
		<c:url var="post_url" value="/search" />
		<form:form class="form-inline my-2 my-lg-0" 
			modelAttribute="searchCriteria" action="${post_url}" method="post">
			<form:input path="criteria" class="form-control mr-sm-2"
				type="search" placeholder="Search" aria-label="Search" />
			<form:select path="criteriaType" class="form-control">
				<form:option value="series_name">Name of the show</form:option>
				<form:option value="series_genre">Genre of the show</form:option>
				<form:option value="artist_name">Name of the artist</form:option>
				<form:option value="character_name">Name of the character in the episode</form:option>
				<form:option value="director_name">Director of the show</form:option>
			</form:select>
			<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
		</form:form>
	</div>
</nav>