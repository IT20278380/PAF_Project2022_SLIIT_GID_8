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
<body onload="getDataBill()">
	<h3 style="font-size:50px; padding-top:20px;">Electro Gird (Pvt) Ltd</h3>
	<h5 style="font-size:35px; padding-top:10px;">Customer Bill</h5>
	
		<div id="alertSuccess" class="alert alert-success alert"></div>
		<div id="alertError" class="alert alert-danger"></div><br>
			

	<div id="divViewGrid">
		
	</div>

</body>
</html>