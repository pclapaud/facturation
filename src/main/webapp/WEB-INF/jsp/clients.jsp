<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Les films</title>
</head>
<body>
<h1>Les Clients</h1>
<p />
<hr width="100%" />

<table border="1" >
    <tr><th>Nom</th><th>Prénom</th></tr>
    <c:forEach var="client" items="${clients}">


                <td><a href="DetailsClient?nom=${client.nom}"> ${client.nom} </a></td>
                <td> ${client.pnom} </td>


        </tr>
    </c:forEach>
</table>
<h1>Ajouter client</h1>
<form method="post" action="/clients.html">
<table>
    <tr><th>Nom</th><th>Prénom</th><th>Ville</th><th>Pays</th></tr>

        <tr>
            <td> <label for="nom"></label> <input id="nom" name="nom" type = text value="${client.nom}"> </td>
            <td> <label for="prenom"></label> <input id="prenom" name="prenom" type = text value="${client.pnom}"> </td>
            <td> <label for="loc"></label> <input id="loc" name="loc" type = text value="${client.loc}"> </td>
            <td> <label for="pays"></label> <input id="pays"name="pays" type = text value="${client.pays}"> </td>
            <td> <input type="submit"></td>
        </tr>




</table>
</form>



</body>
</html>

