<%--
  Created by IntelliJ IDEA.
  User: emmao
  Date: 06/11/2022
  Time: 14:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html><head><title>Register Event in TicketSell</title>
    <link rel="stylesheet" href="style/register_login.css"><link rel="stylesheet" href="style/header_footer.css"></head>
<link rel="stylesheet" href="style/header_footer.css"><link rel="stylesheet" href="style/header_footer.css"></head>
<%
Integer id = Integer.parseInt(request.getAttribute("attributes").toString());
%>
<body><header>
    <div class="logo_container">
        <img src="images/logo.png" alt="TicketSell logo">
    </div>
    <div class="main_title">
        <a href="loggedUser.html">ticketsell</a>
    </div>
    <!-- REGISTERED HEADER  -->
    <div class="registered_container">
        <img id="messages" src="images/icons/dm.png" alt="send a message">
        <div id="profile_info_div">
            <img id="user-profile-pic" src="images/avatars/default.png" alt="User's profile picture">
            <label id="username-label"></label>
        </div>
        <button class="logout_button" onclick="window.location.href='loggedAdmin.jsp'">Log out</button>
    </div>
</header>
<h1 id="page_title">Register Ticket in TicketSell</h1><br>
<form method="POST" action="createTicket.html">
    <label for="ticketcode">Ticket code: </label>
    <input type="text" id="ticketcode" name="ticketcode" value=""><br><br>
    <label for="category">Category: </label>
    <input type="text" id="category" name="category" value=""><br><br>
    <label for="price">Price: </label>
    <input type="text" id="price" name="price" value=""><br><br>
    <input type="hidden" id="event" name="event" value=<%=id%>><br><br>

    <input type="Submit" value="Register Ticket">
</form>

<footer>
    <div class="logo_container">
        <img src="images/logo.png" alt="TicketSell logo">
    </div>
    <div class="main_title">
        <a>ticketsell</a>
    </div>
</footer>
</body></html>