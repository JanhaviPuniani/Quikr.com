<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
body {
	background: #ffffcc;
}
</style>
<title>Quikr Application</title>
</head>

<body>

<h1>Welcome to Quikr !</h1>
  <c:set var="contextPath" value="${pageContext.request.contextPath}" />
  <a href="${contextPath}/user/login">Click here to login</a>    

</body>
</html>