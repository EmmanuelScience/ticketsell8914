package servlet;

import entities.Event;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Objects;

import static java.lang.System.out;

@WebServlet({ "/modifyEvent.html" })
public class ModifyEventServlet extends HttpServlet {
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
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<HTML>");

        String id = request.getParameter("id_mod");
        if (Objects.equals(id, "")) {
            out.println("<h2>Please, enter an event id.</h2>");
        }else {
            int idInt = Integer.parseInt(id);
            String param = request.getParameter("modifying");
            String val = request.getParameter("info");

            try {
                Event event = em.find(Event.class, idInt);
                if (event == null) {
                    out.println("<h2> Event not found.</h2>");
                } else {
                    switch (param) {
                        case "eventname":
                            event.setEventName(val);
                            break;
                        case "venue":
                            event.setVenue(val);
                            break;
                        case "city":
                            event.setCity(val);
                            break;
                        case "country":
                            event.setCountry(val);
                            break;
                        case "date":
                            event.setDate(LocalDate.parse(val));
                            break;
                        case "category":
                            event.setCategory(val);
                            break;
                    }
                    /*Event new_event = new Event();

                    new_event.setEventName(event.getEventName());
                    new_event.setVenue(event.getVenue());
                    new_event.setCity(event.getCity());
                    new_event.setCountry(event.getCountry());
                    new_event.setDate(event.getDate());
                    new_event.setCategory(event.getCategory());
                    //creating a new event with the changed value
                    em.persist(new_event);
                    if (!em.contains(event)) {
                        event = em.merge(event);
                    }
                    //removing the old event
                    em.remove(event);*/
                    ut.begin();
                    Event newEvent = em.merge(event);
                    ut.commit();
                    out.println("<script>window.location.href='loggedAdmin.html';</script>");
                }

            } catch (Exception e) {

                    out.println(e.getMessage());
            }
        }
        out.println("</HTML>");

        out.close();
    }
}