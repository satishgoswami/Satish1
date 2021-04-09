<%@page import="in.co.rays.ORSproject3.controller.CourseCtl"%>
<%@page import="in.co.rays.ORSproject3.utility.HTMLUtility"%>
<%@page import="in.co.rays.ORSproject3.utility.DataUtility"%>
<%@page import="in.co.rays.ORSproject3.utility.ServletUtility"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.HashMap"%>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" />
<title>Course View page</title>

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
	<%@ include file="Header.jsp"%>
	</div>
	<jsp:useBean id="dto" class="in.co.rays.ORSproject3.dto.CourseDTO"
		scope="request"></jsp:useBean>

	<form action="<%=ORSView.COURSE_CTL%>" method="post">


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
								<%=(id == 0) ? "Add Course" : "Update Course"%>
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

		<input type="hidden" name="modifiedBy"
			value="<%=dto.getCreatedBy()%>"> 
			<input type="hidden"
			name="createdDateime" value="<%=dto.getCreatedDatetime()%>">
		<input type="hidden" name="modifiedDateime"
			value="<%=dto.getModifiedDatetime()%>">


<h6 style="color: #c7361f">Course<font color="red">*</font>
							</h6>

							<div class="md-form">

								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"><i style="width: 17px"
											class="fa fa-university" aria-hidden="true"></i></span>
									</div>
									<input type="text" placeholder="Enter Course Name"
										class="form-control border" name="CourseName"
										value="<%=DataUtility.getStringData(dto.getCourseName())%>">
								</div>
								<font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("CourseName", request)%>
								</font>

							</div>

<h6 class="paddingclass" style="color: #c7361f">
								Duration<font color="red">*</font>
							</h6>
							<div class="md-form">

								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"><i style="width: 17px"
											class="fa fa-address-card" aria-hidden="true"></i></span>
									</div>
				<%
					HashMap map = new LinkedHashMap();
					map.put("1 year","1 year");
					map.put("2 years","2 years");
					map.put("3 years","3 years");
					map.put("4 years","4 years");
					map.put("5 years","5 years");
%>
					<%=HTMLUtility.getList("Duration", dto.getDuration(), map)%>
								</div>
								<font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("Duration", request)%>
								</font>

							</div>

<h6 class="paddingclass" style="color: #c7361f">
								Description<font color="red">*</font>
							</h6>
							<div class="md-form">

								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"><i style="width: 17px"
											class="fa fa-bars" aria-hidden="true"></i></span>
									</div>
									<textarea name="Description" class="form-control border" placeholder="Enter Description" style='width: 174px;'><%=DataUtility.getStringData(dto.getDescription())%></textarea>
								</div>
								<font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("Description", request)%>
								</font>

							</div>
<br>
<%if (id>0){ %>
                           <div class="text-center"  id="defaultForm-email">
                           <input class="btn btn-success" type="submit"  name="operation" value="<%=CourseCtl.OP_UPDATE%>">
                           <input class="btn btn-warning" type="submit"  name="operation" value="<%=CourseCtl.OP_CANCEL%>">
                          
                            </div>
                          
                          <%}else{ %> 
                           <div class="text-center"  id="defaultForm-email">
                           <input class="btn btn-success" type="submit"  name="operation" value="<%=CourseCtl.OP_SAVE%>">
                           <input class="btn btn-warning" type="submit"  name="operation" value="<%=CourseCtl.OP_RESET%>">
                          
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