<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Timer</title>
    </head>
    <body>
        <h1> Gestion du timer </h1>
        <%@ include file="../jspf/startTimer.jspf"%>
        <c:if test="${requestScope.timerLance==1}">
            Le timer pour la tache n°${requestScope.numTache} a été démarré!
        </c:if>
    </body>
</html>
