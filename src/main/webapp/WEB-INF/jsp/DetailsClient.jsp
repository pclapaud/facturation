<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Les films</title>
</head>
<body>
<h1>${client.nom}</h1>
<p />
<hr width="100%" />

<form method="post" action="/DetailsClient">
<table border="1" >
    <tr><th>Nom</th><th>Prénom</th><th>Ville</th><th>Pays</th></tr>

        <tr>
            <td> <label for="nom"></label> <input id="nom" name="nom" type = text value="${client.nom}"> </td>
            <td> <label for="prenom"></label> <input id="prenom" name="prenom" type = text value="${client.pnom}"> </td>
            <td> <label for="loc"></label> <input id="loc" name="loc" type = text value="${client.loc}"> </td>
            <td> <label for="pays"></label> <input id="pays"name="pays" type = text value="${client.pays}"> </td>
            <td><INPUT value="modifier"type="submit"></td>
        </tr>




</table>
</form>

</body>
</html>

