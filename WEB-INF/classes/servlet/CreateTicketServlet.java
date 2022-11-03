package servlet;

import entities.Event;
import entities.Ticket;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;
import java.io.IOException;
import java.time.LocalDate;

import static java.lang.System.out;

@WebServlet({ "/createTicket.html" })
public class CreateTicketServlet extends HttpServlet {
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

        //finding event
        int idEv = Integer.valueOf(request.getParameter("event"));

        //To search A song
        try {
            Event event = em.find(Event.class, idEv );
            if (event == null) {
                out.println("Song not found");
            } else {
                Ticket tick = new Ticket();
                tick.setTicketCode(request.getParameter("ticketcode"));
                tick.setCategory(request.getParameter("category"));
                tick.setPrice(Double.valueOf(request.getParameter("price")));
                //tick.setUser();
                tick.setEvent(idEv);
                ut.begin();
                em.persist(event);
                ut.commit();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        HttpSession session = request.getSession();
        User user =session.getAttribute("user");

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
