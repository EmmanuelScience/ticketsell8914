<!DOCTYPE html>
<html lang="en">
<head>
    <title>Events TicketSell</title>
    <link rel="stylesheet" href="style/register_login.css">
    <link rel="stylesheet" href="style/header_footer.css">
    <link rel="stylesheet" href="style/logged_user.css">
</head>

<body>
<header>
    <div class="logo_container">
        <img src="images/logo.png" alt="TicketSell logo">
    </div>
    <div class="main_title">
                <a href="loggedUser.jsp">ticketsell</a>
    </div>
    <!-- REGISTERED HEADER  -->

    <div class="registered_container">
        <img id="messages" src="images/icons/dm.png" alt="send a message">
        <div id="profile_info_div">
            <img id="user-profile-pic" src="images/avatars/default.png" alt="User's profile picture">
            <%HttpSession sesion=request.getSession();%>
            <%String user = (String)sesion.getAttribute("user");%>
            <label id="username-label"><%out.println(user);%></label>
        </div>
        <button class="logout_button" onclick="window.location.href='index.html'">Log out</button>
    </div>
</header>
<div class="create_ticket">
    <button onclick="window.location.href='registerTicket.html';">Click here to publish a ticket</button>
</div>
añadir aqui search y eventos
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