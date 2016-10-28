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
		<h2>Department List</h2>

		<table>
			<tr>
				<th>Department Id</th>
				<th>Department Name</th>
				<th>Salary Min Range</th>
				<th>Salary Max Range</th>
			</tr>
			<c:forEach items="${departments}" var="dep">
				<tr>
					<td>${dep.getId()}</td>
					<td>${dep.getName()}</td>
					<td>${dep.getSalary_min_range()}</td>
					<td>${dep.getSalary_max_range()}</td>
				</tr>
			</c:forEach>
		</table>
		<br />
		<a href="/newdepartment">Create New Department</a>
	</div>
</body>
</html>