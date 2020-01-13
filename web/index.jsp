<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" import="java.sql.*"%>

<%@ page import = "bab.Kapcsolat"%>
<%@ page import = "bab.Register" %>
<%@ page import = "bab.Login" %>
<%@ page import = "bab.Notes" %>

<jsp:useBean id="dbKapcs" class="bab.Kapcsolat" scope="session"/>
<jsp:useBean id="userReg" class="bab.Register" scope="session"/>
<jsp:useBean id="login" class="bab.Login" scope="session"/>
<jsp:useBean id="notes" class="bab.Notes" scope="session"/>

<jsp:setProperty name="userReg" property="*"/>
<jsp:setProperty name="login" property="*"/>
<jsp:setProperty name="notes" property="*"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="bootstrap.min.css">
    </head>
    <body>
        <div class="container">
            <div>
                <%@include file="WEB-INF/jspf/menu.jspf" %>
            </div>
            <div>
                <% out.print(dbKapcs.uzenet()); %> 
                <h1>Üdvözlöm a NotesApp webalkalmazásban!</h1>
            </div>
            <div>
                <%
                    
                    if(login.isLoggedIn())
                    {
                        out.println("<h3>Helló " + login.getUsername() + "!</h3>");
                        out.print("Most már használhatod a rendszert!");
                        if(request.getParameter("page") != null)
                        {
                            String showPage = request.getParameter("page");
                            switch(showPage)
                            {

                            case "allNotes": %>
                                <%@include file="WEB-INF/jspf/allNotes.jspf" %>
                                <% break;
                            case "newNote": %>
                                <%@include file="WEB-INF/jspf/newNote.jspf" %>
                                <% break;
                            case "modNote": %>
                                <%@include file="WEB-INF/jspf/modNote.jspf" %>
                                <% break;
                            case "delNote": %>
                                <%@include file="WEB-INF/jspf/delNote.jspf" %>
                                <% break;
                            }
                        }
                    }
                    else if(request.getParameter("page") != null)
                    {
                        String showPage = request.getParameter("page");
                        switch(showPage)
                        {
                            case "login": %>
                                <%@include file="WEB-INF/jspf/login.jspf" %>
                                <% break;
                            case "register": %>
                                <%@include file="WEB-INF/jspf/register.jspf" %>
                                <% break;
                            
                        }
                    }
                    
                %>
            </div>
        </div>            
    </body>
</html>
