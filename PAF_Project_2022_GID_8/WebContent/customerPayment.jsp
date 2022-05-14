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
<body onload="getDataPayment()">
	<h3 style="font-size:50px; padding-top:5px;">Electro Gird (Pvt) Ltd</h3>
	<h5 style="font-size:35px; padding-top:10px;">Add Customers</h5>
	
		<div id="alertSuccess" class="alert alert-success alert"></div>
		<div id="alertError" class="alert alert-danger"></div><br>
			
	<div class="addform">
	<div class="formdiv">
		<form id="customerPay" name="customerPay">
		
		<div class="row justify-content-between text-left">
        	<div class="form-group col-sm-6 flex-column d-flex">
        	
        	 User ID: 
			 <input id="UserID" name="UserID" type="text" class="UserID form-control form-control-sm" disabled>
			 <input id="UserID" name="UserID" type="hidden" class="UserID form-control form-control-sm">
			 <br> Full Name: 
			 <input id="FullName" name="FullName" type="text" class="FullName form-control form-control-sm" disabled>
			 <input id="FullName" name="FullName" type="hidden" class="FullName form-control form-control-sm">
			 <br> Bill Price: 
			 <input id="BillPrice" name="BillPrice" type="text" class="BillPrice form-control form-control-sm" disabled>
			 <input id="BillPrice" name="BillPrice" type="hidden" class="BillPrice form-control form-control-sm">
			 <br>
			 
        	</div>
        	<div class="form-group col-sm-6 flex-column d-flex">
        	
        	 Bank Name: 
			 <input id="BankName" name="BankName" type="text" class="form-control form-control-sm">
			 <br> Account Name: 
			 <input id="AccName" name="AccName" type="text" class="form-control form-control-sm">
			 <br> Account No: 
			 <input id="AccNo" name="AccNo" type="text" class="form-control form-control-sm">
        	</div>
        	
        	<div class="form-group col-12 flex-column d-flex">
        	
        	 Branch Name: 
			 <input id="BrachName" name="BrachName" type="text" class="form-control form-control-sm">
			 <br> Date: 
			 <input id="Date" name="Date" type="date" class="form-control form-control-sm">
			 <br>
        	 <input id="butPayment" name="butPayment" type="button" value="Payment" class="btn btn-outline-dark" style="width:50%; margin-left:100px; ">
        	
        	</div>
        </div>		 
		</form>
	</div>	
	</div>
		
</body>
</html>