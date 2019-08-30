<%@ page import="model.User" %><%--
  Created by IntelliJ IDEA.
  User: Quentin
  Date: 25/07/2019
  Time: 11:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  User user = new User();
  boolean isLogged = false;
  System.out.println("dans le jsp");
  System.out.println("session :"+session.getAttribute("isLogged"));
  if((Boolean) session.getAttribute("isLogged") != null) {
    user = (User)session.getAttribute("user");
    isLogged = (Boolean) session.getAttribute("isLogged");
  }
%>
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Accueil</title>
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
  <jsp:include page="Modal.jsp" />
  <header >
    <div class="head d-md-flex p-4">
      <h2 class="col-md-4 offset-md-5">GAME LIBRARY MANAGER HELPER </h2>
      <div class="col-md-3">
      <% if(isLogged == true){%>
        <a href="./logout" class="col-2 offset-md-1" class="btn">Déconnexion</a>
      <%} else {%>
        <button id="connect" data-toggle="modal" data-target="#login_modal" class="btn btn-dark">Connexion</button>
        <button id="signin" data-toggle="modal" data-target="#register_modal" class="btn btn-dark">S'inscrire</button>
      <%}%>
      </div>
    </div>
  </header>
  <section id="mainSection">
    <div class="container-fluid mainBody h-100">
      <div class=" bg-grey contentDiv">
        <h3>A quoi sert ce site ?</h3>
        <p>Ce site sert a vous aidez a géré votre bibliothèque, en vous permetant d'enregirsté vos jeux avec votre temp de jeux et une note/ évaluation.</p>
        </br>
        <p>Vous pouvez importer votre bilbliothèque steam ou ajouté les jeux que vous voulez</p>

        <h3>Pourquoi j'ai fais ce site ?</h3>
        <p> j'ai fais ce site comme projet chef d'oeuvre et aussi car j'ai une bonne bibliothèque de jeux ,mais je n'avais rien pour mieux la gère que steam donc j'ai dévélopper cet outils  </p>
        <%
          if(isLogged == true){
        %>
        <p>ok tu es log : <%=user.getUserName()%></p> <button>testing</button>
        <a href="./user"> to user page</a>
        <%
          } else{
        %>
        <button data-toggle="modal" data-target="#register_modal">S'inscrire</button>
      <%}%>
      </div>
    </div>
  </section>

  <footer class="footer">
    © Copyright Cnudde Quentin 2019
  </footer>
  </div>
<script src="scripts/maVue.js"></script>
</body>

</html>
