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

<body onload="document.loginForm.username.focus();">
	<jsp:include page="my-header.jsp" />
	<br/><br/><br/>	
	
	<%-- Login form card --%>
	<div class="w3-card-8" style="max-width:400px; margin:auto;"> 
		<header class="w3-container w3-blue w3-center">
		  <h1><spring:message code="label.login.formName" text="Login Form"/></h1>
		</header>
		
		<c:choose>
			<c:when test="${username ne null}" >
				<c:set var="login" value="${username}" />
			</c:when>
			<c:otherwise>
					<c:set var="login" value="" />
			</c:otherwise>
		</c:choose>
		
		<div class="w3-container">
			<c:url var="loginUrl" value="/login" />
			<sf:form class="w3-container" name="loginForm" action="${loginUrl}" method="POST">
			
				<p>
				<label class="w3-label w3-text-blue" for="username"><b><spring:message code="label.login.username"/></b></label>
				<input class="w3-input w3-border w3-round" type="text" name="username" id="username" value="${login}" required ></p>
				
				<p>
				<label class="w3-label w3-text-blue" for="password"><b><spring:message code="label.login.password"/></b></label>
				<input class="w3-input w3-border w3-round" type="password" name="password" id="password" required ></p>
				
				<p><br/>
				<input type="submit" name="submit" value="<spring:message code='label.login.signin'/>" 
				class="w3-btn w3-ripple w3-blue w3-border w3-border-blue w3-round w3-center" />
				
				<p>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />					
			
			</sf:form>
		</div>
		
		<footer class="w3-container w3-blue w3-center">
			<c:choose>
				<c:when test="${!empty errorMsg}">
					<div class="w3-text-red"><h6>${errorMsg}</h6></div>					
				</c:when>
				<c:when test="${!empty logoutMsg}">
					<div class="w3-text-green"><h6>${logoutMsg}</h6></div>
				</c:when>
			</c:choose>					  
		</footer>
	</div>
</body>
</html>