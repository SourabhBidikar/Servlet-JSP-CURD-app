<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" 
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" 
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" 
	crossorigin="anonymous">
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
			<div>
				<a href="http://localhost:9494/Servlet-JDBC-JSP-CRUD-App/" class="navbar-brand">User management app</a>
			</div>
			
			<ul class="navbar-nav">
				<li><a href="<%request.getContextPath();%>/Servlet-JDBC-JSP-CRUD-App/list" class="nav-link">Users</a>
				</li>
			</ul>
		</nav>
	</header>
<br>
<div class="row">
	
		<div class="container">
			<h3 class="text-center">List of Users</h3>
			<hr>
			<div class="container text-left">
			
				<a href="<%=request.getContextPath() %>/new" class="btn btn-success">Add New User</a>
				
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>Id</th>
						<th>Name</th>
						<th>Email</th>
						<th>Country</th>
						<th>Actions</th>
						
					</tr>
				</thead>
				<tbody>
					<c:forEach var="user" items="${usersList}">
						<tr>
							<td><c:out value="${user.id }"/></td>
							<td><c:out value="${user.name }"/></td>
							<td><c:out value="${user.email }"/></td>
							<td><c:out value="${user.country }"/></td>
							<td><a href="edit?id=<c:out value='${user.id }'/>">Edit</a>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="delete?id=<c:out value='${user.id }'/>">Delete</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>		
		</div>	
	</div>
</body>
</html>