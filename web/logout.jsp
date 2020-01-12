<%-- 
    Document   : logout
    Created on : 2020.01.12., 17:02:27
    Author     : bendeati
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="login" class="bab.Login" scope="session" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logout</title>
    </head>
    <body>
        <%
          login.logOut();
          response.sendRedirect("index.jsp");  
        %>
    </body>
</html>
