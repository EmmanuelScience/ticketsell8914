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

        ServletContext context = getServletContext();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        out.println("a");
        String id = request.getParameter("id");
        int idInt = Integer.parseInt(id);
        out.println("bbbbbbbbbbbbbbbbbbbbbbb");
        try {
            Event event = em.find(Event.class, idInt );
            if (event == null) {
                out.println("c");
                out.println("Event not found");
            } else {
                out.println("d");
                ut.begin();
                out.println("e");
                em.persist(event);
                out.println("f");
                ut.commit();
            }

        } catch (Exception e) {
            out.println("Error");
        }
/*
        String id = request.getParameter("id");
        int idInt = Integer.parseInt(id);
        //To search A song
        try {
            event = em.find(Event.class, idInt );
            if (event == null) {
                out.println("Song not found");
            } else {
                request.setAttribute("eventBean", event);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/ShowEvents.jsp");
                requestDispatcher.forward(request, response);
            }

        } catch (Exception e) {
            out.println("Error");
        }*/
    }
}
