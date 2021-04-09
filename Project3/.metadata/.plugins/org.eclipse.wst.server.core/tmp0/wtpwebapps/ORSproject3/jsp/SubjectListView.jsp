<%@page import="in.co.rays.ORSproject3.utility.DataUtility"%>
<%@page import="in.co.rays.ORSproject3.dto.SubjectDTO"%>
<%@page import="in.co.rays.ORSproject3.utility.HTMLUtility"%>
<%@page import="in.co.rays.ORSproject3.controller.SubjectListCtl"%>
<%@page import="in.co.rays.ORSproject3.utility.ServletUtility"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" />

<script  src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js">	
</script>	
<script type="text/javascript" src="<%=ORSView.APP_CONTEXT%>/js/checkbox11.js"></script>

<title>SubjectList View page</title>
</head>


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


<body class="hm-gradient">
<div>
<%@ include file="Header.jsp" %>

</div>
<div class="container-fluid my-container">
<form action="<%=ORSView.SUBJECT_LIST_CTL%>" method="post">
  <jsp:useBean id="dto" class="in.co.rays.ORSproject3.dto.SubjectDTO" scope="request"></jsp:useBean>

<br><br><br>
<h1 style="color:white; text-align: center; font-variant: small-caps;">Subject List</h1>
	
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
  int nextListSize= DataUtility.getInt(request.getAttribute("nextListSize").toString());
  %>


<%
List lc= (List)request.getAttribute("courselist");

List ls= (List)request.getAttribute("subjectlist");

%>

<%
List list=ServletUtility.getList(request);

if(list==null || list.size()==0){
%>
<center>
<input type="submit" name="operation"  class="btn btn-primary " style="margin-left: 54px;margin-top:24px;" value="<%=SubjectListCtl.OP_BACK%>">
</center>
<%} else{ %>

<br>
	 
	 
	
	  <div class="row"> 
          
          <div class="col-sm-3">
           <div class="form-group" >
						
						<!-- <label style="color: white">Name</label>
						 --> 	<div class="input-group">
						 		<div class="input-group-prepend">
									<span class="input-group-text" id="inputGroup-sizing-default">Cousre<!-- <i class="fa fa-user" aria-hidden="true"></i> --></span>
								</div>
								<%=HTMLUtility.getList1("courseId",String.valueOf(dto.getCourseId()),lc) %>
						</div>
					</div>		
               </div>
    
       <div class="col-sm-3">
           <div class="form-group" >
						
						<!-- <label style="color: white">Roll No.</label>
						 --> 	<div class="input-group">
						 		<div class="input-group-prepend">
									<span class="input-group-text" id="inputGroup-sizing-default">Subject<!-- <i class="fa fa-user" aria-hidden="true"></i> --></span>
								</div>
								<%=HTMLUtility.getList1("subjectId",String.valueOf(dto.getId()),ls) %>
						</div>
					</div>		
               </div>           
    
       <div class="col-sm-3 ">
           <input type="submit" name="operation"  class="btn btn-success"  value="<%=SubjectListCtl.OP_SEARCH%>">    
           <input type="submit" name="operation" class="btn btn-warning" value="<%=SubjectListCtl.OP_RESET%>">
           
           </div>
             
        </div>

	  <br>
	  <center>
       <div class="row">
     
         <div class="col">
           <input type="submit" name = "operation" class="btn btn-success"  value="<%=SubjectListCtl.OP_NEW%>">
     
            <input type="submit" name = "operation" class="btn btn-danger"  value="<%=SubjectListCtl.OP_DELETE%>">   
     </div>         
        
     </div>
</center>

  <div class="table-responsive" >
      <table class="table table-bordered table-striped">
      <!-- <thead> -->
      <tr style="background-color:  #dabd53">
      <th style="text-align: center;color: black;"><input type="checkbox" id = "select_all" name="ids" >Select All</th>
                    <th style="text-align: center;color: black;">S.NO</th>
					<th style="text-align: center;color: black;">COURSE NAME</th>
					<th style="text-align: center;color: black;">SUBJECT NAME</th>
					<th style="text-align: center;color: black;">DESCRIPTION</th>
					<th style="text-align: center;color: black;"><i class="fa fa-edit"></i></th>
      </tr>

 <%
 int pageNo= ServletUtility.getPageNo(request);
 int pageSize= ServletUtility.getPageSize(request);
 int index=((pageNo-1)*pageSize)+1;
 
 
 List list1 = ServletUtility.getList(request);
 
 Iterator it = list1.iterator();
 SubjectDTO dto1=null;
 
 while(it.hasNext()){
	 dto1= (SubjectDTO)it.next();
  %>
				<tr style="background-color:  #feffba">
					<td style="text-align: center;color: black;"><input type="checkbox" class="checkbox" name="ids" value="<%=dto1.getId()%>"></td>
					<td style="text-align: center;color: black;"><%=index++%></td>
					<td style="text-align: center;color: black;"><%=dto1.getCourseName()%></td>
					<td style="text-align: center;color: black;"><%=dto1.getSubjectName()%></td>
					<td style="text-align: center;color: black;"><%=dto1.getDescription()%></td>
					<td style="text-align: center;"><a href="SubjectCtl?id=<%=dto1.getId()%>"><i class="fa fa-edit" ></i></a></td>
				</tr>
				<%
                    }
                %>
			</table>
</div>	


 <center>
     <div class="row">
     
     <div class="col">
      <input type="submit" name = "operation" class="btn btn-primary" value="<%=SubjectListCtl.OP_PREVIOUS%>"
           <%=(pageNo==1)?"disabled":"" %>>

    <input type="submit" name = "operation"      class="btn btn-primary" value="<%=SubjectListCtl.OP_NEXT%>"
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