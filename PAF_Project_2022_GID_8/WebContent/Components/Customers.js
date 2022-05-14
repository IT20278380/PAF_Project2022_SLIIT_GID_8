$(document).ready(function() { 
	if ($("#alertSuccess").text().trim() == "") { 
		$("#alertSuccess").hide(); 
	} 
	$("#alertError").hide(); 
}); 

// Register ============================================
$(document).on("click", "#butRegister", function(event) { 
	// Clear alerts---------------------
	 $("#alertSuccess").text(""); 
	 $("#alertSuccess").hide(); 
	 $("#alertError").text(""); 
	 $("#alertError").hide(); 
	 
	// Form validation-------------------
	var status = validateForm(); 
	if (status != true) { 
		 $("#alertError").text(status); 
		 $("#alertError").show(); 
		 return; 
	} 
	
	// If valid------------------------
	var type = "POST"; 
	 $.ajax( { 
		 url : "http://localhost:8080/PAF_Project_2022_GID_8/ElectroG/Customers/Add", 
		 type : type, 
		 data : $("#customerForm").serialize(), 
		 dataType : "text", 
		 complete : function(response, status) { 
		 	onSaveComplete(response.responseText, status); 
		 }
	 });
}); 


// View Profile================================================================
$(document).on("click", "#butView", function(event) { 

	// Clear alerts---------------------
	 $("#alertSuccess").text(""); 
	 $("#alertSuccess").hide(); 
	 $("#alertError").text(""); 
	 $("#alertError").hide(); 
	 
	// Form validation-------------------
	var status = validateView(); 
	if (status != true) { 
		 $("#alertError").text(status); 
		 $("#alertError").show(); 
		 return; 
	} 
	
	// If valid------------------------
	var xml = "<ProfileData><Email>"+$("#Email").val()+"</Email><Password>"+$("#Password").val()+"</Password></ProfileData>";
	var type = "POST"; 

	 $.ajax( { 
		 url : "http://localhost:8080/PAF_Project_2022_GID_8/ElectroG/Customers/One", 
		 type : type, 
		 contentType: "application/xml", 
		 data : xml,
		 dataType : "text", 
		 complete : function(response, status) { 
		 	onViewComplete(response.responseText, status); 
		 }
	 });
});


// Update================================================================
$(document).on("click", "#butUp", function(event) { 

	// Clear alerts---------------------
	 $("#alertSuccess").text(""); 
	 $("#alertSuccess").hide(); 
	 $("#alertError").text(""); 
	 $("#alertError").hide(); 
	 
	// Form validation-------------------
	var status = validateUpdate(); 
	if (status != true) { 
		 $("#alertError").text(status); 
		 $("#alertError").show(); 
		 return; 
	} 
	
	// If valid------------------------
	var json = '{"UserID":'+$("#UserID").val().trim()+',"FullName":'+$("#FullName").val().trim()+',"Address":'+$("#Address").val().trim()+',"City":'+$("#City").val().trim()+',"MobileNumber":'+$("#MobileNumber").val().trim()+',"Email":'+$("#Email").val().trim()+'}';
	var type = "PUT"; 

	 $.ajax( { 
		 url : "http://localhost:8080/PAF_Project_2022_GID_8/ElectroG/Customers/Update", 
		 type : type, 
		 contentType: "application/json", 
		 data : json,
		 dataType : "text", 
		 complete : function(response, status) { 
		 	onUpdateComplete(response.responseText, status); 
		 }
	 });
});


// Delete================================================================
$(document).on("click", ".btnRemove", function(event) { 
	
var xml = "<ProfileData><UserID>"+$(this).closest("tr").find('td:eq(0)').text()+"</UserID></ProfileData>";
	$.ajax( { 
		 url : "http://localhost:8080/PAF_Project_2022_GID_8/ElectroG/Customers/Delete", 
		 type : "DELETE", 
		 contentType: "application/xml", 
		 data : xml,
		 dataType : "text", 
		 complete : function(response, status) { 
		 	onDeleteComplete(response.responseText, status); 
		 } 
	 });
});

// Send Data From Update================================================================
$(document).on("click", ".btnUpdate", function(event) { 
	 var userId = $(this).closest("tr").find('td:eq(0)').text();
	 var fullName = $(this).closest("tr").find('td:eq(1)').text();
	 var address = $(this).closest("tr").find('td:eq(2)').text();
	 var city = $(this).closest("tr").find('td:eq(3)').text();
	 var mNumber = $(this).closest("tr").find('td:eq(4)').text();
	 var email = $(this).closest("tr").find('td:eq(5)').text();
	 
	 localStorage.setItem("userId", userId);
	 localStorage.setItem("fullName", fullName);
	 localStorage.setItem("address", address);
	 localStorage.setItem("city", city);
	 localStorage.setItem("mNumber", mNumber);
	 localStorage.setItem("email", email);
	 
	 window.location.href="customerUpdate.jsp";

});


