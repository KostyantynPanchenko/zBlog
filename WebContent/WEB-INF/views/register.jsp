<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head profile="http://www.w3.org/2005/10/profile">
	<title>theBlog</title>
	<spring:url value="/resources/css/register.css" var="regCss"/>
	<link rel="stylesheet" type="text/css" href="${regCss}">
	<jsp:include page="cssImport.jsp"/>	
</head>
<body onload="document.getElementById('userName').focus()">
	<jsp:include page="my-header.jsp" />
	<br/><br/><br/>	
	
	<div class="w3-card-8" style="max-width:400px; margin:auto;"> 
		<header class="w3-container w3-blue w3-center">
		  <h1><spring:message code="label.form.title"></spring:message></h1>
		</header>
		
		<div class="w3-container">
			<c:url var="registerUrl" value="/register" />
			<sf:form class="w3-container" name="loginForm" modelAttribute="user" method="POST" action="${registerUrl}">
									
				<p>								
				<label class="w3-label w3-text-blue"><b><spring:message code="label.user.username"></spring:message></b></label>
				<sf:errors path="userName" element="span" cssClass="error"/>				
				<sf:input path="userName" cssClass="w3-input w3-border w3-round" value="" id="userName" required="required" />				
				
				<p>				
				<label class="w3-label w3-text-blue"><b><spring:message code="label.user.login"></spring:message></b></label>
				<sf:errors path="login" element="span" cssClass="error" />				
				<sf:input path="login" cssClass="w3-input w3-border w3-round" value="" required="required" />				
				
				<p>				
				<label class="w3-label w3-text-blue"><b><spring:message code="label.user.email"></spring:message></b></label>
				<sf:errors path="email" element="span" cssClass="error" />
				<sf:input path="email" type="email" cssClass="w3-input w3-border w3-round" value="" required="required" />
								
				<p>				
				<sf:label path="password" cssClass="w3-label w3-text-blue"><b><spring:message code="label.user.password"></spring:message></b></sf:label>
				<sf:errors path="password" element="span" cssClass="error" />
				<sf:password path="password" cssClass="w3-input w3-border w3-round" value="" required="required" />				
				
				<p>				
				<sf:label path="matchingPassword" cssClass="w3-label w3-text-blue"><b><spring:message code="label.user.confirmPass"></spring:message></b></sf:label>
				<sf:errors path="matchingPassword" element="span" cssClass="error" />
				<sf:errors element="span" cssClass="error" /><%-- this is for global (entire UserDTO object) errors field --%>
				<sf:password path="matchingPassword" cssClass="w3-input w3-border w3-round" value="" required="required" />
				
				<p><br/>
				<input type="submit" name="submit" value="<spring:message code='label.form.loginSignUp'></spring:message>" 
				class="w3-btn w3-ripple w3-blue w3-border w3-border-blue w3-round w3-center"/>
								
				<p>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />					
			
			</sf:form>
		</div>
		
		<footer class="w3-container w3-blue w3-center">
			<p class="w3-center">
			&nbsp;		  
		</footer>
	</div>    
</body>
</html>