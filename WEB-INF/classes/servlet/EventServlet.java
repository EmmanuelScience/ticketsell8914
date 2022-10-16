package servlet;

import entities.Event;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.transaction.UserTransaction;
import java.io.IOException;

import static java.lang.System.out;

@WebServlet({ "/EventServlet", "/search.html", "/doUpdate.html" })
public class EventServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @PersistenceContext(unitName="ticketSell")
    private EntityManager em;

    @Resource
    private UserTransaction ut;

    public void init() {

        ServletContext context = getServletContext();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        int idInt = Integer.parseInt(id);
        //To search A song
        try {
            Event event = em.find(Event.class, idInt );
            if (event == null) {
                out.println("Song not found");
            } else {
                request.setAttribute("eventBean", event);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/ShowEvents.jsp");
                requestDispatcher.forward(request, response);
            }

        } catch (Exception e) {
            out.println("Error");
        }
    }
}
