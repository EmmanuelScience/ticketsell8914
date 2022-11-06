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
import javax.servlet.http.Part;
import javax.servlet.annotation.MultipartConfig;
import javax.transaction.UserTransaction;
import java.io.IOException;
import java.time.LocalDate;

import static java.lang.System.out;

@WebServlet({ "/createEvent.html" })
@MultipartConfig
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


        if (request.getParameter("eventname") != null) {

        }
        Event event = new Event();
        event.setEventName(request.getParameter("eventname"));
        event.setVenue(request.getParameter("venue"));
        event.setCity(request.getParameter("city"));
        event.setCountry(request.getParameter("country"));
        event.setDate(LocalDate.parse(request.getParameter("date")));
        event.setCategory(request.getParameter("category"));

        Part filePart = request.getPart("image");
        byte[] data = new byte[(int) filePart.getSize()];
        filePart.getInputStream().read(data, 0, data.length);
        event.setImage(data);
        try {
            ut.begin();
            em.persist(event);
            ut.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/loggedAdmin.jsp");
        requestDispatcher.forward(request, response);

    }
}
