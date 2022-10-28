<%@ page import="entities.Event" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: emmao
  Date: 16/10/2022
  Time: 12:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> Events </title>
</head>
<body>
<% ArrayList<Event> event = (ArrayList<Event>) request.getAttribute("events");
%>
<form method="post"  action="doUpdate.html">
    <% %>
    Id: <input name="id" value="<%= event.getId()%>">
    Name: <input name="eventName" value="<%= event.getEventName()%>">
    City: <input name="city" value="<%= event.getCity()%>">
    Country: <input name="country" value="<%= event.getCountry()%>">
    Category: <input name="category" value="<%= event.getCategory()%>">
    Date: <input name="date" value="<%= event.getDate()%>">
    <input type="submit" value="Save"/>
</form>
</body>
</html>
