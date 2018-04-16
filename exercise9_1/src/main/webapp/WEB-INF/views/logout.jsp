<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	 
<c:url var="logoutUrl" value="/logout" />
<form action="${logoutUrl}" method="post">
	<input type="submit" value="Log out" /> 
	<sec:csrfInput/>
</form>