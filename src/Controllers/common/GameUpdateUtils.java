package Controllers.common;

import model.Game;
import persistance.HibernateUtils;

import java.util.ArrayList;

public class GameUpdateUtils {
    public static void main(String tab, ArrayList<String> modifList) throws Exception{
        int idGame=4;
        Game modGame = (Game) HibernateUtils.getSession().load(Game.class, idGame);
        System.out.println(modGame);
        if (tab.equals("game")){
            alterGame(modifList);
        }


    }
    private static void alterGame(ArrayList<String> listMod){
        Game modGame = (Game) HibernateUtils.getSession().load(Game.class, 3);
        System.out.println(modGame);
    }
}

