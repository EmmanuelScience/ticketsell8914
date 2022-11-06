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
    <link rel="stylesheet" href="style/index_style.css.css">
    <link rel="stylesheet" href="style/header_footer.css">
    <link rel="stylesheet" href="style/logged_user.css">
    <link rel="stylesheet" href="style/adv_search.css">
</head>
<body>

<header>
    <div class="logo_container">
        <img src="images/logo.png" alt="TicketSell logo">
    </div>
    <div class="ticketsell_title">
        <a href="index.html">ticketsell</a>
    </div></header>
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
<div class="main_title">
    <h1>Advanced search</h1>
</div>
<form method="get" action="browse.html">
    <label>Event&ensp;<input name="eventName" placeholder=" Enter Event Name"></label>
    <label>Venue&ensp;<input name="eventVenue" placeholder=" Enter Venue"></label>
    <label>Date <input type="date" name="eventDate" value="2022-11-06"></label>
    <label>City&emsp;<input name="eventCity" placeholder=" Enter City"></label>
    <label>Category<select name="eventCategory" id="cars">
        <option value="Concert">Concert</option>
        <option value="Musical">Musical</option>
        <option value="Park">Park</option>
        <option value="Festival">Festival</option>
    </select></label>

    <input type="submit" value="Search">
</form>

<div class="main_title"><h1>Events</h1></div>

<% if (request.getAttribute("error") != null) { %>
<h1>There are no available events for this search</h1>
<% } %>

<<form method="get"  action="tickets.html">
    <% for (Event event : events) { %>
    <button type="submit"
            name="eventID"
            value=<%=event.getId()%>>
        <%="Event: " +
            event.getEventName() + "&emsp;Category: "
                    + event.getCategory() + "&emsp;Date: "
                    + event.getDate() + "&emsp;Venue: "
                    + event.getVenue() + "&emsp;City: "
                    + event.getCity()
        %>
    </button>
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
