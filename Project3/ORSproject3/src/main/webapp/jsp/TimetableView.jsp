<%@page import="in.co.rays.ORSproject3.controller.TimetableCtl"%>
<%@page import="in.co.rays.ORSproject3.utility.DataUtility"%>
<%@page import="in.co.rays.ORSproject3.utility.HTMLUtility"%>
<%@page import="in.co.rays.ORSproject3.utility.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.HashMap"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" />
<title>Timetable View Page</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script>
function DisableSunday(date) {
	 
	  var day = date.getDay();
	 // If day == 0 then it is Sunday
	 if (day == 0) {
	 
	 return [false] ; 
	 
	 } else { 
	 
	 return [true] ;
	 }
	  
	}
	$(function() {
		$("#examdate").datepicker({
			beforeShowDay: DisableSunday,
			changeMonth : true,
			changeYear : true,
			yearRange:'0:+1',
			dateFormat: 'dd-mm-yy',
			minDate: 0          //It will disable previous dates
			
		});
	});
</script>

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
<%@include file="Header.jsp"%>
</div>
<jsp:useBean id="dto" class="in.co.rays.ORSproject3.dto.TimetableDTO" scope="request"></jsp:useBean>

<form action="<%=ORSView.TIMETABLE_CTL%>" method="post">

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
								<%=(id == 0) ? "Add Timetable" : "Update Timetale"%>
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
List clist= (List)request.getAttribute("courseList");
List slist= (List)request.getAttribute("subjectList");
%>

<input type="hidden" name ="id" value="<%=dto.getId()%>">
<input type="hidden" name ="createdBy" value="<%=dto.getCreatedBy()%>">
<input type="hidden" name ="modifiedBy" value="<%=dto.getModifiedBy()%>">
<input type="hidden" name ="createDateTime" value="<%=dto.getCreatedDatetime()%>">
<input type="hidden" name ="modifiedDateTime" value="<%=dto.getModifiedDatetime()%>">


<h6 style="color: #c7361f">Course Name<font color="red">*</font>
							</h6>

							<div class="md-form">

								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"><i style="width: 17px"
											class="fa fa-columns" aria-hidden="true"></i></span>
									</div>
									<%=HTMLUtility.getList("courseid",String.valueOf(dto.getCourseId()), clist)%>	</div>
								<font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("courseName1", request)%>
								</font>

							</div>

<h6 class="paddingclass"  style="color: #c7361f">Subject Name<font color="red">*</font>
							</h6>

							<div class="md-form">

								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"><i style="width: 17px"
											class="fa fa-book" aria-hidden="true"></i></span>
									</div>
					<%=HTMLUtility.getList("subjectid",String.valueOf(dto.getSubId()),slist)%>			</div>
								<font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("subjectName1", request)%>
								</font>

							</div>

<h6 class="paddingclass" style="color: #c7361f">
								Semester<font color="red">*</font>
							</h6>
							<div class="md-form">

								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"><i style="width: 17px"
											class="fa fa-bars" aria-hidden="true"></i></span>
									</div>
	 <%
     
      LinkedHashMap map = new LinkedHashMap();
    map.put("1st","1st");
    map.put("2nd","2nd");
    map.put("3rd","3rd");
    map.put("4th","4th");
    map.put("5th","5th");
    map.put("6th","6th");
    map.put("7th","7th");
    map.put("8th","8ht");
    String htmllist = HTMLUtility.getList("sem",dto.getSemester(), map);
    %>
    <%=htmllist%>							</div>
								<font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("sem1", request)%>
								</font>

							</div>  
    
<h6 class="paddingclass" style="color: #c7361f">
								Exam Time<font color="red">*</font>
							</h6>
							<div class="md-form">

								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"><i style="width: 17px"
											class="fa fa-clock-o" aria-hidden="true"></i></span>
									</div>
	<%
      HashMap map1 = new HashMap();
    map1.put("8 to 10 AM","8 to 10 AM");
    map1.put("12 to 2 PM","12 to 2 PM");
    map1.put("3 to 5 PM","3 to 5 PM");
    
    String htmllist1 = HTMLUtility.getList("examtime",dto.getExamTime(), map1);
    %>
    <%=htmllist1%>							</div>
								<font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("examtime1", request)%>
								</font>

							</div>  
<h6 class="paddingclass" style="color: #c7361f">Exam Date<font color="red">*</font>
							</h6>

							<div class="md-form">

								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"><i style="width: 17px"
											class="fa fa-calendar" aria-hidden="true"></i></span>
									</div>
								<input type="text" name ="examdate" id ="examdate" class="form-control border" readonly="readonly"
    value="<%=DataUtility.getDateString(dto.getExamDate())%>" placeholder="Enter Exam Date"></div>
								<font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("examdate1", request)%>
								</font>

							</div>
    <br>
<%if (id>0){ %>
                           <div class="text-center"  id="defaultForm-email">
                           <input class="btn btn-success" type="submit"  name="operation" value="<%=TimetableCtl.OP_UPDATE%>">
                           <input class="btn btn-warning" type="submit"  name="operation" value="<%=TimetableCtl.OP_CANCEL%>">
                          
                            </div>
                          
                          <%}else{ %> 
                           <div class="text-center"  id="defaultForm-email">
                           <input class="btn btn-success" type="submit"  name="operation" value="<%=TimetableCtl.OP_SAVE%>">
                           <input class="btn btn-warning" type="submit"  name="operation" value="<%=TimetableCtl.OP_RESET%>">
                          
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