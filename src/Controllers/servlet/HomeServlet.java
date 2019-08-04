package Controllers.servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


@WebServlet("/index")
public class HomeServlet extends HttpServlet {
    private static final long serialVersionUID=7759593256585062442L;
    @Override
    public void doGet(HttpServletRequest req , HttpServletResponse resp)
            throws ServletException, IOException{
        System.out.println("serving request " + req);
        req.setCharacterEncoding("UTF-8");
        String[] segments = req.getRequestURL().toString().split("/");
        String lastSegment = segments[segments.length - 1];
        System.out.println("Last Segement = "+lastSegment);


//        resp.setContentType("text/html");
        req.getRequestDispatcher("index.jsp").forward(req, resp);

//       resp.getWriter().println(fileContent("/index.jsp"));
//        resp.getWriter().println(fileContent("web/WEB-INF/index.jsp"));
//        resp.getWriter().println(fileContent("WEB-INF/index.jsp"));
       // resp.getWriter().println(fileContent("web/index.jsp"));



    } public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        req.getRequestDispatcher("index.jsp").forward(req, resp);

    }
    public String fileContent(String filename) {
        String result = "";
        try
        {
            File file = new File(filename);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null)
            {
                result += st ;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return result;

    }
}
