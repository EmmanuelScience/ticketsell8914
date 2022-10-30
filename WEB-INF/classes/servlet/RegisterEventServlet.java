package servlet;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet({ "/registerEvent.html" })

public class RegisterEventServlet extends HttpServlet {

    //////////////////////////////
    public void init() {

        // Lee del contexto de servlet (Sesi�n a nivel de aplicaci�n)
        ServletContext context = getServletContext();
    }


    //////////////////////////////
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {


        // Establece el Content Type
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        out.println("<HTML>");
        out.println("<HEAD><TITLE>Register Event in TicketSell</TITLE>" +
                "<link rel=\"stylesheet\" href=\"style/register_login.css\">" +
                "<link rel=\"stylesheet\" href=\"style/header_footer.css\"></HEAD>");
        out.println("<header>\n" +
                "        <div class=\"logo_container\">\n" +
                "            <img src=\"images/logo.png\" alt=\"TicketSell logo\">\n" +
                "        </div>\n" +
                "        <div class=\"main_title\">\n" +
                "            <a href=\"loggedAdmin.html\">ticketsell</a>\n" +
                "        </div>\n" +
                "        <!-- REGISTERED HEADER  -->\n" +
                "        <div class=\"registered_container\">\n" +
                "            <img id=\"messages\" src=\"images/icons/dm.png\" alt=\"send a message\" />\n" +
                "            <div id=\"profile_info_div\">\n" +
                "                <img id=\"user-profile-pic\" src=\"images/avatars/default.png\" alt=\"User's profile picture\" />\n" +
                "                <label id=\"username-label\">Admin</label>\n" +
                "            </div>\n" +
                "            <button class=\"logout_button\" onclick=\"window.location.href='loggedAdmin.html';\">Log out</button>\n" +
                "        </div>\n" +
                "    </header>" +
                "<H1 id=\"page_title\">Register Event in TicketSell</H1></BR>");
        out.println("<FORM METHOD=\"POST\" ACTION=\"" + "\">"); // Se llama as� mismo por POST
        out.println("<label for=\"eventname\">Event Name: </label>\n" +
                "    <input type=\"text\" id=\"eventname\" name=\"eventname\" value=\"\" /><br><br>\n" +
                "    <label for=\"category\">Category: </label>\n" +
                "    <input type=\"text\" id=\"category\" name=\"category\" value=\"\" /><br><br>\n" +
                "    <label for=\"city\">city: </label>\n" +
                "    <input type=\"text\" id=\"city\" name=\"city\" value=\"\" /><br><br>" +
                "<label for=\"venue\">Venue: </label>" +
                "<input type=\"text\" id=\"venue\" name=\"venue\"/><br><br>" +
                "<label for=\"country\">Country: </label>" +
                "<input type=\"text\" id=\"country\" name=\"country\"/><br><br>" +
                "<label for=\"date\">Date: </label>\n" +
                "<input type=\"date\" id=\"date\" name=\"date\" value=\"\" /><br><br>\n" +
                "<input type=Submit value=\"Register Event\" />");

        out.println("</FORM></BODY>" +
                "<footer>\n" +
                "    <div class=\"logo_container\">\n" +
                "        <img src=\"images/logo.png\" alt=\"TicketSell logo\">\n" +
                "    </div>\n" +
                "    <div class=\"main_title\">\n" +
                "        <h1>ticketsell</h1>\n" +
                "    </div>\n" +
                "</footer>" +
                "</HTML>");

        out.close();
    }

    //////////////////////////////
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {

        String database = "ticketselldb";
        String servername = "localhost";
        String port = "3306";
        String username  = "root";
        String password  = "1234";

        PrintWriter out2 = res.getWriter();

        try {

            // 1- Load driver
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            // 2- Obtain a Connection object --> con
            String url = "";
            Connection con = null;
            con = DriverManager.getConnection("jdbc:mysql://"+servername+":"+port+"/"+database+"?useSSL=false& serverTimezone=UTC&allowPublicKeyRetrieval=true", username, password);

            if (con==null){
                System.out.println("--->UNABLE TO CONNECT TO SERVER:" + servername);
            } else {

                // 3- Obtain an Statement object -> st
                Statement st = con.createStatement();

                String sEventName    = req.getParameter("eventname");
                String sCategory    = req.getParameter("category");
                String sCountry    = req.getParameter("country");
                String sCity    = req.getParameter("city");
                String sVenue    = req.getParameter("venue");
                String sDate   = req.getParameter("date");


                // 4.- Execute the queries
                st.executeUpdate("insert into Events(eventName, venue, city, country, date, category) value (\""+ sEventName + "\",\"" + sVenue + "\",\"" + sCity+ "\",\"" + sCountry+ "\",\"" + sDate + "\",\"" + sCategory + "\");");
                ResultSet rs = st.executeQuery("SELECT * FROM users");

                // 6.- Close the statement and the connection
                st.close();
                con.close();
                out2.println("<script> alert(\"Event " +  sEventName + " was created successfully.\");" +
                        "window.location.href='loggedAdmin.html';</script>");
                // TODO: LINK TO LOGGED PAGE

            }
        } catch (Exception e) {
            //checking if error comes from already registered user
            String[] check = e.getMessage().split(" ");
            if (Objects.equals(check[0], "Duplicate") && Objects.equals(check[1], "entry")) {
                out2.println("<script> alert(\"Event already registered. Please, look for it.\");" +
                        "window.location.href='registerEvent.html';</script>");
            } else { //another error
                out2.println("<FONT color=\"#ff0000\">" + e.getMessage() + " Please, go back and solve it.</FONT><BR>");
            }
        }

        }

    }


