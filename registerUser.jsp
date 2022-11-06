<%--
  Created by IntelliJ IDEA.
  User: cosmin
  Date: 11/6/22
  Time: 1:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<HTML>
<HEAD><TITLE>Register TicketSell</TITLE>
<link rel="stylesheet" href="style/register_login.css">
<link rel="stylesheet" href="style/header_footer.css"></HEAD>
<header>
        <div class="logo_container">
                <img src="images/logo.png" alt="TicketSell logo">
            </div>
        <div class="main_title">
                <a href="index.html">ticketsell</a>
            </div>
        <!-- SIGN IN AND LOG IN BUTTONS -->
        <div class="buttons_container">
                <button class="signup_button"  onclick="window.location.href='registerUser.html';">Sign up</button>
                <button class="login_button" onclick="window.location.href='login.html';">Log in</button>
            </div></header>
<BODY>
<H1 id="page_title">Register TicketSell</H1></BR>

<% if (request.getAttribute("error") != null) { %>
<h2 id="page_title"><%= request.getAttribute("error")%></h2>
<% } %>

<FORM METHOD="POST" ACTION="">
<label for="name">Name: </label>
    <input type="text" id="name" name="name" value="" /><br><br>
    <label for="surname">Surname: </label>
    <input type="text" id="surname" name="surname" value="" /><br><br>
    <label for="alias">Alias: </label>
    <input type="text" id="alias" name="alias" value="" /><br><br>
<label for="email">Email address: </label>
<input type="text" id="email" name="email"/><br><br>
<label for="password">Password: </label>
<input type="password" id="password" name="password"/><br><br>
<label for="phone">Phone number: </label>
    <input type="text" id="phone" name="phone" value="" /><br><br>
    <label for="address">Address: </label>
    <input type="text" id="address" name="address" value="" /><br><br>
<input type=Submit value="Register" />

</FORM></BODY>
<footer>
    <div class="logo_container">"
            <img src="images/logo.png" alt="TicketSell logo">
        </div>
    <div class="main_title">
            <a>ticketsell</a>
        </div>
</footer>
</HTML>
