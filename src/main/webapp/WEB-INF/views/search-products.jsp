<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Search Online</title>
<style>
body {
	background: #ffffcc;
}
</style>
</head>
<body>
<h2>Search Products !</h2>
<form action="${pageContext.request.contextPath}/searchProducts/search"  method="post">
	
	<table colspan="5">
	<tr>
			<td><b>Category</b></td>
			<td><b>Item</b></td>
			<td><b>Min Price</b></td>
			<td><b>Max Price</b></td>
		</tr>
		<td><select name="categories" id="categories">
				<c:forEach var="category" items="${categories}">
					<option value=${category.title}>${category.title}</option>
				</c:forEach>
		</select></td>

		<td><input type="text" name = "name" size="30" required="required"
			placeholder="Search product" /></td>
		<td><input type="number" name="minprice" min="0" max="100" step="1"
			value="10" required="required"></td>
		<td><input type="number" name="maxprice" min="0" max="500"
			step="1" value="50" required="required"></td>
	</table>
	
	<br />
	
	
	<input type="submit" value="Search" />
</form>
</body>
</html>