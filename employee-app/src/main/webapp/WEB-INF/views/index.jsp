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

#dep_list {
	padding: 20px 60px;
}

table {
	border-collapse: collapse;
}

table, th, td {
	border: 2px solid lightblue;
	text-align: center;
}

th, td {
	width: 180px;
}

a {
	text-decoration: none;
	color: #f49542;
	font-size: 20px;
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
	<div id="dep_list">
		<h2>Employee List</h2>

		<table>
			<tr>
				<th>Employee Id</th>
				<th>Employee Name</th>
				<th>Manager Name</th>
				<th>Department</th>
				<th>Salary</th>
				<th>Delete</th>
			</tr>
			<c:forEach items="${employees}" var="emp">
				<tr>
					<td><a href="/editemployee${emp.getId()}"> &#9997; ${emp.getId()}</a></td>
					<td>${emp.getName()}</td>
					<td>${emp.getManager_name()}</td>
					<td>${departments.get(emp.getDept())}</td>
					<td>${emp.getSalary()}</td>
					<td><a href="/delemployee${emp.getId()}"> &#10007; </a></td>
				</tr>
			</c:forEach>
		</table>
		<br />
		<a href="/newemployee">Create New Employee</a>
	</div>
</body>
</html>