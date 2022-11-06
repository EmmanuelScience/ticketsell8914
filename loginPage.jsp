<%--
  Created by IntelliJ IDEA.
  User: emmao
  Date: 06/11/2022
  Time: 13:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<HEAD>
  <TITLE>Login in ticketsell</TITLE>
  <link rel="stylesheet" href="style/register_login.css">
  <link rel="stylesheet" href="style/header_footer.css">
</HEAD>
<BODY>
<!-- header -->
  <header>
    <div class="logo_container">
      <img src="images/logo.png" alt="TicketSell logo">
    </div>
    <div class="main_title">
      <h1>ticketsell</h1>
    </div>
    <!-- SIGN IN AND LOG IN BUTTONS -->
    <div class="buttons_container">
      <button class="signup_button"  onclick="window.location.href='register.html';">Sign up</button>
      <button class="login_button" onclick="window.location.href='login.html';">Log in</button>
    </div>
    <!-- REGISTERED HEADER CHANGES -->
    <div class="registered_container">
      <img id="messages" src="images/icons/dm.png" alt="send a message" />
      <div id="profile_info_div">
        <img id="user-profile-pic" src="images/avatars/default.png" alt="User's profile picture" />
        <label id="username-label"></label>
      </div>
      <button class="logout_button">Log out</button>
    </div>
  </header>
  <H1 id="page_title">Login TicketSell</H1></BR>
  <% if (request.getAttribute("error") != null) {
    out.println("<h2 style='color:red'>" + request.getAttribute("error") + "</h2>");
  } %>
  <FORM METHOD="POST" ACTION="login.html">
    <label for="email">Email address: </label>
    <input type="text" id="email" name="email"/><br><br>
    <label for="password">Password: </label>
    <input type="password" id="password" name="password"/><br><br>
    <input type=Submit value="Login" />

  </FORM>
</BODY>
<!-- Footer -->
<footer>
  <div class="logo_container">
    <img src="images/logo.png" alt="TicketSell logo">
  </div>
  <div class="main_title">
    <h1>ticketsell</h1>
  </div>
</footer>
</html>