// get Data From Update================================================================
function getData(){
	var userId = localStorage.getItem("userId");
	var fullName = localStorage.getItem("fullName");
	var address = localStorage.getItem("address");
	var city = localStorage.getItem("city");
	var mNumber = localStorage.getItem("mNumber");
	var email = localStorage.getItem("email");
	
	$("#UserID").val(userId);
	$("#FullName").val(fullName); 
	$("#Address").val(address); 
	$("#City").val(city); 
	$("#MobileNumber").val(mNumber); 
	$("#Email").val(email); 
}


// Send View Bill================================================================
$(document).on("click", "#billvi", function(event) { 
	
	 var alertSuccess = $("#alertSuccess").text();
	 var userId = $('#tab').find("td").eq(0).text();
	 var a = document.getElementById("tab");
	 
	 if(a){		
		if(alertSuccess == "Successfully View Profile."){
		localStorage.setItem("userId", userId);
		window.location.href="customerBill.jsp";
	 }
	}else{
		billvalidation()
	}	 
});

// get Data to bill================================================================
function getDataBill(){
	var userId = localStorage.getItem("userId");
	var xml = "<ProfileData><UserID>"+userId+"</UserID></ProfileData>";
	$.ajax( { 
		 url : "http://localhost:8080/PAF_Project_2022_GID_8/ElectroG/Customers/Bill", 
		 type : "POST", 
		 contentType: "application/xml", 
		 data : xml,
		 dataType : "text", 
		 complete : function(response, status) { 
		 	onBillComplete(response.responseText, status); 
		 } 
	 });
}


function billvalidation(){
	$("#alertError").text("Login Before"); 
	$("#alertError").show();
}


// Send Data for payment================================================================
$(document).on("click", ".paybu", function(event) { 
	
	 var userId = $("#UserId").val().trim();
	 var fullName = $("#FullName").val().trim();
	 var mobileNumber = $("#MobileNumber").val().trim();
	 var city = $("#City").val().trim();
	 var unit = $("#Unit").val().trim();
	 var price = $("#Price").val().trim();

	 localStorage.setItem("userId", userId);
	 localStorage.setItem("fullName", fullName);
	 localStorage.setItem("mobileNumber", mobileNumber);
	 localStorage.setItem("city", city);
	 localStorage.setItem("unit", unit);
	 localStorage.setItem("price", price);
	 
	 window.location.href="customerPayment.jsp";

});


// get Data to Payment================================================================
function getDataPayment(){
	var userId = localStorage.getItem("userId");
	var fullName = localStorage.getItem("fullName");
	var price = localStorage.getItem("price");

	$(".UserID").val(userId);
	$(".FullName").val(fullName); 
	$(".BillPrice").val(price); 
}


// Payment Button ============================================
$(document).on("click", "#butPayment", function(event) { 
	// Clear alerts---------------------
	 $("#alertSuccess").text(""); 
	 $("#alertSuccess").hide(); 
	 $("#alertError").text(""); 
	 $("#alertError").hide(); 
	 
	// Form validation-------------------
	var status = validatePay(); 
	if (status != true) { 
		 $("#alertError").text(status); 
		 $("#alertError").show(); 
		 return; 
	} 
	// If valid------------------------
	var type = "POST"; 
	 $.ajax( { 
		 url : "http://localhost:8080/PAF_Project_2022_GID_8/ElectroG/Customers/Payment", 
		 type : type, 
		 data : $("#customerPay").serialize(), 
		 dataType : "text", 
		 complete : function(response, status) { 
		 	onPayComplete(response.responseText, status); 
		 }
	 });
});


