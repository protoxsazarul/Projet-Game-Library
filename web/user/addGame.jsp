<%@ page import="model.User" %><%--
  Created by IntelliJ IDEA.
  User: Quentin
  Date: 31/07/2019
  Time: 16:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%User userA = (User)session.getAttribute("user");
System.out.println(userA.getSteamid());
%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>
        Add a Game
    </title>
    <%--  Jquery & popper--%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <%--  Boostrap --%>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <%--  vuejs--%>
    <script src="https://cdn.jsdelivr.net/npm/vue@2.6.10/dist/vue.js"></script>
    <link rel="stylesheet" type="text/css" href="./css/main.css" />
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>


</head>
<body>
    <div id="wrapper">
        <header class="head d-flex p-4">
            <h2 class="col-4 offset-lg-3 offset-md-5">GAME LIBRARY MANAGER HELPER </h2>/button>
            <div id="UserBtn"  class="col-2 offset-md-1">
                <a href="./logout"class="btn btn-dark">Déconnexion</a>
                <a href="./user" class="btn btn-dark">Votre profil</a>
            </div>
        </header>
        <section id="mainSection">
                <div class="container-fluid mainBody h-100">
                    <div class="contentDiv bg-grey">
                            <% if(userA.getSteamid() ==""){ %>
                        <form action="" @submit="">
                            <label for="steamidAG">Entrée votre Steam id pour ajouté les jeux de votre bibilothèque :</label>
                            <input type="number" id="steamidAG">
                        </form>
                            <%}else { %>
                        <p>Vous avez déja ajouter votre steamID clicker sur le bouton ci-desous pour </p>
                        <button id="addGBtn" value=<%= userA.getSteamid()%> @click="addSteamGame()" class="btn btn-dark">Ajouter ma bibliothèque</button>
                        <p value="<%= userA.getSteamid()%>"> Votre steam Id : <%=userA.getSteamid()%></p>
                        <p>Ce n'est pas le bon ? changais le en clickant <button id="SteamidChanger"  @click="display('formSteamidChange')" class="btn btn-dark">ICI</button> </p>
                        <div id="formSteamidChange">
                            <form action="">
                                <input type="number">
                            </form>
                        </div>
                        <%};%>
                    </div>
                </div>
            </section>
    </div>
    <script src="scripts/maVue.js"></script>
</body>
</html>
