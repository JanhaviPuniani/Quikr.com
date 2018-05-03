<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List Adverts</title>
<style>
body {
	background: #ffffcc;
}
table {
    margin: 0 auto;
    border-collapse: collapse;
    border: 1px solid black;
}

th {
    color: #800000;
    font-weight: bold;
}

tr:nth-child(even) {
    background-color: #f2f2f2;
}
</style>
</head>
<body>
<h2>Popular Users!</h2><br><br>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<a href="${contextPath}/user/">Home</a><br/>

	<table border="1" cellpadding="5" cellspacing="5" style="float: left">
		<tr>
			<td><b>ADVERT TITLE</b></td>
			<td><b>MESSAGE</b></td>
			<td><b>POSTED BY</b></td>
			<td><b>CATEGORIES</b></td>
		</tr>
		<c:forEach var="adv" items="${adverts}">
			<tr>
				<td>${adv.title}</td>
				<td>${adv.message}</td>
				<td>${adv.user.username}</td>
				<td><c:forEach var="categ" items="${adv.categories}">
                    	${categ} 
                    </c:forEach></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>