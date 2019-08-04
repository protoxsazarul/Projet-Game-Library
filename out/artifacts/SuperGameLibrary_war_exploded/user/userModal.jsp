<div class="container">
    <div class="modal fade" id="register_modal" role="dialog" style="width: 100%;">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Register</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <form action="" method="post" @submit="register()" autocomplete="on">
                    <div class="modal-body">

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