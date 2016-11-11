<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head profile="http://www.w3.org/2005/10/profile">
	<title><spring:message code="label.admin.title"></spring:message></title>
	<style>
		.tab {display:none;}
	</style>
	<jsp:include page="cssImport.jsp"/>	
</head>

<c:choose>
	<c:when test="${tabName ne null}">
		<c:set var="name" value="${tabName}" />
	</c:when>
	<c:otherwise>
		<c:set var="name" value="Users" />
	</c:otherwise>
</c:choose>

<body onload="activateTab('${name}'); filterTable()">
	<jsp:include page="my-header.jsp" />
	
	<spring:message code="label.admin.Users" var="Users"></spring:message>
	<spring:message code="label.admin.Posts" var="Posts"></spring:message>
	<spring:message code="label.admin.Comments" var="Comments"></spring:message>
	
	<spring:message code="label.admin.findAll" var="findAll"></spring:message>
	<spring:message code="label.admin.findById" var="findById"></spring:message>
	<spring:message code="label.admin.findByName" var="findByName"></spring:message>
	
	<spring:message code="label.admin.findAll.p" var="findAll_p"></spring:message>
	<spring:message code="label.admin.findById.p" var="findById_p"></spring:message>
	<spring:message code="label.admin.findByName.p" var="findByName_p"></spring:message>
	
	<spring:message code="label.admin.userName" var="userName"></spring:message>
	<spring:message code="label.admin.email" var="email"></spring:message>
	<spring:message code="label.admin.enabled" var="enabled"></spring:message>
	<spring:message code="label.admin.postTitle" var="postTitle"></spring:message>
	<spring:message code="label.admin.comment" var="comment"></spring:message>
	<spring:message code="label.admin.added" var="added"></spring:message>
	
	<spring:url value="/admin" var="actionURL" />
	
	<%-- TABS LINKS --%>
	<ul class="w3-navbar w3-black">
		<li><a href="#" class="tablink" onclick="openTab(event, 'Users');" id="UsersLink" >${Users}</a></li>
		<li><a href="#" class="tablink" onclick="openTab(event, 'Posts');" id="PostsLink">${Posts}</a></li>
		<li><a href="#" class="tablink" onclick="openTab(event, 'Comments');" id="CommentsLink">${Comments}</a></li>
	</ul>

	<%-- USERS TAB --%>
	<div id="Users" class="w3-container w3-border tab w3-center">
		<br/>
		<div class="w3-row">
			<div class="w3-container w3-card-8 w3-light-grey w3-quarter	">
				<h2><b>${Users}</b></h2>
				<br/>
				<form action="${actionURL}" method="post">
						<input type="text" class="w3-input w3-border" placeholder="${findAll_p}" disabled="disabled" />
						<input type="submit" class="w3-btn-block w3-indigo" value="${findAll}"/>
						<input type="hidden" name="section" value="users" />
						<input type="hidden" name="action" value="findAll" />
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				</form>				
				<br/>
				<form action="${actionURL}" method="post">
						<input type="number" name="userId" class="w3-input w3-border"  placeholder="${findById_p}" required="required" min="1000" max="99999"/>
						<input type="submit" class="w3-btn-block w3-indigo" value="${findById}" />
						<input type="hidden" name="section" value="users" />
						<input type="hidden" name="action" value="findById" />
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				</form>			
				<br/>
				<form action="${actionURL}" method="post">
						<input type="text" name="userName" class="w3-input w3-border" placeholder="${findByName_p}" required="required"/>
						<input type="submit" class="w3-btn-block w3-indigo" value="${findByName}" />
						<input type="hidden" name="section" value="users" />
						<input type="hidden" name="action" value="findByName" />
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				</form>
				<br/>
				<br/>				
			</div>
			
			<%-- RESULTS TABLE --%>
			<div class="w3-container w3-threequarter">
				<div class="w3-container w3-card-8 w3-padding-16">
					<c:if test="${theUsers ne null and theUsers.size() > 0}">
						<table class="w3-table w3-striped w3-bordered w3-border w3-hoverable">
							<thead>
							<tr class="w3-light-grey">
								<th>id</th><th>${userName}</th><th>${email}</th><th>${enabled}</th>
							</tr>
							</thead>
							<c:forEach items="${theUsers}" var="cur">
							<tr class="row">
								<td>${cur.id}</td><td>${cur.username}</td><td>${cur.email}</td><td>${cur.enabled}</td>
							</tr>
						</c:forEach>
						</table>
						<c:if test="${theUsers.size() > 10}">
							<br/>
							<button onclick="pageBack()">«</button> <button onclick="pageForward()">»</button>
						</c:if>							
					</c:if>
					<c:if test="${theUser ne null}">
						<table class="w3-table w3-striped w3-bordered w3-border w3-hoverable">
							<thead>
							<tr class="w3-light-grey">
								<th>id</th><th>${userName}</th><th>${email}</th><th>${enabled}</th>
							</tr>
							</thead>
							<tr class="row">
								<td>${theUser.id}</td><td>${theUser.username}</td><td>${theUser.email}</td><td>${theUser.enabled}</td>
							</tr>						
						</table>
					</c:if>			
				</div>
			</div><%-- END OF RESULTS TABLE --%>
		</div><%-- END OF ROW CONTAINER --%>
		<br/>
	</div><%-- END OF USERS TAB --%>			
			

	<%-- POSTS TAB --%>
	<div id="Posts" class="w3-container w3-border w3-center tab">
		<br/>
		<div class="w3-row">			
			<div class="w3-container w3-card-8 w3-light-grey w3-quarter	">
				<h2><b>${Posts}</b></h2>
				<br/>
				<form action="${actionURL}" method="post">
						<input type="text" class="w3-input w3-border" placeholder="${findAll_p}" disabled="disabled" />
						<input type="submit" class="w3-btn-block w3-orange" value="${findAll}"/>
						<input type="hidden" name="section" value="posts" />
						<input type="hidden" name="action" value="findAll" />
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				</form>				
				<br/>
				<form action="${actionURL}" method="post">
						<input type="number" name="postId" class="w3-input w3-border" placeholder="${findById_p}" required="required" min="10000" max="99999"/>
						<input type="submit" class="w3-btn-block w3-orange" value="${findById}" />
						<input type="hidden" name="section" value="posts" />
						<input type="hidden" name="action" value="findById" />
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				</form>			
				<br/>
				<form action="${actionURL}" method="post">
						<input type="text" name="postAuthor" class="w3-input w3-border" placeholder="${findByName_p}" required="required"/>
						<input type="submit" class="w3-btn-block w3-orange" value="${findByName}" />
						<input type="hidden" name="section" value="posts" />
						<input type="hidden" name="action" value="findByName" />
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				</form>
				<br/>
				<br/>			
			</div>
			
			<%-- RESULTS TABLE --%>
			<div class="w3-container w3-threequarter">
				<div class="w3-container w3-card-8 w3-padding-16">
					<c:if test="${thePosts ne null and thePosts.size() > 0}">
						<table class="w3-table w3-striped w3-bordered w3-border w3-hoverable">
							<thead>
							<tr class="w3-light-grey">
								<th>id</th><th>${postTitle}</th><th>${added}</th><th>${userName}</th>
							</tr>
							</thead>
							<c:forEach items="${thePosts}" var="post">
							<tr class="row">
								<td>${post.id}</td><td>${post.title}</td><td>${post.added}</td><td>${post.author.username}</td>
							</tr>
						</c:forEach>
						</table>
						<c:if test="${thePosts.size() > 10}">
							<br/>
							<button onclick="pageBack()">«</button> <button onclick="pageForward()">»</button>
						</c:if>							
					</c:if>
					<c:if test="${thePost ne null}">
						<table class="w3-table w3-striped w3-bordered w3-border w3-hoverable">
							<thead>
							<tr class="w3-light-grey">
								<th>id</th><th>${postTitle}</th><th>${added}</th><th>${userName}</th>
							</tr>
							</thead>
							<tr>
								<td>${thePost.id}</td><td>${thePost.title}</td><td>${thePost.added}</td><td>${thePost.author.username}</td>
							</tr>						
						</table>
					</c:if>			
				</div>
			</div><%-- END OF RESULTS TABLE --%>
		</div><%-- END OF ROW CONTAINER --%>
		<br/>
	</div><%-- END OF POSTS TAB --%>


	<%-- COMMENTS TAB --%>
	<div id="Comments" class="w3-container w3-border w3-center tab">
		<br/>
		<div class="w3-row">			
			<div class="w3-container w3-card-8 w3-light-grey w3-quarter	">
				<h2><b>${Comments}</b></h2>
				<br/>
				<form action="${actionURL}" method="post">
						<input type="text" class="w3-input w3-border" placeholder="${findAll_p}" disabled="disabled" />
						<input type="submit" class="w3-btn-block w3-teal" value="${findAll}"/>
						<input type="hidden" name="section" value="comments" />
						<input type="hidden" name="action" value="findAll" />
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				</form>				
				<br/>
				<form action="${actionURL}" method="post">
						<input type="number" name="commentId" class="w3-input w3-border" placeholder="${findById_p}" required="required" min="10000" max="99999"/>
						<input type="submit" class="w3-btn-block w3-teal" value="${findById}" />
						<input type="hidden" name="section" value="comments" />
						<input type="hidden" name="action" value="findById" />
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				</form>			
				<br/>
				<form action="${actionURL}" method="post">
						<input type="text" name="commentAuthor" class="w3-input w3-border" placeholder="${findByName_p}" required="required"/>
						<input type="submit" class="w3-btn-block w3-teal" value="${findByName}" />
						<input type="hidden" name="section" value="comments" />
						<input type="hidden" name="action" value="findByName" />
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				</form>
				<br/>
				<br/>			
			</div>
			
			<%-- RESULTS TABLE --%>
			<div class="w3-container w3-threequarter	">
				<div class="w3-container w3-card-8 w3-padding-16">
					<c:if test="${theComments ne null and theComments.size() > 0}">
						<table class="w3-table w3-striped w3-bordered w3-border w3-hoverable">
							<thead>
							<tr class="w3-light-grey">
								<th>id</th><th>${comment}</th><th>${added}</th><th>${userName}</th>
							</tr>
							</thead>
							<c:forEach items="${theComments}" var="com">
								<tr class="row">
									<td>${com.id}</td><td>${com.content}</td><td>${com.added}</td><td>${com.author.username}</td>
								</tr>
							</c:forEach>
						</table>
						<c:if test="${theComments.size() > 10}">
							<br/>
							<button onclick="pageBack()">«</button> <button onclick="pageForward()">»</button>
						</c:if>						
					</c:if>
					<c:if test="${theComment ne null}">
						<table class="w3-table w3-striped w3-bordered w3-border w3-hoverable">
							<thead>
							<tr class="w3-light-grey">
								<th>id</th><th>${comment}</th><th>${added}</th><th>${author}</th>
							</tr>
							</thead>
							<tr>
								<td>${theComment.id}</td><td>${theComment.content}</td><td>${theComment.added}</td><td>${theComment.author.username}</td>
							</tr>						
						</table>
					</c:if>			
				</div>
			</div><%-- END OF RESULTS TABLE --%>
		</div><%-- END OF ROW CONTAINER --%>
		<br/>
	</div><%-- END OF COMMENTS TAB --%>

	<script>
	var pageSize = 10;
	
	function openTab(evt, tabName) {
		var i, x, tablinks;
		x = document.getElementsByClassName("tab");
		for (i = 0; i < x.length; i++) {
			x[i].style.display = "none";
		}
		tablinks = document.getElementsByClassName("tablink");
		for (i = 0; i < x.length; i++) {
			tablinks[i].className = tablinks[i].className.replace(" w3-red", "");
		}
		document.getElementById(tabName).style.display = "block";
		evt.currentTarget.className += " w3-red";
	}
	
	function activateTab(tabName) {
		document.getElementById(tabName).style.display = "block";
		document.getElementById(tabName + 'Link').className += " w3-red";
	}
	
	function filterTable() {
		var rows = document.getElementsByClassName("row");
		var len = rows.length;
		var i;
		var j;
		var end = ((len > pageSize) ? pageSize : len);
		
		for (i = 0; i < end; i++) {
			rows[i].style.display = "block";
		}
		
		if ((len - pageSize) > 0) {
			for (j = pageSize; j < len; j++) {
				rows[j].style.display = "none";
			}	
		}		
	}
	
	function pageBack() {		
		var rows = document.getElementsByClassName("row");
		var len = rows.length;
		var i;			
		
		for (i = (len - 1); i > -1; i--) {
			if (rows[i].style.display == "block") {
				if (i - pageSize > 0) {
					while (rows[i].style.display == "block") {
						rows[i].style.display = "none";
						i--;
					}	
				}				
				
				var end;
				if ((i - pageSize) > 0) {
					end = (i - pageSize);
				} else {
					end = -1;
				}
					
				var j;
				for (j = i; j > end; j--) {
					rows[j].style.display = "block";									
				}
				break;
			}			
		}
	}
	
	function pageForward() {		
		var rows = document.getElementsByClassName("row");
		var len = rows.length;
		var i;				
		
		for (i = 0; i < len; i++) {
			if (rows[i].style.display == "block") {
				//if tail larger then page
				if ((len - i) > pageSize) {
					var j;
					for (j = i; j < (i + pageSize); j++) {
						rows[j].style.display = "none";
					}
					
					var y;
					var end;
					if ((len - j) > pageSize) {
						end = j + pageSize;
					} else {
						end = len;
					}
					
					for (y = j; y < end; y++) {
						rows[y].style.display = "block";
					}					
				}
				break;
			}
		}		
	}
	</script>	
	
	<footer class="w3-container w3-round w3-blue w3-center">
		<p class="w3-center">
		&nbsp;	  
	</footer>	    
</body>
</html>