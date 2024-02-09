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
	
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${user!=null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${user==null}">
					<form action="insert" method="post">
				</c:if>
				
				<caption>
					<h2>
						<c:if test="${user!=null}">
						Edit user
						</c:if>
						<c:if test="${user==null}">
						Add new User
						</c:if>
					</h2>
				</caption>
				
				<c:if test="${user!=null}">
					<input type="hidden" name="id" value='<c:out value="${user.id}"></c:out>'/>
				</c:if>
				
				<fieldset class="form-group">
					<label>User Name</label>
					<input type="text" name="name" required="required" value='<c:out value="${user.name}"></c:out>'/>
				</fieldset>	
				<fieldset class="form-group">
					<label>User email</label>
					<input type="text" name="email" required="required" value='<c:out value="${user.email} "></c:out>'/>
				</fieldset>	
				<fieldset class="form-group">
					<label>User country</label>
					<input type="text" name="country" required="required" value='<c:out value="${user.country} "></c:out>'/>
				</fieldset>	
				
				<button class="btn btn-success" type="submit">Submit</button>
			</form>
				
			</div>
		</div>
	
	</div>
	
</body>
</html>