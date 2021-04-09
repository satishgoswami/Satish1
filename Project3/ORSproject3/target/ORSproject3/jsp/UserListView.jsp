<%@page import="in.co.rays.ORSproject3.utility.DataUtility"%>
<%@page import="in.co.rays.ORSproject3.controller.ORSView"%>
<%@page import="in.co.rays.ORSproject3.utility.HTMLUtility"%>
<%@page import="in.co.rays.ORSproject3.controller.UserListCtl"%>
<%@page import="in.co.rays.ORSproject3.utility.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" />
<title>UserList View Page</title>
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


<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" />
<title>User List View Page</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>

<body>

      <%@ include file="Header.jsp" %>
<div class="container-fluid my-container">

<jsp:useBean id="dto" class="in.co.rays.ORSproject3.dto.UserDTO" scope="request"></jsp:useBean>
  <form class="form-container" action="<%=ORSView.USER_LIST_CTL %>" method="post">
  

<br><br><br>
  <h1 style="color:white; text-align: center; font-variant: small-caps;">User List</h1>
  
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
            List lt = (List) request.getAttribute("role_list");
        %>
  
  <% List l = ServletUtility.getList(request); 
if(l==null || l.size()==0){%>
<center>
<input type="submit" name="operation"  class="btn btn-primary " style="margin-left: 54px;margin-top:24px;" value="<%=UserListCtl.OP_BACK%>">
</center>
<%}else{ %>
     

       <div class="row"> 
          <div class="col-sm-3">
           <div class="form-group" >
						
						<label style="color: white">First Name</label>
						 	<div class="input-group">
						 		<div class="input-group-prepend">
									<span class="input-group-text"><i class="fa fa-user" aria-hidden="true"></i></span>
								</div>
								<input type="text" name="firstName" class="form-control"  placeholder="Enter First Name" value="<%=ServletUtility.getParameter("firstName", request) %>" >
						</div>
					</div>		
               </div>
               
           <div class="col-sm-3">
           <div class="form-group" >
						
						<label style="color: white">Role Name</label>
						 	<div class="input-group">
						 		<div class="input-group-prepend">
									<span class="input-group-text"><i class="fa fa-user" aria-hidden="true"></i></span>
								</div>
								<%=HTMLUtility.getList1("rname", String.valueOf(dto.getRoleId()), lt) %>
						</div>
					</div>		
               </div>           
               
             <div class="col-sm-3">
           <div class="form-group" >
						
						<label style="color: white">Login Id</label>
						 	<div class="input-group">
						 		<div class="input-group-prepend">
									<span class="input-group-text"><i class="fa fa-envelope" aria-hidden="true"></i></span>
								</div>
								<input type="text" name="login" class="form-control"  placeholder="Enter Email Id" value="<%=ServletUtility.getParameter("login", request) %>" >
						</div>
					</div>		
               </div>          
               
                      
           <div class="col-sm-3 ">
           <input type="submit" name="operation"  class="btn btn-success" style="margin-top:35px;" value="<%=UserListCtl.OP_SEARCH%>">    
           <input type="submit" name="operation" class="btn btn-warning"  style="margin-top: 35px;"  value="<%=UserListCtl.OP_RESET%>">
           
           </div>
             
        </div>
       
       
      <br>
 
 
     
     <center>
       <div class="row">
     
         <div class="col">
           <input type="submit" name = "operation" class="btn btn-success"  value="<%=UserListCtl.OP_NEW%>">
     
            <input type="submit" name = "operation" class="btn btn-danger"  value="<%=UserListCtl.OP_DELETE%>">   
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
      <th style="text-align: center;color: black;">FIRST NAME</th>
      <th style="text-align: center;color: black;">LAST NAME</th>
      <th style="text-align: center;color: black;">ADDRESS</th>
      <th style="text-align: center;color: black;">MOBILE NO</th>
      <th style="text-align: center;color: black;">GENDER</th>
      <th style="text-align: center;color: black;">ROLE Name</th>
      <th style="text-align: center;color: black;">DOB</th>
      <th style="text-align: center;color: black;">EMAIL ID</th>
      <th style="text-align: center;color: black;"><i class="fa fa-edit"></i></th>
      </tr>
    <!--  </thead> -->
     
    
<%
   int pageNo= ServletUtility.getPageNo(request);
   int pageSize= ServletUtility.getPageSize(request);
   int index= (pageNo-1)*pageSize+1;
   
   List list = ServletUtility.getList(request);
   
   Iterator it = list.iterator();
   
   while(it.hasNext()){
	   
	   dto= (UserDTO)it.next();
	%>
  
  <tr style="background-color:  #feffba">
	      <td style="text-align: center;color: black;"><input type="checkbox" name="ids" class="checkbox" value="<%=dto.getId()%>" <%=(dto.getRoleId()==RoleDTO.ADMIN)?"disabled":"" %>></td>   
	      <td style="text-align: center;color: black;"><%=index++%></td>
	      <td style="text-align: center;color: black;"><%=dto.getFirstName()%></td>
	      <td style="text-align: center;color: black;"><%=dto.getLastName()%></td>
	      <td style="text-align: center;color: black;"><%=dto.getAddress()%></td>
	      <td style="text-align: center;color: black;"><%=dto.getMobileNo()%></td>
	      <td style="text-align: center;color: black;"><%=dto.getGender()%></td>
	      <td style="text-align: center;color: black;"><%=dto.getRoleName()%></td>
	      <td style="text-align: center;color: black;"><%=DataUtility.getDateString(dto.getDob())%></td>
	      <td style="text-align: center;color: black;"><%=dto.getLogin()%></td>
	        
	      <td style="text-align: center;color: white;">
	      
	      <a href="UserCtl?id=<%=dto.getId()%>"  <% if(dto.getRoleId()==RoleDTO.ADMIN){ %>onclick="return false;" <%} %>><i class="fa fa-edit" ></i></a></td>
	       
	        
	 </tr>  
   
    <%} %>
    
    <% 
    System.out.println("id is"+dto.getId()+" list size"+list.size());
    System.out.println("page size is"+pageSize+"index is "+index);
    %>
   </table>
  </div> 
   
  
  <center>
     <div class="row">
     
     <div class="col">
      <input type="submit" name = "operation" class="btn btn-primary" value="<%=UserListCtl.OP_PREVIOUS%>"
           <%=(pageNo==1)?"disabled":"" %>>

    <input type="submit" name = "operation"      class="btn btn-primary" value="<%=UserListCtl.OP_NEXT%>"
             <%=((list.size()<pageSize||nextListSize==0)?"disabled":"") %>>
     </div>         
        
     </div>
</center>

   
   <input type="hidden" name= "pageNo" value="<%=pageNo%>">
  <input type="hidden" name= "pageSize" value="<%=pageSize%>">
   
<%} %>

 
  </form>
 </div>




<br><br><br><br>
<div><%@include file="Footer.jsp" %></div>
   </body>
</html>
