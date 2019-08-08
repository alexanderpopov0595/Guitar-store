<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<body>
	 
	<!-- Область для ввода данных -->
	<fieldset>		
		<legend>Input data</legend>
		<!-- post-запрос отправляется сервлету с url "login" -->
		<form action="login" method="post">
		
		<!-- В случае неверного ввода логина\пароля будет выведено сообщение об ошибке -->
		<p>${message}</p>
		
		<!-- Поля для ввода имени -->
		<p>
			<label for="firstName">Your first name</label>
			<input type="text" id="firstName" name="firstName" required placeholder="First name"/>
		</p>
		
		<!-- Поля для ввода фамилии -->
		<p>
			<label for="lastName">Your last name</label>
			<input type="text" id="lastName" name="lastName" required placeholder="Last name"/>
		</p>
		
		<!-- Поля для ввода номера телефона -->
		<p>
			<label for="telephone">Your telephone number</label>
			<input type="text" max=11 required id="telephone"  name="telephone" placeholder="X-(XXX)-XXX-XX-XX"/>
		</p>		
		
		<!-- Поле для ввода логина -->
		<p>
			<label for="login">Input login</label>
			<input type="text"  id="login" name="login" required placeholder="login"/>
		</p>
		
		<!-- Поле для ввода пароля -->
		<p>
			<label for="password">Input password</label>
			<input type="password" id="password" name="password" required placeholder="password"/>
		</p>
		
		<!-- Кнопка отправки результатов -->
			<input type="submit" value="Enter"/>
			
		</form>
		
	</fieldset>
	 
</body>
</html>