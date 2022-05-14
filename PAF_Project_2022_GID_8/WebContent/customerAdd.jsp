<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Electro Gord</title>
	<meta charset="ISO-8859-1">
	<title>Items</title>
	<link rel="stylesheet" href="Views/bootstrap.min.css">
	<link rel="stylesheet" href="Views/Customers.css">
	<script src="Components/jquery-3.2.1.min.js"></script>
	<script src="Components/Customers.js"></script>
</head>
<body>
	<h3 style="font-size:50px; padding-top:5px;">Electro Gird (Pvt) Ltd</h3>
	<h5 style="font-size:35px; padding-top:10px;">Add Customers</h5>
	
		<div id="alertSuccess" class="alert alert-success alert"></div>
		<div id="alertError" class="alert alert-danger"></div><br>
			
	<div class="addform">
	<div class="formdiv">
		<form id="customerForm" name="customerForm">
			 Full Name: 
			 <input id="FullName" name="FullName" type="text" class="form-control form-control-sm">
			 <br> Address: 
			 <input id="Address" name="Address" type="text" class="form-control form-control-sm">
			 <br> City: 
			 <input id="City" name="City" type="text" class="form-control form-control-sm">
			 <br> Mobile Number: 
			 <input id="MobileNumber" name="MobileNumber" type="text" class="form-control form-control-sm">
			 <br> Email: 
			 <input id="Email" name="Email" type="text" class="form-control form-control-sm">
			 <br>
			 
			 <input id="butRegister" name="butRegister" type="button" value="Register" class="btn btn-outline-dark" style="width:50%; margin-left:100px; ">
		</form>
	</div>	
	</div>
		
</body>
</html>