<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Add Advert Form</title>
<style>
body {
	background: #ffffcc;
}
</style>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<a href="${contextPath}/user/">Home</a><br/>

	<h2>Posting a New Advert</h2>


	<form:form action="${contextPath}/advert/add" method="post"
		commandName="advert" enctype="multipart/form-data">

		<table>
			<tr>
				<td>Posted By</td>
				<td>${sessionScope.user.username}</td>
				<td><form:hidden path="postedBy"
						value="${sessionScope.user.personID}" /></td>
			</tr>

			<tr>
				<td>Category:</td>
				<td><form:select path="categories" items="${categories}"
						multiple="true" required="required" /></td>
			</tr>

			<tr>
				<td>Advert Title:</td>
				<td><form:input type="text" path="title" size="30" required="required"/></td>
			</tr>
			
			<tr>
				<td>Item Name:</td>
				<td><form:input type="text" path= "itemName" size="30" required="required"/></td>
			</tr>
			
			<tr>
				<td>Item Price:</td>
				<td><form:input type="text" path= "price" size="30" required="required"/></td>
			</tr>
			
			<tr>
				<td>Item sold:</td>
				<td><form:input path= "itemStatus" size="30" readonly="readonly" /></td>
			</tr>
			
			<tr>
				<td>Message:</td>
				<td><form:input type="text" path="message" size="30" required="required"/></td>
			</tr>
			<tr>
                <td>File Upload:</td>
                <td><form:input type="file" path="photo" size="50" required="required"/></td>
            </tr>
			<tr>
				<td colspan="2"><input type="submit" value="Post Advert" /></td>
			</tr>
		</table>

	</form:form>

</body>
</html>