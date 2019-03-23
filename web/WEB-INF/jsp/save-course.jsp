<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Save Course</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/save-course" method="post">
    <label>Type:
        <select name="type">
            <option>Full-Time</option>
            <option>Extramural</option>
            <option>Distance</option>
        </select>
        <%--<input type="text" name="type"/>--%>
    </label><br>
    <label>Name:
        <input type="text" name="name"/>
    </label><br>
    <label>StartDate:
        <input type="date" name="startdate"/>
    </label><br>
    <label>Duration:
        <input type="number" name="duration"/>
    </label><br>
    <label>Description:
        <input type="text" name="description"/>
    </label><br>
    <label>Plan:
        <input type="text" name="plan"/>
    </label><br>
    <input type="submit" value="SAVE"/>
</form>
</body>
</html>
