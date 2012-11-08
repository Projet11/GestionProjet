<%-- 
    Document   : Erreur
    Created on : 26-oct.-2012, 11:21:39
    Author     : g35364
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${requestScope.erreurTitre}</title>
    </head>
    <body>
        <h1>${requestScope.erreurTitre}</h1>
        <p>${requestScope.erreurContenu}</p>
    </body>
</html>
