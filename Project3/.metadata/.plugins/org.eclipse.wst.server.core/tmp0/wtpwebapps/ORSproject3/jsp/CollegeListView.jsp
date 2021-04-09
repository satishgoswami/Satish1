<%@page import="in.co.rays.ORSproject3.utility.DataUtility"%>
<%@page import="in.co.rays.ORSproject3.controller.MarksheetListCtl"%>
<%@page import="in.co.rays.ORSproject3.model.ModelFactory"%>
<%@page import="in.co.rays.ORSproject3.model.CollegeModelInt"%>
<%@page import="in.co.rays.ORSproject3.dto.CollegeDTO"%>
<%@page import="in.co.rays.ORSproject3.utility.HTMLUtility"%>
<%@page import="in.co.rays.ORSproject3.controller.CollegeListCtl"%>
<%@page import="in.co.rays.ORSproject3.utility.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script  src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js">	
</script>	
<script type="text/javascript" src="<%=ORSView.APP_CONTEXT%>/js/checkbox11.js"></script>
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16"/>
<title>College List Page</title>
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
<jsp:useBean id="dto" class="in.co.rays.ORSproject3.dto.CollegeDTO" scope="request"></jsp:useBean>
	<div>
	<%@include file="Header.jsp"%>
</div>
<div class="container-fluid my-container">
	<form action="<%=ORSView.COLLEGE_LIST_CTL%>" method="post">

<%
   int nextListSize= DataUtility.getInt(request.getAttribute("nextListSize").toString());
  %>

<br><br><br>
<h1 style="color:white; text-align: center; font-variant: small-caps;">College List</h1>
	
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

 <% List l = ServletUtility.getList(request); 
 List clist=(List)request.getAttribute("clist");
 
if(l==null || l.size()==0){%>
<center>
<input type="submit" name="operation"  class="btn btn-primary " style="margin-left: 54px;margin-top:24px;" value="<%=CollegeListCtl.OP_BACK%>">	
</center>
<%}else{ %> 
  
  
  <br>
	  <div class="row"> 
          
          <div class="col-sm-3">
           <div class="form-group" >
						
						<!-- <label style="color: white">Name</label>
						 --> 	<div class="input-group">
						 		<div class="input-group-prepend">
									<span class="input-group-text" id="inputGroup-sizing-default">Name:-<!-- <i class="fa fa-user" aria-hidden="true"></i> --></span>
								</div>
	<%=HTMLUtility.getList1("cName", String.valueOf(dto.getId()), clist) %>
						</div>
					</div>		
               </div>
    
<div class="col-sm-3">
           <div class="form-group" >
						
						<!-- <label style="color: white">Roll No.</label>
						 --> 	<div class="input-group">
						 		<div class="input-group-prepend">
									<span class="input-group-text" id="inputGroup-sizing-default">City:-<!-- <i class="fa fa-user" aria-hidden="true"></i> --></span>
								</div>
	<input type="text" name ="city" placeholder="Enter City Name" class="form-control" value="<%=ServletUtility.getParameter("city", request) %>">
						</div>
					</div>		
               </div>           
               <div class="col-sm-3 ">
           <input type="submit" name="operation"  class="btn btn-success"  value="<%=CollegeListCtl.OP_SEARCH%>">    
           <input type="submit" name="operation" class="btn btn-warning" value="<%=CollegeListCtl.OP_RESET%>">
           
           </div>
             
        </div>
       
 
	  
	   
	  <br>
	  <center>
       <div class="row">
     
         <div class="col">
           <input type="submit" name = "operation" class="btn btn-success"  value="<%=CollegeListCtl.OP_NEW%>">
     
            <input type="submit" name = "operation" class="btn btn-danger"  value="<%=CollegeListCtl.OP_DELETE%>">   
     </div>         
        
     </div>
</center>
              
    <div class="table-responsive" >
      <table class="table table-bordered table-striped">
      <!-- <thead> -->
      <tr style="background-color:  #dabd53">
      <th style="text-align: center;color: black;"><input type="checkbox" id = "select_all" name="ids" >Select All</th>
                    <th style="text-align: center;color: black;">S.NO</th>
					<th style="text-align: center;color: black;">NAME</th>
					<th style="text-align: center;color: black;">ADDRESS</th>
					<th style="text-align: center;color: black;">CITY</th>
					<th style="text-align: center;color: black;">PHONE NO</th>
					<th style="text-align: center;color: black;"><i class="fa fa-edit"></i></th>
      </tr>
  
   
   <%
   int pageNo = ServletUtility.getPageNo(request);
   int pageSize= ServletUtility.getPageSize(request);
   int index = ((pageNo-1)*pageSize)+1;
   
   //int nextlist= DataUtility.getInt(request.getAttribute("nextList1").toString());
   //System.out.println("next list "+nextlist);
   // Another way to disable next button
   
   List list = ServletUtility.getList(request);
   
   
   Iterator<CollegeDTO> it = list.iterator();
   
   while(it.hasNext()){
	   dto = it.next();
	%>   
	
					<tr style="background-color:  #feffba">
					<td style="text-align: center;color: black;"><input type="checkbox" class="checkbox" name="ids" value="<%=dto.getId()%>"></td>
					<td style="text-align: center;color: black;"><%=index++%></td>
					<td style="text-align: center;color: black;"><%=dto.getName()%></td>
					<td style="text-align: center;color: black;"><%=dto.getAddress()%></td>
					<td style="text-align: center;color: black;"><%=dto.getCity()%></td>
					<td style="text-align: center;color: black;"><%=dto.getPhoneNo()%></td>
					<td style="text-align: center;"><a href="CollegeCtl?id=<%=dto.getId()%>"><i class="fa fa-edit" ></i></a></td>
				</tr>
				<%
                    }
                %>
			</table>
</div>	


 <center>
	
	
	     <div class="row">
     
     <div class="col">
      <input type="submit" name = "operation" class="btn btn-primary" value="<%=CollegeListCtl.OP_PREVIOUS%>"
           <%=(pageNo==1)?"disabled":"" %>>

    <input type="submit" name = "operation"      class="btn btn-primary" value="<%=CollegeListCtl.OP_NEXT%>"
             <%=((list.size()<pageSize || nextListSize==0)?"disabled":"") %>>
     </div>         
        
     </div>
</center>

   <input type="hidden" name= "pageno" value="<%=pageNo%>">
  <input type="hidden" name= "pagesize" value="<%=pageSize%>">
			
			
			<%} %>
			
</form>
</div>
<br>
<br>
<br>
<div><%@include file="Footer.jsp" %></div>
</body>
</html>