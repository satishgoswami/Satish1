<%@page import="in.co.rays.ORSproject3.controller.FacultyCtl"%>
<%@page import="in.co.rays.ORSproject3.utility.DataUtility"%>
<%@page import="in.co.rays.ORSproject3.utility.HTMLUtility"%>
<%@page import="in.co.rays.ORSproject3.utility.ServletUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" />

<title>Faculty View Page</title>

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
<jsp:useBean id="dto" class="in.co.rays.ORSproject3.dto.FacultyDTO" scope="request"></jsp:useBean>
<div>
<%@ include file="Header.jsp" %>
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


<form action="<%=ORSView.FACULTY_CTL%>" method="post">

<%
List list1=(List)request.getAttribute("collegelist");
List list2=(List)request.getAttribute("courselist");
List list3=(List)request.getAttribute("subjectlist");
%>

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
								<%=(id == 0) ? "Add Faculty" : "Update Faculty"%>
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
<input type="hidden" name="createdDateTime" value="<%=dto.getCreatedDatetime()%>">
<input type="hidden" name="modifiedDateTime" value="<%=dto.getModifiedDatetime()%>">

<h6 style="color: #c7361f">College Name<font color="red">*</font>
							</h6>

							<div class="md-form">

								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"><i style="width: 17px"
											class="fa fa-university" aria-hidden="true"></i></span>
									</div>
									<%=HTMLUtility.getList("collegeid",String.valueOf(dto.getCollegeId()) , list1) %>
								</div>
								<font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("collegeid", request)%>
								</font>

							</div>

							<h6 class="paddingclass" style="color: #c7361f">
								Course Name<font color="red">*</font>
							</h6>
							<div class="md-form">

								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"><i style="width: 17px"
											class="fa fa-columns" aria-hidden="true"></i></span>
									</div>
									<%=HTMLUtility.getList("courseid",String.valueOf(dto.getCourseId()) , list2) %></div>
								<font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("courseid", request)%>
								</font>

							</div>
							<h6 class="paddingclass" style="color: #c7361f">
								Subject Name<font color="red">*</font>
							</h6>
							<div class="md-form">

								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"><i style="width: 17px"
											class="fa fa-book" aria-hidden="true"></i></span>
									</div>
									<%=HTMLUtility.getList("subjectid",String.valueOf(dto.getSubjectId()) , list3) %></div>
								<font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("subjectid", request)%>
								</font>

							</div>
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
										class="form-control border" name="fname"
										value="<%=DataUtility.getStringData(dto.getFirstName())%>">
								</div>
								<font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("fname", request)%>
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
										name="lname"
										value="<%=DataUtility.getStringData(dto.getLastName())%>">
								</div>
								<font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("lname", request)%>
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
										value="<%=DataUtility.getStringData(dto.getEmailId())%>"
									<%=(dto.getId() > 0) ? "readonly" : ""%>">
								</div>
								<font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("login1", request)%>
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
								<font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("address1", request)%>
								</font>

							</div>

							<h6 class="paddingclass" style="color: #c7361f">Date of joining<font color="red">*</font>
							</h6>
							<div class="md-form">

								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"><i style="width: 17px"
											class="fa fa-calendar"></i></span>
									</div>
									<input type="text" name="doj" readonly="readonly" 
									placeholder="Enter Date of joining"  class="form-control border"
									id="datepicker"
									value="<%=DataUtility.getDateString(dto.getDateOfJoining())%>">
								</div>
								<font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("doj1", request)%>
								</font>

							</div>

							<h6 class="paddingclass" style="color: #c7361f">Qualification<font color="red">*</font>
							</h6>
							<div class="md-form">

								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"><i style="width: 17px"
											class="fa fa-address-card" aria-hidden="true"></i></span>
									</div>
									<input type="text" name="qual"
									placeholder="Enter Qualification" class="form-control border"
									value="<%=DataUtility.getStringData(dto.getQualification())%>">
								</div>
								<font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("qual1", request)%>
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
								<font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("mobile1", request)%>
								</font>

							</div>

<br>
<%if (id>0){ %>
                           <div class="text-center"  id="defaultForm-email">
                           <input class="btn btn-success" type="submit"  name="operation" value="<%=FacultyCtl.OP_UPDATE%>">
                           <input class="btn btn-warning" type="submit"  name="operation" value="<%=FacultyCtl.OP_CANCEL%>">
                          
                            </div>
                          
                          <%}else{ %> 
                           <div class="text-center"  id="defaultForm-email">
                           <input class="btn btn-success" type="submit"  name="operation" value="<%=FacultyCtl.OP_SAVE%>">
                           <input class="btn btn-warning" type="submit"  name="operation" value="<%=FacultyCtl.OP_RESET%>">
                          
                            </div>
                             <%} %>


</div>
</div>
</div>
<div class="col-md-3"></div>
</div>
</div>
<br><br><br>
</main>
</body>
<%@include file="Footer.jsp" %>
</html>