// VALIDATION FROML================================================================
function validateForm() { 

	if ($("#FullName").val().trim() == "") { 
		return "Insert Full Name."; 	
	}
	
	var chFullName = validateName($("#FullName").val());
	if (chFullName == false){
		return "Insert Full Name Corectly."; 
	}
	 
	if ($("#Address").val().trim() == "") { 
		return "Insert Address."; 
	} 
 
	if ($("#City").val().trim() == "") { 
		return "Insert City."; 
	} 
	
	var chCity = validateCity($("#City").val());
	if (chCity == false){
		return "Insert City Corectly."; 
	}
	if ($("#MobileNumber").val().trim() == "") { 
		return "Insert Mobile Number."; 
	} 
	
	var chMobileNumber = validateMobileNumber($("#MobileNumber").val());
	if (chMobileNumber == false){
		return "Insert Mobile Number Corectly."; 
	}
	
	if ($("#Email").val().trim() == "") { 
		return "Insert Email."; 
	} 
	
	var chEmail = validateEmail($("#Email").val());
	if (chEmail == false){
		return "Insert Email Corectly."; 
	}
	
	return true; 
}


// VALIDATION View================================================================
function validateView() { 
	
	if ($("#Email").val().trim() == "") { 
		return "Insert Email."; 
	} 
	
	if ($("#Password").val().trim() == "") { 
		return "Insert Password."; 
	}
	
	var chEmail = validateEmail($("#Email").val());
	if (chEmail == false){
		return "Insert Email Corectly."; 
	}
	
	return true; 
}


// VALIDATION Update================================================================
function validateUpdate() { 

	if ($("#FullName").val().trim() == "") { 
		return "Insert Full Name."; 	
	}
	
	var chFullName = validateName($("#FullName").val());
	if (chFullName == false){
		return "Insert Full Name Corectly."; 
	}
	 
	if ($("#Address").val().trim() == "") { 
		return "Insert Address."; 
	} 
 
	if ($("#City").val().trim() == "") { 
		return "Insert City."; 
	} 
	
	var chCity = validateCity($("#City").val());
	if (chCity == false){
		return "Insert City Corectly."; 
	}
	if ($("#MobileNumber").val().trim() == "") { 
		return "Insert Mobile Number."; 
	} 
	
	var chMobileNumber = validateMobileNumber($("#MobileNumber").val());
	if (chMobileNumber == false){
		return "Insert Mobile Number Corectly."; 
	}
	
	return true; 
}


// VALIDATION Payment================================================================
function validatePay() { 

	if ($("#BankName").val().trim() == "") { 
		return "Insert Bank Name."; 	
	}
	
	var chBankName = validateName($("#BankName").val());
	if (chBankName == false){
		return "Insert Bank Name Corectly."; 
	}
	 
	if ($("#AccName").val().trim() == "") { 
		return "Insert Account Name."; 	
	}
	
	var chAccName = validateName($("#AccName").val());
	if (chAccName == false){
		return "Insert Account Name Corectly."; 
	}
	
		
	if ($("#AccNo").val().trim() == "") { 
		return "Insert Account Number."; 	
	}
	
	var chAccNo = validateAccountNumber($("#AccNo").val());
	if (chAccNo == false){
		return "Insert Account Number Corectly."; 
	}
	
	if ($("#BrachName").val().trim() == "") { 
		return "Insert Branch Name."; 	
	}
	
	var chBranchName = validateName($("#BrachName").val());
	if (chBranchName == false){
		return "Insert Btanch Name Corectly."; 
	}

	if ($("#Date").val().trim() == "") { 
		return "Insert Date"; 	
	}
	
	
	return true; 
}


// VALIDATION Name================================================================
function validateName(FullName){
	
	if(FullName.match(/^[A-Za-z]+$/)) {
   		return true;
  	}else { 
   		return false; 
  }
}

// VALIDATION City================================================================
function validateCity(City){
	
	if(City.match(/^[A-Za-z]+$/)) {
   		return true;
  	}else { 
   		return false; 
  }
}

// VALIDATION MobileNumber================================================================
function validateMobileNumber(MobileNumber){
	
	if(MobileNumber.match(/^\d{10}$/)) {
   		return true;
  	}else { 
   		return false; 
  }
}

// VALIDATION Email================================================================
function validateEmail(Email){
	
	if(Email.match(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/)) {
   		return true;
  	}else { 
   		return false; 
  }
}

// VALIDATION Acc No================================================================
function validateAccountNumber(AccNO){
	
	if(AccNO.match(/^[0-9]*$/)) {
   		return true;
  	}else { 
   		return false; 
  }
}


function onSaveComplete(response, status) { 
	if (status == "success") { 
		 var resultSet = JSON.parse(response); 
		 if (resultSet.status.trim() == "success") { 
			 $("#alertSuccess").text("Successfully saved."); 
			 $("#alertSuccess").show(); 
			 
		 } else{ 
			 $("#alertError").text("Error while inserting the item"); 
			 $("#alertError").show(); 
		 } 
		 
	 } else if (status == "error") { 
		 $("#alertError").text("Error while saving."); 
		 $("#alertError").show(); 
	 } else { 
		 $("#alertError").text("Unknown error while saving.."); 
		 $("#alertError").show(); 
 	 }
	 
	 $("#hidItemIDSave").val(""); 
	 $("#customerForm")[0].reset(); 
}


