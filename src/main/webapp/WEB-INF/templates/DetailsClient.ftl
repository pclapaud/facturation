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
            <td> <input checked name="button" value="mod" type="radio"></td>
            <td> <input name="button" value="sup" type="radio"></td>
            <td><input type="submit"></td>
        </tr>




    </table>
</form>

</body>
</html>

