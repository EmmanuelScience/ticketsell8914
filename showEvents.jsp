<%@ page import="entities.Event" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: emmao
  Date: 16/10/2022
  Time: 12:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.Base64" %>
<html>
<head>
    <title> Events </title>
</head>
<body>
    <%
        ArrayList<Event> events = (ArrayList<Event>) request.getAttribute("events");
    %>
    <h1>Events</h1>
    <% if (request.getAttribute("error") != null) { %>
        <h2>There are no available events for this search</h2>
    <% } %>

    <form method="get"  action="tickets.html">
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
                <img style="height: 50px;" src="<% StringBuilder sb = new StringBuilder();
						sb.append("data:image/png;base64,");
						sb.append(Base64.getEncoder().encodeToString(event.getImage()));
						out.print(sb.toString()); %>">
            </button>
            <br><br>
        <% } %>
    </form>
</body>
</html>
