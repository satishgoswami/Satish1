<%@page import="in.co.rays.ORSproject3.dto.TimetableDTO"%>
<%@page import="in.co.rays.ORSproject3.utility.HTMLUtility"%>
<%@page import="in.co.rays.ORSproject3.utility.DataUtility"%>
<%@page import="in.co.rays.ORSproject3.controller.TimetableListCtl"%>
<%@page import="in.co.rays.ORSproject3.utility.ServletUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" />
<title>TimetableList View Page</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" />

<script src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js">
	
</script>
<script type="text/javascript" src="<%=ORSView.APP_CONTEXT%>/js/checkbox11.js"></script>

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

			return [ false ];

		} else {

			return [ true ];
		}

	}

	$(function() {
		$("#examdate").datepicker({
			beforeShowDay : DisableSunday,
			changeMonth : true,
			changeYear : true,
			yearRange : '1980:2020',
			dateFormat : 'dd-mm-yy'

		});
	});
</script>


<style>
body {
	background-image: url(/ORS_3/img/nasa1.jpg);
	background-size: 100% 100%;
	width: 100%;
	/* 	height:100vh; */
}

.btn-success {
	width: 85px;
}

.btn-warning {
	width: 95px;
}

.btn-primary {
	width: 85px;
}
</style>


</head>
<body>
	<div>
		<%@include file="Header.jsp"%>
	</div>
	<jsp:useBean id="dto" class="in.co.rays.ORSproject3.dto.TimetableDTO"
		scope="request"></jsp:useBean>

	<form action="<%=ORSView.TIMETABLE_LIST_CTL%>" method="post">

		<br>
		<br>
		<br>
		<h1
			style="color: white; text-align: center; font-variant: small-caps;">Timetable
			List</h1>

		<%
			int nextListSize = DataUtility.getInt(request.getAttribute("nextListSize").toString());
		%>

		<%
			if (ServletUtility.getSuccessMessage(request) != null
					&& ServletUtility.getSuccessMessage(request).length() > 0) {
		%>
		<div class="alert alert-success alert-dismissible fade show">
			<button type="button" class="close" data-dismiss="alert">&times;</button>
			<strong>Success..!</strong>
			<%=ServletUtility.getSuccessMessage(request)%>
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
			<strong>Error..!</strong>
			<%=ServletUtility.getErrorMessage(request)%>
		</div>
		<%
			}
		%>



		<%
			List clist = (List) request.getAttribute("courseList");
			List slist = (List) request.getAttribute("subjectList");
		%>

		<%
			List list1 = ServletUtility.getList(request);
			System.out.println("list size=====" + list1.size());
			if (list1 == null || list1.size() == 0) {
		%>
		<center>
			<input type="submit" name="operation" class="btn btn-primary "
				style="margin-left: 54px; margin-top: 24px;"
				value="<%=TimetableListCtl.OP_BACK%>">
		</center>
		<%
			} else {
		%>

		<div class="row">
			<div class="col-sm-3">
				<div class="form-group">

					<label style="color: white">Course Name</label>
					<div class="input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fa fa-columns"
								aria-hidden="true"></i></span>
						</div>
						<%=HTMLUtility.getList1("courseid", String.valueOf(dto.getCourseId()), clist)%>
					</div>
				</div>
			</div>

			<div class="col-sm-3">
				<div class="form-group">

					<label style="color: white">Subject Name</label>
					<div class="input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fa fa-book"
								aria-hidden="true"></i></span>
						</div>
						<%=HTMLUtility.getList1("subjectid", String.valueOf(dto.getSubId()), slist)%>
					</div>
				</div>
			</div>

			<div class="col-sm-3">
				<div class="form-group">

					<label style="color: white">Exam Date</label>
					<div class="input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fa fa-calendar"
								aria-hidden="true"></i></span>
						</div>
						<input type="text" name="examdate" class="form-control"
							placeholder="Enter ExamDate" id="examdate" readonly="readonly"
							value="<%=DataUtility.getDateString(dto.getExamDate())%>">

					</div>
				</div>
			</div>
			<div class="col-sm-3 ">
				<input type="submit" name="operation" class="btn btn-success"
					style="margin-top: 35px;" value="<%=TimetableListCtl.OP_SEARCH%>">
				<input type="submit" name="operation" class="btn btn-warning"
					style="margin-top: 35px;" value="<%=TimetableListCtl.OP_RESET%>">

			</div>

		</div>


		<br>



		<center>
			<div class="row">

				<div class="col">
					<input type="submit" name="operation" class="btn btn-success"
						value="<%=TimetableListCtl.OP_NEW%>"> <input type="submit"
						name="operation" class="btn btn-danger"
						value="<%=TimetableListCtl.OP_DELETE%>">
				</div>

			</div>
		</center>

		<div class="table-responsive">
			<!-- <table class="table table-striped table-bordered"> -->
			<table class="table table-bordered table-striped">
				<!-- <thead  style="background-color:  #ff751a"> -->
				<tr style="background-color: #dabd53">
					<th style="text-align: center; color: black;"><input
						type="checkbox" id="select_all" name="ids">Select All</th>
					<th style="text-align: center; color: black;">S.NO.</th>
					<th style="text-align: center; color: black;">COURSE NAME</th>
					<th style="text-align: center; color: black;">SUBJECT NAME</th>
					<th style="text-align: center; color: black;">SEMESTER</th>
					<th style="text-align: center; color: black;">EXAM DATE</th>
					<th style="text-align: center; color: black;">EXAM TIME</th>
					<th style="text-align: center; color: black;"><i
						class="fa fa-edit"></i></th>
				</tr>

				<%
					int pageNo = ServletUtility.getPageNo(request);
						int pageSize = ServletUtility.getPageSize(request);
						int index = ((pageNo - 1) * pageSize) + 1;

						//List list = ServletUtility.getList(request);
						Iterator it = list1.iterator();
						/*  TimetableBean dto1=null; */

						while (it.hasNext()) {
							dto = (TimetableDTO) it.next();
				%>

				<tr style="background-color: #feffba">
					<td style="text-align: center; color: black;"><input
						type="checkbox" name="ids" class="checkbox"
						value="<%=dto.getId()%>"></td>
					<td style="text-align: center; color: black;"><%=index++%></td>
					<td style="text-align: center; color: black;"><%=dto.getCourseName()%></td>
					<td style="text-align: center; color: black;"><%=dto.getSubName()%></td>
					<td style="text-align: center; color: black;"><%=dto.getSemester()%></td>
					<td style="text-align: center; color: black;"><%=DataUtility.getDateString(dto.getExamDate())%></td>
					<td style="text-align: center; color: black;"><%=dto.getExamTime()%></td>
					<td style="text-align: center; color: white;"><a
						href="TimetableCtl?id=<%=dto.getId()%>"><i class="fa fa-edit"></i></a></td>
				</tr>
				<%
					}
				%>
			</table>
		</div>


		<center>
			<div class="row">

				<div class="col">
					<input type="submit" name="operation" class="btn btn-primary"
						value="<%=TimetableListCtl.OP_PREVIOUS%>"
						<%=(pageNo == 1) ? "disabled" : ""%>> <input type="submit"
						name="operation" class="btn btn-primary"
						value="<%=TimetableListCtl.OP_NEXT%>"
						<%=((list1.size() < pageSize || nextListSize == 0) ? "disabled" : "")%>>
				</div>

			</div>
		</center>


		<input type="hidden" name="pageno" value="<%=pageNo%>"> <input
			type="hidden" name="pagesize" value="<%=pageSize%>">

		<%
			}
		%>


	</form>
	</div>



	<br>
	<br>
	<br>
	<br>
	<div><%@include file="Footer.jsp"%></div>
</body>
</html>
