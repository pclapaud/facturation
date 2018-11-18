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
    <tr><th>Nom</th><th>Prénom</th><th>Ville</th><th>Pays</th><th>modifier</th><th>supprimer</th></tr>

        <tr>
            <td> <label for="nom"></label> <input maxlength="25" id="nom" name="nom" type = text value="${client.nom}"> </td>
            <td> <label for="prenom"></label> <input maxlength="20" id="prenom" name="prenom" type = text value="${client.pnom}"> </td>
            <td> <label for="loc"></label> <input maxlength="20" id="loc" name="loc" type = text value="${client.loc}"> </td>
            <td> <label for="pays"></label> <input maxlength="2" id="pays"name="pays" type = text value="${client.pays}"> </td>
            <td><label for="mod"></label> <input checked name="mod" id="mod" type="radio"></td>
            <td><label for="sup"></label> <input name="sup" id="sup" type="radio"></td>
            <td><INPUT value="modifier"type="submit"></td>
        </tr>




</table>
</form>

</body>
</html>

