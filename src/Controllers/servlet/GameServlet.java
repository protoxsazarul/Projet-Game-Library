package Controllers.servlet;

import Controllers.common.GetUserGamesUtils;
import com.google.gson.Gson;
import model.Game;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
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

@WebServlet("/game/*")
public class GameServlet extends HttpServlet {
	private static final long serialVersionUID = -7759593256585062849L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException,IOException {
		System.out.println("serving request " + req);
		req.setCharacterEncoding("UTF-8");

		// PREPARATION DU RESULTAT
		Session s = HibernateUtils.getSession();
		String[] segments = req.getRequestURL().toString().split("/");
		String lastSegment = segments[segments.length - 1];
		List<Game> results = new ArrayList<Game>();

		try {
			if ("game".equals(lastSegment)) {
				Query query = s.createQuery("from Game where appsid = 218620");
				results = query.list();
			} else {
				results.add((Game) s.get(Game.class, Integer.parseInt(lastSegment)));
			}

		} catch (HibernateException ex) {
			ex.printStackTrace(System.err);
		} finally {
			s.close();
		}

		// API CALL - JSON RESULT

		resp.setContentType("application/json");
		Gson gson = new Gson();
		String json = null;
		if (results.size() == 1) {
			json = gson.toJson(results.get(0));
		} else {
			//LOG.debug(results.getClass().getName());
			json = gson.toJson(results);
		}
//		resp.getWriter().println(json);
//		this.getServletContext().getRequestDispatcher("/web-inf/views/home/index.jsp").forward(req, resp);
		resp.setContentType("text/html");
		resp.getWriter().println(fileContent("src/views/home/index.jsp")) ;
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
	 @Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		System.out.println(req.getParameter("addGames"));
		System.out.println(req.getParameter("steamid64"));
		try {
			GetUserGamesUtils.run(req.getParameter("steamid64"));
		} catch (Exception e){
			e.printStackTrace();
		}
		 res.setContentType("text/html");
		 res.getWriter().println(fileContent("src/views/home/index.jsp")) ;
	}
}