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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

@WebServlet({ "/EventServlet", "/search.html", "/advSearch.html" })
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

    public boolean checkToBool(String s) {
        if (s == null) return false;
        return s.equals("on");
    }

    public boolean contains(String s, String[] arr,HttpServletRequest request) {
        for (String str : arr) {
            if (str.equals(s)) return true;
        }
        return false;
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);

        if (request.getRequestURI().equals("/ticketsell8914/advSearch.html")) {
            List<Event> events = new ArrayList<Event>();
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
            events.addAll(em.createQuery(sQuery, Event.class)
                    .setParameter("eventName", "%" + eventName + "%")
                    .setParameter("eventCity", "%" + eventCity + "%")
                    .setParameter("eventDate", eventDate)
                    .setParameter("eventCategory", "%" + eventCategory + "%")
                    .setParameter("eventVenue", "%" + eventVenue + "%")
                    .getResultList());
            sendEventsToDisplay(request, response, events);
        }
        else if (request.getRequestURI().equals("/ticketsell8914/search.html")) {

            //Search for events
            List<Event> events = new ArrayList<>();
            if (request.getParameter("search") != null) {
                String searchValue = request.getParameter("search");
                String sQuery = "Select e from Event e where e.eventName = '"+searchValue+"'";
                events.addAll(em.createQuery(sQuery).getResultList());
                sendEventsToDisplay(request, response, events);
            }

            //Advanced search for events
            else {
                String url = request.getRequestURI();
                boolean today = false;
                boolean weekend = false;
                if (request.getParameter("todayClicked") != null) {
                    request.setAttribute("todayClicked", true);
                    request.setAttribute("weekendClicked", false);
                    today = true;
                }

                if (request.getParameter("weekendClicked") != null) {
                    request.setAttribute("weekendClicked", true);
                    request.setAttribute("todayClicked", false);
                    weekend = true;
                }
                if (request.getParameter("allCategoryClicked") != null) {
                    request.setAttribute("allCategoryClicked", true);
                    request.setAttribute("weekendClicked", false);
                    request.setAttribute("todayClicked", false);
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
                try {
                    request.setAttribute("events", events);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/AdvanceSearch.jsp");
                    requestDispatcher.forward(request, response);
                } catch (Exception e) {
                    out.println("Error");
                }
            }
        }
    }

    private void sendEventsToDisplay(HttpServletRequest request, HttpServletResponse response, List<Event> events) {
        try {
            if (events.size() == 0) {
                request.setAttribute("error", "No events found");
            } else {
                request.setAttribute("events", events);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/ShowEvents.jsp");
                requestDispatcher.forward(request, response);
            }
        } catch (Exception e) {
            out.println("Error");
        }
    }
}
