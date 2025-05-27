<%-- 
    Document   : index
    Created on : Mar 5, 2025, 12:59:42 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Index</h1>
        <% response.sendRedirect(request.getContextPath() + "/MyExecution.jsp"); %>
    </body>
</html>
