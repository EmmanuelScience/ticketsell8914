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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Search for events
        String searchValue = request.getParameter("searchValue");
        boolean cityBtn = Boolean.parseBoolean(request.getParameter("cityClicked"));
        boolean eventBtn = Boolean.parseBoolean(request.getParameter("eventClicked"));
        boolean venueBtn = Boolean.parseBoolean(request.getParameter("venueClicked"));
        boolean categoryBtn = Boolean.parseBoolean(request.getParameter("categoryClicked"));
        boolean dateBtn = Boolean.parseBoolean(request.getParameter("dateClicked"));

        ArrayList<Event> events = new ArrayList<>();
        List queryResult;
        boolean atLeastOneButton = false;
        if (cityBtn) {

            queryResult = em.createQuery("select * from entities.Event where city = " + searchValue).getResultList();
            events.addAll(queryResult);
            atLeastOneButton = true;

        }
        if (eventBtn) {
            queryResult = em.createQuery("select * from entities.Event where eventName = " + searchValue).getResultList();
            events.addAll(queryResult);
            atLeastOneButton = true;
        }
        if (categoryBtn) {
            queryResult = em.createQuery("select * from entities.Event where category = " + searchValue).getResultList();
            events.addAll(queryResult);
            atLeastOneButton = true;
        }
        if (venueBtn) {
            queryResult = em.createQuery("select * from entities.Event where venue = " + searchValue).getResultList();
            events.addAll(queryResult);
            atLeastOneButton = true;
        }
        if (dateBtn) {
            queryResult = em.createQuery("select * from entities.Event where date = " + searchValue).getResultList();
            events.addAll(queryResult);
            atLeastOneButton = true;
        }
        out.println(events);
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
        }
    }

    private void toggleButton() {

    }
}
