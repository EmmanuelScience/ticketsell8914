package servlet;

import entities.Event;
import entities.Ticket;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

@WebServlet({ "/SearchEventServlet", "/search.html", "/browse.html", "/event/tickets.html" })
public class SearchEventServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @PersistenceContext(unitName="ticketSell")
    private EntityManager em;

    @Resource
    private UserTransaction ut;

    public void init() {

        ServletContext context = getServletContext();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String uri = request.getRequestURI();
        if (uri.endsWith("/search.html")) {
            doSearchEvents(request, response);
        } else if (uri.endsWith("/browse.html")) {
            doSearchEventsAdvance(request, response);
        } else if (uri.endsWith("/event/tickets.html")) {
            doShowTickets(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        doGet(request, response);
    }

    private void doSearchEvents(HttpServletRequest request, HttpServletResponse response) {
        //Search for events
        List<Object> events = new ArrayList<>();
        if (request.getParameter("search") != null) {
            String searchValue = request.getParameter("search");
            String sQuery = "Select e from Event e where e.eventName = '"+searchValue+"'";
            events.addAll(em.createQuery(sQuery).getResultList());
            sendDataToDisplay(request, response, events,"/ShowEvents.jsp");
        }
        //Advanced search for events
        else {
            boolean today = false;
            boolean weekend = false;
            if (request.getParameter("todayClicked") != null) {
                request.setAttribute("todayClicked", true);
                today = true;
            }

            if (request.getParameter("weekendClicked") != null) {
                request.setAttribute("weekendClicked", true);
                weekend = true;
            }
            if (request.getParameter("allCategoryClicked") != null) {
                request.setAttribute("allCategoryClicked", true);
            }
            if (today) {
                String sQuery = "Select e from Event e where e.date = current_date";
                events.addAll(em.createQuery(sQuery).getResultList());
            } else if (weekend) {
                String sQuery = "Select e from Event e WHERE function('dayofweek', e.date) in (5,6,7)";
                events.addAll(em.createQuery(sQuery).getResultList());

            }
            if (events.size() == 0) {
                String sQuery = "Select e from Event e";
                events.addAll(em.createQuery(sQuery).getResultList());
            }
            sendDataToDisplay(request, response, events,"/AdvanceSearch.jsp");
        }
    }

    private void doSearchEventsAdvance(HttpServletRequest request, HttpServletResponse response) {
        //Advanced search
        String eventName = request.getParameter("eventName");
        String eventDateString = request.getParameter("eventDate");
        LocalDate eventDate = LocalDate.parse(eventDateString);
        String eventCity = request.getParameter("eventCity");
        String eventCategory = request.getParameter("eventCategory");
        String eventVenue = request.getParameter("eventVenue");
        String sQuery = "SELECT e FROM Event e WHERE " +
                " e.eventName LIKE :eventName AND" +
                " e.date = :eventDate AND" +
                " e.city LIKE :eventCity AND " +
                " e.category LIKE :eventCategory AND" +
                " e.venue LIKE :eventVenue";
        List<Object> events = new ArrayList<>(em.createQuery(sQuery, Event.class)
                .setParameter("eventName", "%" + eventName + "%")
                .setParameter("eventCity", "%" + eventCity + "%")
                .setParameter("eventDate", eventDate)
                .setParameter("eventCategory", "%" + eventCategory + "%")
                .setParameter("eventVenue", "%" + eventVenue + "%")
                .getResultList());
        sendDataToDisplay(request, response, events, "/ShowEvents.jsp");
    }

    private void doShowTickets(HttpServletRequest request, HttpServletResponse response) {
        String eventId = request.getParameter("eventID");
        List<Object> tickets = new ArrayList<>(em.createQuery("SELECT t FROM Ticket t WHERE t.event = :eventID", Ticket.class)
                .setParameter("eventID", Integer.parseInt(eventId))
                .getResultList());
        request.setAttribute("tickets", tickets);
        request.setAttribute("event", em.find(Event.class, Integer.parseInt(eventId)));
        sendDataToDisplay(request, response, tickets, "/ShowTickets.jsp");
    }

    private void sendDataToDisplay(HttpServletRequest request, HttpServletResponse response, List<Object> attributes, String url) {
        try {
            if (attributes.size() == 0) {
                request.setAttribute("error", "No Data found");
            } else {
                request.setAttribute("events", attributes);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
                requestDispatcher.forward(request, response);
            }
        } catch (Exception e) {
            out.println("Error");
        }
    }
}
