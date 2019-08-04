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

@WebServlet("/LogServlet")
public class LogServlet extends HttpServlet {
    private String email;
    private String pwd;
    private String respPost;
    private Gson gson = new Gson();
    private Session sessionhib = HibernateUtils.getSession();
    private static final long serialVersionUID=7759593256585062432L;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException,IOException {
        System.out.println("doGet");

        System.out.println("serving request " + req);
        req.setCharacterEncoding("UTF-8");
        System.out.println(req.getParameterValues("user"));
        System.out.println(req.getParameter("pwd"));
        System.out.println("-----------------------------------------------------");

    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("doPost");
        List<User> results = new ArrayList<User>();

        if ("POST".equalsIgnoreCase(req.getMethod()))
        {
            System.out.println("in if");

            respPost = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            System.out.println(respPost);
            JsonParser jsonParser = new JsonParser();
            JsonElement jsonTree = jsonParser.parse(respPost);
            JsonObject jsonObject = jsonTree.getAsJsonObject();
            JsonElement userJSon = jsonObject.get("email");
            JsonElement pwdJSon = jsonObject.get("pwd");
            email = userJSon.getAsString();
            pwd = pwdJSon.getAsString();
        }
        System.out.println(email +pwd);
        System.out.println(CommonUse.emailCheck(email));
        if (CommonUse.emailCheck(email)) {
            Query query = sessionhib.createQuery("from User where password like '" + pwd + "' and email like '" + email + "'");
            results = query.list();
        };
        System.out.println("-----------------------------------------------------");
        System.out.println(results);
        if(!results.isEmpty()){
            System.out.println("dans le if///////////");
            User loged = results.get(0);
            loged.setPassword(null);
            req.getSession().setAttribute("isLogged",true);
            req.getSession().setAttribute("user",loged);
            boolean sucess = true;
            System.out.println("gson toJson "+gson.toJson(loged));
            resp.getWriter().println(gson.toJson(loged));

        }else{
            req.getSession().setAttribute("isLogged",false);

            resp.setStatus(204);
        }


//        System.out.println(req.getReader());
//        System.out.println(req.getInputStream());

        System.out.println("-----------------------------------------------------");

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getRespPost() {
        return respPost;
    }

    public void setRespPost(String respPost) {
        this.respPost = respPost;
    }
}
