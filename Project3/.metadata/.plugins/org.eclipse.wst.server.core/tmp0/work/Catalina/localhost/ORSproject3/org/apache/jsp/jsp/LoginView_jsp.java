/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2021-03-15 10:39:53 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import in.co.rays.ORSproject3.utility.DataUtility;
import in.co.rays.ORSproject3.utility.ServletUtility;
import in.co.rays.ORSproject3.dto.RoleDTO;
import in.co.rays.ORSproject3.controller.LoginCtl;
import in.co.rays.ORSproject3.controller.ORSView;
import in.co.rays.ORSproject3.dto.UserDTO;

public final class LoginView_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/jsp/Footer.jsp", Long.valueOf(1582975750043L));
    _jspx_dependants.put("/jsp/Header.jsp", Long.valueOf(1615796699760L));
  }

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=ISO-8859-1");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<link rel=\"icon\" type=\"image/png\" href=\"");
      out.print(ORSView.APP_CONTEXT);
      out.write("/img/logo.png\" sizes=\"16*16\"/>\r\n");
      out.write("<title> Login Page</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<!-- font-awesome library -->\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write("/* @import\r\n");
      out.write("\turl(https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css)\r\n");
      out.write("\t;\r\n");
      out.write("\r\n");
      out.write("@import\r\n");
      out.write("\turl(https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.4.3/css/mdb.min.css)\r\n");
      out.write("\t;\r\n");
      out.write(" */\r\n");
      out.write(".hm-gradient {\r\n");
      out.write("\tbackground-image: url(\"");
      out.print(ORSView.APP_CONTEXT);
      out.write("/img/1233.jpg\");\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".darken-grey-text {\r\n");
      out.write("\tcolor: #2E2E2E;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".danger-text {\r\n");
      out.write("\tcolor: #ff3547;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".default-text {\r\n");
      out.write("\tcolor: #c7361f;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".info-text {\r\n");
      out.write("\tcolor: #33b5e5;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write(".paddingclass {\r\n");
      out.write("\tpadding-top: 10px;\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write(".setForm {\r\n");
      out.write("\tpadding-top: 5%;\r\n");
      out.write("\tpadding-left: 25%;\r\n");
      out.write("\twidth: 130%\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".button {\r\n");
      out.write("\tborder-radius: 10px;\r\n");
      out.write("\tpadding: 10px;\r\n");
      out.write("\tcolor: white;\r\n");
      out.write("\tfont-size: 20px;\r\n");
      out.write("\tbackground-color: #00cc88\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".textfield {\r\n");
      out.write("\tborder: 1px solid #8080803b;\r\n");
      out.write("\theight: 38px;\r\n");
      out.write("\tpadding-left: 6px;\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write(" <body class=\"hm-gradient\"> \r\n");
      out.write("\r\n");
 	String uri = (String)request.getAttribute("uri");
      out.write('\r');
      out.write('\n');
      out.write('	');
      in.co.rays.ORSproject3.dto.UserDTO dto = null;
      dto = (in.co.rays.ORSproject3.dto.UserDTO) _jspx_page_context.getAttribute("dto", javax.servlet.jsp.PageContext.REQUEST_SCOPE);
      if (dto == null){
        dto = new in.co.rays.ORSproject3.dto.UserDTO();
        _jspx_page_context.setAttribute("dto", dto, javax.servlet.jsp.PageContext.REQUEST_SCOPE);
      }
      out.write("\r\n");
      out.write("\t<div>\r\n");
      out.write("\t\t");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n");
      out.write("<title>Header</title>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\" integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">\r\n");
      out.write("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script>\r\n");
      out.write("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js\"></script>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write(" <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js\" integrity=\"sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl\" crossorigin=\"anonymous\"></script>\r\n");
      out.write("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css\">\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write(" \r\n");
      out.write("<link rel=\"stylesheet\" href=\"//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css\">\r\n");
      out.write("<link rel=\"stylesheet\" href=\"/resources/demos/style.css\">\r\n");
      out.write("<script src=\"https://code.jquery.com/jquery-1.12.4.js\"></script>\r\n");
      out.write("<script src=\"https://code.jquery.com/ui/1.12.1/jquery-ui.js\"></script>\r\n");
      out.write(" \r\n");
      out.write("<style>\r\n");
      out.write("body {\r\n");
      out.write("  background-image: url(\"");
      out.print(ORSView.APP_CONTEXT);
      out.write("/img/1233.jpg\"); \r\n");
      out.write(" background-color: #cccccc;\r\n");
      out.write("background-repeat: no-repeat;\r\n");
      out.write("    background-size: cover;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".hm-gradient .navbar .dropdown-menu a:hover {\r\n");
      out.write("    color: #0e0e0e!important;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".navbar-expand-lg .navbar-nav .nav-link {\r\n");
      out.write("    padding-right: 0.8rem;\r\n");
      out.write("    padding-left: .8rem;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("</style>\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");

		UserDTO userBean = (UserDTO) session.getAttribute("user");

		boolean userLoggedIn = userBean!=null;

		String welcomeMsg = "Hi,";

		if (userLoggedIn) {
			String role = (String) session.getAttribute("role");
			welcomeMsg += userBean.getFirstName() + " (" + role + ")";
		} else {
			welcomeMsg += "guest";
		}
	
      out.write("\r\n");
      out.write("\r\n");
      out.write("<nav class=\"navbar navbar-expand-lg navbar-dark bg-dark fixed-top\">\r\n");
      out.write("  <!-- Navbar content -->\r\n");
      out.write("  <img  src=\"");
      out.print(ORSView.APP_CONTEXT);
      out.write("/img/customLogo.png\" width=\"70\" height=\"30\" alt=\"\">\r\n");
      out.write("  &nbsp;&nbsp;\r\n");
      out.write("  \r\n");
      out.write("   <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarSupportedContent\" aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\r\n");
      out.write("     <span class=\"navbar-toggler-icon\"></span> \r\n");
      out.write("  </button>\r\n");
      out.write("\r\n");
      out.write("  <!-- <div class=\"collapse navbar-collapse\" id=\"navbarSupportedContent\"> -->\r\n");
      out.write("    <ul class=\"navbar-nav ml-auto\">\r\n");
      out.write("      <!-- <li class=\"nav-item\"> -->\r\n");
      out.write("       <a class=\"navbar-brand\" href=\"");
      out.print(ORSView.WELCOME_CTL);
      out.write("\"> <i class=\"fa fa-home\" aria-hidden=\"true\"></i></a> \r\n");
      out.write("       </li>\r\n");
      out.write("       ");

        if (userLoggedIn) {
    
      out.write("\r\n");
      out.write("     \r\n");
      out.write("        <li class=\"dropdown show\" >  \r\n");
      out.write("         \r\n");
      out.write("         <a class=\"nav-link dropdown-toggle\"   id=\"navbarDropdown\" role=\"button\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\r\n");
      out.write("           <i class=\"fa fa-sticky-note\"  aria-hidden=\"true\"></i>Marksheet\r\n");
      out.write("        </a>\r\n");
      out.write("        <div  class=\"dropdown-menu\"  aria-labelledby=\"navbarDropdown\"> \r\n");
      out.write("         \r\n");
      out.write("          <a class=\"dropdown-item\" href=\"");
      out.print(ORSView.MARKSHEET_CTL);
      out.write("\"><i class=\"fa fa-plus-square\" aria-hidden=\"true\"></i>Add Marksheet</a>\r\n");
      out.write("          <a class=\"dropdown-item\" href=\"");
      out.print(ORSView.GET_MARKSHEET_CTL);
      out.write("\"><i class=\"fa fa-file-text\" aria-hidden=\"true\"></i>Get Marksheet</a>\r\n");
      out.write("          <a class=\"dropdown-item\" href=\"");
      out.print(ORSView.MARKSHEET_MERIT_LIST_CTL);
      out.write("\"><i class=\"fa fa-list\" aria-hidden=\"true\"></i>MarksheetMerit List</a>\r\n");
      out.write("          <!-- <div class=\"dropdown-divider\"></div> -->\r\n");
      out.write("          <a class=\"dropdown-item\" href=\"");
      out.print(ORSView.MARKSHEET_LIST_CTL);
      out.write("\"><i class=\"fa fa-list\" aria-hidden=\"true\"></i>Marksheet List</a>\r\n");
      out.write("        </div>\r\n");
      out.write("      </li>\r\n");
      out.write("     \r\n");
      out.write("      \r\n");
      out.write("      <li class=\"dropdown show\"> \r\n");
      out.write("         <a class=\"nav-link dropdown-toggle\"  id=\"navbarDropdown\" role=\"button\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\r\n");
      out.write("          <i class= \"fa fa-user-o\"  aria-hidden=\"true\"></i>User\r\n");
      out.write("        </a>  \r\n");
      out.write("        <div class=\"dropdown-menu\" aria-labelledby=\"navbarDropdown\">\r\n");
      out.write("          <a class=\"dropdown-item\" href=\"");
      out.print(ORSView.USER_CTL);
      out.write("\"><i class=\"fa fa-plus-square\" aria-hidden=\"true\"></i>Add User</a>\r\n");
      out.write("          <a class=\"dropdown-item\" href=\"");
      out.print(ORSView.USER_LIST_CTL);
      out.write("\"><i class=\"fa fa-list\" aria-hidden=\"true\"></i>User List</a>\r\n");
      out.write("        </div>\r\n");
      out.write("      </li>\r\n");
      out.write("    \r\n");
      out.write("    <li class=\"dropdown show\">\r\n");
      out.write("        <a class=\"nav-link dropdown-toggle\"  id=\"navbarDropdown\" role=\"button\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\r\n");
      out.write("          <i class=\"fa fa-user-plus\" aria-hidden=\"true\"></i>Role\r\n");
      out.write("        </a>\r\n");
      out.write("        <div class=\"dropdown-menu\" aria-labelledby=\"navbarDropdown\">\r\n");
      out.write("          <a class=\"dropdown-item\" href=\"");
      out.print(ORSView.ROLE_CTL);
      out.write("\"><i class=\"fa fa-plus-square\" aria-hidden=\"true\"></i>Add Role</a>\r\n");
      out.write("          <a class=\"dropdown-item\" href=\"");
      out.print(ORSView.ROLE_LIST_CTL);
      out.write("\"><i class=\"fa fa-list\" aria-hidden=\"true\"></i>Role List</a>\r\n");
      out.write("        </div>\r\n");
      out.write("      </li>\r\n");
      out.write("    \r\n");
      out.write("    <li class=\"dropdown show\">\r\n");
      out.write("        <a class=\"nav-link dropdown-toggle\"  id=\"navbarDropdown\" role=\"button\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\r\n");
      out.write("          <i class=\"fa fa-graduation-cap\" aria-hidden=\"true\"></i>Student\r\n");
      out.write("        </a>\r\n");
      out.write("        <div class=\"dropdown-menu\" aria-labelledby=\"navbarDropdown\">\r\n");
      out.write("          <a class=\"dropdown-item\" href=\"");
      out.print(ORSView.STUDENT_CTL);
      out.write("\"> <i class=\"fa fa-plus-square\" aria-hidden=\"true\"></i>Add Student</a>\r\n");
      out.write("          <a class=\"dropdown-item\" href=\"");
      out.print(ORSView.STUDENT_LIST_CTL);
      out.write("\"><i class=\"fa fa-list\" aria-hidden=\"true\"></i>Student List</a>\r\n");
      out.write("        </div>\r\n");
      out.write("      </li>\r\n");
      out.write("    \r\n");
      out.write("    <li class=\"dropdown show\">\r\n");
      out.write("        <a class=\"nav-link dropdown-toggle\"  id=\"navbarDropdown\" role=\"button\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\r\n");
      out.write("          <i class=\"fa fa-book\" aria-hidden=\"true\"></i>Subject\r\n");
      out.write("        </a>\r\n");
      out.write("        <div class=\"dropdown-menu\" aria-labelledby=\"navbarDropdown\">\r\n");
      out.write("          <a class=\"dropdown-item\" href=\"");
      out.print(ORSView.SUBJECT_CTL);
      out.write("\"><i class=\"fa fa-plus-square\" aria-hidden=\"true\"></i>Add Subject</a>\r\n");
      out.write("          <a class=\"dropdown-item\" href=\"");
      out.print(ORSView.SUBJECT_LIST_CTL);
      out.write("\"><i class=\"fa fa-list\" aria-hidden=\"true\"></i>Subject List</a>\r\n");
      out.write("        </div>\r\n");
      out.write("      </li>\r\n");
      out.write("    \r\n");
      out.write("    <li class=\"dropdown show\">\r\n");
      out.write("        <a class=\"nav-link dropdown-toggle\"  id=\"navbarDropdown\" role=\"button\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\r\n");
      out.write("          <i class=\"fa fa-users\" aria-hidden=\"true\"></i>Faculty\r\n");
      out.write("        </a>\r\n");
      out.write("        <div class=\"dropdown-menu\" aria-labelledby=\"navbarDropdown\">\r\n");
      out.write("          <a class=\"dropdown-item\" href=\"");
      out.print(ORSView.FACULTY_CTL);
      out.write("\"><i class=\"fa fa-plus-square\" aria-hidden=\"true\"></i>Add Faculty</a>\r\n");
      out.write("          <a class=\"dropdown-item\" href=\"");
      out.print(ORSView.FACULTY_LIST_CTL);
      out.write("\"><i class=\"fa fa-list\" aria-hidden=\"true\"></i>Faculty List</a>\r\n");
      out.write("        </div>\r\n");
      out.write("      </li>\r\n");
      out.write("    <li class=\"dropdown show\">\r\n");
      out.write("        <a class=\"nav-link dropdown-toggle\"  id=\"navbarDropdown\" role=\"button\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\r\n");
      out.write("          <i class=\"fa fa-university\" aria-hidden=\"true\"></i>College\r\n");
      out.write("        </a>\r\n");
      out.write("        <div class=\"dropdown-menu\" aria-labelledby=\"navbarDropdown\">\r\n");
      out.write("          <a class=\"dropdown-item\" href=\"");
      out.print(ORSView.COLLEGE_CTL);
      out.write("\"><i class=\"fa fa-plus-square\" aria-hidden=\"true\"></i>Add College</a>\r\n");
      out.write("          <a class=\"dropdown-item\" href=\"");
      out.print(ORSView.COLLEGE_LIST_CTL);
      out.write("\"><i class=\"fa fa-list\" aria-hidden=\"true\"></i>College List</a>\r\n");
      out.write("        </div>\r\n");
      out.write("      </li>\r\n");
      out.write("    <li class=\"dropdown show\">\r\n");
      out.write("        <a class=\"nav-link dropdown-toggle\"  id=\"navbarDropdown\" role=\"button\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\r\n");
      out.write("          <i class=\"fa fa-list-alt\" aria-hidden=\"true\"></i>Course\r\n");
      out.write("        </a>\r\n");
      out.write("        \r\n");
      out.write("        \r\n");
      out.write("        \r\n");
      out.write("        <div class=\"dropdown-menu\" aria-labelledby=\"navbarDropdown\">\r\n");
      out.write("          <a class=\"dropdown-item\" href=\"");
      out.print(ORSView.COURSE_CTL);
      out.write("\"><i class=\"fa fa-plus-square\" aria-hidden=\"true\"></i>Add Course</a>\r\n");
      out.write("          <a class=\"dropdown-item\" href=\"");
      out.print(ORSView.COURSE_LIST_CTL);
      out.write("\"><i class=\"fa fa-list\" aria-hidden=\"true\"></i>Course List</a>\r\n");
      out.write("        </div>\r\n");
      out.write("      </li>\r\n");
      out.write("      <li class=\"dropdown show\">\r\n");
      out.write("        <a class=\"nav-link dropdown-toggle\"  id=\"navbarDropdown\" role=\"button\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\r\n");
      out.write("          <i class=\"fa fa-table\" aria-hidden=\"true\"></i>Timetable\r\n");
      out.write("        </a>\r\n");
      out.write("        <div class=\"dropdown-menu\" aria-labelledby=\"navbarDropdown\">\r\n");
      out.write("          <a class=\"dropdown-item\" href=\"");
      out.print(ORSView.TIMETABLE_CTL);
      out.write("\"><i class=\"fa fa-plus-square\" aria-hidden=\"true\"></i>Add Timetable</a>\r\n");
      out.write("          <a class=\"dropdown-item\" href=\"");
      out.print(ORSView.TIMETABLE_LIST_CTL);
      out.write("\"><i class=\"fa fa-list\" aria-hidden=\"true\"></i>Timetable List</a>\r\n");
      out.write("        </div>\r\n");
      out.write("      </li>\r\n");
} 
      out.write(" \r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t</ul>\r\n");
      out.write("\t\t\r\n");
      out.write("<div class=\" collapse navbar-nav \" id=\"navbarSupportedContent\" >   \r\n");
      out.write("\t<li class=\"dropdown show\">\r\n");
      out.write("        <a class=\"nav-link dropdown-toggle\"  id=\"navbarDropdown\" role=\"button\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\r\n");
      out.write("         <i class=\"fa fa-user-circle-o\" aria-hidden=\"true\"></i>");
      out.print(welcomeMsg);
      out.write("\r\n");
      out.write("        </a>\r\n");
      out.write("        \r\n");
      out.write("        ");

						if (userLoggedIn) {
					
      out.write("\r\n");
      out.write("        <div class=\"dropdown-menu dropdown-menu-left\" aria-labelledby=\"navbarDropdown\">\r\n");
      out.write("          <a class=\"dropdown-item dropdown-menu-left\" href=\"");
      out.print(ORSView.MY_PROFILE_CTL);
      out.write("\"><i class=\"fa fa-user-secret\" aria-hidden=\"true\"></i>My Profile</a>\r\n");
      out.write("          <a class=\"dropdown-item dropdown-menu-left\" href=\"");
      out.print(ORSView.CHANGE_PASSWORD_CTL);
      out.write("\"><i class=\"fa fa-list\" aria-hidden=\"true\"></i>Change Password</a>\r\n");
      out.write("<a\r\n");
      out.write("\t\t\t\t\t\t\tclass=\"dropdown-item dropdown-menu-left\" href=\"");
      out.print(ORSView.JAVA_DOC_VIEW);
      out.write("\" target=\"blank\"><i class=\"fa fa-sticky-note\" aria-hidden=\"true\"></i>Java Doc</a> <a class=\"dropdown-item\"\r\n");
      out.write("\t\t\t\t\t\t\thref=\"");
      out.print(ORSView.LOGIN_CTL);
      out.write("?operation=");
      out.print(LoginCtl.OP_LOG_OUT);
      out.write("\"><i class=\"fa fa-sign-out\" aria-hidden=\"true\"></i>Logout</a>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t");

						} else {
					
      out.write("\r\n");
      out.write("\t\t\t\t\t<div class=\"dropdown-menu\" style=\"margin-left: -55px\">\r\n");
      out.write("\t\t\t\t\t\t<a class=\"dropdown-item\" href=\"");
      out.print(ORSView.LOGIN_CTL);
      out.write("\"><i class=\"fa fa-sign-in\" aria-hidden=\"true\"></i> Login</a> <a class=\"dropdown-item\"\r\n");
      out.write("\t\t\t\t\t\t\thref=\"");
      out.print(ORSView.USER_REGISTRATION_CTL);
      out.write("\"><i\r\n");
      out.write("\t\t\t\t\t\t\tclass=\"fa fa-registered\"></i> User Registration </a> <a\r\n");
      out.write("\t\t\t\t\t\t\tclass=\"dropdown-item\" href=\"");
      out.print(ORSView.FORGET_PASSWORD_CTL);
      out.write("\"><i class=\"fa fa-question-circle\" aria-hidden=\"true\"></i> Forget Password</a>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t");
} 
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</li>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("  </div>\r\n");
      out.write("</nav>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
      out.write("\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<form action=\"");
      out.print(ORSView.LOGIN_CTL);
      out.write("\" method=\"post\">\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t <main> MDB Forms\r\n");
      out.write("\t\t <div class=\"container-fluid mt-4\"> \r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\tGrid row\r\n");
      out.write("\t\t\t<div class=\"row\">\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t  <div class=\"col-md-3\"></div>\r\n");
      out.write(" \r\n");
      out.write("\t\t\t\tGrid column\r\n");
      out.write("\t\t\t\t <div class=\"col-md-6\">   \r\n");
      out.write("\r\n");
      out.write("<br>\r\n");
      out.write("<br>\r\n");
      out.write("<br>\r\n");
      out.write("<br>\r\n");
      out.write("\t\t\t\t\t <div class=\"card\"> \r\n");
      out.write("\t\t\t\t\t\t <div class=\"card-body\"> \r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t<h3 class=\"text-center default-text py-3\">Login:</h3>\r\n");
      out.write("\t\t\t\t\t\t\t<!--Body-->\r\n");
      out.write("\t\t\t\t\t\t\t");
if(!ServletUtility.getSuccessMessage(request).equals("")){ 
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t <div class=\"alert alert-success alert-dismissible fade show\"> \r\n");
      out.write("\t\t\t\t\t\t\t\t<button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>\r\n");
      out.write("\t\t\t\t\t\t\t\t<strong>Success!</strong>");
      out.print(ServletUtility.getSuccessMessage(request) );
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t");
} 
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t");
if(!ServletUtility.getErrorMessage(request).equals("")){ 
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"alert alert-danger alert-dismissible fade show\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>\r\n");
      out.write("\t\t\t\t\t\t\t\t<strong>Error! </strong>");
      out.print(ServletUtility.getErrorMessage(request) );
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t");
} 
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t");
 String str= (String)request.getAttribute("message");
             if(str!=null){ 
             
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"alert alert-success alert-dismissible fade show\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>\r\n");
      out.write("\t\t\t\t\t\t\t\t<strong>Error! </strong><font color=\"red\">");
      out.print(request.getAttribute("message") );
      out.write("</font>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t");
} 
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t<h6 style=\"color: #c7361f\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<b>Login Id:-</b>\r\n");
      out.write("\t\t\t\t\t\t\t</h6>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("     \t\t\t\t\t <div class=\"input-group\">\r\n");
      out.write("                            <div class=\"input-group-prepend\">\r\n");
      out.write("                               <span class=\"input-group-text\"><i style=\"width: 17px\" class=\"fa fa-envelope\"></i></span>\r\n");
      out.write("                                 </div>\r\n");
      out.write("                        <input type=\"text\" placeholder=\"Enter Email\" class=\"form-control border\" name=\"login\" value=\"");
      out.print(DataUtility.getStringData(dto.getLogin()));
      out.write("\">\r\n");
      out.write("                                </div>\r\n");
      out.write("\t\t\t\t\t\t\t<font color=\"red\" class=\"ml-5\">");
      out.print(ServletUtility.getErrorMessage("login",request) );
      out.write(" </font>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t<h6 class=\"paddingclass\" style=\"color: #c7361f\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<b>Password:-</b>\r\n");
      out.write("\t\t\t\t\t\t\t</h6>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("                             <div class=\"input-group\">\r\n");
      out.write("                            <div class=\"input-group-prepend\">\r\n");
      out.write("                               <span class=\"input-group-text\"><i style=\"width: 17px\" class=\"fa fa-key\"></i></span>\r\n");
      out.write("                                 </div>\r\n");
      out.write("                        <input type=\"password\" placeholder=\"Enter Password\" class=\"form-control border\" name=\"password\" value=\"");
      out.print(DataUtility.getStringData(dto.getPassword()));
      out.write("\">\r\n");
      out.write("                                </div>\r\n");
      out.write("\t\t\t\t\t\t\t<font color=\"red\" class=\"ml-5\">");
      out.print(ServletUtility.getErrorMessage("password",request) );
      out.write(" </font>\r\n");
      out.write("\r\n");
      out.write("<br>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"text-center\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<input  type=\"submit\" name=\"operation\" class=\"btn btn-success btn-md\"\r\n");
      out.write(" \t\t\t\t\t\t\t\tstyle=\"font-size: 17px\"\tvalue=\"");
      out.print(LoginCtl.OP_SIGN_IN);
      out.write("\"> \r\n");
      out.write("\t\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<input class=\"btn btn-info btn-md\" style=\"font-size: 17px\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\ttype=\"submit\" name=\"operation\" value=\"");
      out.print(LoginCtl.OP_SIGN_UP);
      out.write("\">\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"text-center\" style=\"color: #20B2AA\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<b> <font size=\"4px\"> <a\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\thref=\"");
      out.print(ORSView.FORGET_PASSWORD_CTL);
      out.write("\">Forget Password?</a></font></b>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\r\n");
      out.write("             <input type=\"hidden\" name=\"uri\" value=\"");
      out.print(uri);
      out.write("\">\r\n");
      out.write("\t\t\t\t<div class=\"col-md-3\"></div>\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t</form>\r\n");
      out.write("<div>");
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("\r\n");
      out.write("<style>\r\n");
      out.write("html {\r\n");
      out.write("  height: 100%;\r\n");
      out.write("  box-sizing: border-box;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("body {\r\n");
      out.write("  position: relative;\r\n");
      out.write("  margin: 0;\r\n");
      out.write("  padding-bottom: 1rem;\r\n");
      out.write("  min-height: 100%;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("/**\r\n");
      out.write(" * Footer Styles\r\n");
      out.write(" */\r\n");
      out.write("\r\n");
      out.write(".footer {\r\n");
      out.write("  position: fixed;\r\n");
      out.write("  right: 0;\r\n");
      out.write("  z-index:4;\r\n");
      out.write("  bottom: 0;\r\n");
      out.write("  color: white;\r\n");
      out.write("  left: 0;\r\n");
      out.write("  display : block;\r\n");
      out.write("  padding: 0.5rem;\r\n");
      out.write("  box-shadow: 0px 0px 0px 0px #244a4a;\r\n");
      out.write(" \r\n");
      out.write("  background-color: black;\r\n");
      out.write("  text-align: center;\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<div class=\"footer\">\r\n");
      out.write("    <b>Copyrights &copy; Rays Technologies </b>\r\n");
      out.write(" \r\n");
      out.write(" </div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
