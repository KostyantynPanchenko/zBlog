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
	
	<%-- include header --%> 
	<jsp:include page="my-header.jsp" />	
	
	<!-- main content -->
	<div class="w3-container" style="max-width:960px; margin:auto;">
				
		<c:if test="${not empty page.posts}">
			<c:forEach items="${page.posts}" var="current">
							
				<div class="w3-card-4 w3-border w3-margin">
	 
					<div class="w3-container w3-light-grey w3-hover-yellow">
						<c:url value="/posts/${current.id}" var="url"/>
					  <h5><b><a href="${url}">${current.title}</a></b></h5>
					</div>
					
					<div class="w3-container w3-justify">
					  ${current}
					</div>
					
					<div class="w3-container w3-light-grey w3-small w3-margin-top w3-padding-4">
						<c:url value="/profile/${current.author.username}" var="authorProfileURL" />
						<spring:message code="label.added" var="added"></spring:message>
						<spring:message code="label.by" var="by"></spring:message>
						<span class="w3-text-grey"><b>${added}:</b> ${current.added} ${by} <i class="fa fa-user"></i> <b><a href="${authorProfileURL}" class="w3-hover-text-blue">${current.author.username}</a></b></span>
						
						<c:set var="admin" value="admin"/>
						<c:if test="${(current.author.username eq currentUser) or (currentUser eq admin)}">
							<c:url value="/posts/${current.id}/edit" var="editUrl"/>
							<a href="${editUrl}" class="w3-hover-text-red w3-right"><i class="w3-large fa fa-edit"></i></a>							
						</c:if>
					</div>
					
				</div>				
				<br/>
			</c:forEach>
		</c:if>		
		
		
		<%-- PAGINATION --%>
		<div class="w3-center w3-margin-bottom">
			<ul class="w3-pagination">						
			<!-- previous page -->
			<c:choose>
				<c:when test="${page.pageNumber eq 1}">
					<li><a href="#">«</a></li>
				</c:when>
				<c:otherwise>
					<c:url value="/page${page.pageNumber - 1}" var="prevPage" />
					<li><a href="${prevPage}">«</a></li>
				</c:otherwise>
			</c:choose>
			
			<c:choose>				
				<%-- if 5 or more pages --%>
				<c:when test="${page.pagesTotal >= 5}">
					<c:choose>
						<%-- page number less or equal 3 --%>
						<c:when test="${page.pageNumber lt 4}">
							<c:set var="startPage" value="1" />
							<c:set var="endPage" value="5" />
						</c:when>
						<%-- page number greater then 3 --%>
						<c:otherwise>
							<c:choose>
								<%-- current page less or equal (end page - 3) --%>
								<c:when test="${page.pageNumber lt (page.pagesTotal - 2)}">
									<c:set var="startPage" value="${page.pageNumber - 2}" />
									<c:set var="endPage" value="${page.pageNumber + 2}" />
								</c:when>
								<%-- current page greater then (end page - 3 ) --%>
								<c:otherwise>
									<c:set var="startPage" value="${page.pagesTotal - 4}" />
									<c:set var="endPage" value="${page.pagesTotal}" />
								</c:otherwise>
							</c:choose>							
						</c:otherwise>
					</c:choose>
												
					<c:forEach begin="${startPage}" end="${endPage}" var="i">
						<c:url value="/page${i}" var="pageUrl" />
						<c:choose>							
							<c:when test="${i eq page.pageNumber}">
								<li><a href="${pageUrl}" class="w3-light-grey">${i}</a><li>
							</c:when>
							<c:otherwise>							
								<li><a href="${pageUrl}">${i}</a><li>								
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:when>
				
				<%-- if less then 5 pages --%>
				<c:otherwise>				
					<c:set var="startPage" value="1" />
					<c:set var="endPage" value="${page.pagesTotal}" />	
					<c:forEach begin="${startPage}" end="${endPage}" var="i">
						<c:url value="/page${i}" var="pageUrl" />
						<c:choose>							
							<c:when test="${i eq page.pageNumber}">								
								<li><a href="${pageUrl}" class="w3-light-grey">${i}</a><li>								
							</c:when>
							<c:otherwise>								
								<li><a href="${pageUrl}">${i}</a><li>				
							</c:otherwise>
						</c:choose>
					</c:forEach>									
				</c:otherwise>				
			</c:choose>		
				
			<%-- next page --%>
			<c:choose>		
				<c:when test="${page.pageNumber eq page.pagesTotal}">
					<li><a href="#">»</a></li>
				</c:when>
				<c:otherwise>
					<c:url value="/page${page.pageNumber + 1}" var="nextPage"/>
					<li><a href="${nextPage}">»</a></li>
				</c:otherwise>
			</c:choose>	
					
			</ul>	
			</div><%-- end of pagination --%>			
	</div>
	<br/><br/><br/><br/>

	<!-- include footer -->
	<jsp:include page="my-footer.jsp" />
</body>
</html>