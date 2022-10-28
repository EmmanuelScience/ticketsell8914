<%--
  Created by IntelliJ IDEA.
  User: emmao
  Date: 19/10/2022
  Time: 13:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        .button {
            border: none;
            color: white;
            padding: 16px 32px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            transition-duration: 0.4s;
            cursor: pointer;
        }

        .button1 {
            background-color: white;
            color: black;
            border: 2px solid #4CAF50;
        }

        .button1:hover {
            background-color: #4CAF50;
            color: white;
        }

        .button2 {
            background-color: white;
            color: black;
            border: 2px solid #008CBA;
        }

        .button2:hover {
            background-color: #008CBA;
            color: white;
        }

        .button3 {
            background-color: white;
            color: black;
            border: 2px solid #008CBA;
        }

        .button3:focus {
            background-color: #008CBA;
            color: white;
        }
        .button3:visited {
            background-color: white;
            color: white;
        }



    </style>
</head>
<body>
<% boolean buttonAClicked = false;
session.setAttribute("buttonAClicked", buttonAClicked);
%>
<form METHOD="get" ACTION="search.html">

    Event: <input name="id" value="" />
    <input type=Submit value="Search Event" />

    <input type="submit" name="button_a_clicked" value="ButtonA" class="<%= (Boolean) session.getAttribute("buttonAClicked") ? "button button1" : "button button2" %>" />
    <input type="button" name="button_b_clicked" value="ButtonB" class="button button2"/>
    <input type="button" name="button_c_clicked" value="ButtonC" class="button button3"/>

</form>
</body>
</html>
