<!--  register-->
<div class="container">
    <div class="modal fade" id="register_modal" role="dialog" style="width: 100%;">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Register</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <form action="" method="post" @submit="register()" autocomplete="on">
                    <div class="modal-body d-flex">
                        <div id="RegLabel"class="col-6 m-1">
                            <label for="usernameR">Saisir votre pseudo :</label>
                            <label for="passwordR">Saisir votre mot de passe :</label>
                            <label for="passwordC">Confirmer votre mdp :</label>
                            <label for="emailR">Sasir votre email :</label>
                            <label for="steamIdR">Saisir votre Steam ID :</label>
                            </div>
                        <div id="RegInp" class="col-4 m-1">
                            <input type="text" id="usernameR" required>
                            <input type="password" id="passwordR" required new-password>
                            <input type="password" id="passwordC" required>
                            <input type="email" id="emailR" required>
                            <input type="number" id="steamIdR">
                        </div>
                        </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Annuler</button>
                        <input id="logoutButtonConfirm" type="submit"  class="btn btn-primary">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- login modal -->
<div class="container">
    <div class="modal fade" id="login_modal" role="dialog" style="width: 100%;">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Login</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <form action=""  @submit="Login($event)" method="post" autocomplete="on">

                <div class="modal-body">
                        <label for="emailL">email :</label>
                        <input type="email" id="emailL" required>
                        </br>
                        <label for="pwdL"> Mot de passe :</label>
                        <input type="password" id="pwdL" required current-password>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-dismiss="modal">Annuler</button>
                    <input id="loginButton" type="submit" class="btn btn-primary">
                </div>
                </form>

            </div>
        </div>
    </div>
</div>
