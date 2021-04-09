<html>
<head>

<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" />
<title>Welcome Page</title>

</head>

<body>
<%@ include file="Header.jsp"%>
    <form action="<%=ORSView.WELCOME_CTL%>">
                 <br><br><br><br><br><br><br><br><br><br><br>
                    <h1 align="Center"><b>
                        <font size="10px" color="white">Welcome to ORS </font></b>
                    </h1>
        
                    <%
                    UserDTO beanUserBean = (UserDTO) session.getAttribute("user");
                        if (beanUserBean != null) {
                            if (beanUserBean.getRoleId() == RoleDTO.STUDENT) {
                    %>
        
                    <h1 align="Center"><b>
                        <a href="<%=ORSView.GET_MARKSHEET_CTL%>">Click here to see your
                            Marksheet </a></b>
                    </h1>
                     
                     <%
                            }
                        }
                     %>
                
                </form>
       <div><%@include file="Footer.jsp" %></div>
</body>        
       

</html>