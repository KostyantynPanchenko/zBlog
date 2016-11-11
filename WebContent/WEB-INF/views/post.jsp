<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head profile="http://www.w3.org/2005/10/profile">
	<title>theBlog</title>	
	<jsp:include page="cssImport.jsp"/>
</head>
<body class="w3-display-container">

	<!-- include header -->
	<jsp:include page="my-header.jsp" />
	
	<!-- main content -->
	<div class="w3-container" style="max-width:960px; margin:auto;">		
		<c:choose>
			<%-- if we have a post to show --%>
			<c:when test="${not empty thePost}">				
				<%-- card --%>
				<div id="post" class="w3-card-4 w3-border w3-margin">
	 				
	 				<%-- post title --%>
					<div id="postTitle" class="w3-container w3-teal w3-padding">
					 <b>${thePost.title}</b>					  
					</div>
					
					<%--  post text --%>
					<div id="postContent" class="w3-container w3-justify">
					  ${thePost}
					</div>
					
					<%-- post footer --%>
					<div  id="postInfo" class="w3-container w3-light-grey w3-small w3-margin-top w3-padding-8">
						<spring:message code="label.added" var="added"></spring:message>
						<spring:message code="label.by" var="by"></spring:message>
						<c:url value="/profile/${thePost.author.username}" var="authorProfileURL" />
						<span class="w3-text-grey"><b>${added}:</b> ${thePost.added} ${by} <i class="fa fa-user"></i> <b><a href="${authorProfileURL}" class="w3-hover-text-blue">${thePost.author.username}</a></b></span>
						
						<c:set var="admin" value="admin"/>
						<c:if test="${(thePost.author.username eq currentUser) or (thePost.author.username eq admin)}">
							<c:url value="/posts/${thePost.id}/edit" var="editUrl"/>
							<a href="${editUrl}" class="w3-hover-text-red w3-right"><i class="w3-large fa fa-edit"></i></a>
						</c:if>
					</div>
				</div><%-- card --%>
				
				<%-- show comment form only to authenticated users --%>												
				<sec:authorize	access="isAuthenticated()">
					<%-- 
					<sec:authentication property="principal.username" var="currentUser" scope="page" />
					--%>
									
					<%-- COMMENT FORM --%>				
					<div class="w3-card-4 w3-rounder w3-border w3-margin">		
									
						<c:url value="/posts/${thePost.id}/comment" var="addComment" />
						
						<form class="w3-container" action="${addComment}" method="post">
							<fieldset class="w3-margin">
								<spring:message code="label.post.comment" var="comment"></spring:message>
					 			<legend class="w3-small">&nbsp;<b>${comment}:</b>&nbsp;</legend>
					 			
					 			<c:choose>
					 				<c:when test="${emptyComment eq null}">
					 					<spring:message code="label.post.comment.placeholder" var="placeholder"></spring:message>
					  					<textarea name="commentText" class="w3-input w3-border" rows="5" maxlength="400" placeholder="${placeholder}..." required ></textarea>
					 				</c:when>
					 				<c:otherwise>
					 					<spring:message code="label.post.comment.isEmpty" var="isEmpty"></spring:message>
					  					<textarea name="commentText" class="w3-input w3-border" rows="5" maxlength="400" placeholder="${isEmpty}" required ></textarea>
					 				</c:otherwise>
					 			</c:choose>					 			
					  			
					  			<br/>
					  			<spring:message code="label.post.submit" var="submit"></spring:message>
					  			<input type="submit" value="${submit}" class="w3-btn w3-ripple w3-blue w3-border w3-border-blue w3-round w3-center" />					  			
					  			
					  			<input type="hidden" name="authorName" value="${currentUser}" />
					  			<%-- csrf protection --%>
					  			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
							</fieldset>												  		
						</form>															
					</div><%-- card --%>
				</sec:authorize>
				
				<%-- COMMENTS IF ANY--%>				
				<c:if test="${thePost.comments.size() gt 0}">					
					<div class="w3-card-4 w3-rouder w3-border w3-margin">
						<spring:message code="label.post.comments" var="comments"></spring:message>
						<p class="w3-margin w3-small"><b>${comments}:</b></p>										
						<c:forEach items="${thePost.comments}" var="comment">
							<div class="w3-container w3-section w3-border w3-round-xlarge w3-margin">
								<%-- comment text --%>
								<div class="w3-container w3-medium">
								  <i class="fa fa-commenting-o"></i> ${comment.content}
								</div>
								
								<%-- author + added/edited info --%>
								<div class="w3-container w3-small w3-text-grey">									
									<c:url value="/profile/${comment.author.username}" var="authorProfileURL" />									
									<span class="w3-text-grey"><b>${added}:</b> ${comment.added} ${by} <i class="fa fa-user"></i> <b><a href="${authorProfileURL}" class="w3-hover-text-blue">${comment.author.username}</a></b></span>							
								</div>
							</div>
						</c:forEach>
						<br/>					
					</div>		
				</c:if>
			</c:when>
			<%-- post not found --%>
			<c:otherwise>
				<h1>Ooopsss! Requested resource in unavailable!</h1>
			</c:otherwise>
		</c:choose>				
	</div>
	<br/><br/><br/><br/>
	<!-- include footer -->
	<jsp:include page="my-footer.jsp" />
</body>
</html>