/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2021-03-08 08:11:10 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import in.co.rays.ORSproject3.controller.ORSView;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

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
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<title>Index Page</title>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">\r\n");
      out.write("\r\n");
      out.write(" <!-- <link rel=\"stylesheet\" href=\"web-fonts-with-css/css/fontawesome.css\"> -->\r\n");
      out.write("  <!-- <link rel=\"stylesheet\" href=\"web-fonts-with-css/css/fa-solid.css\"> -->\r\n");
      out.write("  <link rel=\"stylesheet\" href=\"");
      out.print(ORSView.APP_CONTEXT);
      out.write("/css/bootstrap.css\">\r\n");
      out.write("  <link rel=\"stylesheet\" href=\"");
      out.print(ORSView.APP_CONTEXT);
      out.write("/css/bootstrap.min.css\">\r\n");
      out.write(" \r\n");
      out.write(" \r\n");
      out.write("  <!-- <script src=\"js/jquery-3.2.1.min.js\"> </script>--><!--before use-->\r\n");
      out.write("  <!-- <script src=\"js/bootstrap.js\"></script> -->\r\n");
      out.write(" \r\n");
      out.write("   <link href=\"https://fonts.googleapis.com/css?family=Pacifico\" rel=\"stylesheet\">\r\n");
      out.write(" \r\n");
      out.write(" \r\n");
      out.write("  <link href=\"https://fonts.googleapis.com/css?family=Autour+One\" rel=\"stylesheet\">\r\n");
      out.write(" \r\n");
      out.write(" \r\n");
      out.write("  <style>\r\n");
      out.write("  body{\r\n");
      out.write("marign:0;\r\n");
      out.write("padding:0;\r\n");
      out.write("font-family: 'Varela Round', sans-serif;\r\n");
      out.write("font-size: 16px;\r\n");
      out.write("}\r\n");
      out.write(".custom-nav{\r\n");
      out.write("border:0px;\r\n");
      out.write("-webkit-border-radius: 0px;\r\n");
      out.write("-moz-border-radius: 0px;\r\n");
      out.write("-ms-border-radius: 0px;\r\n");
      out.write("-o-border-radius: 0px;\r\n");
      out.write("border-radius: 0px;\r\n");
      out.write("background-color: #fff!important;\r\n");
      out.write("-webkit-box-shadow: 0 0 10px rgba(0,0,0,0.5);\r\n");
      out.write("box-shadow: 0 0 10px rgba(0,0,0,0.5);\r\n");
      out.write("}\r\n");
      out.write(" \r\n");
      out.write("header{\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("padding:125px 50px;\r\n");
      out.write(" \r\n");
      out.write("\r\n");
      out.write("background-blend-mode: overlay;\r\n");
      out.write("}\r\n");
      out.write(".banner-title{\r\n");
      out.write("font-family: 'Autour One', cursive;\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("font-weight: 1000;\r\n");
      out.write("font-size: 50px;\r\n");
      out.write("margin-top:50px;\r\n");
      out.write("}\r\n");
      out.write(" \r\n");
      out.write("  .bodys1 {\r\n");
      out.write("   background: url(img/1233.jpg) no-repeat center center fixed;\r\n");
      out.write("   \r\n");
      out.write("   background-color: rgba(0,0,0,0.2);\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("background-blend-mode: overlay;\r\n");
      out.write("color:#fff;\r\n");
      out.write("    min-height:100%;\r\n");
      out.write("    background-attachment: fixed;\r\n");
      out.write("    background-repeat: no-repeat;\r\n");
      out.write("    background-size: cover;\r\n");
      out.write("    -moz-background-size: cover;\r\n");
      out.write("}  \r\n");
      out.write(" \r\n");
      out.write("  </style>\r\n");
      out.write("<!--   <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n");
      out.write(" -->  </head>\r\n");
      out.write("<body class=\"bodys1\">\r\n");
      out.write("<header>\r\n");
      out.write("<div class=\"container\">\r\n");
      out.write("<div class=\"row justify-content-center\">\r\n");
      out.write("<div class=\"col-md-12\"><h1 class=\"banner-title text-center\">\r\n");
      out.write("<font color=\"\">Welcome</font><font color=\"\"> to the</font><font color=\"\"> ORS</font></h1></div>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<div class=\"row justify-content-center mr-top\" style=\"margin-top: 10px;\">\r\n");
      out.write("\r\n");
      out.write("<div class=\"text-center\">\r\n");
      out.write("              <img class=\"img-fluid\" src=\"img/sun.jpg\" class=\"\" alt=\"\" title=\"Rays Technology\"/></div>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<br>\r\n");
      out.write("<div class=\"row justify-content-center\">\r\n");
      out.write("<div class=\"text-center\">\r\n");
      out.write("<a href=\"");
      out.print(ORSView.WELCOME_CTL);
      out.write("\" class=\"btn btn-lg btn-primary text-center\" style=\"border-radius:15px\" title=\"Click Here\" >\r\n");
      out.write("\r\n");
      out.write("Online Result System\r\n");
      out.write("\r\n");
      out.write("</a>\r\n");
      out.write("</div><!-- col -->\r\n");
      out.write("</div>\r\n");
      out.write("</div></header>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
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