function onViewComplete(response, status) { 
	if (status == "success") { 
		 var resultSet = JSON.parse(response); 
		 if (resultSet.status.trim() == "success") { 
			 $("#alertSuccess").text("Successfully View Profile."); 
			 $("#alertSuccess").show(); 
			 $("#divViewGrid").html(resultSet.data);
			 
		 } else if (resultSet.status.trim() == "error") { 
			 $("#alertError").text(resultSet.data); 
			 $("#alertError").show(); 
		 } 
		 
	 } else if (status == "error") { 
		 $("#alertError").text("No Profile View."); 
		 $("#alertError").show(); 
	 } else { 
		 $("#alertError").text("Unknown error while View.."); 
		 $("#alertError").show(); 
 	 }
	 
	 $("#hidItemIDSave").val(""); 
	 $("#customerForm")[0].reset(); 
}


function onUpdateComplete(response, status) { 
	if (status == "success") { 
		 var resultSet = JSON.parse(response); 
		 if (resultSet.status.trim() == "success") { 
			 $("#alertSuccess").text("Successfully Updated."); 
			 $("#alertSuccess").show(); 
			 $("#divViewGrid").html(resultSet.data);
			 
		 } else{ 
			 $("#alertError").text("Error while Updating the item"); 
			 $("#alertError").show(); 
		 } 
		 
	 } else if (status == "error") { 
		 $("#alertError").text("No Updation."); 
		 $("#alertError").show(); 
	 } else { 
		 $("#alertError").text("Unknown error while Update.."); 
		 $("#alertError").show(); 
 	 }
	 
	 $("#hidItemIDSave").val(""); 
	 $("#customerForm")[0].reset(); 
}


function onDeleteComplete(response, status) { 
	if (status == "success") { 
		 var resultSet = JSON.parse(response); 
		 if (resultSet.status.trim() == "success") { 
			 $("#alertSuccess").text("Successfully Deleted."); 
			 $("#alertSuccess").show(); 
			 $("#divViewGrid").html(resultSet.data);
			 
		 } else{ 
			 $("#alertError").text("Error while Deleting the item"); 
			 $("#alertError").show(); 
		 } 
		 
	 } else if (status == "error") { 
		 $("#alertError").text("No Deleting."); 
		 $("#alertError").show(); 
	 } else { 
		 $("#alertError").text("Unknown error while Delete.."); 
		 $("#alertError").show(); 
 	 }
	 
	 $("#hidItemIDSave").val(""); 
	 $("#customerForm")[0].reset(); 
}


function onBillComplete(response, status) { 
	if (status == "success") { 
		 var resultSet = JSON.parse(response); 
		 if (resultSet.status.trim() == "success") { 
			 $("#alertSuccess").text("Successfully View."); 
			 $("#alertSuccess").show(); 
			 $("#divViewGrid").html(resultSet.data);
			 
		 } else if (resultSet.status.trim() == "Error while View the Bill") { 
			 $("#alertError").text(resultSet.data); 
			 $("#alertError").show(); 
		 } 
		 
	 } else if (status == "error") { 
		 $("#alertError").text("No Deleting."); 
		 $("#alertError").show(); 
	 } else { 
		 $("#alertError").text("Unknown error while View.."); 
		 $("#alertError").show(); 
 	 }
	 
	 $("#hidItemIDSave").val(""); 
	 $("#customerForm")[0].reset(); 
}


function onPayComplete(response, status) { 
	if (status == "success") { 
		 var resultSet = JSON.parse(response); 
		 if (resultSet.status.trim() == "success") { 
			 $("#alertSuccess").text("Successfully Saved."); 
			 $("#alertSuccess").show(); 
			 
		 } else { 
			 $("#alertError").text("Error while Payment"); 
			 $("#alertError").show(); 
		 } 
		 
	 } else if (status == "error") { 
		 $("#alertError").text("No Updation."); 
		 $("#alertError").show(); 
	 } else { 
		 $("#alertError").text("Unknown error while Update.."); 
		 $("#alertError").show(); 
 	 }
	 
	 $("#hidItemIDSave").val(""); 
	 $("#customerForm")[0].reset(); 
}
