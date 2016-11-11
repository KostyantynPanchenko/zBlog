<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
	<title>theBlog</title>
	<jsp:include page="cssImport.jsp"/>
</head>
<body>
	
	<%-- include header --%> 
	<jsp:include page="my-header.jsp" />	
	
	<!-- main content -->
	<div class="w3-container" style="max-width:960px; margin:auto;">
			
			<br/><br/><br/>	
		
			<ul class="w3-ul w3-card-4 w3-light-grey">
			<c:forEach items="${results}" var="post">
				<c:url value="/posts/${post.id}" var="url"/>
				<li class="w3-hover-grey"><a href="${url}">${post.title}</a>		
			</c:forEach>
			</ul>		
			
			<br/><br/><br/>
				
	</div>

	<!-- include footer -->
	<jsp:include page="my-footer.jsp" />
</body>
</html>