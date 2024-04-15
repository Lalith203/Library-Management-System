<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<html>
<head>
<title>Categories</title>
<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet" />
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/datatables/1.10.12/css/dataTables.bootstrap.min.css"
	rel="stylesheet" />
<script src=https://code.jquery.com/jquery-1.12.4.js></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/datatables/1.10.12/js/jquery.dataTables.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/datatables/1.10.12/js/dataTables.bootstrap.min.js"></script>
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
			<h3 class="display-4">Categories</h3>
			<hr>
		</div>

		<div class="table">
			<table class="table table-striped table-bordered table-hover"
				style="font-size: 1em" id="categoryTable">
				<thead>
					<tr>
						<th>Id</th>
						<th>Name</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="category" items="${list}">
						<tr>
							<td>${category.id}</td>
							<td>${category.categoryName}</td>
							<td><a href="categories/edit/${category.id}">Edit</a><span>
									| </span><a href="categories/delete/${category.id}">Delete</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<hr />
		<div class="text">
			<h4 class="display-4">Add Category</h4>
		
		</div>
		<hr />
		<form method="post" action="categories">
			<div class="form-group">
				<label>Category Name</label><input name="categoryName" type="text"
					class="form-control" id="categoryName"
					placeholder="Enter Category Name" required>
			</div>
			<button type="submit" id="PageRefresh" class="btn btn-primary">Add</button>
		</form>
	</div>
	<!-- sorting the category -->
	<script>
		$(document).ready(function() {
			$('#categoryTable').DataTable();
		});
	</script>
	<!-- Script to reload the categories page after adding a category-->
	<script type="text/javascript">
		$('#PageRefresh').click(function() {
			location.reload();
		});
	</script>

</body>
</html>