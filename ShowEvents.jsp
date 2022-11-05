<%@ page import="entities.Event" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: emmao
  Date: 16/10/2022
  Time: 12:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title> Events </title>
</head>
<body>
<% ArrayList<Event> events = (ArrayList<Event>) request.getAttribute("events");
%>
<form method="get"  action="event/tickets.html">
    <% for (Event event : events) { %>
        <button type="submit"
            name="eventID"
            value=<%=event.getId()%>>
            <%=
                event.getEventName() + " "
                + event.getCategory() + ""
                + event.getDate() + " "
                + event.getVenue() + " "
                + event.getCity() + " "
            %>
        </button>
        <br>
    <% } %>
</form>
</body>
</html>
