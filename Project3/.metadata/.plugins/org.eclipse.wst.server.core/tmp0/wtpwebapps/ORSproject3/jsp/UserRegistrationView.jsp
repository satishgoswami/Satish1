<%@page import="in.co.rays.ORSproject3.controller.ORSView"%>
<%@page import="in.co.rays.ORSproject3.controller.UserRegistrationCtl"%>
<%@page import="in.co.rays.ORSproject3.utility.HTMLUtility"%>
<%@page import="in.co.rays.ORSproject3.utility.DataUtility"%>
<%@page import="in.co.rays.ORSproject3.utility.ServletUtility"%>
<%@page import="java.util.HashMap"%>
<html>
<head>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" />
	

<title>Registration Page</title>
<style type="text/css">
/*  @import
	url(https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css)
	;

@import
	url(https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.4.3/css/mdb.min.css)
	;
 */ 
.hm-gradient {
	background-image: url("<%=ORSView.APP_CONTEXT%>/img/1233.jpg");
}

.darken-grey-text {
	color: #2E2E2E;
}

.danger-text {
	color: #ff3547;
}

.default-text {
	color: #c7361f;
}

.info-text {
	color: #33b5e5;
} 

.paddingclass {
	padding-top: 10px;
}

#textfield {
	border: 2px solid #8080803b;
}
</style>


<style type="text/css">
.setForm {
	padding-top: 5%;
	padding-left: 25%;
	width: 130%
}

.button {
	border-radius: 10px;
	padding: 10px;
	color: white;
	font-size: 20px;
	background-color: #00cc88
}

.textfield {
	border: 1px solid #8080803b;
	height: 38px;
	padding-left: 6px;
}
.hm-gradient .navbar .dropdown-menu a:hover {
    color: #0e0e0e!important;
}

</style>
</head>

<body class="hm-gradient">
<%@include file="Header.jsp"%> 
<script>
	$(function() {
		$("#datepicker").datepicker({
			changeMonth : true,
			changeYear : true,
			yearRange : '-38:-18',
			dateFormat : 'dd-mm-yy'
		});
	});
</script>
 
	<jsp:useBean id="dto" scope="request"
		class="in.co.rays.ORSproject3.dto.UserDTO" />

	<form action="<%=ORSView.USER_REGISTRATION_CTL%>" method="post">
		
		<div class="container-fluid mt-4">
			<!-- Grid row -->
			<div class="row">
				<!-- Grid column -->
				<div class="col-md-3"></div>
		
				<div class="col-md-6">
