package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

import entities.Songsdb;

import static java.lang.System.out;

/**
 * Servlet implementation class BDServlet
 */
@WebServlet({ "/SongServlet", "/search.html", "/doUpdate.html" })
public class SongServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName="songs")
	private EntityManager em;
	
	@Resource
	private UserTransaction ut;
	
	////////////////////////////////////////////////////////////////////////////////////////
	public void init() {

		// Lee del contexto de servlet (Sesi�n a nivel de aplicaci�n)
		ServletContext context = getServletContext();
	}


	////////////////////////////////////////////////////////////////////////////////////////
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
	throws IOException, ServletException {

	
	try {
		Songsdb song = new Songsdb();
		song.setSongName("Mi song");
		song.setArtist("artist");
		song.setDuration(4);
		song.setScore(5);
		ut.begin();
			em.persist(song);
		ut.commit();

	} catch (Exception e) {
			out.println("<FONT color=\"#ff0000\">"+e.getMessage()+"</FONT><BR>");
	};


	out.println("</FORM>");
	out.println("</BODY></HTML>");

	out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		String pathUrl = request.getServletPath();
		if (pathUrl.compareTo("/doUpdate.html") == 0) {
			String id = request.getParameter("id");
			int idInt = Integer.parseInt(id);
			//To search A song
			try {
				Songsdb songsdb = em.find(Songsdb.class, idInt );
				if (songsdb == null) {
					out.println("Song not found");
				} else {
					songsdb.setArtist(request.getParameter("artist"));
					songsdb.setSongName(request.getParameter("name"));
					songsdb.setScore(Integer.valueOf(request.getParameter("score")));
					songsdb.setDuration(Integer.valueOf(request.getParameter("duration")));

					ut.begin();
					songsdb = em.merge(songsdb);
					ut.commit();
				}

			} catch (Exception e) {
				out.println("Error");
			}
		} else {
			String id = request.getParameter("id");
			int idInt = Integer.parseInt(id);
			//To search A song
			try {
				Songsdb songsdb = em.find(Songsdb.class, idInt );
				if (songsdb == null) {
					out.println("Song not found");
				} else {
					request.setAttribute("songBean", songsdb);
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("/songData.jsp");
					requestDispatcher.forward(request, response);
				}

			} catch (Exception e) {
				out.println("Error");
			}
		}
	}

}
