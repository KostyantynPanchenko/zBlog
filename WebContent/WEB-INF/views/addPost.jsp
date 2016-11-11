<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head profile="http://www.w3.org/2005/10/profile">
	<title>theBlog</title>	
	<jsp:include page="cssImport.jsp"/>		
</head>
<body>
	<!-- include header -->
	<jsp:include page="my-header.jsp" />
	
	<!-- main content -->
	<div class="w3-container" style="max-width:960px; margin:auto;">
		<%-- card --%>
		<div id="post" class="w3-card-4 w3-border w3-margin">
		 		
			<%-- post title --%>
			<div id="postTitle" class="w3-container w3-sand">
				<sec:authentication property="principal.username" var="user" scope="page" />
				<h5>${user}, <spring:message code="label.addPost.welcome"></spring:message></h5>
			</div>
						
			<%--  post text --%>
			<div id="postContent" class="w3-container">
				<div class="w3-margin">
				<c:url value="/posts/add" var="add" />
				<form action="${add}" method="POST">
					<b><spring:message code="label.addPost.title"></spring:message>:</b>
					<br/>
					
					<c:choose>
						<c:when test="${emptyTitle eq null}">
							<spring:message code="label.addPost.title.placeholder" var="titlePlaceholder"></spring:message>							
							<input type="text" id="title" name="title" class="w3-input w3-border" maxlength="100" placeholder="${titlePlaceholder}" required /><br/>
						</c:when>
						<c:otherwise>
							<spring:message code="label.addPost.title.isEmpty" var="titleEmpty"></spring:message>
							<input type="text" name="title" class="w3-input w3-border" maxlength="100" placeholder="${titleEmpty}" required/><br/>	
						</c:otherwise>
					
					</c:choose>
					
					<b><spring:message code="label.addPost.content"></spring:message>:</b><br/>
					<c:choose>
						<c:when test="${emptyContent eq null}">							
							<spring:message code="label.addPost.content.placeholder" var="contentPlaceholder"></spring:message>							
							<textarea name="content" class="w3-input w3-border" rows="5" maxlength="4000" placeholder="${contentPlaceholder}" ></textarea>
						</c:when>
						<c:otherwise>
							<spring:message code="label.addPost.content.isEmpty" var="contentEmpty"></spring:message>
							<textarea name="content" class="w3-input w3-border" rows="5" maxlength="4000" placeholder="${contentEmpty}"></textarea>
						</c:otherwise>
					</c:choose>					
					
					<br/>
					<spring:message code="label.addPost.submit" var="postIt"></spring:message>
					<input type="submit" value="${postIt}"  class="w3-btn w3-ripple w3-blue w3-border w3-border-blue w3-round w3-center" />
										
					<input type="hidden" name="author" value="${user}" />
					  
			  		<%-- csrf protection --%>
					<input type="hidden" 
							name="${_csrf.parameterName}"
							value="${_csrf.token}" />
				</form>
				</div>				
			</div><%-- post text --%>				
		</div><%-- card --%>
	</div>
	
	<!-- include footer -->
	<jsp:include page="my-footer.jsp" />	
</body>
</html>