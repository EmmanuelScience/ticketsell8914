<%--
  Created by IntelliJ IDEA.
  User: emmao
  Date: 05/11/2022
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Advance Event Search</title>
</head>
<body>
<%
    String dateHolder = "Date";
    try {
        boolean today = (boolean) (request.getAttribute("todayClicked"));
        boolean weekend = (boolean) (request.getAttribute("weekendClicked"));
        if (today) {
            dateHolder = "Today";
        } else if (weekend) {
            dateHolder = "Weekend";
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
%>
    <form method="post"  action="advSearch.html">
        <input name="eventVenue" placeholder=" Enter Venue">
        <input type="date" name="eventDate" placeholder=<%=dateHolder%>>
        <input name="eventCity" placeholder=" Enter City">
        <input name="eventCategory" placeholder=" Enter Category">
        <input name="eventName" placeholder=" Enter Event Name">
    <input type="submit" value="Search">
    </form>

</body>
</html>
