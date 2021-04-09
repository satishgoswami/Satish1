<%@page import="in.co.rays.ORSproject3.utility.DataUtility"%>
<%@page import="in.co.rays.ORSproject3.dto.CourseDTO"%>
<%@page import="in.co.rays.ORSproject3.utility.HTMLUtility"%>
<%@page import="in.co.rays.ORSproject3.controller.CourseListCtl"%>
<%@page import="in.co.rays.ORSproject3.utility.ServletUtility"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" />
<title>CourseList View</title>
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
<jsp:useBean id="dto" class="in.co.rays.ORSproject3.dto.CourseDTO" scope="request"></jsp:useBean>

<div>
<%@include file="Header.jsp" %>
</div>
  

<%
   int nextListSize= DataUtility.getInt(request.getAttribute("nextListSize").toString());
  %>
<br><br><br>
   <h1 style="color:white; text-align: center; font-variant: small-caps;">Course List</h1>
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
  
  
    
<form action="<%=ORSView.COURSE_LIST_CTL%>" method="post">

<% List l= ServletUtility.getList(request); 
List clist=(List)request.getAttribute("clist");
if(l==null || l.size()==0)
{%>
<center>
<input type="submit" name="operation"  class="btn btn-primary " style="margin-left: 54px;margin-top:24px;" value="<%=CourseListCtl.OP_BACK%>">	
</center>
<%}
else{ %>


<div class="row"> 
          <div class="col-sm-3">
           <div class="form-group" >
		
<div class="input-group">
						 		<div class="input-group-prepend">
									<span class="input-group-text" id="inputGroup-sizing-default">Course:-<!-- <i class="fa fa-user" aria-hidden="true"></i> --></span>
								</div>
								<%=HTMLUtility.getList1("CourseName", String.valueOf(dto.getId()), clist) %>
						</div>
					</div>		
               </div>


 <div class="col-sm-3 ">
           <input type="submit" name="operation"  class="btn btn-success"  value="<%=CourseListCtl.OP_SEARCH%>">    
           <input type="submit" name="operation" class="btn btn-warning"   value="<%=CourseListCtl.OP_RESET%>">
           
           </div>
             
        </div>
       
       
      <br>
 
      <center>
       <div class="row">
     
         <div class="col">
           <input type="submit" name = "operation" class="btn btn-success"  value="<%=CourseListCtl.OP_NEW%>">
     
            <input type="submit" name = "operation" class="btn btn-danger"  value="<%=CourseListCtl.OP_DELETE%>">   
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
      <th style="text-align: center;color: black;">COURSE NAME</th>
      <th style="text-align: center;color: black;">DURATION</th>
      <th style="text-align: center;color: black;">DESCRIPTION</th>
      <th style="text-align: center;color: black;"><i class="fa fa-edit"></i></th>
      </tr>


<%
 int pageNo=ServletUtility.getPageNo(request);
int pageSize=ServletUtility.getPageSize(request);
int index=(pageNo-1)*pageSize+1;

List list=ServletUtility.getList(request);

Iterator it=list.iterator();

while(it.hasNext()){
	dto = (CourseDTO)it.next();

%>


 <tr style="background-color:  #feffba">
	      <td style="text-align: center;color: black;"><input type="checkbox" name="ids" class="checkbox" value="<%=dto.getId()%>" ></td>   
	      <td style="text-align: center;color: black;"><%=index++%></td>
	      <td style="text-align: center;color: black;"><%=dto.getCourseName()%></td>
		<td style="text-align: center;color: black;"><%=dto.getDuration()%></td>
	      <td style="text-align: center;color: black;"><%=dto.getDescription()%></td>

	      <td style="text-align: center;color: white;">
	      
	      <a href="CourseCtl?id=<%=dto.getId()%>"><i class="fa fa-edit" ></i></a></td>
	       
	        
	 </tr>  
   
    <%} %>

   </table>
  </div> 





  <center>
     <div class="row">
     
     <div class="col">
      <input type="submit" name = "operation" class="btn btn-primary" value="<%=CourseListCtl.OP_PREVIOUS%>"
           <%=(pageNo==1)?"disabled":"" %>>

    <input type="submit" name = "operation"      class="btn btn-primary" value="<%=CourseListCtl.OP_NEXT%>"
             <%=((list.size()<pageSize|| nextListSize==0)?"disabled":"") %>>
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
