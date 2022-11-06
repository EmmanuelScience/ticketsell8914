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
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Objects;

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
        if (Objects.equals(id, "")) {
            request.setAttribute("error", "No events found");

        }else {
            try {
                int idInt = Integer.parseInt(id);
                Event event = em.find(Event.class, idInt);
                if (event == null) {
                    request.setAttribute("error", "No events found");
                } else {
                    ut.begin();
                    if (!em.contains(event)) {
                        event = em.merge(event);
                    }
                    em.remove(event);
                    ut.commit();

                }

            } catch (Exception e) {
                request.setAttribute("error", e.getMessage());
            }
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/loggedAdmin.jsp");
        requestDispatcher.forward(request, response);

    }
}
