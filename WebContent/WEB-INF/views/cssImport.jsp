<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:url value="/resources/css/w3.css" var="css"/>
<link rel="stylesheet" type="text/css" href="${css}">

<spring:url value="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css" var="awesome"/>
<link rel="stylesheet" type="text/css" href="${awesome}">

<spring:url value="/resources/images/favicon2.png" var="icon"/>
<link rel="icon" 
      type="image/png" 
      href="${icon}">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
<script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
<![endif]-->