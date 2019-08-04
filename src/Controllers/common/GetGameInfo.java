package Controllers.common;

import com.google.gson.*;

public class GetGameInfo {
    // fonction qui regarde si le jeux n'est pas une beta et contient bien data
    public static boolean isNotNull(String toJson, String appid){
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonTree = jsonParser.parse(toJson);
        JsonObject jsonObject = jsonTree.getAsJsonObject();
        JsonElement reponse = jsonObject.get(appid);
        JsonObject gameData = reponse.getAsJsonObject();
        if (gameData.size() >= 2 ) {
            return true;
        }else{
            return false;
        }
    }
    // fonction qui récupere data de l'api steam
    public static JsonElement getgameData(String toJson, String appid){
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonTree = jsonParser.parse(toJson);
        JsonObject jsonObject = jsonTree.getAsJsonObject();
        JsonElement reponse = jsonObject.get(appid);
        JsonObject gameData = reponse.getAsJsonObject();
        System.out.println("Game data = " + gameData.size());
        JsonElement data = gameData.get("data");
        JsonObject gameInfo = data.getAsJsonObject();
        return gameInfo;


    }
    //fonction pour avoir la short description du jeu
    public static String gameInfoShort(String toJson, String appid){
        String short_description = getgameData(toJson,appid).getAsJsonObject().get("short_description").getAsString();
        return  short_description;
    }
//    fonction pour avoir les developers d'un jeu

    public static String gameInfoDev(String toJson, String appid){
        if (getgameData(toJson, appid).getAsJsonObject().has("developers")) {
            JsonElement testing = getgameData(toJson,appid).getAsJsonObject().get("developers");
            return testing.toString();

        } else {
            return "";
        }
//        String developers = getgameData(toJson,appid).getAsJsonObject().get("developers").getAsString();
    }
}
