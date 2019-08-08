<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<fieldset>
		<legend>Input data to change</legend>
		<!-- post-запрос отправляется сервлету с url "settings" -->
		<form  method="post">
		
			<input type="hidden" name="id" value=${user.id}/>
			
			<!-- Поля для ввода имени -->
			<p>
				<label for="firstName">Your first name</label> 
				<input type="text"	id="firstName" name="firstName" value=${user.firstName} required placeholder="First name" />
			</p>

			<!-- Поля для ввода фамилии -->
			<p>
				<label for="lastName">Your last name</label> 
				<input type="text" id="lastName" name="lastName"  value=${user.lastName} required placeholder="Last name" />
			</p>

			<!-- Поля для ввода номера телефона -->
			<p>
				<label for="telephone">Your telephone number</label> 
				<input type="text" max=11 required id="telephone" name="telephone" value=${user.telephone}	placeholder="X-(XXX)-XXX-XX-XX" />
			</p>

			<!-- Поле для ввода логина -->
			<p>
				<label for="login">Input login</label> 
				<input type="text" id="login"	name="login" value=${user.login} required placeholder="login" />
			</p>

			<!-- Поле для ввода пароля -->
			<p>
				<label for="password">Input password</label>
				 <input type="password"	id="password" name="password" value=${user.password} required placeholder="password" />
			</p>

			<!-- Кнопка отправки результатов -->
			<input type="submit" value="Enter" />

		</form>
		<!-- Ссылка на удаление пользователя -->
		<p><a href='<c:url value="/deleteUser?id=${user.id}"/>'>Delete profile</a></p>

	</fieldset>

</body>
</html>