package servlet;

import entities.Event;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.transaction.UserTransaction;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

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
        /*String buttonA = request.getParameter("button_a_clicked");
        if ((Boolean)(request.getSession().getAttribute("buttonAClicked"))) {
            request.getSession().setAttribute("buttonAClicked", false);
        } else {
            request.getSession().setAttribute("buttonAClicked", true);
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/SearchEvent.jsp");
        requestDispatcher.forward(request, response);*/
    }

    public boolean checkToBool(String s) {
        if (s == null) return false;
        return s.equals("on");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //example
        doGet(request, response);
        // Establece el Content Type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String city = "Madrid";
        out.println("<HTML>");
        out.println("<HEAD><TITLE>BDServlet</TITLE></HEAD>");
        out.println("<BODY bgcolor=\"#ffff66\">");
        out.println("<H1><FONT color=\"#666600\">Database: Users</FONT></H1></BR>");
        out.println("<FORM METHOD=\"POST\" ACTION=\"" + "\">"); // Se llama as� mismo por POST
        /*try {
            String sQuery = "Select e from Event e";
            String sWhere = "where e.eventName = '"+city+"'";
            Query q = em.createQuery(sQuery);
            List lResults = q.getResultList();
            for (Object o : lResults) {
                Event event = (Event) o;
                out.println("<h2>Name " + event.getEventName()+"</h2>");
            }
        } catch (Exception e) {

        }*/

        //Search for events
        String searchValue = request.getParameter("searchValue");
        boolean cityBtn = checkToBool(request.getParameter("cityClicked"));
        boolean eventBtn = checkToBool(request.getParameter("eventClicked"));
        boolean venueBtn = checkToBool(request.getParameter("venueClicked"));
        boolean categoryBtn = checkToBool(request.getParameter("categoryClicked"));
        boolean dateBtn = checkToBool(request.getParameter("dateClicked"));

        ArrayList<Event> events = new ArrayList<>();
        List queryResult;
        boolean atLeastOneButton = false;
        System.out.println("HEmos entrado 1");
        if (cityBtn) {
            System.out.println("HEmos entrado");
            String sQuery = "Select e from Event e where e.city = '"+searchValue+"'";
            //String sWhere = "where e.city = '"+searchValue+"'";
            queryResult = em.createQuery(sQuery).getResultList();
            events.addAll(queryResult);
            atLeastOneButton = true;

        }
        if (eventBtn) {
            queryResult = em.createQuery("select * from Events where eventName = '"+searchValue+"'").getResultList();
            events.addAll(queryResult);
            atLeastOneButton = true;
        }
        if (categoryBtn) {
            queryResult = em.createQuery("select * from Events where category = '"+searchValue+"'").getResultList();
            events.addAll(queryResult);
            atLeastOneButton = true;
        }
        if (venueBtn) {
            queryResult = em.createQuery("select * from Events where venue = '"+searchValue+"'").getResultList();
            events.addAll(queryResult);
            atLeastOneButton = true;
        }
        if (dateBtn) {
            queryResult = em.createQuery("select * from Events where date = '"+searchValue+"'").getResultList();
            events.addAll(queryResult);
            atLeastOneButton = true;
        }
        for (Event event : events) {
            out.println("<h2>Name " + event+"</h2>");
        }
        /*out.println(events);
        if (!atLeastOneButton) {
            // search all columns
        }
        try {
            if (events.size() == 0) {
                out.println("Event  not found");
            } else {
                request.setAttribute("events", events);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/ShowEvents.jsp");
                requestDispatcher.forward(request, response);
            }

        } catch (Exception e) {
            out.println("Error");
        }*/
    }

    private void toggleButton() {

    }
}
