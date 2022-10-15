<%@ page import="entities.Songsdb" %><%--
  Created by IntelliJ IDEA.
  User: emmao
  Date: 14/10/2022
  Time: 16:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Song Data</title>
</head>
<body>
    <% Songsdb songsdb = (Songsdb) request.getAttribute("songBean"); %>
    <form method="post"  action="doUpdate.html">
        Id: <input name="id" value="<%= songsdb.getId()%>">
        Artist: <input name="artist" value="<%= songsdb.getArtist()%>">
        Name: <input name="name" value="<%= songsdb.getSongName()%>">
        Duration: <input name="duration" value="<%= songsdb.getDuration()%>">
        Score: <input name="score" value="<%= songsdb.getScore()%>">
        <input type="submit" value="Save"/>
    </form>

</body>
</html>
