<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Employee App</title>
<style>
body {
	font-family: "Times New Roman", Times, serif;
}

#edit {
	padding: 20px 60px;
}

a {
	text-decoration: none;
	color: black;
}

a:hover {
	text-decoration: none;
	cursor: pointer;
	color: #428ff4;
}
</style>
<script src="https://code.jquery.com/jquery-2.2.1.min.js"></script>

</head>
<body>
	<div id="edit">
		<h2>${message} Employee</h2>
		<form:form action="saveemployee" commandName="employee">
			<table>
				<tr>
					<td><form:input type="hidden" path="id" />Employee Name:</td>
					<td><form:input path="name" /><FONT color="red">
					<form:errors path="name" /></FONT></td>
				</tr>
				<tr>
					<td>Manager Name:</td>
					<td><form:select path="manager_name">
					<c:forEach items="${managerlist}" var="man">
					    <form:option value="${man}" label="${man}"/>
					</c:forEach>
					</form:select>
					<FONT color="red"><form:errors path="manager_name" /></FONT></td>
				</tr>
				<tr>
					<td>Department:</td>
					<td><form:select path="dept">
					<c:forEach items="${deplist}" var="dep">
					    <form:option value="${dep.getId()}" label="${dep.getName()}"/>
					</c:forEach>
					</form:select>
					<FONT color="red">
					<form:errors path="dept" /></FONT></td>
				</tr>
				<tr>
					<td>Salary:</td>
					<td><form:input path="salary" /><FONT color="red">
					<form:errors path="salary" /></FONT></td>
				</tr>
				<tr>
					<td><input type="submit" value="Submit" /></td>
				</tr>
			</table>
		</form:form>
	</div>
	<script>
		
	</script>
</body>
</html>