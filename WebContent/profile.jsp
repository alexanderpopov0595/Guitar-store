<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Страница профиля -->
<!-- Вывод имени и фамилии пользователя  -->
<title>${user.firstName} ${user.lastName}</title>
<!-- Подключение CSS -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
	<!-- ссылка на главную страницу -->
	<p><a href='<c:url value="/welcome"/>'>Main page</a></p>
	<!-- ссылка, чтобы выйти из профиля -->
	<p><a href='<c:url value="/logout"/>'>Logout</a></p>
	<!-- Вывод приветствия -->
	<h2>Hello, ${user.firstName}</h2>
	<p>${message}</p>
	
	<!-- Таблица с товаром этого пользователя -->
	<table>
		<!-- Название таблицы -->
		<caption>Your guitars</caption>
		<!-- Шапка таблицы -->
		<tr><th>Image</th><th>Name</th><th>Price</th><th>Type</th></tr>
		<!-- Перечисление полученного списка товаров через JSTL -->
			
		<c:forEach var="product" items="${products}">
			<tr>
				<td><img src=${product.imagePath}></td>	
				<!-- Вывод имени+ ссылка на вывод полной информации о товаре -->
				<td><a href='<c:url value="/product?id=${product.id}"/>'>${product.name}</a></td>				
				<td>${product.price}</td>
				<td>${product.type}</td>
								
			</tr>
		</c:forEach>
		
	</table>
	
	<!-- Ссылка на добавление нового товара -->
	<p><a href='<c:url value="/create?id=${user.id}"/>'>Add new guitar</a></p>
	
	<!-- Ссылка на изменение информации о пользователе -->
	<p><a href='<c:url value="/settings?id=${user.id}"/>'>Edit profile settings</a></p>
	
	

</body>
</html>