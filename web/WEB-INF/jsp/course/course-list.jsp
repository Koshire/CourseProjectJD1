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
<c:forEach items="${requestScope.allCourses}" var="course">
    <a href="#">Форма обучения:${course.type}, Название:${course.name}, Начинается:${course.startdate}, Длительность:${course.duration} </a><br>
    <a href="${pageContext.request.contextPath}/delete-course?id=${course.id}">Удалить</a>
    <a href="${pageContext.request.contextPath}/get-course?id=${course.id}">Подробнее</a>
    <a href="${pageContext.request.contextPath}/get-course?cid=${course.id}&uid=${sessionScope.courseUser.id}">Записаться</a><br><br>
</c:forEach>

</body>
</html>
