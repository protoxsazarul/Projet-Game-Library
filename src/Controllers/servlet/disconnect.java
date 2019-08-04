package Controllers.servlet;
import model.Game;
import org.hibernate.Session;
import persistance.HibernateUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/logout")
public class disconnect extends HttpServlet {
    private static final long serialVersionUID=7759593256585062442L;
    @Override
    public void doGet(HttpServletRequest req , HttpServletResponse resp)
            throws ServletException, IOException{
        req.getSession().invalidate();
        resp.sendRedirect("/index");
    }

}
