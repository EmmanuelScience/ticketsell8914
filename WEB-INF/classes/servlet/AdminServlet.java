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

@WebServlet({ "/loggedAdmin.html" })
public class AdminServlet extends HttpServlet {

    public void init() {

        // Lee del contexto de servlet (Sesi�n a nivel de aplicaci�n)
        ServletContext context = getServletContext();
    }


    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {

        // Establece el Content Type
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        out.println("<HTML>");
        out.println("<HEAD><TITLE>Admin TicketSell</TITLE>" +
                "<link rel=\"stylesheet\" href=\"style/register_login.css\">\n" +
                "<link rel=\"stylesheet\" href=\"style/header_footer.css\"></HEAD>");
        out.println("<header>\n" +
                "        <div class=\"logo_container\">\n" +
                "            <img src=\"images/logo.png\" alt=\"TicketSell logo\">\n" +
                "        </div>\n" +
                "        <div class=\"main_title\">\n" +
                "            <a href=\"loggedAdmin.html\">ticketsell</a>\n" +
                "        </div>\n"+
                "        <!-- REGISTERED HEADER CHANGES -->\n" +
                "        <div class=\"registered_container\">\n" +
                "            <img id=\"messages\" src=\"images/icons/dm.png\" alt=\"send a message\" />\n" +
                "            <div id=\"profile_info_div\">\n" +
                "                <img id=\"user-profile-pic\" src=\"images/avatars/Admin.png\" alt=\"Admin's profile picture\" />\n" +
                "                <label id=\"username-label\"></label>\n" +
                "            </div>\n" +
                "            <button class=\"logout_button\">Log out</button>\n" +
                "        </div>\n" +
                "    </header>" +
                "<H1 id=\"page_title\">Admin changes in TicketSell TicketSell</H1></BR>");

        // EVENTS VIEW

        String database = "ticketselldb";
        String servername = "localhost";
        String port = "3306";
        String username  = "root";
        String password  = "1234";

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

                String sEmail    = req.getParameter("email");
                String sPassw    = req.getParameter("password");

                System.out.println("email: " + sEmail);
                System.out.println("password: " + sPassw);

                // 4.- Execute the queries
                ResultSet rs = st.executeQuery("SELECT * FROM users");
                // 5.- Iterate through the ResultSet obtained
                int exists = 0;
                while (rs.next()) {
                    String inputEmail = rs.getString("email");
                    if (Objects.equals(sEmail, inputEmail)) {
                        exists = 1;
                        if (Objects.equals(sPassw, rs.getString("password"))) {
                            String sName = rs.getString("name");
                            out.println("<script> alert(\"Welcome again, "+sName+"!</script>\");");
                            if (Objects.equals(sName.toLowerCase(),"admin")){
                                out.println("<script>window.location.href='loggedAdmin.html';</script>");
                            }else{
                                out.println("<script>window.location.href='logged.html';</script>");
                            }
                            break;
                        } else {
                            out.println("<h2> Incorrect password. Please, try again.</h2>");
                            break;
                        }
                    }
                }
                if (exists == 0){
                    out.println("<h2>This email is not registered. Please, register.</h2>");
                }
                // 6.- Close the statement and the connection
                st.close();
                con.close();

            }
        } catch (Exception e) {
            out.println("<FONT color=\"#ff0000\">" + e.getMessage() + "</FONT><BR>");
        }
        out.println("</BODY></HTML>");



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


    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out2 = response.getWriter();
        out2.println("<HTML>");
        out2.println("<HEAD><TITLE>Login TicketSell</TITLE></HEAD>");

        String database = "ticketselldb";
        String servername = "localhost";
        String port = "3306";
        String username  = "root";
        String password  = "1234";

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

                String sEmail    = request.getParameter("email");
                String sPassw    = request.getParameter("password");

                System.out.println("email: " + sEmail);
                System.out.println("password: " + sPassw);

                // 4.- Execute the queries
                ResultSet rs = st.executeQuery("SELECT * FROM users");
                // 5.- Iterate through the ResultSet obtained
                int exists = 0;
                while (rs.next()) {
                    String inputEmail = rs.getString("email");
                    if (Objects.equals(sEmail, inputEmail)) {
                        exists = 1;
                        if (Objects.equals(sPassw, rs.getString("password"))) {
                            String sName = rs.getString("name");
                            out2.println("<script> alert(\"Welcome again, "+sName+"!</script>\");");
                            if (Objects.equals(sName.toLowerCase(),"admin")){
                                out2.println("<script>window.location.href='loggedAdmin.html';</script>");
                            }else{
                                out2.println("<script>window.location.href='logged.html';</script>");
                            }
                            break;
                        } else {
                            out2.println("<h2> Incorrect password. Please, try again.</h2>");
                            break;
                        }
                    }
                }
                if (exists == 0){
                    out2.println("<h2>This email is not registered. Please, register.</h2>");
                }
                // 6.- Close the statement and the connection
                st.close();
                con.close();

            }
        } catch (Exception e) {
                out2.println("<FONT color=\"#ff0000\">" + e.getMessage() + "</FONT><BR>");
            }
        out2.println("</BODY></HTML>");

        }
    }