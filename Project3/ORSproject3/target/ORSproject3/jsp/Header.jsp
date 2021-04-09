<%@page import="in.co.rays.ORSproject3.dto.RoleDTO"%>
<%@page import="in.co.rays.ORSproject3.controller.LoginCtl"%>
<%@page import="in.co.rays.ORSproject3.controller.ORSView"%>
<%@page import="in.co.rays.ORSproject3.dto.UserDTO"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Header</title>



<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>


 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">



 
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
 
<style>
body {
 background-image: url("<%=ORSView.APP_CONTEXT%>/img/1233.jpg");
 background-color: #cccccc;
background-repeat: no-repeat;
    background-size: cover;
}

.hm-gradient .navbar .dropdown-menu a:hover {
    color: #0e0e0e!important;
}

.navbar-expand-lg .navbar-nav .nav-link {
    padding-right: 0.8rem;
    padding-left: .8rem;
}

</style>

</head>
<body>

<%
		UserDTO userBean = (UserDTO) session.getAttribute("user");

		boolean userLoggedIn = userBean!=null;

		String welcomeMsg = "Hi,";

		if (userLoggedIn) {
			String role = (String) session.getAttribute("role");
			welcomeMsg += userBean.getFirstName() + " (" + role + ")";
		} else {
			welcomeMsg += "guest";
		}
	%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
  <!-- Navbar content -->
  <img  src="<%=ORSView.APP_CONTEXT%>/img/customLogo.png" width="70" height="30" alt="">
  &nbsp;&nbsp;
  
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav ml-auto">
      <li class="nav-item">
       <a class="navbar-brand" href="<%=ORSView.WELCOME_CTL%>"><i class="fa fa-home" aria-hidden="true"></i></a>
       </li>
       <%
        if (userLoggedIn) {
    %>
     
      <li class="dropdown show" >
         
        <a class="nav-link dropdown-toggle"  id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <i class="fa fa-sticky-note" aria-hidden="true"></i>Marksheet
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
         
          <a class="dropdown-item" href="<%=ORSView.MARKSHEET_CTL%>"><i class="fa fa-plus-square" aria-hidden="true"></i>Add Marksheet</a>
          <a class="dropdown-item" href="<%=ORSView.GET_MARKSHEET_CTL%>"><i class="fa fa-file-text" aria-hidden="true"></i>Get Marksheet</a>
          <a class="dropdown-item" href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>"><i class="fa fa-list" aria-hidden="true"></i>MarksheetMerit List</a>
          <!-- <div class="dropdown-divider"></div> -->
          <a class="dropdown-item" href="<%=ORSView.MARKSHEET_LIST_CTL%>"><i class="fa fa-list" aria-hidden="true"></i>Marksheet List</a>
        </div>
      </li>
     
      
    <li class="dropdown show">
        <a class="nav-link dropdown-toggle"  id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <i class="fa fa-user-o" aria-hidden="true"></i>User
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="<%=ORSView.USER_CTL%>"><i class="fa fa-plus-square" aria-hidden="true"></i>Add User</a>
          <a class="dropdown-item" href="<%=ORSView.USER_LIST_CTL%>"><i class="fa fa-list" aria-hidden="true"></i>User List</a>
        </div>
      </li>
    
    <li class="dropdown show">
        <a class="nav-link dropdown-toggle"  id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <i class="fa fa-user-plus" aria-hidden="true"></i>Role
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="<%=ORSView.ROLE_CTL%>"><i class="fa fa-plus-square" aria-hidden="true"></i>Add Role</a>
          <a class="dropdown-item" href="<%=ORSView.ROLE_LIST_CTL%>"><i class="fa fa-list" aria-hidden="true"></i>Role List</a>
        </div>
      </li>
    
    <li class="dropdown show">
        <a class="nav-link dropdown-toggle"  id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <i class="fa fa-graduation-cap" aria-hidden="true"></i>Student
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="<%=ORSView.STUDENT_CTL%>"> <i class="fa fa-plus-square" aria-hidden="true"></i>Add Student</a>
          <a class="dropdown-item" href="<%=ORSView.STUDENT_LIST_CTL%>"><i class="fa fa-list" aria-hidden="true"></i>Student List</a>
        </div>
      </li>
    
    <li class="dropdown show">
        <a class="nav-link dropdown-toggle"  id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <i class="fa fa-book" aria-hidden="true"></i>Subject
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="<%=ORSView.SUBJECT_CTL%>"><i class="fa fa-plus-square" aria-hidden="true"></i>Add Subject</a>
          <a class="dropdown-item" href="<%=ORSView.SUBJECT_LIST_CTL%>"><i class="fa fa-list" aria-hidden="true"></i>Subject List</a>
        </div>
      </li>
    
    <li class="dropdown show">
        <a class="nav-link dropdown-toggle"  id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <i class="fa fa-users" aria-hidden="true"></i>Faculty
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="<%=ORSView.FACULTY_CTL%>"><i class="fa fa-plus-square" aria-hidden="true"></i>Add Faculty</a>
          <a class="dropdown-item" href="<%=ORSView.FACULTY_LIST_CTL%>"><i class="fa fa-list" aria-hidden="true"></i>Faculty List</a>
        </div>
      </li>
    <li class="dropdown show">
        <a class="nav-link dropdown-toggle"  id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <i class="fa fa-university" aria-hidden="true"></i>College
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="<%=ORSView.COLLEGE_CTL%>"><i class="fa fa-plus-square" aria-hidden="true"></i>Add College</a>
          <a class="dropdown-item" href="<%=ORSView.COLLEGE_LIST_CTL%>"><i class="fa fa-list" aria-hidden="true"></i>College List</a>
        </div>
      </li>
    <li class="dropdown show">
        <a class="nav-link dropdown-toggle"  id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <i class="fa fa-list-alt" aria-hidden="true"></i>Course
        </a>
        
        
        
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="<%=ORSView.COURSE_CTL%>"><i class="fa fa-plus-square" aria-hidden="true"></i>Add Course</a>
          <a class="dropdown-item" href="<%=ORSView.COURSE_LIST_CTL%>"><i class="fa fa-list" aria-hidden="true"></i>Course List</a>
        </div>
      </li>
      <li class="dropdown show">
        <a class="nav-link dropdown-toggle"  id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <i class="fa fa-table" aria-hidden="true"></i>Timetable
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="<%=ORSView.TIMETABLE_CTL%>"><i class="fa fa-plus-square" aria-hidden="true"></i>Add Timetable</a>
          <a class="dropdown-item" href="<%=ORSView.TIMETABLE_LIST_CTL%>"><i class="fa fa-list" aria-hidden="true"></i>Timetable List</a>
        </div>
      </li>
<%} %> 


		</ul>
		
