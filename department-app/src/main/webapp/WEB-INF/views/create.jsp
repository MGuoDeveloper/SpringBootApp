<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Department App</title>
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
		<h2>New Department</h2>
		<form:form action="savedepartment" commandName="department">
			<table>
				<tr>
					<td>Department Name:</td>
					<td><form:input path="name" />
					<FONT color="red">
					<form:errors path="name" /></FONT></td>
				</tr>
				<tr>
					<td>Min Salary:</td>
					<td><form:input path="salary_min_range" />
					<FONT color="red">
					<form:errors path="salary_min_range" /></FONT></td>
				</tr>
				<tr>
					<td>Max Salary:</td>
					<td><form:input path="salary_max_range" />
					<FONT color="red">
					<form:errors path="salary_max_range" /></FONT></td>
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