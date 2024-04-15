<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<html>
<head>
<title>Categories</title>
<link href="/webjars/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet">
</head>

<body style="padding-top: 70px">
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="/">Library Management</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="/">Home</a></li>
				<li><a href="/library">Library Items</a></li>
				<li class="active"><a href="/categories">Categories</a></li>
				<li><a href="/employees">Employees</a></li>
			</ul>
		</div>
	</nav>
	<div class="container">
		<div class="text">
			<h3 class="display-4">Edit Category</h3>
		</div>
		<hr/>
		<form method="post" action="categories/edit">
			<div class="form-group">
				<label>ID</label><input name="categoryId" type="text"
					class="form-control" id="categoryId"
					value="${id}" readonly/>
			</div>
			<div class="form-group">
				<label>Category Name</label><input name="categoryName" type="text"
					class="form-control" id="categoryName"
					placeholder="Enter Category Name" required/>
			</div>
			<button type="submit" id="PageRefresh" class="btn btn-primary">Update</button>
		</form>
	</div>
</body>
</html>