<br>
<br>
<br>
<br>
					<div class="card">
						<div class="card-body">
							<h3 class="text-center default-text py-3">User Registration:
							</h3>
							<!--Body-->
							<%
		
		long id=DataUtility.getLong(request.getParameter("id"));
		
		if(!ServletUtility.getSuccessMessage(request).equals("")){ %>
							<div class="alert alert-success alert-dismissible fade show">
								<button type="button" class="close" data-dismiss="alert">&times;</button>
								<strong>Success!</strong><%=ServletUtility.getSuccessMessage(request) %>
							</div>
							<%} %>

							<%if(!ServletUtility.getErrorMessage(request).equals("")){ %>
							<div class="alert alert-danger alert-dismissible fade show">
								<button type="button" class="close" data-dismiss="alert">&times;</button>
								<strong>Error!</strong><%=ServletUtility.getErrorMessage(request) %>
							</div>
							<%} %>
							<input type="hidden" name="id" value="<%=dto.getId()%>">
							<input type="hidden" name="createdBy"
								value="<%=dto.getCreatedBy()%>"> <input type="hidden"
								name="modifiedBy" value="<%=dto.getModifiedBy()%>"> <input
								type="hidden" name="createdDatetime"
								value="<%=DataUtility.getTimestamp(dto.getCreatedDatetime())%>">
							<input type="hidden" name="modifiedDatetime"
								value="<%=DataUtility.getTimestamp(dto.getModifiedDatetime())%>">



							<h6 style="color: #c7361f">
								First Name<font color="red">*</font>
							</h6>
							<div class="md-form">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text"><i style="width: 17px"
											class="fa fa-user"></i></span>
									</div>
									<input type="text" name="firstName"
										value="<%=DataUtility.getStringData(dto.getFirstName())%>"
										class="form-control border" placeholder="Enter First Name">
								</div>
								<font color="red" class="ml-5"> <%=ServletUtility.getErrorMessage("firstName", request) %></font>
							</div>







							<h6 style="color: #c7361f" class="paddingclass">
								Last Name<font color="red">*</font>
							</h6>
							<div class="md-form">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text"><i style="width: 17px"
											class="fa fa-user"></i></span>
									</div>
									<input type="text" name="lastName"
										value="<%=DataUtility.getStringData(dto.getLastName())%>"
										class="form-control border" placeholder="Enter Last Name">
								</div>
								<font color="red" class="ml-5"> <%=ServletUtility.getErrorMessage("lastName", request) %></font>
							</div>




							<h6 style="color: #c7361f" class="paddingclass">
								Email<font color="red">*</font>
							</h6>
							<div class="md-form">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text"><i style="width: 17px"
											class="fa fa-envelope"></i></span>
									</div>
									<input type="text" name="email"
										value="<%=DataUtility.getStringData(dto.getLogin())%>"
										class="form-control border" placeholder="Enter Email">
								</div>
								<font color="red" class="ml-5"> <%=ServletUtility.getErrorMessage("login", request) %></font>
							</div>




							<h6 style="color: #c7361f" class="paddingclass">
								Mobile Number<font color="red">*</font>
							</h6>
							<div class="md-form">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text"><i style="width: 17px"
											class="fa fa-mobile-phone"></i></span>
									</div>
									<input type="text" maxlength="10" name="mobileNumber"
										value="<%=DataUtility.getStringData(dto.getMobileNo())%>"
										class="form-control border" placeholder="Enter Mobile Number">
								</div>
								<font color="red" class="ml-5"> <%=ServletUtility.getErrorMessage("mobile", request) %></font>
							</div>

		<h6 style="color: #c7361f" class="paddingclass">
								Address<font color="red">*</font>
							</h6>
							<div class="md-form">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text"><i style="width: 17px"
											class="fa fa-envelope"></i></span>
									</div>
									<input type="text" name="address"
										value="<%=DataUtility.getStringData(dto.getAddress())%>"
										class="form-control border" placeholder="Enter Address">
								</div>
								<font color="red" class="ml-5"> <%=ServletUtility.getErrorMessage("address1", request) %></font>
							</div>


							<h6 style="color: #c7361f" class="paddingclass">
								Password<font color="red">*</font>
							</h6>
							<div class="md-form">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text"><i style="width: 17px"
											class="fa fa-rouble"></i></span>
									</div>
									<input type="password" name="password"
										value="<%=DataUtility.getStringData(dto.getPassword())%>"
										class="form-control border" placeholder="Enter Password">
								</div>
								<font color="red" class="ml-5"> <%=ServletUtility.getErrorMessage("password", request) %></font>
							</div>


							<h6 style="color: #c7361f" class="paddingclass">
								Confirm Password<font color="red">*</font>
							</h6>
							<div class="md-form">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text"><i style="width: 17px"
											class="fa fa-rouble"></i></span>
									</div>
									<input type="password" name="confirmPassword"
										value="<%=((id==0)?DataUtility.getStringData(dto.getConfirmPassword()):DataUtility.getStringData(dto.getPassword()))%>"
										class="form-control border"
										placeholder="Enter Confirm Password">
								</div>
								<font color="red" class="ml-5"> <%=ServletUtility.getErrorMessage("confirmPassword", request) %></font>
							</div>


							<%HashMap<String,String> map=new HashMap<String,String>();
                           map.put("male", "male");
                           map.put("female","female");
                           String gender=HTMLUtility.getList("gender",dto.getGender(), map);
                           %>

							<h6 style="color: #c7361f" class="paddingclass">
								Gender<font color="red">*</font>
							</h6>
							<div class="md-form">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text"><i style="width: 17px"
											class="fa fa-venus-mars"></i></span>
									</div>
									<%=gender%>
								</div>
								<font color="red" class="ml-5"> <%=ServletUtility.getErrorMessage("gender", request) %></font>
							</div>




							<h6 style="color: #c7361f" class="paddingclass">
								Date of Birth<font color="red">*</font>
							</h6>
							<div class="md-form">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text"><i style="width: 17px"
											class="fa fa-calendar"></i></span>
									</div>
									<input type="text" id="datepicker" readonly="readonly"
										name="dob"
										value="<%=DataUtility.getDateString(dto.getDob())%>"
										class="form-control border" placeholder="Enter dob">


								</div>
								<span> <font color="red" class="ml-5"> <%=ServletUtility.getErrorMessage("dob", request) %></font></span>
							</div>





							<div class="text-center paddingclass" id="defaultForm-email">
								<input class="btn btn-success btn-md" style="font-size: 17px"
									type="submit" name="operation"
									value="<%=UserRegistrationCtl.OP_SIGN_UP%>"> <input
									class="btn btn-warning btn-md" style="font-size: 17px"
									type="submit" name="operation"
									value="<%=UserRegistrationCtl.OP_RESET%>">

							</div>
						</div>
					</div>
				</div>

				<div class="col-md-3"></div>
			</div>
		</div>
	</form>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>

<div><%@include file="Footer.jsp" %></div>

</body>
</html>