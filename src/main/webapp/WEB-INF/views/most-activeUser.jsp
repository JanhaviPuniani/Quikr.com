<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Most Popular User</title>
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
<h2>Most Active User!</h2>
<body>
    <br />
    <br />
    <c:set var="contextPath" value="${pageContext.request.contextPath}" />

    <table border="1" cellpadding="5" cellspacing="5" style="float: center">
        <tr>
            <th><b><u>Advert Id</u></b></th>
            <th><b><u>User Name</u></b></th>
            <th><b><u>Total Advertisements</u></b></th>
        </tr>
        <c:forEach var="adv" items="${list}">
            <tr>
                <td>${adv[0]}</td>
                <td>${adv[1]}</td>
                <td>${adv[2]}</td>
        </c:forEach></td>
            </tr>
    </table>
<a href="${contextPath}/user/login" >Go Back to Login</a> <br />
</body>
</html>