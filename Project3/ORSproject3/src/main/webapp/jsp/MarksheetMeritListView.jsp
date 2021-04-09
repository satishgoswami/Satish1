<%@page import="in.co.rays.ORSproject3.controller.MarksheetMeritListCtl"%>
<%@page import="in.co.rays.ORSproject3.dto.MarksheetDTO"%>
<%@page import="in.co.rays.ORSproject3.utility.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<html>
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16"/>
<head>
<title>MarksheetMeritList Page</title>
	
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
.hm-gradient .table-bordered {
    border: none;
}

</style>
</head>
<body class="hm-gradient">
<jsp:useBean id="dto" scope="request" class="in.co.rays.ORSproject3.dto.MarksheetDTO" />
<div>
<%@include file="Header.jsp" %>
</div>

<div class="container-fluid">
<form action="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>" method="post">
<br><br><br>
<h1 style="color:white; text-align: center; font-variant: small-caps;"> Marksheet Merit List:</h1>
	<%
	
	
//	List list = ServletUtility.getList(request);
List list=(List)request.getAttribute("l");
	System.out.println(list.size()+"-----------");
        if (list.size()==0){ %>
			<input type="submit" name="operation"
				value="<%=MarksheetMeritListCtl.OP_BACK%>">
			<%}else{ %>
	 
	  
	  <br>
	  <br>
	  <br>
	  <div class="table-responsive table-bordered" >
      <table class="table table-striped" width="100%">
      <thead>
      <tr style="background-color:  #dabd53">
                    <th class="text-dark">S.NO</th>
					<th >Roll No</th>
					<th>Name</th>
					<th>Maths</th>
					<th>Physics</th>
					<th>Chemistry</th>
					<th>Total</th>
					<th>Percentage%</th>
      </tr>
      </thead>
      <%
	                int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int index = ((pageNo - 1) * pageSize) + 1;
                   
                    Iterator<MarksheetDTO> it = list.iterator();
                    while (it.hasNext()) {
                    	
                       dto = it.next();
               %>
               <tbody>
				<tr style="background-color:  #feffba">
					<td class=""><%=index++%></td>
					<td><%=dto.getRollNo()%></td>
					<td><%=dto.getName()%></td>
					<td><%=dto.getMaths()%></td>
					<td><%=dto.getPhysics()%></td>
					<td><%=dto.getChemistry()%></td>
	                <td><%=(dto.getChemistry()+dto.getMaths()+dto.getPhysics())%></td>
                    <td><%=(dto.getChemistry()+dto.getMaths()+dto.getPhysics())/3%></td>
				</tr>
				</tbody>
				<%
                    }
                %>
			</table>
			
			</div>
			
			
			<center>
     <div class="row">
     
     <div class="col">
   <a href="<%=ORSView.JASPER_CTL%>" type="submit" target="blank" class="btn btn-primary">Print</a>
      <input class="btn btn-primary" type="submit"  name="operation" value="<%=MarksheetMeritListCtl.OP_BACK%>">
  </div>         
        
     </div>
</center>
			
			
			
			<%} %>
</form>
</div>
<br>
<br>
<br>
<br>
<div><%@include file="Footer.jsp" %></div>
</body>
</html>