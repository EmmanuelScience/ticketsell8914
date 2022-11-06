<%--
  Created by IntelliJ IDEA.
  User: cosmin
  Date: 11/6/22
  Time: 12:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String errMsg = (String) request.getAttribute("error");
%>
<h1><%= errMsg%></h1>
</body>
</html>
