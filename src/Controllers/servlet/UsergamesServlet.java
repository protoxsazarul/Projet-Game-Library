package Controllers.servlet;

import org.hibernate.Session;
import persistance.HibernateUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/usergames")

public class UsergamesServlet extends HttpServlet {
    private Session session = HibernateUtils.getSession();
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException {
        if((Boolean)req.getSession().getAttribute("isLogged")){
            System.out.println("patate");
            req.getRequestDispatcher("./user/usergames.jsp").forward(req, resp);

        }else{
            req.getRequestDispatcher("index.jsp").forward(req, resp);
            System.out.println("tu n'est pas log");
        }
    }

    }
