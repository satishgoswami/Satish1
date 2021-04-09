<%@page import="in.co.rays.ORSproject3.utility.DataUtility"%>
<%@page import="in.co.rays.ORSproject3.controller.ForgetPasswordCtl"%>
<%@page import="in.co.rays.ORSproject3.utility.ServletUtility"%>
<html>
<head>
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" />
<title>Forget Password Page</title>

<!-- font-awesome library -->
<style type="text/css">
@import
	url(https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css)
	;

@import
	url(https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.4.3/css/mdb.min.css)
	;


.hm-gradient .navbar .dropdown-menu a:hover {
    color: #0e0e0e!important;
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

.textfield {
	border: 1px solid #8080803b;
	height: 38px;
	padding-left: 6px;
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
</style>
</head>

<body>
	<jsp:useBean id="dto" scope="request"
		class="in.co.rays.ORSproject3.dto.UserDTO"> </jsp:useBean>

	<div>
		<%@include file="Header.jsp"%>
	</div>


	<form action="<%=ORSView.FORGET_PASSWORD_CTL%>" method="post">

		<main> <!--MDB Forms-->
		<div class="container mt-4">


			<!-- Grid row -->
			<div class="row">
				<div class="col-md-3"></div>
				<!-- Grid column -->
				<div class="col-md-6">
<br>
<br>
<br>
<br>
					<div class="card">
						<div class="card-body">

							<h3 class="text-center default-text py-3">Forget Password:</h3>
							<!--Body-->
							<%if(!ServletUtility.getSuccessMessage(request).equals("")){ %>
							<div class="alert alert-success alert-dismissible fade show">
								<button type="button" class="close" data-dismiss="alert">&times;</button>
								<strong>Success!</strong><%=ServletUtility.getSuccessMessage(request) %>
							</div>
							<%} %>

							<%if(!ServletUtility.getErrorMessage(request).equals("")){ %>
							<div class="alert alert-success alert-dismissible fade show">
								<button type="button" class="close" data-dismiss="alert">&times;</button>
							<h5>	<strong>Error!</strong><font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
							  </h5>  </div>
							<%} %>



							<h6 style="color: #c7361f">
								<b>Please Enter Your Email:</b>
							</h6>
							<div class="md-form">
							
							
							 <div class="input-group">
                            <div class="input-group-prepend">
                               <span class="input-group-text">@</span>
                                 </div>
                        <input type="text" placeholder="Enter Email" class="form-control border" name="login" value="<%=DataUtility.getStringData(dto.getLogin())%>">
                                </div>
							<font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("login",request) %> </font>
							
                                 </div>							
							
									
					
								
							<div class="text-center">
								<input class="btn btn-success btn-md" style="font-size: 17px" type="submit" class="button" name="operation"
									value="<%=ForgetPasswordCtl.OP_GO%>">
							</div>
						</div>
					</div>
				</div>


				<div class="col-md-3"></div>
			</div>
		</div>
	</form>
<div><%@include file="Footer.jsp" %></div>
</body>

</html>