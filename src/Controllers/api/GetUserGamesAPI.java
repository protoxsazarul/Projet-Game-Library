package Controllers.api;
import Controllers.common.GetUserGamesUtils;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.hibernate.Session;
import persistance.HibernateUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet("/getUsersGame")
public class GetUserGamesAPI extends HttpServlet {
    private String respPost;
    private String userid;
    private String steamid;
    private Gson gson = new Gson();
    private Session sessionhib = HibernateUtils.getSession();


    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {


    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        if ("POST".equalsIgnoreCase(req.getMethod())) {
            respPost = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            System.out.println("serving request " + req);
            System.out.println(respPost);

            JsonParser jsonParser = new JsonParser();
            JsonElement jsonTree = jsonParser.parse(respPost);
            JsonObject jsonObject = jsonTree.getAsJsonObject();

            userid =  jsonObject.get("user").getAsString();
            steamid = jsonObject.get("steamid").getAsString();
            try {
                GetUserGamesUtils.run(steamid);
            }catch (Exception ex ){
                ex.printStackTrace(System.err);
            }
        }
    }
}