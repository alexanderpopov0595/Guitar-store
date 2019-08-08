<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- Область для ввода логина и пароля -->
	<fieldset>		
		<legend>Input data</legend>
		<!-- post-запрос отправляется сервлету с url "authorization" -->
		<form action="authorization" method="post">
		
		<!-- В случае неверного ввода логина\пароля будет выведено сообщение об ошибке -->
		<p>${message}</p>
		
		<!-- Поле для ввода логина -->
		<p>
			<label for="login">Input login</label>
			<input type="text"  id="login" name="login" placeholder="login"/>
		</p>
		
		<!-- Поле для ввода пароля -->
		<p>
			<label for="password">Input password</label>
			<input type="password" id="password" name="password" placeholder="password"/>
		</p>
		
		<!-- Кнопка отправки результатов -->
			<input type="submit" value="Enter"/>
			
		</form>
		<!-- Ссылка для регистрации -->
		<a href='<c:url value="/login" />'>Login</a>  
		
	</fieldset>

</body>
</html>