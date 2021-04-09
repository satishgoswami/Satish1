<%@page import="in.co.rays.ORSproject3.controller.CollegeCtl"%>
<%@page import="in.co.rays.ORSproject3.utility.DataUtility"%>
<%@page import="in.co.rays.ORSproject3.utility.ServletUtility"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16"/>
<title>College View Page</title>
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
<div>
<%@include file="Header.jsp" %>
</div>
<jsp:useBean id="dto" class="in.co.rays.ORSproject3.dto.CollegeDTO" scope="request"></jsp:useBean>
  
  <form action="<%=ORSView.COLLEGE_CTL%>" method ="post">


		<div class="container-fluid mt-4">
			<div class="row">
				<div class="col-md-3"></div>
	
				<div class="col-md-6">
					<br>
					<br>
					<br>
					<br>
					<div class="card">
						<div class="card-body">
							<%
								long id = DataUtility.getLong(request.getParameter("id"));
							%>
							<h3 class="text-center default-text py-3">
								<%=(id == 0) ? "Add College" : "Update College"%>
							</h3>

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



  <input type="hidden" name="id" value="<%=dto.getId() %>">
  <input type="hidden" name="createdBy" value="<%=dto.getCreatedBy() %>">
  <input type="hidden" name="modifiedBy" value="<%=dto.getModifiedBy() %>">
  <input type="hidden" name="createDateTime" value="<%=DataUtility.getTimestamp(dto.getCreatedDatetime()) %>">
  <input type="hidden" name="modifiedDateTime" value="<%=DataUtility.getTimestamp(dto.getModifiedDatetime()) %>">

							<h6 style="color: #c7361f">
								Name<font color="red">*</font>
							</h6>

							<div class="md-form">

								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"><i style="width: 17px"
											class="fa fa-university" aria-hidden="true"></i></span>
									</div>
									<input type="text" placeholder="Enter College Name"
										class="form-control border" name="cName"
										value="<%=DataUtility.getStringData(dto.getName())%>">
								</div>
								<font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("c_Name", request)%>
								</font>

							</div>

<h6 class="paddingclass" style="color: #c7361f">
								Address<font color="red">*</font>
							</h6>
							<div class="md-form">

								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"><i style="width: 17px"
											class="fa fa-address-card" aria-hidden="true"></i></span>
									</div>
									<input type="text"
										placeholder="Enter Address" class="form-control border"
										name="cAddress"
										value="<%=DataUtility.getStringData(dto.getAddress())%>">
								</div>
								<font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("c_Address", request)%>
								</font>

							</div>
<h6 class="paddingclass" style="color: #c7361f">
								State<font color="red">*</font>
							</h6>
							<div class="md-form">

								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"><i style="width: 17px"
											class="fa fa-bars" aria-hidden="true"></i></span>
									</div>
									<input type="text"
										placeholder="Enter State Name" class="form-control border"
										name="cState"
										value="<%=DataUtility.getStringData(dto.getAddress())%>">
								</div>
								<font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("c_State", request)%>
								</font>

							</div>
<h6 class="paddingclass" style="color: #c7361f">
								City<font color="red">*</font>
							</h6>
							<div class="md-form">

								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"><i style="width: 17px"
											class="fa fa-address-card-o" aria-hidden="true"></i></span>
									</div>
									<input type="text"
										placeholder="Enter City Name" class="form-control border"
										name="cCity"
										value="<%=DataUtility.getStringData(dto.getAddress())%>">
								</div>
								<font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("c_City", request)%>
								</font>

							</div>
  
<h6 class="paddingclass" style="color: #c7361f">
								Phone<font color="red">*</font>
							</h6>
							<div class="md-form">

								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"><i style="width: 17px"
											class="fa fa-phone-square" aria-hidden="true"></i></span>
									</div>
									<input type="text"
										placeholder="Enter Phone Number" class="form-control border"
										name="cPhone"
										value="<%=DataUtility.getStringData(dto.getPhoneNo())%>">
								</div>
								<font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("c_Phone", request)%>
								</font>

							</div>
							<br>
							
<%if (id>0){ %>
                           <div class="text-center"  id="defaultForm-email">
                           <input class="btn btn-success" type="submit"  name="operation" value="<%=CollegeCtl.OP_UPDATE%>">
                           <input class="btn btn-warning" type="submit"  name="operation" value="<%=CollegeCtl.OP_CANCEL%>">
                          
                            </div>
                          
                          <%}else{ %> 
                           <div class="text-center"  id="defaultForm-email">
                           <input class="btn btn-success" type="submit"  name="operation" value="<%=CollegeCtl.OP_SAVE%>">
                           <input class="btn btn-warning" type="submit"  name="operation" value="<%=CollegeCtl.OP_RESET%>">
                          
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