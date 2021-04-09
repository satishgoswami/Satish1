

<%@page import="in.co.rays.ORSproject3.controller.ChangePasswordCtl"%>
<%@page import="in.co.rays.ORSproject3.utility.DataUtility"%>
<%@page import="in.co.rays.ORSproject3.utility.ServletUtility"%>
<html>
<head>
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16"/>
<title>Change Password</title>
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
    <form action="<%=ORSView.CHANGE_PASSWORD_CTL%>" method="post">
        
<div>
        <%@ include file="Header.jsp"%>
</div>
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
							<h3 class="text-center default-text py-3">
								Change Password
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
                  
            <input type="hidden" name="id" value="<%=dto.getId()%>">
            <input type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
            <input type="hidden" name="modifiedBy" value="<%=dto.getModifiedBy()%>">
            <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(dto.getCreatedDatetime())%>">
            <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(dto.getModifiedDatetime())%>">

      
      							<h6 style="color: #c7361f">
								Old password<font color="red">*</font>
							</h6>
      
      							<div class="md-form">

								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"><i style="width: 17px"
											class="fa fa-rouble"></i></span>
									</div>
									<input type="password" placeholder="Enter Old Password"
										class="form-control border" name="oldPassword"
										value="<%=DataUtility.getStringData(request.getParameter("oldPassword"))%>">
								</div>
								<font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("oldPassword", request)%>
								</font>

							</div>
      
       							<h6 class="paddingclass" style="color: #c7361f">
							New	Password<font color="red">*</font>
							</h6>
							<div class="md-form">

								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"><i style="width: 17px"
											class="fa fa-rouble"></i></span>
									</div>
									<input type="password"
										placeholder="Enter New Password" class="form-control border"
										name="newPassword"
										value="<%=DataUtility.getStringData(request.getParameter("newPassword"))%>">
								</div>
								<font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("newPassword", request)%>
								</font>

							</div>
       							<h6 class="paddingclass" style="color: #c7361f">
							Confirm	Password<font color="red">*</font>
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
										value="<%=DataUtility.getStringData(request.getParameter("confirmPassword"))%>">
								</div>
								<font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("confirmPassword", request)%>
								</font>

							</div>
							
							<br>
							
       
                    <div class="text-center"  id="defaultForm-email">
                           <input class="btn btn-success" type="submit"  name="operation" value="<%=ChangePasswordCtl.OP_SAVE%>">
                           <input class="btn btn-primary" type="submit"  name="operation" value="<%=ChangePasswordCtl.OP_CHANGE_MY_PROFILE %>">
                          
                            </div>
              

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