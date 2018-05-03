<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
      <ul>
            <li><a href="${contextPath}/user/post">Post Advertisement</a></li>
            <br />
            <li><a href="${contextPath}/user/search">Search Products</a></li>
            <br />
        </ul>
    </div>
    

</body>
</html>