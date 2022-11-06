package servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;

import static java.lang.System.out;

@WebServlet({ "/login.html" })
public class LoginUserServlet extends HttpServlet {

    public void init() {

        // Lee del contexto de servlet (Sesi�n a nivel de aplicaci�n)
        ServletContext context = getServletContext();
    }


    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
    }


    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        String database = "ticketselldb";
        String servername = "localhost";
        String port = "3306";
        String username = "root";
        String password = "1234";

        try {

            // 1- Load driver
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            // 2- Obtain a Connection object --> con
            String url = "";
            Connection con = null;
            con = DriverManager.getConnection("jdbc:mysql://" + servername + ":" + port + "/" + database + "?useSSL=false& serverTimezone=UTC&allowPublicKeyRetrieval=true", username, password);

            if (con == null) {
                System.out.println("--->UNABLE TO CONNECT TO SERVER:" + servername);
            } else {

                // 3- Obtain an Statement object -> st
                Statement st = con.createStatement();

                String sEmail = request.getParameter("email");
                String sPassw = request.getParameter("password");


                // 4 Execute the queries
                ResultSet rs = st.executeQuery("SELECT * FROM Users");
                // 5 Iterate through the ResultSet obtained
                int exists = 0;
                while (rs.next()) {
                    String inputEmail = rs.getString("email");
                    if (Objects.equals(sEmail, inputEmail)) {
                        exists = 1;
                        if (Objects.equals(sPassw, rs.getString("password"))) {
                            String sName = rs.getString("name");
                            String user_id = rs.getString("userID");
                            //out2.println("<script> alert(\"Welcome again, "+sName+"!</script>\");");
                            HttpSession session = request.getSession();
                            session.setAttribute("user", sName);
                            session.setAttribute("userid", user_id);
                            if (Objects.equals(sName.toLowerCase(), "admin")) {
                                sendDataToDisplay(request, response,  "/loggedAdmin.jsp", null);
                            } else {
                                sendDataToDisplay(request, response, "/loggedUser.jsp", null);
                            }
                            break;
                        } else {
                            sendDataToDisplay(request, response,  "/loginPage.jsp", "Incorrect password. Please, try again.");
                            break;
                        }
                    }
                }
                if (exists == 0) {
                    sendDataToDisplay(request, response,  "/loginPage.jsp", "Incorrect password. Please, try again.");
                }
                st.close();
                con.close();
            }
        } catch (Exception e) {
            sendDataToDisplay(request, response,  "/loginPage.jsp", e.getMessage());
        }
    }

    private void sendDataToDisplay(HttpServletRequest request, HttpServletResponse response, String url, String error) {
        try {
            if (error != null) {
                request.setAttribute("error", error);
            }
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
            requestDispatcher.forward(request, response);
        } catch (Exception e) {
            out.println("Error");
        }
    }
}