<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head profile="http://www.w3.org/2005/10/profile">
	<title>theBlog</title>
	<jsp:include page="cssImport.jsp"/>
</head>
<body>
	<jsp:include page="my-header.jsp" />
	<br/><br/><br/>	
	
	<div class="w3-card-8 w3-round " style="max-width:920px; margin:auto;"> 
		<header class="w3-container w3-round w3-blue w3-center">
		  <h1><spring:message code="label.profile.title"></spring:message></h1>
		</header>
		
		<%--<div class="w3-container w3-margin w3-round w3-border"> --%>
		<div class="w3-container w3-margin w3-card-4">
			<br/>		
			<table class="w3-table w3-striped w3-bordered w3-border w3-hoverable">
				<tr>
					<td><spring:message code="label.profile.username"></spring:message></td>
					<td>${user.username}</td>
				</tr>
				<tr>
					<td><spring:message code="label.profile.email"></spring:message></td>
					<td>${user.email}</td>
				</tr>
				<tr>
					<td><spring:message code="label.profile.password"></spring:message></td>
					<td><spring:message code="label.profile.haha"></spring:message></td>
				</tr>
				<%--
				<tr>
					<td><spring:message code="label.profile.registered"></spring:message></td>
					<td>${user.registered}</td>
				</tr>
				 --%>
				<tr>
					<td><spring:message code="label.profile.enabled"></spring:message></td>
					<td>${user.enabled}</td>
				</tr>
			</table>
			<br/>		
		</div>
		<%-- </div>--%>
		
		<footer class="w3-container w3-round w3-blue w3-center">
			<p class="w3-center">
			&nbsp;
		</footer>
	</div>    
</body>
</html>