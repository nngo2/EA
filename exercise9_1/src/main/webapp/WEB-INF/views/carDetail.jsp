<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml11.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Add a Car</title>
</head>
<body>
	<c:import url="logout.jsp"/>
	<form action="../cars/${car.id}" method="post">
		<table>
			<tr>
				<td>Make:</td>
				<td><input type="text" name="make" value="${car.make}" /></td>
			</tr>
			<tr>
				<td>Model:</td>
				<td><input type="text" name="model" value="${car.model}" /></td>
			</tr>
			<tr>
				<td>Year:</td>
				<td><input type="text" name="year" value="${car.year}" /></td>
			</tr>
			<tr>
				<td>Color:</td>
				<td><input type="text" name="color" value="${car.color}" /></td>
			</tr>
		</table>
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<input type="submit" value="update" />
		</sec:authorize>
		<sec:csrfInput />
	</form>
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<form action="delete?carId=${car.id}" method="post">
			<button type="submit">Delete</button>
			<sec:csrfInput />
		</form>
	</sec:authorize>
</body>
</html>