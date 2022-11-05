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
</head>
<body>
<%  ArrayList<Ticket> tickets = (ArrayList<Ticket>) request.getAttribute("tickets");
    Event event = (Event) request.getAttribute("event");
%>
<h1>Event: <%=event.getEventName()%></h1>

<form method="post" action="createTicket.html">
    <button type="submit" name ="sellTicket" value=<%=event.getId()%>>Sell Ticket</button>
</form>

<h2>Available Tickets</h2>
    <% if (request.getAttribute("error") != null) { %>
        <h2>There are no available tickets for this event</h2>
    <%} %>
    <form method="post"  action=buyTicket.html">
        <% for (Ticket ticket : tickets) { %>
            <button type="submit"
                    name=<%=ticket.getId()%>
                    value=<%=ticket.getId()%>>
                    <%=
                        ticket.getPrice() + " "
                        + ticket.getCategory() + " "
                        + ticket.getPrice() + " "
                        + ticket.getTicketOwnerName()
                    %>
            </button>
            <br><br>
        <% } %>
    </form>


</body>
</html>
