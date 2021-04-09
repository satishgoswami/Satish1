<%@page import="in.co.rays.ORSproject3.utility.DataUtility"%>
<%@page import="in.co.rays.ORSproject3.dto.FacultyDTO"%>
<%@page import="in.co.rays.ORSproject3.utility.HTMLUtility"%>
<%@page import="in.co.rays.ORSproject3.controller.FacultyListCtl"%>
<%@page import="in.co.rays.ORSproject3.utility.ServletUtility"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" />
<title>Faculty list Page</title>
<script  src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js">
	
</script>	
<script type="text/javascript" src="<%=ORSView.APP_CONTEXT%>/js/checkbox11.js"></script>
 <style>
body{
	background-image:url(/ORS_3/img/nasa1.jpg);
     background-size:100%100%;
	width: 100%;
/* 	height:100vh; */
}
 
.btn-success{
	
	width:85px;
}
.btn-warning{
    
	width:95px;

}
.btn-primary{
    
	width:85px;

}

</style>
 
</head>
<body>
<div>
<%@include file="Header.jsp"%>
</div>
<div class="container-fluid my-container">
<jsp:useBean id="dto" class="in.co.rays.ORSproject3.dto.FacultyDTO" scope="request"></jsp:useBean>

<form action="<%=ORSView.FACULTY_LIST_CTL%>" method="post">

<br><br><br>
<h1 style="color:white; text-align: center; font-variant: small-caps;">Faculty List</h1>
  
  <%
   int nextListSize= DataUtility.getInt(request.getAttribute("nextListSize").toString());
  %>
     
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

<%

List list1=(List)request.getAttribute("cclist");

List list2=(List)request.getAttribute("clist");

List li = ServletUtility.getList(request);

if(li==null || li.size()==0){
%>
<center>
<input type="submit" name="operation"  class="btn btn-primary " style="margin-left: 54px;margin-top:24px;" value="<%=FacultyListCtl.OP_BACK%>">	
</center>
<%} else { %>


       <div class="row"> 
          <div class="col-sm-3">
           <div class="form-group" >
						
						<label style="color: white">First Name</label>
						 	<div class="input-group">
						 		<div class="input-group-prepend">
									<span class="input-group-text"><i class="fa fa-user" aria-hidden="true"></i></span>
								</div>
								<input type="text" name="fname" class="form-control"  placeholder="Enter First Name" value="<%=ServletUtility.getParameter("fname", request)%>">
						</div>
					</div>		
               </div>
           <div class="col-sm-3">
           <div class="form-group" >
						
						<label style="color: white">College Name</label>
						 	<div class="input-group">
						 		<div class="input-group-prepend">
									<span class="input-group-text"><i class="fa fa-user" aria-hidden="true"></i></span>
								</div>
								<%=HTMLUtility.getList1("cname", String.valueOf(dto.getCollegeId()), list2) %>
						</div>
					</div>		
               </div>           


           <div class="col-sm-3">
           <div class="form-group" >
						
						<label style="color: white">Course Name</label>
						 	<div class="input-group">
						 		<div class="input-group-prepend">
									<span class="input-group-text"><i class="fa fa-user" aria-hidden="true"></i></span>
								</div>
								<%=HTMLUtility.getList1("lname", String.valueOf(dto.getCourseId()), list1) %>
						</div>
					</div>		
               </div>           

           <div class="col-sm-3 ">
           <input type="submit" name="operation"  class="btn btn-success" style="margin-top:35px;" value="<%=FacultyListCtl.OP_SEARCH%>">    
           <input type="submit" name="operation" class="btn btn-warning"  style="margin-top: 35px;"  value="<%=FacultyListCtl.OP_RESET%>">
           
           </div>
             
        </div>
       
       
      <br>
 
 
     
     <center>
       <div class="row">
     
         <div class="col">
           <input type="submit" name = "operation" class="btn btn-success"  value="<%=FacultyListCtl.OP_NEW%>">
     
            <input type="submit" name = "operation" class="btn btn-danger"  value="<%=FacultyListCtl.OP_DELETE%>">   
     </div>         
        
     </div>
</center>
<div class="table-responsive"> 
   <!-- <table class="table table-striped table-bordered"> -->
   <table class="table table-bordered table-striped">
   <!-- <thead  style="background-color:  #ff751a"> -->
      <tr style="background-color:  #dabd53" >
      <th style="text-align: center;color: black;"><input type="checkbox" id = "select_all" name="ids" >Select All</th>
      <th style="text-align: center;color: black;">S.NO.</th>
      <th style="text-align: center;color: black;">F_NAME</th>
      <th style="text-align: center;color: black;">L_NAME</th>
      <th style="text-align: center;color: black;">EMAIL ID</th>
     <!--  <th style="text-align: center;color: black;">ADDRESS</th> -->
      <th style="text-align: center;color: black;">DOJ</th>
      <th style="text-align: center;color: black;">QUALI.</th>
      <th style="text-align: center;color: black;">COL_NAME</th>
      <th style="text-align: center;color: black;">COURSE NAME</th>
       <th style="text-align: center;color: black;">SUB_NAME</th>
      <th style="text-align: center;color: black;"><i class="fa fa-edit"></i></th>
      </tr>

 <%
 int pageNo  = ServletUtility.getPageNo(request);
 int pageSize= ServletUtility.getPageSize(request);
 int index   = ((pageNo-1)*pageSize)+1;
 
 List list = ServletUtility.getList(request);
 
 Iterator it= list.iterator();
  
 while(it.hasNext()){
	  dto= (FacultyDTO)it.next();
	 System.out.println("view in"+dto.getCollegeName());
 %>
  <tr style="background-color:  #feffba">
	      <td style="text-align: center;color: black;"><input type="checkbox" name="ids" class="checkbox" value="<%=dto.getId()%>"></td>   
	      <td style="text-align: center;color: black;"><%=index++%></td>
	      <td style="text-align: center;color: black;"><%=dto.getFirstName()%></td>
	      <td style="text-align: center;color: black;"><%=dto.getLastName()%></td>
	      <td style="text-align: center;color: black;"><%=dto.getEmailId()%></td>
	      <%-- <td style="text-align: center;color: black;"><%=dto.getAddress()%></td> --%>
	      <td style="text-align: center;color: black;"><%=DataUtility.getDateString(dto.getDateOfJoining())%></td>
	      <td style="text-align: center;color: black;"><%=dto.getQualification()%></td>
	      <td style="text-align: center;color: black;"><%=dto.getCollegeName()%></td>
	      <td style="text-align: center;color: black;"><%=dto.getCourseName()%></td>
	      <td style="text-align: center;color: black;"><%=dto.getSubjectName()%></td>
	      
	        
	      <td style="text-align: center;color: black;">
	      
	      <a href="FacultyCtl?id=<%=dto.getId()%>"><i class="fa fa-edit" ></i></a></td>
	 </tr>  
   
    <%} %>
   </table>
  </div> 
   
  
  <center>
     <div class="row">
     
     <div class="col">
      <input type="submit" name = "operation" class="btn btn-primary" value="<%=FacultyListCtl.OP_PREVIOUS%>"
           <%=(pageNo==1)?"disabled":"" %>>

    <input type="submit" name = "operation"      class="btn btn-primary" value="<%=FacultyListCtl.OP_NEXT%>"
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
<%@include file="Footer.jsp"%> 
   </body>
</html>
  
