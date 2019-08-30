package Controllers.common;
import com.google.gson.*;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import model.Game;
import model.User;
import model.Usersgames;
import org.hibernate.Session;
import persistance.HibernateUtils;
import org.hibernate.query.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GetUserGamesUtils {
    private final String USER_AGENT = "Mozilla/5.0";
    private JsonParser jsonParser = new JsonParser();
    private Session session = HibernateUtils.getSession();

public static void run(String steamid64) throws Exception {

        GetUserGamesUtils http = new GetUserGamesUtils();

        System.out.println("Testing 1 - Send Http GET request");
        http.getOwnGame(steamid64);
    }
    private int howIs(String steamid64){
        List<User> user= new ArrayList<User>();
        Query query =session.createQuery("from User where steamid ="+ steamid64);
        user = query.list();
        return user.get(0).getId();

    }
//    fonction pour recupéré un jeu via la bdd grace son appid
    private List getGameAppid(int appid){
        List<Game> resultsAppid = new ArrayList<Game>();
        Query query = session.createQuery("from Game where appsid = "+appid);
        resultsAppid = query.list();

        return resultsAppid ;
    }
//    fonction qui regard si l'user a deja ce jeux
    private boolean userHaveIt(int userId, int gameId){
        List<Game> gameIsIn = new ArrayList<Game>();
        //requete la bdd pour savoir si l'utilisateur posséde deja le jeux
        Query query = session.createQuery("from Usersgames where idUser ="+userId+"and idGame ="+gameId);
        gameIsIn = query.list();
        // si la reponse est vide alors il ne posséde pas le jeu donc return true sinon return false,
        if(gameIsIn.isEmpty()){
            return true;
        }else {
            return false;
        }
    }
    // check if a game is in the DB
    private boolean gameisin(Integer appid){
        List<Game> results = new ArrayList<Game>();
        results = getGameAppid(appid);
        if(results.isEmpty()) {
            return false;
        }else{
            return true;
        }
    }
//    fonction pour attribuer un jeu a un joueur
    private void gameLink(int appid, int userid){
        List<Game> results = new ArrayList<Game>();
        results = getGameAppid(appid);
        int id =results.get(0).getId();
        Usersgames newUsergames =new Usersgames();
        if (userHaveIt(userid,id)) {
            System.out.println("//////////////////////////////////////");
            System.out.println("Ajout du jeu au joueur");
            System.out.println("//////////////////////////////////////");

            newUsergames.setIdGame(id);
            newUsergames.setIdUser(userid);
            newUsergames.save();
        }


    }
    private void getOwnGame(String steamid64) throws Exception {
//        String steamid64 = "76561198052088897" ;
        int userId = howIs(steamid64);
        String url = "http://api.steampowered.com/IPlayerService/GetOwnedGames/v0001/?key=B65DA6D5CC774D2D9292A79E5B0A8D2A&steamid="+steamid64+"&include_appinfo=1&include_played_free_games=1&&format=json ";

        String result = webapi.sendGet(steamid64,url);
        // get game info
        JsonElement jsonTree = jsonParser.parse(result);
        if(jsonTree.isJsonObject()) {
            JsonObject jsonObject = jsonTree.getAsJsonObject();
            JsonElement reponse = jsonObject.get("response");
            JsonObject gameObject = reponse.getAsJsonObject();
            JsonElement gameList = gameObject.get("games");
            JsonArray games = gameList.getAsJsonArray();
            int c = 0;


            for (int i = 0; i <games.size() ; i++) {
                if( c == 75) {
                    System.out.println("Dans le Sleep");
                    TimeUnit.SECONDS.sleep(60);
                    c=0;
                }
                JsonElement game = games.get(i);
                JsonObject temp = game.getAsJsonObject();
                String iconCheck = temp.get("img_icon_url").getAsString();
                Integer appid = temp.get("appid").getAsInt();
                String strAppid = appid.toString();
                Integer playtime = temp.get("playtime_forever").getAsInt();
                String name = temp.get("name").getAsString();
                System.out.println("avant le if de game is in ");

                if(!gameisin(appid) && !iconCheck.isEmpty())
                {
                    url = "https://store.steampowered.com/api/appdetails?appids="+ appid;
                    String patate = webapi.sendGet(appid.toString(), url);
                    if(GetGameInfo.isNotNull(patate,strAppid)) {
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        System.out.println("j'ajoute ce jeux dans la BDD");
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

                        String shortDes = GetGameInfo.gameInfoShort(patate, strAppid);
                        String developers = GetGameInfo.gameInfoDev(patate, strAppid);
                        Game newGame = new Game();
                        newGame.setTitle(name);
                        newGame.setOnSteam(Byte.parseByte("1"));
                        newGame.setAppsid(appid);
                        newGame.setDeveloppeur(developers);
                        newGame.setDescription(shortDes);
                        newGame.save();
                        gameLink(appid,userId);

                        c++;

                    }
                } else if (gameisin(appid)) {

                    gameLink(appid,userId);
                }
                System.out.println("après le if ");
                System.out.println("Titre du jeu :"+name);
                System.out.println("Appid du jeu :"+appid);
                System.out.println("Temp de jeu :"+playtime);
                System.out.println("----------------------------------------------------------------------------");

            }


        }
    }
}



