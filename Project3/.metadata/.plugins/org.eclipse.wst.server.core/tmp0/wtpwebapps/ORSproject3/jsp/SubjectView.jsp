<%@page import="in.co.rays.ORSproject3.controller.SubjectCtl"%>
<%@page import="in.co.rays.ORSproject3.utility.DataUtility"%>
<%@page import="in.co.rays.ORSproject3.utility.HTMLUtility"%>
<%@page import="in.co.rays.ORSproject3.utility.ServletUtility"%>
<%@page import="java.util.List"%>
<html>
<head>
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" />

<title>Subject View Page</title>

<style type="text/css">

.hm-gradient {
background-image: url("<%=ORSView.APP_CONTEXT%>/img/1233.jpg");
}

.darken-grey-text {
    color: #2E2E2E;
}
.btn-primary{
    color: #cca300;
	width:85px;

}

.danger-text {
    color: #ff3547; }
    
.default-text 
{
    color: #c7361f; 
}
.info-text {
    color: #33b5e5; 
}
.paddingclass{
padding-top: 10px;
}

</style>
<style type="text/css">
.setForm{
padding-top: 5%;
padding-left: 25%;
width: 130%
}
.button{
border-radius:10px;padding:10px;color:white;font-size:20px;background-color:#dabd53
}
.textfield{
border: 1px solid #8080803b;height: 38px; padding-left: 6px;
}
</style>

</head>
<body>
 
<%@include file="Header.jsp" %>
<jsp:useBean id="dto" class="in.co.rays.ORSproject3.dto.SubjectDTO" scope="request"></jsp:useBean>

<form action="<%=ORSView.SUBJECT_CTL%>" method="post">

     		<main>

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
								<%=(id == 0) ? "Add Subject" : "Update Subject"%>
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



<%
List l = (List)request.getAttribute("CourseList");
%>


<input type="hidden" name="id" value="<%=dto.getId()%>">
<input type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
<input type="hidden" name="modifiedBy" value="<%=dto.getModifiedBy()%>">
<input type="hidden" name="createdDateTime" value="<%=dto.getCreatedDatetime()%>">
<input type="hidden" name="modifiedDateTime" value="<%=dto.getModifiedDatetime()%>">



<h6 style="color: #c7361f">Course Name<font color="red">*</font>
							</h6>

							<div class="md-form">

								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"><i style="width: 17px"
											class="fa fa-user"></i></span>
									</div>
									<%=HTMLUtility.getList("CourseId",String.valueOf(dto.getCourseId()),l) %>	</div>
								<font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("CourseName", request)%>
								</font>

							</div>

							<h6 class="paddingclass" style="color: #c7361f">
								Subject Name<font color="red">*</font>
							</h6>
							<div class="md-form">

								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"><i style="width: 17px"
											class="fa fa-bars" aria-hidden="true"></i></span>
									</div>
																		<input type="text" placeholder="Enter Subject Name"
										class="form-control border" name="SubjectName"
										value="<%=DataUtility.getStringData(dto.getSubjectName())%>">
</div>
								<font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("SubjectName", request)%>
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
                <% if(dto!=null && dto.getId()>0){
               %>
<div class="text-center"  id="defaultForm-email">
                           <input class="btn btn-success" type="submit"  name="operation" value="<%=SubjectCtl.OP_UPDATE%>">
                           <input class="btn btn-warning" type="submit"  name="operation" value="<%=SubjectCtl.OP_CANCEL%>">
                          
                            </div>
                          
                          <%}else{ %> 
                           <div class="text-center"  id="defaultForm-email">
                           <input class="btn btn-success" type="submit"  name="operation" value="<%=SubjectCtl.OP_SAVE%>">
                           <input class="btn btn-warning" type="submit"  name="operation" value="<%=SubjectCtl.OP_RESET%>">
                          
                            </div>
                             <%} %>


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