<div class=" collapse navbar-nav " id="navbarSupportedContent" >   
	<li class="dropdown show">
        <a class="nav-link dropdown-toggle"  id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
         <i class="fa fa-user-circle-o" aria-hidden="true"></i><%=welcomeMsg%>
        </a>
        
        <%
						if (userLoggedIn) {
					%>
        <div class="dropdown-menu dropdown-menu-left" aria-labelledby="navbarDropdown">
          <a class="dropdown-item dropdown-menu-left" href="<%=ORSView.MY_PROFILE_CTL%>"><i class="fa fa-user-secret" aria-hidden="true"></i>My Profile</a>
          <a class="dropdown-item dropdown-menu-left" href="<%=ORSView.CHANGE_PASSWORD_CTL%>"><i class="fa fa-list" aria-hidden="true"></i>Change Password</a>
<a
							class="dropdown-item dropdown-menu-left" href="<%=ORSView.JAVA_DOC_VIEW%>" target="blank"><i class="fa fa-sticky-note" aria-hidden="true"></i>Java Doc</a> <a class="dropdown-item"
							href="<%=ORSView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOG_OUT%>"><i class="fa fa-sign-out" aria-hidden="true"></i>Logout</a>


					</div>
					<%
						} else {
					%>
					<div class="dropdown-menu" style="margin-left: -55px">
						<a class="dropdown-item" href="<%=ORSView.LOGIN_CTL%>"><i class="fa fa-sign-in" aria-hidden="true"></i> Login</a> <a class="dropdown-item"
							href="<%=ORSView.USER_REGISTRATION_CTL%>"><i
							class="fa fa-registered"></i> User Registration </a> <a
							class="dropdown-item" href="<%=ORSView.FORGET_PASSWORD_CTL%>"><i class="fa fa-question-circle" aria-hidden="true"></i> Forget Password</a>
										</div>
					<%} %>

				</div>
			</li>
</div>


  </div>
</nav>


</body>
</html>