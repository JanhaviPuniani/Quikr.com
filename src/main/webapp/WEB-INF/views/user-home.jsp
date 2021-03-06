<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Home</title>
<style>
body {
	background: #ffffcc;
}
.menu-container a {
    margin: 5px 5px 5px 10px;
    font-weight: bold;
    text-decoration: none;
    font-size:120%;
}
</style>
</head>
<body>

<h1>Hi, ${user.firstName}</h1>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="menu-container">
<a href="${contextPath}/category/add" >Add Category</a> <br />
<a href="${contextPath}/advert/add" >Add Advertisement</a> <br />
<a href="${contextPath}/advert/list" >View All Advertisements</a> <br />
</div>
<br><br>
<a href="${contextPath}/user/login" >Go Back to Login</a> <br />
</body>
</html>