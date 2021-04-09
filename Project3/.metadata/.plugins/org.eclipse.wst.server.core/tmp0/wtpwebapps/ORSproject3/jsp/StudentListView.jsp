<%@page import="in.co.rays.ORSproject3.utility.DataUtility"%>
<%@page import="in.co.rays.ORSproject3.dto.StudentDTO"%>
<%@page import="in.co.rays.ORSproject3.utility.HTMLUtility"%>
<%@page import="in.co.rays.ORSproject3.controller.StudentListCtl"%>
<%@page import="in.co.rays.ORSproject3.utility.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" />
<title>StudentList View Page</title>
<script src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js">
	
</script>
<script type="text/javascript"
	src="<%=ORSView.APP_CONTEXT%>/js/checkbox11.js"></script>
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
	<%@include file="Header.jsp"%>
	<div class="container-fluid my-container">
		<jsp:useBean id="dto" class="in.co.rays.ORSproject3.dto.StudentDTO"
			scope="request"></jsp:useBean>


		<form action="<%=ORSView.STUDENT_LIST_CTL %>" method="post">

<br><br><br>
			<h1
				style="color:white; text-align: center; font-variant: small-caps;">Student
				List</h1>

			<%
  int nextListSize= DataUtility.getInt(request.getAttribute("nextListSize").toString());
    
  %>

			<%
							if (ServletUtility.getSuccessMessage(request) != null
									&& ServletUtility.getSuccessMessage(request).length() > 0) {
						%>
			<div class="alert alert-success alert-dismissible fade show">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<strong>Success..!</strong>
				<%=ServletUtility.getSuccessMessage(request) %>
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
				<%=ServletUtility.getErrorMessage(request) %>
			</div>
			<%
							}
						%>


			<%
List slist=(List)request.getAttribute("slist");

List li1=(List)ServletUtility.getList(request);
if(li1==null ||li1.size()==0){   
%>
		<center>
			<input type="submit" name="operation" class="btn btn-primary "
				style="margin-left: 54px; margin-top: 24px;"
				value="<%=StudentListCtl.OP_BACK%>">
			</center>
			<%}else{ %>

			<div class="row">
				<div class="col-sm-3">
					<div class="form-group">
						<label style="color: white">First Name</label>
						<div class="input-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fa fa-user"
									aria-hidden="true"></i></span>
							</div>
							<%=HTMLUtility.getList1("firstName",String .valueOf(dto.getId()) , slist) %>
						</div>
					</div>
				</div>

				<div class="col-sm-3">
					<div class="form-group">

						<label style="color: white">Last Name</label>
						<div class="input-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fa fa-user"
									aria-hidden="true"></i></span>
							</div>
							<input type="text" name="lastName" class="form-control"
								placeholder="Enter Last Name"
								value="<%=ServletUtility.getParameter("lastName", request) %>">
						</div>
					</div>
				</div>

				<div class="col-sm-3">
					<div class="form-group">

						<label style="color: white">Email Id</label>
						<div class="input-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fa fa-envelope"
									aria-hidden="true"></i></span>
							</div>
							<input type="text" name="email" class="form-control"
								placeholder="Enter Email Id"
								value="<%=ServletUtility.getParameter("email", request) %>">
						</div>
					</div>
				</div>
				<div class="col-sm-3 ">
					<input type="submit" name="operation" class="btn btn-success"
						style="margin-top: 35px;" value="<%=StudentListCtl.OP_SEARCH%>">
					<input type="submit" name="operation" class="btn btn-warning"
						style="margin-top: 35px;" value="<%=StudentListCtl.OP_RESET%>">

				</div>

			</div>


			<br>



			<center>
				<div class="row">

					<div class="col">
						<input type="submit" name="operation" class="btn btn-success"
							value="<%=StudentListCtl.OP_NEW%>"> <input type="submit"
							name="operation" class="btn btn-danger"
							value="<%=StudentListCtl.OP_DELETE%>">
					</div>

				</div>
			</center>


			<div class="table-responsive">
				<!-- <table class="table table-striped table-bordered"> -->
				<table class="table table-bordered table-striped">
					<!-- <thead  style="background-color:  #ff751a"> -->
					<tr style="background-color: #dabd53">
						<th style="text-align: center; color: white;"><input
							type="checkbox" id="select_all" name="ids">Select All</th>
						<th style="text-align: center; color: black;">S.NO.</th>
						<th style="text-align: center; color: black;">COLLEGE</th>
						<th style="text-align: center; color: black;">FIRST NAME</th>
						<th style="text-align: center; color: black;">LAST NAME</th>
						<th style="text-align: center; color: black;">DOB</th>
						<th style="text-align: center; color: black;">MOBILE NO</th>
						<th style="text-align: center; color: black;">ADDRESS</th>
						<th style="text-align: center; color: black;">EMAIL ID</th>
						<th style="text-align: center; color: black;"><i
							class="fa fa-edit"></i></th>
					</tr>

					<%
   
    int pageNo = ServletUtility.getPageNo(request);
    int pageSize = ServletUtility.getPageSize(request);
    int index = ((pageNo-1)*pageSize)+1;
 
    List list= ServletUtility.getList(request);
    
    Iterator<StudentDTO> it = list.iterator();
    
    //StudentBean dto1;
    while(it.hasNext()){
    	dto = it.next();
    %>

					<tr style="background-color: #feffba">
						<td style="text-align: center; color: white;"><input
							type="checkbox" name="ids" class="checkbox"
							value="<%=dto.getId()%>"></td>
						<td style="text-align: center; color: black;"><%=index++%></td>
						<td style="text-align: center; color: black;"><%=dto.getCollegeName()%></td>
						<td style="text-align: center; color: black;"><%=dto.getFirstName()%></td>
						<td style="text-align: center; color: black;"><%=dto.getLastName()%></td>
						<td style="text-align: center; color: black;"><%=DataUtility.getDateString(dto.getDob())%></td>
						<td style="text-align: center; color: black;"><%=dto.getMobileNo()%></td>
						<td style="text-align: center; color: black;"><%=dto.getAddress()%></td>
						<td style="text-align: center; color: black;"><%=dto.getEmail()%></td>

						<td style="text-align: center; color: white;"><a
							href="StudentCtl?id=<%=dto.getId()%>"><i class="fa fa-edit"></i></a></td>

					</tr>

					<%} %>

</table>
				  </div> 
   
  
  <center>
     <div class="row">
     
     <div class="col">
      <input type="submit" name = "operation" class="btn btn-primary" value="<%=StudentListCtl.OP_PREVIOUS%>"
           <%=(pageNo==1)?"disabled":"" %>>

    <input type="submit" name = "operation"      class="btn btn-primary" value="<%=StudentListCtl.OP_NEXT%>"
             <%=((list.size()<pageSize||nextListSize==0)?"disabled":"") %>>
     </div>         
        
     </div>
</center>

   
   <input type="hidden" name= "pageno" value="<%=pageNo%>">
  <input type="hidden" name= "pagesize" value="<%=pageSize%>">
   
<%} %>

 
  </form>
 </div>




<br><br><br><br>
<div><%@include file="Footer.jsp" %></div>
   </body>
</html>