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
</style>
</head>
<body>
<h1>Hi You Selected This Item ! </h1>

<p> Enter shipping details</p>
<form action = "${pageContext.request.contextPath}/confirm/conf" method ="POST">
Customer Name :<input type="text" name = "cutomername">
Customer Address :<input type="text" name = "address"> 
<input type="hidden" name = "itemName" value="${map.itemName}" />
<input type="hidden" name = "itemPrice" value="${map.itemPrice}" />
<input type="hidden" name="advId" value ="${map.advId}" />
<input type="submit" value="Confirm Order" />

</form>
</body>
</html>