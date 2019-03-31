<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Course-List</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>
<body>
<%@ include file="../support/menu.jsp" %>
<br>
<br>
<p>${requestScope.course.type}</p>
<p>${requestScope.course.name}</p>
<p>${requestScope.course.startdate}</p>
<p>${requestScope.course.duration}</p>
<p>${requestScope.course.description}</p>
<p>${requestScope.course.plan}</p>
</body>
</html>
