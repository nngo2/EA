<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<spring:url value="/resources/style.css"/>" />
<title>Add a Book</title>
</head>
<body>
	<form:form modelAttribute="book" action="../books/${book.id}" method="post">
		<form:errors element="div" path="*" cssClass="errorBlock"  />
		<table>
			<tr>
				<td>Title:</td>
				<td><form:input path="title" /></td>
				<td><form:errors path="title" cssClass="error"/></td>
			</tr>
			<tr>
				<td>ISBN:</td>
				<td><form:input path="ISBN"/></td>
				<td><form:errors path="ISBN" cssClass="error"/></td>
			</tr>
			<tr>
				<td>Published Date:</td>
				<td><form:input path="publishedDate" type="date"/></td>
				<td><form:errors path="publishedDate" cssClass="error"/></td>
			</tr>				
			<tr>
				<td>Author:</td>
				<td><form:input path="author"/></td>
				<td><form:errors path="author" cssClass="error"/></td>
			</tr>
			<tr>
				<td>Price:</td>
				<td><form:input path="price"/></td>
				<td><form:errors path="price" cssClass="error"/></td>
			</tr>
		</table>
		<input type="submit" value="Update" />
	</form:form>	
	
	<form action="delete?bookId=${book.id}" method="post">
		<button type="submit">Delete</button>
	</form>
</body>
</html>