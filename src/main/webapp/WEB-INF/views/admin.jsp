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
<h2>Admin Page!</h2><br><br>
<h3>List of Advertisements</h3><br><br>
  <body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />



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

  
  
<br> <br>  <br> <br>  <br> <br> 
<p align="left"> 
<a href="${contextPath}/admin/mostActive" >Click here view most popular user</a>
</p>
   
</body> 

</html>