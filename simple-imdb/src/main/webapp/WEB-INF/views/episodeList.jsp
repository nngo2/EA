<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="card">
	<div class="card-header text-white bg-secondary">
		<h5 class="card-title">${season.summary}</h5>
	</div>
	<div class="card-body">
		<div class="row">
			<div class="col">
				<c:forEach var="episode" items="${season.episodes}">
					<div class="card">
						<div class="card-header text-white bg-secondary">
							<h5 class="card-title">${episode.name}|
								${episode.arrivalDate}</h5>
						</div>
						<div class="card-body">
							<div class="card-text">${episode.description}</div>
						</div>
						<c:forEach var="character" items="${episode.characters}">
							<div class="card">
								<div class="card-body">
									<div class="card">
										<div class="card-header text-white bg-secondary">
											<h6 class="card-title">Character: ${character.name}</h6>
										</div>
										<div class="card-body">
											<div class="card-text">${character.description}</div>
										</div>
									</div>
									<div class="card">
										<div class="card-header text-white bg-secondary">
											<h6 class="card-title">Cast: ${character.artist.name}</h6>
										</div>
										<div class="card-body">
											<div class="card-text">
												<img class="rounded float-left" alt="picture"
													src='<c:url value="/images/person/${character.artist.id}"/>'/>${character.artist.biography}
											</div>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</div>

