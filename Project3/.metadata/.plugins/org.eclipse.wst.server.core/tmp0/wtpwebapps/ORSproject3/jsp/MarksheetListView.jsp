<%@page import="in.co.rays.ORSproject3.controller.ORSView"%>
<%@page import="in.co.rays.ORSproject3.controller.MarksheetListCtl"%>
<%@page import="in.co.rays.ORSproject3.utility.DataUtility"%>
<%@page import="in.co.rays.ORSproject3.dto.MarksheetDTO"%>
<%@page import="in.co.rays.ORSproject3.utility.HTMLUtility"%>
<%@page import="in.co.rays.ORSproject3.controller.MarksheetListCtl"%>
<%@page import="in.co.rays.ORSproject3.utility.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" />
<title>MarksheetList View Page</title>

<script  src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js">	
</script>	

<script type="text/javascript" src="<%=ORSView.APP_CONTEXT%>/js/checkbox11.js"></script>
 
<!-- font-awesome library -->

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
<body class="hm-gradient">
<jsp:useBean id="dto" scope="request" class="in.co.rays.ORSproject3.dto.MarksheetDTO" />
<div>
<%@include file="Header.jsp" %>
</div>



<div class="container-fluid my-container">
<form action="<%=ORSView.MARKSHEET_LIST_CTL%>" method="post">
<br><br><br>
<h1 style="color:white; text-align: center; font-variant: small-caps;">Marksheet List</h1>
	
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
	List slist=(List)request.getAttribute("slist");    
    List mlist=(List)request.getAttribute("mlist");    

	List list = ServletUtility.getList(request);
	System.out.println(list);

	int nextListSize=DataUtility.getInt(request.getAttribute("nextListSize").toString());
	if (list.size()==0){ %>

<center>
<input type="submit" name="operation"  class="btn btn-primary " style="margin-left: 54px;margin-top:24px;" value="<%=MarksheetListCtl.OP_BACK%>">
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
								<%= HTMLUtility.getList1("name",String.valueOf(dto.getStudentId()),slist)%>
						</div>
					</div>		
               </div>
               
           <div class="col-sm-3">
           <div class="form-group" >
						
						<!-- <label style="color: white">Roll No.</label>
						 --> 	<div class="input-group">
						 		<div class="input-group-prepend">
									<span class="input-group-text" id="inputGroup-sizing-default">Roll No:-<!-- <i class="fa fa-user" aria-hidden="true"></i> --></span>
								</div>
								<%= HTMLUtility.getList1("rollNo",String.valueOf(dto.getId()),mlist)%>
						</div>
					</div>		
               </div>           
                      
           <div class="col-sm-3 ">
           <input type="submit" name="operation"  class="btn btn-success"  value="<%=MarksheetListCtl.OP_SEARCH%>">    
           <input type="submit" name="operation" class="btn btn-warning" value="<%=MarksheetListCtl.OP_RESET%>">
           
           </div>
             
        </div>
       
 
	  
	   
	  <br>
	  <center>
       <div class="row">
     
         <div class="col">
           <input type="submit" name = "operation" class="btn btn-success"  value="<%=MarksheetListCtl.OP_NEW%>">
     
            <input type="submit" name = "operation" class="btn btn-danger"  value="<%=MarksheetListCtl.OP_DELETE%>">   
     </div>         
        
     </div>
</center>


  <div class="table-responsive" >
      <table class="table table-bordered table-striped">
      <!-- <thead> -->
      <tr style="background-color:  #dabd53">
      <th style="text-align: center;color: black;"><input type="checkbox" id = "select_all" name="ids" >Select All</th>
                    <th style="text-align: center;color: black;">S.NO</th>
					<th style="text-align: center;color: black;">Roll No</th>
					<th style="text-align: center;color: black;">Name</th>
					<th style="text-align: center;color: black;">Physics</th>
					<th style="text-align: center;color: black;">Chemistry</th>
					<th style="text-align: center;color: black;">Maths</th>
					<th style="text-align: center;color: black;"><i class="fa fa-edit"></i></th>
      </tr>
      <%
	                int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int index = ((pageNo - 1) * pageSize) + 1;
                    /*  int nextPageSize = DataUtility.getInt
                   		 (request.getAttribute("nextlistsize").toString());
                     */
                    Iterator<MarksheetDTO> it = list.iterator();
                    while (it.hasNext()) {
                    	
                       dto = it.next();
               %>
				<tr style="background-color:  #feffba">
					<td style="text-align: center;color: black;"><input type="checkbox" class="checkbox" name="ids" value="<%=dto.getId()%>"></td>
					<td style="text-align: center;color: black;"><%=index++%></td>
					<td style="text-align: center;color: black;"><%=dto.getRollNo()%></td>
					<td style="text-align: center;color: black;"><%=dto.getName()%></td>
					<td style="text-align: center;color: black;"><%=dto.getPhysics()%></td>
					<td style="text-align: center;color: black;"><%=dto.getChemistry()%></td>
					<td style="text-align: center;color: black;"><%=dto.getMaths()%></td>
					<td style="text-align: center;"><a href="MarksheetCtl?id=<%=dto.getId()%>"><i class="fa fa-edit" ></i></a></td>
				</tr>
				<%
                    }
                %>
			</table>
</div>	


 <center>
     <div class="row">
     
     <div class="col">
      <input type="submit" name = "operation" class="btn btn-primary" value="<%=MarksheetListCtl.OP_PREVIOUS%>"
           <%=(pageNo==1)?"disabled":"" %>>

    <input type="submit" name = "operation"      class="btn btn-primary" value="<%=MarksheetListCtl.OP_NEXT%>"
             <%=((list.size()<pageSize|| nextListSize==0)?"disabled":"") %>>
     </div>         
        
     </div>
</center>

   <input type="hidden" name= "pageNo" value="<%=pageNo%>">
  <input type="hidden" name= "pageSize" value="<%=pageSize%>">
			
			
			<%} %>
			
</form>
</div>
<br>
<br>
<br>
<div><%@include file="Footer.jsp" %></div>
</body>
</html>