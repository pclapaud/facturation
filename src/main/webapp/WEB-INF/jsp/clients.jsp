<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Les films</title>
</head>
<body>
<h1>Les films</h1>
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

</body>
</html>

