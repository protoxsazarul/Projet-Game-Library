package Controllers.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUse {
    public final String APIKey = "B65DA6D5CC774D2D9292A79E5B0A8D2A" ;

    public String getAPIKey() {
        return APIKey;
    }
    public static Boolean emailCheck(String email){
        // string regex pour le filtre
        String regex = "^(.+)@(.+)$";
        // compilation du filtre
        Pattern pattern = Pattern.compile(regex);
        // preparation
        Matcher matcher = pattern.matcher(email);
        /* regard si l'email corespon au filtre si oui return
        donc le email est valide sinon return false */
        if(matcher.matches()){
        return true ;
        }
        return false;
    }
}
