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
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        req.getRequestDispatcher("index.jsp").forward(req, resp);

    }
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
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
