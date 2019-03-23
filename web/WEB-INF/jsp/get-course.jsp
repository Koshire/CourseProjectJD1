<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Course by ID</title>
</head>
<body>
<div>
    <p>${requestScope.course.type}</p>
    <p>${requestScope.course.name}</p>
    <p>${requestScope.course.startdate}</p>
    <p>${requestScope.course.duration}</p>
    <p>${requestScope.course.description}</p>
    <p>${requestScope.course.plan}</p>
</div>
</body>
</html>
