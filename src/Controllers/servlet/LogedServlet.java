package Controllers.servlet;
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


@WebServlet("/user")
public class LogedServlet extends HttpServlet {
    private Session session = HibernateUtils.getSession();

@Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)  throws ServletException,IOException{
        if((Boolean)req.getSession().getAttribute("isLogged")){
            System.out.println("patate");
            req.getRequestDispatcher("./user/userpage.jsp").forward(req, resp);

        }else{
            req.getRequestDispatcher("index.jsp").forward(req, resp);
            System.out.println("tu n'est pas log");
        }

    }
    public void doPost(HttpServletRequest req, HttpServletResponse resp)  throws ServletException,IOException{
        if((Boolean)req.getSession().getAttribute("isLogged")){
            System.out.println("patate");
            req.getRequestDispatcher("./user/userpage.jsp").forward(req, resp);

        }else{
            req.getRequestDispatcher("index.jsp").forward(req, resp);
            System.out.println("tu n'est pas log");
        }
    }
}
