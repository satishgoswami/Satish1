
<%@page import="in.co.rays.ORSproject3.controller.ORSView"%>
<%@page import="in.co.rays.ORSproject3.controller.GetMarksheetCtl"%>
<%@page import="in.co.rays.ORSproject3.utility.DataUtility"%>
<%@page import="in.co.rays.ORSproject3.utility.ServletUtility"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16" />
<title>Get Marksheet</title>

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
border-radius:10px;padding:10px;color:white;font-size:20px;background-color:#dabd53;width:90px;
}
.textfield{
border: 1px solid #8080803b;height: 38px; padding-left: 6px;
}

</style>
</head>
<body>
<form action="<%=ORSView.GET_MARKSHEET_CTL%>" method="post">
<div></div>
        <%@ include file="Header.jsp"%>
</div>
 <jsp:useBean id="dto" class="in.co.rays.ORSproject3.dto.MarksheetDTO"
            scope="request"></jsp:useBean>


      		<main>

		<div class="container-fluid mt-4">
			<div class="row">
				<div class="col-md-3"></div>
				<div class="col-md-6">
<br>
<br>
<br>
<br>
					<div class="card">
						<div class="card-body">
							<%
								long id = DataUtility.getLong(request.getParameter("id"));
							%>
							<h3 class="text-center default-text py-3">
								<%="Get Marksheet"%>
							</h3>

							<%
								if (!ServletUtility.getErrorMessage(request).equals("")) {
							%>
							<div class="alert alert-danger alert-dismissible fade show">
								<button type="button" class="close" data-dismiss="alert">&times;</button>
								<strong>Error!</strong><%=ServletUtility.getErrorMessage(request)%>
							</div>
							<%
								}
							%>

							<%
								if (!ServletUtility.getSuccessMessage(request).equals("")) {
							%>
							<div class="alert alert-success alert-dismissible fade show">
								<button type="button" class="close" data-dismiss="alert">&times;</button>
								<strong>Success!</strong><%=ServletUtility.getSuccessMessage(request)%>
							</div>
							<%
								}
							%>

<h5 style="color: #c7361f">Roll No:-
							</h5>

							<div class="md-form">

								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"><i style="width: 17px"
											class="fa fa-user"></i></span>
									</div>
									<input type="text" placeholder="Enter Roll Number"
							class="form-control border" name="rollNo"
										value="<%=DataUtility.getStringData(dto.getRollNo())%>"> &nbsp;&nbsp;
<div class="text-center"  id="defaultForm-email">
                           <input class="btn btn-success" type="submit"  name="operation" value="<%=GetMarksheetCtl.OP_GO%>">
                           <input class="btn btn-primary" type="submit"  name="operation" value="<%=GetMarksheetCtl.OP_RESET%>">
                          
                            </div>



								</div>
								<font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("rollN1o", request)%>
								</font>

							</div>

<br>
<br>

