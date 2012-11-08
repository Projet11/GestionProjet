<%-- 
    Document   : Tache
    Created on : 23-oct.-2012, 15:13:37
    Author     : g35364
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gestion d'une tâche</title>
    </head>
    <body>
        
        <form method="GET" action="/GestionProjet/FrontController"/>
            <input type="hidden" name="action" value="choixMembresTache"/>
            <input type="hidden" name="numTache" value="1"/>
            <input type="submit" value="Attribuer des membres"/>
        </form>
    </body>
</html>
