<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.temporal.TemporalAdjusters" %>
<%@ page import="java.time.DayOfWeek" %>
<%@ page import="java.lang.reflect.Array" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entities.Event" %><%--
  Created by IntelliJ IDEA.
  User: emmao
  Date: 05/11/2022
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Advance Event Search</title>
</head>
<body>
<%
    ArrayList<Event> events = (ArrayList<Event>) request.getAttribute("events");

    String date = "";
    try {
        boolean today = (boolean) (request.getAttribute("todayClicked"));
        boolean weekend = (boolean) (request.getAttribute("weekendClicked"));
        if (today) {
            date = LocalDate.now().toString();
        } else if (weekend) {
            LocalDate weekendDate = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
            date = weekendDate.toString();
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
%>
    <form method="get"  action="browse.html">
        <label>
            <input name="eventVenue" placeholder=" Enter Venue">
            <input type="date" name="eventDate"  value=<%=date%>>
            <input name="eventCity" placeholder=" Enter City">
            <select name="eventCategory" id="cars">
                <option value="Concert">Concert</option>
                <option value="Musical">Musical</option>
                <option value="Park">Park</option>
                <option value="Festival">Festival</option>
            </select>
            <input name="eventName" placeholder=" Enter Event Name">
        </label>
    <input type="submit" value="Search">
    </form>

    <h1>Events</h1>
    <% if (request.getAttribute("error") != null) { %>
        <h2>There are no available events for this search</h2>
    <% } %>

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
        <br><br>
        <% } %>
    </form>

</body>
</html>
