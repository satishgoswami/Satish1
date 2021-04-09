
<%@page import="in.co.rays.ORSproject3.utility.DataUtility"%>
<%@page import="in.co.rays.ORSproject3.utility.HTMLUtility"%>
<%@page import="in.co.rays.ORSproject3.controller.RoleListCtl"%>
<%@page import="in.co.rays.ORSproject3.utility.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>

<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" />
<title>Role list Page</title>
<script src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js">
	
</script>
<script type="text/javascript"
	src="<%=ORSView.APP_CONTEXT%>/js/checkbox11.js"></script>

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


<html>
<body>
<%@include file="Header.jsp"%>

<div class="container-fluid my-container">

<jsp:useBean id="dto" class="in.co.rays.ORSproject3.dto.RoleDTO" scope="request"></jsp:useBean>

<form action="<%=ORSView.ROLE_LIST_CTL %>" method="post">


  <%
   int nextListSize= DataUtility.getInt(request.getAttribute("nextListSize").toString());
  %>
<br><br><br>  
 <h1 style="color:white; text-align: center; font-variant: small-caps;">Role List</h1>
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
List li = ServletUtility.getList(request);
List rlist=(List)request.getAttribute("rlist");
if(li==null || li.size()==0){
%>
<input type="submit" name="operation"  class="btn btn-primary " style="margin-left: 54px;margin-top:24px;" value="<%=RoleListCtl.OP_BACK%>">
<%}else{ %>


<div class="row"> 
          <div class="col-sm-3">
           <div class="form-group" >
		
<div class="input-group">
						 		<div class="input-group-prepend">
									<span class="input-group-text" id="inputGroup-sizing-default">Name:-<!-- <i class="fa fa-user" aria-hidden="true"></i> --></span>
								</div>
								<%= HTMLUtility.getList1("name",String.valueOf(dto.getId()), rlist)%>
						</div>
					</div>		
               </div>


 <div class="col-sm-3 ">
           <input type="submit" name="operation"  class="btn btn-success"  value="<%=RoleListCtl.OP_SEARCH%>">    
           <input type="submit" name="operation" class="btn btn-warning"   value="<%=RoleListCtl.OP_RESET%>">
           
           </div>
             
        </div>
       
       
      <br>
 
 
     
     <center>
       <div class="row">
     
         <div class="col">
           <input type="submit" name = "operation" class="btn btn-success"  value="<%=RoleListCtl.OP_NEW%>">
     
            <input type="submit" name = "operation" class="btn btn-danger"  value="<%=RoleListCtl.OP_DELETE%>">   
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
      <th style="text-align: center;color: black;">NAME</th>
      <th style="text-align: center;color: black;">DESCRIPTION</th>
      <th style="text-align: center;color: black;"><i class="fa fa-edit"></i></th>
      </tr>
 
   <%
  int pageNo = ServletUtility.getPageNo(request);
  int pageSize = ServletUtility.getPageSize(request);
  int index= ((pageNo-1)*pageSize)+1;
  
  List list= ServletUtility.getList(request);
  
  Iterator<RoleDTO> it =list.iterator();
  
  
  while(it.hasNext()){
	   
	  dto = (RoleDTO)it.next();
  %>


 <tr style="background-color:  #feffba">
	      <td style="text-align: center;color: black;"><input type="checkbox" name="ids" class="checkbox" value="<%=dto.getId()%>" <%=(dto.getId()==RoleDTO.ADMIN)?"disabled":"" %>></td>   
	      <td style="text-align: center;color: black;"><%=index++%></td>
	      <td style="text-align: center;color: black;"><%=dto.getName()%></td>
	      <td style="text-align: center;color: black;"><%=dto.getDescription()%></td>

	      <td style="text-align: center;color: white;">
	      
	      <a href="RoleCtl?id=<%=dto.getId()%>"  <% if(dto.getId()==RoleDTO.ADMIN){ %>onclick="return false;" <%} %>><i class="fa fa-edit" ></i></a></td>
	       
	        
	 </tr>  
   
    <%} %>

   </table>
  </div> 
   
  
  <center>
     <div class="row">
     
     <div class="col">
      <input type="submit" name = "operation" class="btn btn-primary" value="<%=RoleListCtl.OP_PREVIOUS%>"
           <%=(pageNo==1)?"disabled":"" %>>

    <input type="submit" name = "operation"      class="btn btn-primary" value="<%=RoleListCtl.OP_NEXT%>"
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
