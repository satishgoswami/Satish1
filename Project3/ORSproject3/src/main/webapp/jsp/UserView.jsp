<%@page import="in.co.rays.ORSproject3.controller.UserCtl"%>
<%@page import="in.co.rays.ORSproject3.utility.HTMLUtility"%>
<%@page import="in.co.rays.ORSproject3.utility.DataUtility"%>
<%@page import="in.co.rays.ORSproject3.utility.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

 
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16" />

<title>User View Page</title>

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
	<form action="<%=ORSView.USER_CTL%>" method="post">
<div>
		<%@ include file="Header.jsp"%>
	</div>
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
	

		<jsp:useBean id="dto" class="in.co.rays.ORSproject3.dto.UserDTO"
			scope="request"></jsp:useBean>

		<%
			List lt = (List) request.getAttribute("role_list");
		%>
	

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
							<h3 class="text-center default-text py-3">
								<%=(id == 0) ? "Add User" : "Update User"%>
							</h3>

							
			<%
							if (ServletUtility.getSuccessMessage(request) != null
									&& ServletUtility.getSuccessMessage(request).length() > 0) {
						%>
						<div class="alert alert-success alert-dismissible fade show">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
							<strong>Success..!</strong> <%=ServletUtility.getSuccessMessage(request) %>
						</div>
						<%
							}
						%>
						<%
							if (ServletUtility.getErrorMessage(request) != null
									&& ServletUtility.getErrorMessage(request).length() > 0) {
						%>

						<div class="alert alert-danger alert-dismissible fade show">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
							<strong>Error..!</strong> <%=ServletUtility.getErrorMessage(request) %>
						</div>
						<%
							}
						%>


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

							<h6 class="paddingclass" style="color: #c7361f">
								LoginId<font color="red">*</font>
							</h6>
							<div class="md-form">

								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"><i style="width: 17px"
											class="fa fa-envelope"></i></span>
									</div>
									<input type="text"
										placeholder="Enter Login Id" class="form-control border"
										name="login"
										value="<%=DataUtility.getStringData(dto.getLogin())%>"
									<%=(dto.getId() > 0) ? "readonly" : ""%>">
								</div>
								<font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("login", request)%>
								</font>

							</div>


							<input type="hidden"
								value="<%=HTMLUtility.getList("roleName", String.valueOf(dto.getRoleId()), lt)%>">

							<%
								if (dto.getId() > 0 && dto != null) {
							%>

							<input type="hidden" name="password"
								value="<%=DataUtility.getStringData(dto.getPassword())%>">
							<input type="hidden" name="confirmPassword"
								value="<%=DataUtility.getStringData(dto.getConfirmPassword())%>">


							<%
								} else {
							%>

							<h6 class="paddingclass" style="color: #c7361f">
								Password<font color="red">*</font>
							</h6>
							<div class="md-form">

								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"><i style="width: 17px"
											class="fa fa-rouble"></i></span>
									</div>
									<input type="password"
										placeholder="Enter Password" class="form-control border"
										name="password"
										value="<%=DataUtility.getStringData(dto.getPassword())%>">
								</div>
								<font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("password", request)%>
								</font>

							</div>

							<h6 class="paddingclass" style="color: #c7361f">Confirm Password<font color="red">*</font>
							</h6>
							<div class="md-form">

								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"><i style="width: 17px"
											class="fa fa-rouble"></i></span>
									</div>
									<input type="password"
										placeholder="Enter Confirm Password" class="form-control border"
										name="confirmPassword"
										value="<%=DataUtility.getStringData(dto.getConfirmPassword())%>">
								</div>
								<font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("confirmPassword", request)%>
								</font>

							</div>


							<%
								}
							%>


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
							
							<h6 class="paddingclass" style="color: #c7361f">Gender<font color="red">*</font>
							</h6>
							<div class="form-group">

								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"><i style="width: 17px"
											class="fa fa-venus-mars"></i></span>
									</div>
									<%
										HashMap map = new HashMap();
										map.put("Male", "Male");
										map.put("Female", "Female");

										String htmlList = HTMLUtility.getList("gender", dto.getGender(), map);
									%> <%=htmlList%>

								</div>
								<font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("gender", request)%>
								</font>

							</div>
							
							<%String student=HTMLUtility.getList("roleId", String.valueOf(dto.getRoleId()), lt); %>
        
                           <h6 class="paddingclass" style="color: #c7361f " >Role<font color="red">*</font></h6>
                           
                          <div class="md-form">
                 
                 		<div class="input-group mb-3">
                             <div class="input-group-prepend">
                             <span class="input-group-text"><i style="width: 17px" class="fa fa-venus-mars"></i></span>
                                      </div>
                           	<%=student%>
                                      </div>
								<font color="red" class="ml-5"> <%=ServletUtility.getErrorMessage("role", request) %></font>
           
                          </div>
							
							<h6 class="paddingclass" style="color: #c7361f">Date of Birth (dd/mm/yyyy)<font color="red">*</font>
							</h6>
							<div class="md-form">

								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"><i style="width: 17px"
											class="fa fa-calendar"></i></span>
									</div>
		
									<input type="text"  id="datepicker"  readonly="readonly"
									name="dob"
									value="<%=DataUtility.getDateString(dto.getDob())%>"
						class="form-control border"
									placeholder="Enter Date of Birth" 
									>
								</div>
								<font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("dob", request)%>
								</font>

							</div>
								
							<h6 class="paddingclass" style="color: #c7361f">Address<font color="red">*</font>
							</h6>
							
							<div class="md-form">

								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"><i style="width: 17px"
											class="fa fa-address-card" aria-hidden="true"></i></span>
									</div>
									<input type="text" name="address"
									placeholder="Enter Address" class="form-control border"
									value="<%=DataUtility.getStringData(dto.getAddress())%>">
								</div>
								<font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("address", request)%>
								</font>

							</div>

<br>
<%if (id>0){ %>
                           <div class="text-center"  id="defaultForm-email">
                           <input class="btn btn-success" style="font-size: 17px" type="submit"  name="operation" value="<%=UserCtl.OP_UPDATE%>">
                           <input class="btn btn-warning" style="font-size: 17px" type="submit"  name="operation" value="<%=UserCtl.OP_CANCEL%>">
                          
                            </div>
                          
                          <%}else{ %> 
                           <div class="text-center"  id="defaultForm-email">
                           <input class="btn btn-success btn-md" style="font-size: 17px" type="submit"  name="operation" value="<%=UserCtl.OP_SAVE%>">
                           <input class="btn btn-warning btn-md" style="font-size: 17px" type="submit"  name="operation" value="<%=UserCtl.OP_RESET%>">
                          
                            </div>
                             <%} %>


</div>
</div>
</div>
<div class="col-md-3"></div>
</div>
</div>
<br><br><br>

</form>
<div><%@include file="Footer.jsp" %></div>
</body>
</html>