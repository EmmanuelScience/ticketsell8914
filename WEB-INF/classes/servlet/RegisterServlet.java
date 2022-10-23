package servlet;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import javax.servlet.ServletContext;

import java.io.PrintWriter;
import java.sql.DriverManager;
        import java.sql.Connection;
        import java.sql.Statement;
        import java.sql.ResultSet;
import java.util.Objects;

/**
 * Servlet implementation class UserServlet
 */
public class RegisterServlet extends HttpServlet {


    ////////////////////////////////////////////////////////////////////////////////////////
    public void init() {

        // Lee del contexto de servlet (Sesi�n a nivel de aplicaci�n)
        ServletContext context = getServletContext();
    }


    ////////////////////////////////////////////////////////////////////////////////////////
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {


        // Establece el Content Type
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        out.println("<HTML>");
        out.println("<HEAD><TITLE>Register TicketSell</TITLE></HEAD>");
        //out.println("<BODY bgcolor=\"#ffff66\">");
        out.println("<H1>Register TicketSell</H1></BR>");
        out.println("<FORM METHOD=\"POST\" ACTION=\"" + "\">"); // Se llama as� mismo por POST
        out.println("<label for=\"name\">Name: </label>\n" +
                "    <input type=\"text\" id=\"name\" name=\"name\" value=\"\" /><br><br>\n" +
                "    <label for=\"surname\">Surname: </label>\n" +
                "    <input type=\"text\" id=\"surname\" name=\"surname\" value=\"\" /><br><br>\n" +
                "    <label for=\"alias\">Alias: </label>\n" +
                "    <input type=\"text\" id=\"alias\" name=\"alias\" value=\"\" /><br><br>" +
                "<label for=\"email\">Email address: </label>" +
                "<input type=\"text\" id=\"email\" name=\"email\"/><br><br>" +
                "<label for=\"password\">Password: </label>" +
                "<input type=\"password\" id=\"password\" name=\"password\"/><br><br>" +
                "<label for=\"phone\">Phone number: </label>\n" +
                        "    <input type=\"text\" id=\"phone\" name=\"phone\" value=\"\" /><br><br>\n" +
                        "    <label for=\"address\">Address: </label>\n" +
                        "    <input type=\"text\" id=\"address\" name=\"address\" value=\"\" /><br><br>"+
                "<input type=Submit value=\"Register\" />");

        out.println("</FORM>");
        out.println("</BODY></HTML>");

        out.close();
    }

    ////////////////////////////////////////////////////////////////////////////////////////
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

                String sName    = req.getParameter("name");
                String sSurname    = req.getParameter("surname");
                String sAddress    = req.getParameter("address");
                String sAlias    = req.getParameter("alias");
                String sPhone    = req.getParameter("phone");
                String sEmail    = req.getParameter("email");
                String sPassword   = req.getParameter("password");


                // 4.- Execute the queries
                st.executeUpdate("insert into Users(name , surname, address, alias, phone, email, password) value (\""+ sName + "\",\"" + sSurname + "\",\"" + sAddress+ "\",\"" + sAlias+ "\",\"" + sPhone+ "\",\"" + sEmail + "\",\"" + sPassword + "\");");
                ResultSet rs = st.executeQuery("SELECT * FROM users");

                // 6.- Close the statement and the connection
                st.close();
                con.close();
                out2.println("<h2>User logged succesfully. Welcome, " + sName + "!</h2><BR>");

            }
        } catch (Exception e) {
            //checking if error comes from already registered user
            String[] check = e.getMessage().split(" ");
            if (Objects.equals(check[0], "Duplicate") && Objects.equals(check[1], "entry")) {
                out2.println("<FONT color=\"#ff0000\">" + "<h2>User already registered. Please, log in</h2>" + "</FONT><BR>");
            } else { //another error
                out2.println("<FONT color=\"#ff0000\">" + e.getMessage() + "</FONT><BR>");
            }
        }

        }

    }


