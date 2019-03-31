
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Global</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>
<body>
<%@ include file="support/menu.jsp" %>
<br>
<br>
    <P>Hello World ! ! !</P>
    <p>${sessionScope.courseUser.firstName}</p>
    <p>${sessionScope.courseUser.middleName}</p>
    <p>${sessionScope.courseUser.lastName}</p>
    <p>${sessionScope.courseUser.email}</p>
    <p>${sessionScope.courseUser.phone}</p>
    <p>${sessionScope.courseUser.lang}</p>
    <p>${sessionScope.courseUser.id}</p>
</body>
</html>
