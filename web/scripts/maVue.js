var app = new Vue({
    el: '#wrapper',
    data: {
        regstierErr :"",
        loginErr : "",
        loggedUser:{},
        gamesList:{},
        gameInfo:{}
    },
    methods : {


        addGameToAUser:function(){
            var steamID =$("#steamidAG").val()

        },
        // fonction qui gere l'envoie des login saisi ver le back
        Login: function (event) {

            var emailL = $("#emailL").val();
            var pwdL = $("#pwdL").val();
            console.log("testing crypting ");
            console.log("patate=" + emailL);
            axios.post("/LogServlet", {
                email: emailL,
                pwd: pwdL
            })
                .then(function (response) {
                    if (response.data !=null) {
                        console.log("tu est redirigé");
                        app.loggedUser = response.data;
                        window.location.href = './user';
                    } else if (response.status == 204) {
                        console.log("err bad login")
                    }
                })
                .catch(function (error) {
                    console.log("catch err");
                    console.log(error);
                });

        },
        getUserGame: function () {
            console.log($(".user_info").prop("id"));
            var id = $(".user_info").prop("id")
            if($(".user_info").prop("id") != null){

               axios.post("/usersgamelist",
                   {
                       userid: id
                   })
                    .then(function (response) {
                        // console.log("je suis ici"+response.data);
                        app.gamesList = response.data;
                        console.log(app.gamesList[0]["gameByIdGame"])
                    })
                    .catch(function (error) {
                        console.log("catch err");
                        console.log(error);
                    });
                console.log("i'm loaded")

            }
        },
        addSteamGame: function(userid,steamid){
            axios.post("./getUsersGame",
                {
                user: userid,
                    steamid: steamid
                }).then(function (response) {
                console.log(response);
            }).catch(function (error) {
                console.log("catch err");
                console.log(error);
            });
        },
        regForm: function(userN,pwd,pwdC,email){
            if (userN.value == "")
            {
                window.alert("Please enter your username.");
                return false;
            }
            if (email.value == "")
            {
                window.alert("Please enter a valid e-mail address.");
                return false;
            }
            if ( pwd == "" || pwdC =="")
            {
                window.alert("Entrée votre mot de passe");
                return false;
            }
            if (pwd != pwdC){
                alert("les 2 mots de passe ne corresponde pas ");
                return false
            }
            return true
        },
        register: function(){
            var userR =$("#usernameR").val();
            var pwdR =$("#passwordR").val();
            var pwdC =$("#passwordC").val();
            var emailR =$("#emailR").val();
            var steamidR =$("#steamIdR").val();

            if (this.regForm(userR,pwdR,pwdC,emailR)){
                axios.post("./register",{
                    user: userR ,
                    pwd: pwdR,
                    email: emailR,
                    steamid: steamidR,

                }).then(function (response)
                    {
                    console.log(response);
                        JSON.stringify(response);
                        if (response.status == 200) {
                            window.location.href = './user';
                        }
                    })
                    .catch(function (error)
                    {
                        console.log("catch err");
                        console.log(error);
                    });
            }
        }

    },
        mounted(){
            this.getUserGame();

    }
})