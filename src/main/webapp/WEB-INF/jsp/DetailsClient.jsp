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

<table border="1" >
    <tr><th>Nom</th><th>Prénom</th><th>Ville</th><th>Pays</th></tr>

        <tr>
            <td> ${client.nom} </td>
            <td> ${client.pnom} </td>
            <td> ${client.loc} </td>
            <td> ${client.pays} </td>
        </tr>

</table>

</body>
</html>

