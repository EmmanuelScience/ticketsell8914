package servlet;

import entities.Event;

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
import java.io.IOException;
import java.time.LocalDate;

import static java.lang.System.out;

@WebServlet({ "/deleteEvent.html" })
public class DeleteEventServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @PersistenceContext(unitName="ticketSell")
    private EntityManager em;

    @Resource
    private UserTransaction ut;

    public void init() {

        //ServletContext context = getServletContext();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // deleting an event from the database
        String id = request.getParameter("id_del");
        int idInt = Integer.parseInt(id);
        try {
            Event event = em.find(Event.class, idInt );
            if (event == null) {
                out.println("Event not found");
            } else {
                ut.begin();
                if (!em.contains(event)) {
                    event = em.merge(event);
                }
                em.remove(event);
                ut.commit();
                out.println("<script>window.location.href='loggedAdmin.html';</script>");
            }

        } catch (Exception e) {
            out.println("Error");
        }

    }
}
