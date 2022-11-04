package servlet;

import entities.Event;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet({ "/createEvent.html" })
public class CreateEventServlet extends HttpServlet {
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


        Event event = new Event();
        event.setEventName(request.getParameter("eventname"));
        event.setVenue(request.getParameter("venue"));
        event.setCity(request.getParameter("city"));
        event.setCountry(request.getParameter("country"));
        //event.setDate(Date.parse(request.getParameter("date")));
        event.setDate(LocalDate.parse(request.getParameter("date")));
        event.setCategory(request.getParameter("category"));

        try {
            ut.begin();
            em.persist(event);
            ut.commit();

        } catch (Exception e) {
            throw new RuntimeException(e);
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
