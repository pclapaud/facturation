# facturation

Question 1:
-Externaliser Driver
-externaliser password
-externaliser nom

Question 2:
-pour chaque client afficher une fiche details et ne laisser que le nom et prenoim dans la liste

Question 3:
-modifier un utilisateur
-ajouter un utilisateur
-supprimer un utilisateur

Question 4:
-initialiser la connexion au niveau de l'application:
vous devez en plus modifier votre context.xml dans votre dossier tomcat/conf et ajouter dans les balises context la ressource suivante:


    <Resource name="jdbc/connection" auth="Container" type="javax.sql.DataSource"
              maxTotal="25" maxIdle="5" maxWaitMillis="5000"
              username="postgres" password="secret" driverClassName="org.postgresql.Driver"
              url="jdbc:postgresql://192.168.99.100/exemple"/>

