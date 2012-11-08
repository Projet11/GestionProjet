<%-- any content can be specified here e.g.: --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page pageEncoding="UTF-8" %>
<form method="GET" action="/GestionProjet/FrontController">
    <select multiple="multiple" name="membres" size="10">
        <c:forEach var="membre" items="${requestScope.tacheAttribuerMembres.projet.membres}">
            <option value="${membre.id}">${membre.nom}</option>
        </c:forEach>
    </select>
    <input type="hidden" name="cible" value="membresAttribuesTache"/>
    <input type="hidden" name="tache" value="${requestScope.tacheAttribuerMembres.id}"/>
    <input type="submit" value="Attribuer"/>
</form>