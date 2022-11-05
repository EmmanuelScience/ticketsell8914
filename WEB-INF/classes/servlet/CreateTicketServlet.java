package servlet;

import entities.Event;
import entities.Ticket;
import entities.User;

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

        //finding user
        HttpSession session = request.getSession();
        int user = Integer.valueOf((String) session.getAttribute("userid"));
        //creating the event
        try {
            Event event = em.find(Event.class, idEv );
            if (event == null) {
                out.println("Song not found");
            } else {
                Ticket tick = new Ticket();
                tick.setTicketCode(request.getParameter("ticketcode"));
                tick.setCategory(request.getParameter("category"));
                tick.setPrice(Double.valueOf(request.getParameter("price")));
                tick.setUser(user);
                tick.setEvent(idEv);
                ut.begin();
                em.persist(tick);
                ut.commit();
                out.println("<script>window.location.href='loggedUser.jsp';</script>");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }



        out.println("Error");

    }
}