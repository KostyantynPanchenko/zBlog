<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<header>
	<div class="w3-row">
		<ul class="w3-navbar w3-blue">
			<%-- HOME --%>
			<c:url value="/" var="home" />
			<li><a href="${home}" class="w3-hover-yellow w3-padding-16 w3-large"><i class="fa fa-home"></i> <spring:message code="label.menu.home" text="Home"/></a></li>
			
			<%-- CONTACT --%>
			<c:url value="/contact" var="contact" />
			<li><a href="${contact}" class="w3-padding-16 w3-large"><spring:message code="label.menu.contact" text="Contact"/></a></li>
			
			<%-- NEW POST --%>
			<c:url value="/posts/add" var="newPost" />			
			<sec:authorize	access="isAuthenticated()">
				<li><a href="${newPost}" class="w3-padding-16 w3-large"><spring:message code="label.menu.newpost" text="New Post"/></a></li>
			</sec:authorize>
			
			<%-- LANGUAGE --%>
			<li class="w3-dropdown-click">
			    <a href="#" class="w3-padding-16 w3-large" onclick="showDropdown()"><spring:message code="label.menu.language" text="Language" /> <i class="fa fa-caret-down"></i></a>
				<div id="dropDown" class="w3-dropdown-content w3-white w3-card-4">
					<c:url value="?lang=en" var="en"/>					
					<a href="${en}"><spring:message code="label.menu.lang.en"/></a>
					<c:url value="?lang=de" var="de"/>					
					<a href="${de}"><spring:message code="label.menu.lang.de"/></a>					
					<c:url value="?lang=uk" var="uk"/>					
					<a href="${uk}"><spring:message code="label.menu.lang.uk"/></a>
										
				</div>
			</li>
			
			<%-- SEARCH --%>
			
			<li>
				<spring:message code="label.search" var="search"></spring:message>
				<a class="w3-padding-16  w3-large" style="cursor:pointer" onclick="document.getElementById('id01').style.display='block'"><i class="fa fa-search"></i> <c:out value="${search}"/></a>
			</li>			
			
			<%-- LOGOUT --%>
			<sec:authorize	access="isAuthenticated()" var="logedIn">
				<li class="w3-right"><a href="javascript:logout()" class=" w3-hover-red w3-padding-16  w3-large"><i class="fa fa-sign-out"></i> <spring:message code="label.menu.logout" text ="Logout" /></a></li>
			</sec:authorize>
			
			<%-- LOGIN --%>
			<sec:authorize access="isAnonymous()">
				<c:url value="/login" var="login" />
				<li class="w3-right"><a href="${login}" class="w3-hover-green w3-padding-16  w3-large"><i class="fa fa-sign-in"></i> <spring:message code="label.menu.login" text ="Login" /></a></li>
				<c:url value="/register" var="register" />
				<li class="w3-right"><a href="${register}" class="w3-padding-16  w3-large"><spring:message code="label.menu.register" text ="Register" /></a></li>
			</sec:authorize>
			
			<%-- ADMINISTRATION --%>
			<sec:authorize url="/admin">
				<c:url value="/admin" var="adminURL" />
				<li class="w3-right"><a href="${adminURL}" class="w3-padding-16 w3-large w3-hover-orange"><spring:message code="label.menu.admin" text="Administration"/></a></li>
			</sec:authorize>
		</ul>
		
		<%-- CONGRATS or WARNING --%>
		<c:choose>
			<c:when test="${logedIn}">
				<sec:authentication property="principal.username" var="currentUser" scope="request" />
				<div class="w3-row w3-light-grey w3-round" style="height:30px; margin:auto">
				<h5 class="w3-center"><spring:message code="label.pages.welcome" text ="Welcome to out site" />, ${currentUser}!</h5>
					</div>	
			</c:when>
			<c:otherwise>
				<div class="w3-row w3-yellow w3-round" style="height:30px; margin:auto">
					<h5 class="w3-center w3-margin"><i class="fa fa-warning"></i> <spring:message code="label.pages.mustBeLoggedIn" text ="You must be logged in to write posts and/or comments" /></h5>
				</div>	
			</c:otherwise>
		</c:choose>	
	</div>
	<div class="w3-row">
		
	</div>
	
			<%-- SERACH FORM --%>
			<div id="id01" class="w3-modal">
				<div class="w3-modal-content">
					<div class="w3-container">
						<span onclick="document.getElementById('id01').style.display='none'" class="w3-closebtn"><i class="fa fa-close"></i></span>
						<br/><br/>						
						<form action="search" method="post">
							<spring:message code="label.search.placeholder" var="placeholder" ></spring:message>
							<input type="text" name="pattern" class="w3-input w3-border" placeholder="${placeholder}" >
							<br/>							
							<button type="submit" class="w3-btn w3-ripple w3-blue"><i class="fa fa-search"></i> ${search}</button>
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						</form>					
					</div>
				</div>
			</div>
	
			<!-- LOGOUT HANDLING -->
			<c:url value="/logout" var="logoutUrl" />
			<form action="${logoutUrl}" method="POST" id="logoutForm">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			</form>
	
			<script>
				function logout() {
					document.getElementById("logoutForm").submit();
				}
				
				function showDropdown() {
				    var x = document.getElementById("dropDown");
				    if (x.className.indexOf("w3-show") == -1) {
				        x.className += " w3-show";
				    } else {
				        x.className = x.className.replace(" w3-show", "");
				    }
				}
			</script>
</header>	