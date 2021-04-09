
<%@page import="in.co.rays.ORSproject3.controller.MarksheetCtl"%>
<%@page import="in.co.rays.ORSproject3.utility.HTMLUtility"%>
<%@page import="in.co.rays.ORSproject3.utility.DataUtility"%>
<%@page import="in.co.rays.ORSproject3.utility.ServletUtility"%>
<%@page import="java.util.List"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" />
<title>Marksheet View Page</title>

<style type="text/css">

.hm-gradient {
background-image: url("<%=ORSView.APP_CONTEXT%>/img/1233.jpg");
}

.darken-grey-text {
    color: #2E2E2E;
}
.btn-primary{
    color: #c7361f;
	width:85px;

}

.danger-text {
    color: #ff3547; }
    
.default-text 
{
    color: #c7361f; 
}
.info-text {
    color: #33b5e5; 
}
.paddingclass{
padding-top: 10px;
}

</style>
<style type="text/css">
.setForm{
padding-top: 5%;
padding-left: 25%;
width: 130%
}
.button{
border-radius:10px;padding:10px;color:white;font-size:20px;background-color:#dabd53
}
.textfield{
border: 1px solid #8080803b;height: 38px; padding-left: 6px;
}
</style>
<script type="text/javascript">
function isNumberKey(evt)
{
   var charCode = (evt.which) ? evt.which : event.keyCode
   if (charCode > 31 && (charCode < 48 || charCode > 57))
      return false;

   return true;
}
</script>

</head>
<body >

<jsp:useBean id="dto" class="in.co.rays.ORSproject3.dto.MarksheetDTO" scope="request"></jsp:useBean>
<div>
<%@include file="Header.jsp" %>
</div>
<form action="<%=ORSView.MARKSHEET_CTL%>" method="post">

<%
List l = (List)request.getAttribute("StudentList");

/* List list= ServletUtility.getList(request); */
%>
<main>

<div class="container-fluid mt-4">
<div class="row">
<div class="col-md-3"></div>
<div class="col-md-6">
<br>
<br>
<br>
<div class="card">
<div class="card-body">
<%long id=DataUtility.getLong(request.getParameter("id")); %> 
<h3 class="text-center default-text py-3">
<%=(id==0)?"Add Marksheet":"Update Marksheet" %>
</h3>

<% if(!ServletUtility.getErrorMessage(request).equals("")) { %>
<div class="alert alert-success alert-dismissible fade show">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <strong>Error!</strong><%=ServletUtility.getErrorMessage(request) %>
    </div>
<%} %>

<% if(!ServletUtility.getSuccessMessage(request).equals("")) { %>
<div class="alert alert-success alert-dismissible fade show">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <strong>Success!</strong><%=ServletUtility.getSuccessMessage(request) %>
    </div>
<%} %>
<input type="hidden" name="id" value="<%=dto.getId()%>">
            <input type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
            <input type="hidden" name="modifiedBy" value="<%=dto.getModifiedBy()%>">
            <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(dto.getCreatedDatetime())%>">
            <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(dto.getModifiedDatetime())%>">
   
                          <h6 style="color: #c7361f" >Roll No<font color="red">*</font></h6>
                            
                            <div class="md-form">

         <div class="input-group">
                            <div class="input-group-prepend">
                               <span class="input-group-text"><i style="width: 17px"  class="fa fa-user"></i></span>
                                 </div>
                        <input type="text" placeholder="Enter Roll Number" class="form-control border" name="rollno" value="<%=DataUtility.getStringData(dto.getRollNo())%>">
                                </div>
							<font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("rollno",request) %> </font>

                               </div>            

<%String student=HTMLUtility.getList("studentid",String.valueOf(dto.getStudentId()),l); %>
                           <h6 class="paddingclass" style="color: #c7361f" >Student Name<font color="red">*</font></h6>
                           
                          <div class="md-form">
                 
                 		<div class="input-group mb-3">
                             <div class="input-group-prepend">
                             <span class="input-group-text"><i style="width: 17px" class="fa fa-venus-mars"></i></span>
                                      </div>
                           	<%=student%>
                                      </div>
								<font color="red" class="ml-5"> <%=ServletUtility.getErrorMessage("studentid1", request) %></font>
           
                          </div>
                          
<h6 style="color: #c7361f" >Physics<font color="red">*</font></h6>
                            <div class="md-form">
                                    <div class="input-group">
                            <div class="input-group-prepend">
                               <span class="input-group-text"><i style="width: 17px"  class="fa fa-circle"></i></span>
                                 </div>
                        <input onkeypress="return isNumberKey(event)" type="text" placeholder="Enter physics marks" class="form-control border" name="physics" value="<%=DataUtility.getStringData(dto.getPhysics()).equals("0")?"":DataUtility.getStringData(dto.getPhysics())%>">
                                </div>
							<font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("physics1",request) %> </font>
                           
                           
                                </div>
                                
                             <h6 class="paddingclass" style="color: #c7361f" >Chemistry<font color="red">*</font></h6>
                            <div class="md-form">
                        
                                 <div class="input-group">
                            <div class="input-group-prepend">
                               <span class="input-group-text"><i style="width: 17px"  class="fa fa-circle"></i></span>
                                 </div>
                        <input onkeypress="return isNumberKey(event)" type="text" placeholder="Enter chemistry  marks" class="form-control border" name="chemistry" value="<%=DataUtility.getStringData(dto.getChemistry()).equals("0")?"":DataUtility.getStringData(dto.getChemistry())%>">
                                </div>
							<font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("chemistry",request) %> </font>
                        
                                </div>
                            
                            
                             <h6 class="paddingclass" style="color: #c7361f" >Maths<font color="red">*</font></h6>
                            <div class="md-form">	
         
                  <div class="input-group">
                            <div class="input-group-prepend">
                               <span class="input-group-text"><i style="width: 17px"  class="fa fa-circle"></i></span>
                                 </div>
                        <input onkeypress="return isNumberKey(event)" type="text" placeholder="Enter maths marks" class="form-control border" name="math" value="<%=DataUtility.getStringData(dto.getMaths()).equals("0")?"":DataUtility.getStringData(dto.getMaths())%>">
                                </div>
							<font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("math",request) %> </font>
         
                                </div>
                                <br>
                            
<%if (id>0){ %>
                           <div class="text-center"  id="defaultForm-email">
                           <input class="btn btn-success" type="submit"  name="operation" value="<%=MarksheetCtl.OP_UPDATE%>">
                           <input class="btn btn-warning" type="submit"  name="operation" value="<%=MarksheetCtl.OP_CANCEL%>">
                          
                            </div>
                          
                          <%}else{ %> 
                           <div class="text-center"  id="defaultForm-email">
                           <input class="btn btn-success" type="submit"  name="operation" value="<%=MarksheetCtl.OP_SAVE%>">
                           <input class="btn btn-warning" type="submit"  name="operation" value="<%=MarksheetCtl.OP_RESET%>">
                          
                            </div>
                             <%} %>


</div>
</div>
</div>
<div class="col-md-3"></div>
</div>
</div>
<br><br><br>
</main>
<div><%@include file="Footer.jsp" %></div>
</body>
</html>