<%
	if(dto.getRollNo()!=null && dto.getRollNo().trim().length()>0 && dto.getName()!=null ){
		System.out.println("roll no is "+dto.getRollNo());
	%>
	<div class="table-responsive">
			<table border="1" width="100%">
				<tr style="background-color:  #dabd53">
					<td align="center"><h2 >Rays Technologies</h2></td>
				</tr>
			</table>

			<table border="1" width="100%">
				<tr>
					<td align="center"><b>Name</b></td>
					<th><%=DataUtility.getStringData(dto.getName()) %></th>
					<td align="center"><b>Roll No</b></td>
					<th><%=DataUtility.getStringData(dto.getRollNo()) %></th>
				</tr>
				<tr>
					<td align="center"><b>Status</b></td>
					<th>Regular</th>

					<td align="center"><b>Course</b></td>
					<th>BE</th>
				</tr>
			</table>
			<%
		    int phy= DataUtility.getInt(DataUtility.getStringData(dto.getPhysics()));
		    int che=DataUtility.getInt(DataUtility.getStringData(dto.getChemistry()));
		    int mat=DataUtility.getInt(DataUtility.getStringData(dto.getMaths()));
		    int tot=(phy+che+mat);
		    System.out.print("TotalMarks "+tot);
		    float per= tot/3;
		      
		    System.out.print("per "+per);
		    %>
			<table border="1" width="100%">
				<tr>
					<td align="center" style="width: 15%"><b>Subject</b></td>
					
					<td align="center" style="width: 22.28%"><b>Max Marks</b></td>
					<td align="center" style="width: 22.27%"><b>Min Marks</b></td>
					<td align="center" style="width: 18%"><b>Marks got</b></td>
					<td align="center" style="width: 22%"><b>Grade</b></td>
				</tr>
				<tr>
					<td align="center">Physics</td>
					<td align="center">100</td>
					<td align="center">33</td>
	
					<td align="center"><%=phy%> <%
 	if (phy < 33) {
 %><span style="color: red">*</span> <%
 	}
 %></td>
					<td align="center">
						<%
							if (phy > 90 && phy <= 100) {
						%>A+ <%
							} else if (phy > 80 && phy <= 90) {
						%>A <%
							} else if (phy > 70 && phy <= 80) {
						%>B+ <%
							} else if (phy > 70 && phy <= 80) {
						%>B <%
							} else if (phy > 60 && phy <= 70) {
						%>C+ <%
							} else if (phy > 50 && phy <= 60) {
						%>C <%
							} else if (phy >= 33 && phy <= 50) {
						%>D <%
							} else if (phy >= 0 && phy < 33) {
						%><span style="color: red;">Fail</span> <%
 	}
 %>
					</td>

				</tr>

				<tr>
					<td align="center">Chemistry</td>
					<td align="center">100</td>
					<td align="center">33</td>

					<td align="center"><%=che%> <%
if(che<33){
%><span style="color: red">*</span> <%} %></td>

					<td align="center">
						<%if(che>90 && che<=100){
           %>A+<%
           } else if(che>80 && che <=90){				
        	   %>A<%
        	   } else if(che>70 && che <=80){
        	   %>B+<%
        	   }else if(che>60 && che <=70){
        	   %>B<%
        	   }else if(che>50 && che <=60){
        	   %>C+<%
        	   }else if(che>40 && che <=50){
        	   %>C<%
        	   }else if(che>=33 && che <=40){
        	   %>D<%
        	   }else if(che>0 && che < 33){
        	   %><span color="red">Fail</span> <%}
%>
					</td>
				</tr>
				<tr>
					<td align="center">Math</td>
					<td align="center">100</td>
					<td align="center">33</td>

					<td align="center"><%=mat%> <%if(mat<33){
%><span style="color: red">*</span> <%} %></td>
					<td align="center">
						<%if(mat>90 && mat<=100){ 
             %>A+<%
             }else if(mat>80 && mat<=90){
             %>A<%
             }else if(mat>70 && mat<=80){
             %>B+<%
             }else if(mat>60 && mat<=70){
             %>B<%
             }else if(mat>50 && mat<=60){
             %>C+<%
             }else if(mat>40 && mat<=50){
             %>C<%
             }else if(mat>=33 && mat<=40){
             %>D<%
             }else if(mat>0 && mat<33){
             %><span style="color: red">Fail</span>
						<% 	 
            	 }%>
					</td>
				</tr>
			</table>


			<table border="1" width="100%">
				<tr>
					<td align="center"><b>Total</b></td>
					<td align="center"><b>Percentage(%)</b></td>
					<td align="center"><b>Division</b></td>
					<td align="center"><b>Result</b></td>
				</tr>
				<tr>
					<td align="center"><b><%=tot%> <%
		       if(tot<99 ||phy<33||che<33||mat<33){
		       %><span style="color: red">*</span> <%} %></b></td>

					<td align="center"><b><%=per%>%</b></td>
					<td align="center"><b>
						<%
		      if(per>=60 && per<100){
		      %>1<sup>st</sup> <%} else if(per>=40 && per<60){
		      %>2<sup>nd</sup> <%}else if(per>0 && per<40){ 
		      %>3<sup>rd</sup> <%} %>
					</b>
					</td>

					<td align="center">
						<b><%
		      if(phy>=33 && che>=33 && mat>=33)
		      {%><span style="color: green">Pass</span> <%}else{ %> <span
						style="color: red">Fail</span> <%} %>
			</b>		</td>
				</tr>
			</table>

			<%} %>

			</table>
	</center>
</div>

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