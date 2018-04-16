<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="card">
	<div class="card-header text-white bg-secondary">
		<h5 class="card-title">${tvseries.name} ${tvseries.genres}</h5>
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
					<div class="card-header text-white bg-secondary">
						<h6>Studio</h6>
					</div>
					<div class="card-body">
						<div class="card-title">${tvseries.studio.name},
							${tvseries.studio.location}</div>
						<div class="card-text">${tvseries.studio.description}</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col">
				<div class="card">
					<div class="card-header text-white bg-secondary">
						<h6>Director</h6>
					</div>
					<div class="card-body">
						<div class="card-title">${tvseries.director.name}</div>
						<div class="card-text">
							<c:out value="${tvseries.director.biography}" escapeXml="false" />
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col">
				<div class="card">
					<div class="card-header text-white bg-secondary">
						<h6 class="card-title">Casts</h6>
					</div>
					<div class="card-body">
						<table class="table table-striped">
							<thead>
								<tr>
									<th scope="col">Cast</th>
									<th scope="col">Character</th>
									<th scope="col">Description</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="cast" items="${tvseries.casts}">
									<tr>
										<td>${cast.artist.name}</td>
										<td>${cast.name}</td>
										<td>${cast.description}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col">
				<div class="card">
					<div class="card-header text-white bg-secondary">
						<h6 class="card-title">Seasons</h6>
					</div>
					<div class="card-body">
						<c:forEach var="season" items="${tvseries.seasons}">
							<div class="card">
								<div class="card-header text-white bg-secondary">
									<h6 class="card-title"><a href='<c:url value="/season/${season.id}"/>'>${season.year}</a></h6>
								</div>
								<div class="card-body">
									<img class="rounded float-left" alt="poster"
										src='<c:url value="/images/season/${season.id}"/>' />
									<div class="card-text">
										<c:out value="${season.summary}" escapeXml="false" />
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

