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
<body class="w3-display-container">

	<!-- include header -->
	<jsp:include page="my-header.jsp" />
	<br/>
	
	<div class="w3-container" style="max-width:960px; margin:auto;">
	<div class="w3-card-8 w3-round" style="max-width:920px; margin:auto;"> 
		<header class="w3-container w3-round w3-blue w3-center">
		  <h1><spring:message code="label.contact.title"></spring:message></h1>
		</header>
		
		<div class="w3-container w3-margin w3-round">
		<div class="w3-container w3-margin w3-card-4">
			<br/>				
			<table class="w3-table w3-striped w3-bordered w3-border w3-hoverable">
				<tr>
					<c:url value="/resources/images/myPhoto.jpg" var="contactUrl" />
					<td><img src="${contactUrl}" width="200px" height="200px" alt="Photo" /></td>
					<td class="w3-center">
						<p class="w3-xxlarge"><b>Kostyantyn Panchenko</b></p>
						<p><i>It’s impossible. But doable.</i></p>
						<p><b>+380 95 268 05 90</b></p>
						<p><a href="mailto:Kostyantyn.Panchenko@gmail.com">Kostyantyn.Panchenko@gmail.com</a></p>
						<p><a href="https://github.com/KostyantynPanchenko">https://github.com/KostyantynPanchenko</a></p>
						
					</td>
				</tr>
				<tr>
					<td><h1>Education</h1></td>
					<td><p class="w3-text-blue"><b>Lviv Polytechnic National University</b> | 2001 – 2005 | Bachelor</p>
						<p>Faculty of Computer Science and Information Technologies</p>
						<p>Software Engineering Department</p>
					</td>
				</tr>
				<tr>
					<td><h1>COURCES</h1></td>
					<td>
						<ul>
							<li><p>Java EE  | 98 hrs.   |  <a href="https://prog.kiev.ua/">https://prog.kiev.ua/</a>   course “Java PRO”</p></li>
							<li><p>Java SE  | 120 hrs. |  <a href="http://brainacad.com/">http://brainacad.com/</a></p></li>							
						</ul>
					</td>
				</tr>
				<tr>
					<td><h1>SKILLS</h1></td>
					<td>
						<p class="w3-text-blue"><b>Technical:</b>
								<ul>
									<li>Java SE, Java EE. Frameworks/technologies: JAXB, GSON, Servlets, JSP, JSTL, Spring IoC, Spring MVC, JDBC, Hibernate.</li>
									<li>HTML, CSS, JavaScript</li>
									<li>SQL</li>
									<li>Eclipse, Git, Maven, Tomcat</li>
								</ul>
							
						<p class="w3-text-blue"><b>Languages:</b>								
							<ul>
								<li>Ukrainian - native</li>
								<li>Russian - bilingual proficiency</li>
								<li>English - full professional proficiency</li>
							</ul>
						<p class="w3-text-blue"><b>Soft skills:</b>	
							<ul>
								<li>highly developed communication and presentation skills</li>
								<li>team and cross-unit collaboration</li>
								<li>time management</li>
							</ul>						
					</td>
				</tr>
				<tr>
					<td><h1>EXPERIENCE</h1></td>
					<td>
						<p class="w3-text-blue"><b>Sep 2013 to Feb 2015 | EMC Corp. | Account Manager</b>
						<p>Business development in Banking Industry. Managing relationships with TOP-20 banks. Local support of Worldwide Global Accounts.
						<p class="w3-text-blue"><b>Sep 2009 to Aug 2013 | TechnoServ | Deputy Head of Division</b>
						<p>Business development in Enterprise accounts. Full cycle of sales and promotion of company's solutions. 
						<p class="w3-text-blue"><b>Oct 2008 to May 2009 | IBM Corp. | Brand Sales Specialist</b> 
						<p>Sales and promotion of x86 servers and entry-level storage systems. 
						<p class="w3-text-blue"><b>Aug 2005 to Aug 2008 | PJSC Incom | Sales Specialist</b>
						<p>Sales to assigned customer list. Managing relationships with clients.	
					</td>
				</tr>
				<tr>
					<td><h1>ADDITIONAL INFO</h1></td>
					<td>
						<p><b>Personal qualities:</b> responsible, self-motivated, high educability.
						<p><b>Hobbies:</b> photography, basketball.
						<p><b>Date of birth:</b> 26.04.1983
						
					</td>
				</tr>				
			</table>
			<br/>		
		</div>
		</div>
		<br/>		
	</div>
	</div>
	<br/><br/><br/><br/>
	<jsp:include page="my-footer.jsp" />    
</body>
</html>