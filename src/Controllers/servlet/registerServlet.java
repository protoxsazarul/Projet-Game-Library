package Controllers.servlet;

import Controllers.common.CommonUse;
import com.google.gson.*;
import model.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import persistance.HibernateUtils;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/register")
public class registerServlet extends HttpServlet {
    private String respPost;
    private Session sessionhib = HibernateUtils.getSession();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)  throws ServletException,IOException{

    }
    public void doPost(HttpServletRequest req, HttpServletResponse resp)  throws ServletException,IOException{
        List<User> results = new ArrayList<User>();

        if ("POST".equalsIgnoreCase(req.getMethod()))
        {
            // recup du post
            respPost = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            System.out.println(respPost);
            // parsing du post
            JsonParser jsonParser = new JsonParser();
            JsonElement jsonTree = jsonParser.parse(respPost);
            JsonObject jsonObject = jsonTree.getAsJsonObject();
//            transfo des JsonElement en string
            String user = jsonObject.get("user").getAsString();
            String pwd = jsonObject.get("pwd").getAsString();
            String email = jsonObject.get("email").getAsString();
            String steamId = jsonObject.get("steamid").getAsString();
//          check si l'email est valide
            if (CommonUse.emailCheck(email)) {
//          création du nouveal utilisateur
                User regUser = new User();
//          attribution des données l'utilisateur pour ça création et creation
                regUser.setUserName(user);
                regUser.setPassword(pwd);
                regUser.setEmail(email);
                regUser.setSteamid(steamId);
                regUser.save();

//            requette pour recuperé l'id de l'utilisateur qui viens d'être créé
                Query query = sessionhib.createQuery("from User where password = '" + pwd + "' and email = '" + email + "'");
                results = query.list();
                User userwx = (User) results.get(0);
                regUser.setId(userwx.getId());

                req.getSession().setAttribute("isLogged", true);
                req.getSession().setAttribute("user", regUser);
                resp.setStatus(200);
            } else{
                resp.setStatus(204);
            }
        }
    }
}
