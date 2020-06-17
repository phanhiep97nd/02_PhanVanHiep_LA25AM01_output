<%@page import="model.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List User</title>
</head>
<body>
<h1>Cách 1</h1>
	<table border="1" style="width: 30%" cellspacing="0">
		<thead>
			<th>ID</th>
			<th>Name</th>
			<th>Birthday</th>
			<th>Birth Place</th>
		</thead>
		<tbody>
			<%
				List<User> listUser = (List<User>) request.getAttribute("listUser");
				int id;
				String name;
				String birthday;
				String birthPlace;
				for (User u : listUser) {
					id = u.getId();
					name = u.getName();
					birthday = u.getBirthday();
					birthPlace = u.getBirthPlace();
			%>
			<tr>
				<td><%=id%></td>
				<td><%=name%></td>
				<td><%=birthday%></td>
				<td><%=birthPlace%></td>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>
	<hr>
	<h1>Cách 2</h1>
	<table border="1" style="width: 30%" cellspacing="0">
		<thead>
			<th>ID</th>
			<th>Name</th>
			<th>Birthday</th>
			<th>Birth Place</th>
		</thead>
		<tbody>
			<c:forEach items="${listUser}" var="user">
				<tr>
					<td>${user.getId()}</td>
					<td>${user.getName()}</td>
					<td>${user.getBirthday()}</td>
					<td>${user.getBirthPlace()}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>