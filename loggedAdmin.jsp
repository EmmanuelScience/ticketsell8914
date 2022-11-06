<!DOCTYPE html>
<html lang="en">
<head>
  <title>Admin view TicketSell</title>
  <link rel="stylesheet" href="style/header_footer.css">
  <link rel="stylesheet" href="style/logged_admin.css">
</head>

<body>
<header>
  <div class="logo_container">
    <img src="images/logo.png" alt="TicketSell logo">
  </div>
  <div class="main_title">
    <a href="loggedAdmin.jsp">ticketsell</a>
  </div>
  <!-- REGISTERED HEADER  -->

  <div class="registered_container">
    <div id="profile_info_div">
      <img id="user-profile-pic" src="images/avatars/Admin.png" alt="User's profile picture">
      <label id="username-label">Admin</label>
    </div>
    <button class="logout_button" onclick="window.location.href='index.html'">Log out</button>
  </div>
</header>
<h1 id="page_title">ADMINISTER EVENTS IN TICKETSELL</h1><br>
<div class="create_event"><H2>Create an event:</H2>
  <button onclick="window.location.href='registerEvent.html';">Click here to create an event</button>
</div>
<% if (request.getAttribute("error") != null) {%>
<script>alert("Error while introducing data")</script>
<%} %>

<H2>Modify an event:</H2>
<form class="admin_modify" METHOD="post" ACTION="modifyEvent.html">
  <label>Type here the event to modify:
    <input name="id_mod" placeholder="id of the event" /></label>
  <label>Select the field to modify:
    <select id="modifying" name="modifying">Click to see the options...
      <option name="modifying" value="Def"></option>
      <option name="eventname" value="eventname">Event name</option>
      <option name="venue" value="venue">Venue</option>
      <option name="city" value="city">City</option>
      <option name="country" value="country">Country</option>
      <option name="date" value="date">Date</option>
      <option name="category" value="category">Category</option>
    </select></label>
  <label>Type here the new value of the field:
    <input name="info" value="" />
  </label>
  <input type=Submit value="Modify Event" />
</form>
<H2>Delete an event:</H2>
<form class="delete_form" METHOD="post" ACTION="deleteEvent.html">
  <label>Type here the event to delete:
    <input name="id_del" placeholder="id of the event" /></label>
  <input type=Submit value="Delete Event" />
</form>
<footer>
  <div class="logo_container">
    <img src="images/logo.png" alt="TicketSell logo">
  </div>
  <div class="main_title">
    <a>ticketsell</a>
  </div>
</footer>
</body>
</html>