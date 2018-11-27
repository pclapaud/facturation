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
<#list clients as client>



        <td><a href="DetailsClient?nom=${client.nom}"> ${client.nom} </a></td>
        <td> ${client.pnom} </td>


        </tr>
</#list>
</table>
<h1>Ajouter client</h1>
<form method="post" action="/clients.html">
    <table>
        <tr><th>Nom</th><th>Prénom</th><th>Ville</th><th>Pays</th></tr>

        <tr>
            <td> <label for="nom"></label> <input maxlength="25" id="nom" name="nom" type = text > </td>
            <td> <label for="prenom"></label> <input maxlength="20" id="prenom" name="prenom" type = text > </td>
            <td> <label for="loc"></label> <input maxlength="20" id="loc" name="loc" type = text > </td>
            <td> <label for="pays"></label> <input maxlength="2" id="pays"name="pays" type = text > </td>
            <td> <input value="créer" type="submit"></td>
        </tr>




    </table>
</form>



</body>
</html>

