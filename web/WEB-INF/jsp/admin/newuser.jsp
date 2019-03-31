<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="ru">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Новый пользователь">

    <title>Новый пользователь</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	<style>
	 select, input, span[type="text"]{
      width:40%;
      box-sizing:border-box;
    }
	</style>
	
	</head>
<body>
	<div>
		<p><a href="#">Главная</a></p>
		<p><a href="#">Профиль</a></p>
		<p><a href="#">Список курсов</a></p>
		<p><a href="#">Контакты</a></p>
	</div>
	
	<div>
	<form method="post">
			<label for="role">Роль: </label>
			<select name="role">
				<c:forEach items="${requestScope.roles}" var="role">
					<option value="${role.id}">${role.name}</option>
				</c:forEach>
			</select><br>
			<label for="firstName">Имя: </label>
			<input type="text" name="firstName" VALUE="Имя"><br>
			<label for="middleName">Отчество: </label>
			<input type="text" name="middleName" VALUE="Отчество"><br>
			<label for="lastName">Фамилия: </label>
			<input type="text" name="lastName" VALUE="Фамилия"><br>
			<label for="email">E-mail: </label>
			<input type="text" name="email" VALUE="kuku@gmail.com"><br>
			<label for="phone">Телефон: </label>
			<input type="tel" name="phone" VALUE="Телефон"><br>
			<label for="password">Пароль: </label>
			<input type="password" name="passphrase" VALUE="Пароль"><br>
			<button formaction="/new-cource-user">Добавить</button><br>
	</form>
	</div>	
</body>
</html>
