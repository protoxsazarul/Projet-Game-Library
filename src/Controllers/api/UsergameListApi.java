package Controllers.api;

import com.google.gson.*;
import model.User;
import model.Usersgames;
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

@WebServlet("/usersgamelist")
public class UsergameListApi extends HttpServlet {
    private Session sessionhib = HibernateUtils.getSession();
    private String respPost;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException,IOException{
        resp.getWriter().println("patate");

    }
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException,IOException{
        List<Usersgames> result = new ArrayList<Usersgames>();
//        resp.getWriter().println("patate");

        if ("POST".equalsIgnoreCase(req.getMethod())) {
            // recup du post
            respPost = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            System.out.println(respPost);
            // parsing du post
            JsonParser jsonParser = new JsonParser();
            JsonElement jsonTree = jsonParser.parse(respPost);
            JsonObject jsonObject = jsonTree.getAsJsonObject();
            String userid = jsonObject.get("userid").getAsString();

            Query query = sessionhib.createQuery("from Usersgames where idUser ='"+userid+"'");
            result = query.list();
            System.out.print(result.get(0).getNote());

            Gson gson = new Gson();
            resp.getWriter().println(gson.toJson(result));
//        resp.getWriter().println("patate");
        }

    }
}
