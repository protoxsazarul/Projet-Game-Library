<%@ page import="model.User" %><%--
  Created by IntelliJ IDEA.
  User: Quentin
  Date: 31/07/2019
  Time: 11:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% User user = (User)session.getAttribute("user");%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>User's games</title>
    <%--  Jquery & popper--%>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
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
        <h2 class="col-4 offset-lg-3 offset-md-5">GAME LIBRARY MANAGER HELPER </h2>
        <div id="UserBtn"  class="col-2 offset-md-1">
            <a href="./logout">Déconnexion</a>
            <a href="./user" >Votre profil</a>
        </div>
    </header>
    <section id="mainSection">
        <div id="<%=user.getId()%>" class="container-fluid mainBody h-100 user_info">
           <div>
               <a href="./userAddGame">Add a game</a>
           </div>
            <div class="contentDiv bg-grey">
                <table id="gameTab">
                    <thead> Liste de vos jeux </thead>
                    <tr>
                        <th>Titre du jeu</th>
                        <th>Temps de jeu</th>
                        <th>Note</th>
                    </tr>
                    <tbody>
                    <tr v-for="game in gamesList">
                        <td>{{ game["gameByIdGame"]["title"] }}</td>
                        <td>{{ game["playtime"] }}</td>
                        <td>{{ game["note"] }}</td>
                    </tr>
                    </tbody>
                </table>

            </div>
        </div>
    </section>
</div>
<script src="scripts/maVue.js"></script>

</body>
</html>
