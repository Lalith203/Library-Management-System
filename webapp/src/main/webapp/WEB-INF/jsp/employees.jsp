<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Employees</title>
    <link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">

    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" />
    <link href="https://cdnjs.cloudflare.com/ajax/libs/datatables/1.10.12/css/dataTables.bootstrap.min.css" rel="stylesheet" />

    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/datatables/1.10.12/js/jquery.dataTables.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/datatables/1.10.12/js/dataTables.bootstrap.min.js"></script>
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
                <li><a href="/categories">Categories</a></li>
                <li class="active"><a href="/employees">Employees</a></li>
            </ul>
        </div>
    </nav>
    <div class="container">
        <div class="text">
            <h3 class="display-4">Employees</h3>
            <hr />
        </div>
        <div class="table">
            <table class="table table-striped table-bordered table-hover" style="font-size: 1em" id="employeesTable">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <!-- <th>CEO</th> -->
                        <th>Manager</th>
                        <th>Manager Id</th>
                        <th>Salary</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- Getting list of employees from attributes and populating the table -->
                    <!-- <td>${employees.isCEO}</td> -->
                    <c:forEach var="employees" items="${list}">
                        <tr>
                            <td>${employees.id}</td>
                            <td>${employees.firstName} ${employees.lastName}</td>
                            
                            <td>${employees.isManager}</td>
                            <td>${employees.managerId}</td>
                            <td>${employees.salary}</td>
                            <td><a href="employees/edit/${employees.id}">Edit</a><span> | </span><a href="employees/delete/${employees.id}">Delete</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <hr />
        <div class="text">
            <h4 class="display-4">Add Employees</h4>
        </div>
        <hr />
        <form method="post" action="employees" name="employeesForm" onsubmit="return validate()">
            <div class="form-group" id="firstName">
                <label>First Name</label>
                <input name="firstName" type="text" class="form-control" placeholder="Enter First Name" required=required />
            </div>
            <div class="form-group" id="lastName">
                <label>Last Name</label>
                <input name="lastName" type="text" class="form-control" placeholder="Enter Last Name" required=required />
            </div>
            <div class="form-group" id="salary">
                <label>Rank</label>
                <select id="rank" name="rank" class="form-control">
                    <option value="">Select a Rank</option>
                    <c:forEach begin="1" end="10" varStatus="loop">
                        <option value="${loop.index}">${loop.index}</option>
                    </c:forEach>
                </select>
            </div>
            <!-- <div class="form-group" id="isCeo">
                <label>CEO</label>
                <select required name="isCeo" id="isCeo" class="form-control" onchange="validate()">
                    <option value="">Select True/False</option>
                    <option value="true">True</option>
                    <option value="false">False</option>
                </select>
            </div> -->
            <div class="form-group" id="isManager">
                <label>Manager</label>
                <select required name="isManager" id="isManagerSelect" class="form-control" onchange="toggleManagerDropdown()">
                    <option value="">Select True/False</option>
                    <option value="true">True</option>
                    <option value="false">False</option>
                </select>
            </div>
            <div class="form-group" id="managerId">
                <label>Manager</label>
                <select name="managerId" class="form-control" id="manager" required="required">
                    <option value="">Select a Manager</option>
                    <!-- Getting list of managers from attributes and populating the dropdown -->
                    <c:forEach var="manager" items="${managerList}">
                        <option value="${manager.getId()}">${manager.getFirstName()} ${manager.getLastName()}</option>
                    </c:forEach>
                </select>
            </div>
            <button type="submit" id="PageRefresh" class="btn btn-primary">Add</button>
        </form>
        <!-- Sorting the employees script -->
        <script>
            function toggleManagerDropdown() {
                let isManagerSelect = document.getElementById("isManagerSelect");
                let managerIdDiv = document.getElementById("managerId");

                if (isManagerSelect.value === "false") {
                    managerIdDiv.style.display = "block";
                    document.getElementById("manager").setAttribute("required", "required");
                } else {
                    managerIdDiv.style.display = "none";
                    document.getElementById("manager").removeAttribute("required");
                }
            }

            // Initial toggle based on default value
            toggleManagerDropdown();

            $(document).ready(function() {
                $('#employeesTable').DataTable();
            });

            // Validation function to get the manager id required if the employee is not a CEO or Manager
            function validate(){
                // var isCeo = $('#isCeo').find(":selected").val();
                var isManager = $('#isManager').find(":selected").val();
                
                // if(isCeo == "true" || isManager == "true")
                if(isManager == "true") {
                    $('#manager').removeAttr('required');
                } else {
                    $('#manager').attr('required', 'required');
                }
            }
        </script>
    </div>
</body>
</html>
