<%@page import="in.co.rays.ORSproject3.controller.MyProfileCtl"%>
<%@page import="in.co.rays.ORSproject3.utility.HTMLUtility"%>
<%@page import="in.co.rays.ORSproject3.utility.DataUtility"%>
<%@page import="in.co.rays.ORSproject3.utility.ServletUtility"%>
<%@page import="java.util.HashMap"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" />

<title>My Profile Page</title>

<style type="text/css">
.hm-gradient {
	background-image: url("<%=ORSView.APP_CONTEXT%>/img/white.png");
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
	background-color: #dabd53
}

.textfield {
	border: 1px solid #8080803b;
	height: 38px;
	padding-left: 6px;
}
</style>
</head>
<body>

	<form action="<%=ORSView.MY_PROFILE_CTL%>" method="post">
<div>
		<%@ include file="Header.jsp"%>
</div>
		<script type="text/javascript" src="../js/calendar.js"></script>

		<jsp:useBean id="dto" class="in.co.rays.ORSproject3.dto.UserDTO"
			scope="request"></jsp:useBean>

		

		<div class="container-fluid mt-4">
			<div class="row">
				<div class="col-md-3"></div>
				<div class="col-md-6">
<br>
<br>
<br>
					<div class="card">
						<div class="card-body">
							<%
								long id = DataUtility.getLong(request.getParameter("id"));
							%>
							<h3 class="text-center default-text py-3">My Profile </h3>

							<%
								if (!ServletUtility.getErrorMessage(request).equals("")) {
							%>
							<div class="alert alert-success alert-dismissible fade show">
								<button type="button" class="close" data-dismiss="alert">&times;</button>
								<strong>Error!</strong><%=ServletUtility.getErrorMessage(request)%>
							</div>
							<%
								}
							%>

							<%
								if (!ServletUtility.getSuccessMessage(request).equals("")) {
							%>
							<div class="alert alert-success alert-dismissible fade show">
								<button type="button" class="close" data-dismiss="alert">&times;</button>
								<strong>Success!</strong><%=ServletUtility.getSuccessMessage(request)%>
							</div>
							<%
								}
							%>
		
		
		
					<input type="hidden" name="id" value="<%=dto.getId()%>"> <input
				type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
			<input type="hidden" name="modifiedBy"
				value="<%=dto.getModifiedBy()%>"> <input type="hidden"
				name="createdDatetime"
				value="<%=DataUtility.getTimestamp(dto.getCreatedDatetime())%>">
			<input type="hidden" name="modifiedDatetime"
				value="<%=DataUtility.getTimestamp(dto.getModifiedDatetime())%>">

							<h6 style="color: #c7361f">
								LoginId<font color="red">*</font>
							</h6>

							<div class="md-form">

								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"><i style="width: 17px"
											class="fa fa-user"></i></span>
									</div>
									<input type="text" placeholder="Enter Email Id"
										class="form-control border" name="login"
										value="<%=DataUtility.getStringData(dto.getLogin())%>">
								</div>
								<font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("login", request)%>
								</font>

							</div>

							<h6 class="paddingclass"  style="color: #c7361f">
								First Name<font color="red">*</font>
							</h6>

							<div class="md-form">

								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"><i style="width: 17px"
											class="fa fa-user"></i></span>
									</div>
									<input type="text" placeholder="Enter First Name"
										class="form-control border" name="firstName"
										value="<%=DataUtility.getStringData(dto.getFirstName())%>">
								</div>
								<font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("firstName", request)%>
								</font>

							</div>

			<h6 class="paddingclass" style="color: #c7361f">
								Last Name<font color="red">*</font>
							</h6>
							<div class="md-form">

								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"><i style="width: 17px"
											class="fa fa-user"></i></span>
									</div>
									<input type="text"
										placeholder="Enter Last Name" class="form-control border"
										name="lastName"
										value="<%=DataUtility.getStringData(dto.getLastName())%>">
								</div>
								<font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("lastName", request)%>
								</font>

							</div>

							<h6 class="paddingclass" style="color: #c7361f">Gender<font color="red">*</font>
							</h6>
							<div class="md-form">

								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"><i style="width: 17px"
											class="fa fa-venus-mars"></i></span>
									</div>
									<%
										HashMap map = new HashMap();
										map.put("M", "Male");
										map.put("F", "Female");

										String htmlList = HTMLUtility.getList("gender", dto.getGender(), map);
									%> <%=htmlList%>

								</div>
								<font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("gender", request)%>
								</font>

							</div>

							<h6 class="paddingclass" style="color: #c7361f">Mobile No<font color="red">*</font>
							</h6>
							<div class="md-form">

								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"><i style="width: 17px"
											class="fa fa-mobile-phone"></i></span>
									</div>
									<input type="text"
										placeholder="Enter Mobile Number" class="form-control border"
										name="mobile"
										value="<%=DataUtility.getStringData(dto.getMobileNo())%>">
								</div>
								<font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("mobile", request)%>
								</font>

							</div>

							<h6 class="paddingclass" style="color: #c7361f">Date of Birth (dd/mm/yyyy)<font color="red">*</font>
							</h6>
							<div class="md-form">

								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"><i style="width: 17px"
											class="fa fa-calendar"></i></span>
									</div>
									<input type="text" name="dob"
									placeholder="Enter Date of Birth" <% if(dto!=null && dto.getId()>0){
    	  %> readonly="readonly" <%} %> class="form-control border"
									id="datepicker"
									value="<%=DataUtility.getDateString(dto.getDob())%>">
								</div>
								<font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("Date", request)%>
								</font>

							</div>

                     <br>
                           <div class="text-center"  id="defaultForm-email">
                           <input class="btn btn-success" type="submit"  name="operation" value="<%=MyProfileCtl.OP_SAVE%>">
                           <input class="btn btn-primary" type="submit"  name="operation" value="<%=MyProfileCtl.OP_CHANGE_MY_PASSWORD%>">
                          
                            </div>


</div>
</div>
</div>
<div class="col-md-3"></div>
</div>
</div>
<br><br><br>
<div><%@include file="Footer.jsp" %></div>
</body>
</html>