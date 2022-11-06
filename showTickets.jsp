<%@ page import="entities.Ticket" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entities.Event" %><%--
  Created by IntelliJ IDEA.
  User: emmao
  Date: 05/11/2022
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>

    <title>Title</title>
    <link rel="stylesheet" href="style/index_style.css">
    <link rel="stylesheet" href="style/header_footer.css">
    <link rel="stylesheet" href="style/logged_user.css">
    <link rel="stylesheet" href="style/adv_search.css">
</head>
<body>
<<<<<<< Updated upstream:showTickets.jsp
<%  @SuppressWarnings("unchecked")
    ArrayList<Ticket> tickets = (ArrayList<Ticket>) request.getAttribute("attributes");
=======
<header>
    <div class="logo_container">
        <img src="images/logo.png" alt="TicketSell logo">
    </div>
    <div class="main_title">
        <a href="index.html">ticketsell</a>
    </div></header>
<%  ArrayList<Ticket> tickets = (ArrayList<Ticket>) request.getAttribute("tickets");
>>>>>>> Stashed changes:ShowTickets.jsp
    Event event = (Event) request.getAttribute("event");

%>
<div class="main_title">
<h1>Event: <%=event.getEventName()%></h1>
</div>

<form class="sell_ticket" method="get" action="createTicket.html">
    <button type="submit" name ="sellTicket" value=<%=event.getId()%>>Sell ticket for this event</button>
</form>
<div class="main_title">
    <h1>Available Tickets</h1></div>
    <% if (request.getAttribute("error") != null) { %>
        <h2>There are no available tickets for this event</h2>
    <%} %>
    <form method="get"  action="buyTicket.html">
        <% for (Ticket ticket : tickets) { %>
            <button type="submit"
                    name=<%=ticket.getId()%>
                    value=<%=ticket.getId()%>>
                    <%="Ticket Code: " +
                        ticket.getTicketCode() + "&emsp;Category: "
                        + ticket.getCategory() + "&emsp;Price: "
                        + ticket.getPrice() + "&emsp;Ticket Owner "
                        + ticket.getTicketOwnerName()
                    %>

            </button>
            <br><br>
        <% } %>
    </form>


</body>
<footer>
    <div class="logo_container">
        <img src="images/logo.png" alt="TicketSell logo">
    </div>
    <div class="main_title">
        <a>ticketsell</a>
    </div>
</footer>
</html>
