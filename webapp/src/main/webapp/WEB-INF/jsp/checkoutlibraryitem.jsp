<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Library</title>
<link href="/webjars/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src=https://code.jquery.com/jquery-1.12.4.js></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

</head>
<body style="padding-top: 70px">
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="/">Library Management</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="/">Home</a></li>
				<li class="active"><a href="/library">Library Items</a></li>
				<li><a href="/categories">Categories</a></li>
				<li><a href="/employees">Employees</a></li>
			</ul>
		</div>
	</nav>


	<div class="container">
		<div class="text">
			<h3 class="display-4">Checkout Library Item</h3>
		</div>
		<hr/>
		<form method="post" action="library/checkout" name="libraryItemsForm"">
			<div class="form-group" id="id">
				<label>Id</label><input name="id" type="text"
					class="form-control" value="${id}" readonly/>
			</div>
			<div class="form-group" id="borrowable">
				<label>Borrowable</label><select name="borrowable"
					id="selectBorrowable" class="form-control" required=required>
					<option>Select True/False</option>
					<option value="true" selected>True</option>
					<option value="false">False</option>
				</select>
			</div>
			<div class="form-group" id="borrower">
				<label>Borrower Name</label><input name="borrower" type="text"
					class="form-control" placeholder="Enter Borrower Name"/>
			</div>
			<div class="form-group" id="borrowDate">
				<label>Borrow Date</label><input name="borrowDate" type="date"
					class="form-control" id="datepicker"
					placeholder="Select Borrow Date"/>
			</div>
			<button type="submit" id="PageRefresh" class="btn btn-primary">Checkout</button>
		</form>

		<!-- Script for borrowable -->
<script>
$('#selectBorrowable option[value="false"]').attr("selected",true);
$('#selectBorrowable').attr("disabled", true);
	
</script>

	</div>

</body>
</html>