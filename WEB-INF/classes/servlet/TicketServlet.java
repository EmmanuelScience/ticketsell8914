package servlet;

import entities.Event;
import entities.Ticket;
import entities.User;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.transaction.UserTransaction;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

@WebServlet({ "/TicketServlet",  "/tickets.html", "/createTicket.html" })
public class TicketServlet extends HttpServlet {
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
        String uri = request.getRequestURI();
        if (uri.endsWith("/tickets.html")) {
            doShowTickets(request, response);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String uri = request.getRequestURI();
        if (uri.endsWith("createTicket.html")) {
            doCreateTicket(request, response, session);
        }
    }


    private void doCreateTicket(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        // Sell ticket
        if (request.getParameter("sellTicket") != null) {
            if (session.getAttribute("userid") != null) {
                int user = Integer.parseInt((String) session.getAttribute("userid"));
                int eventID = Integer.parseInt((String) request.getParameter("sellTicket"));
                sendDataToDisplay(request, response, eventID, "/registerTicket.jsp",null);
            } else {
                sendDataToDisplay(request, response, 0, "/loginPage.jsp","please log in to sell tickets");
            }
        }
        else {
            // Create ticket and add to database
            //finding event
            String eventID =  request.getParameter("event");
            int idEv = Integer.parseInt(eventID);

            //finding user
            int userId = Integer.parseInt((String) session.getAttribute("userid"));

            //creating the event
            try {
                Event event = em.find(Event.class, idEv );
                Ticket tick = new Ticket();
                tick.setTicketCode(request.getParameter("ticketcode"));
                tick.setCategory(request.getParameter("category"));
                tick.setPrice(Double.valueOf(request.getParameter("price")));
                tick.setUser(userId);
                tick.setEvent(idEv);
                String userQuery = "Select u from User u where u.id = '"+userId+"'";
                User user = (User) em.createQuery(userQuery).getSingleResult();
                tick.setTicketOwnerName(user.getName());
                ut.begin();
                em.persist(tick);
                ut.commit();
                sendDataToDisplay(request, response, idEv, "/loggedUser.jsp", null);
            } catch (Exception e) {
                sendDataToDisplay(request, response, idEv, "/loggedUser.jsp", "Error creating ticket");
            }
        }
    }

    private void sendDataToDisplay(HttpServletRequest request, HttpServletResponse response, Object attributes, String url, String error) {
        try {
            if (error != null) {
                request.setAttribute("error", error);
            }
            request.setAttribute("attributes", attributes);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
            requestDispatcher.forward(request, response);
        } catch (Exception e) {
            out.println("Error");
        }
    }

    private void doShowTickets(HttpServletRequest request, HttpServletResponse response) {
        String eventId = request.getParameter("eventID");
        List<Object> tickets = new ArrayList<>(em.createQuery("SELECT t FROM Ticket t WHERE t.event = :eventID", Ticket.class)
                .setParameter("eventID", Integer.parseInt(eventId))
                .getResultList());
        request.setAttribute("tickets", tickets);
        request.setAttribute("event", em.find(Event.class, Integer.parseInt(eventId)));
        if (tickets.size() == 0) {
            sendDataToDisplay(request, response, null, "/showTickets.jsp", "No tickets found");
        } else {
            sendDataToDisplay(request, response, tickets, "/showTickets.jsp", null);
        }
    }
}
