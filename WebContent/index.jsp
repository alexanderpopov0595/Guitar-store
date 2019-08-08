<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Guitar Avito</title>

<!-- Подключение CSS -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
	
</head>
<body>

	<!-- Ссылки на вход в аккаунт и регистрацию -->
	<a href='<c:url value="/authorization" />'>Enter profile</a>
	<a href='<c:url value="/login" />'>Register profile</a>  
	

	<!-- Поле поиска товара по имени -->	
	<jsp:include page="search.jsp" />
	
	<p>${SearchResults}</p>

	<!-- Таблица с перечнем товаров -->	
	<table>
		<caption>Avaliable guitars </caption>
		<!-- Столбцы -->
		<tr><th>Image</th><th>Name</th><th>Price</th><th>Type</th></tr>
		
		<!-- Перечисление с помощью JSTL -->		
		<c:forEach var="product" items="${products}">
			<tr>		
				<!-- из параметров товара извлекается путь к изображению, затем загружается изображение -->	
				<td><img src=${product.imagePath}></td>			
				<!-- Имя товара выступает как ссылка на его личную страницу -->				
				<td><a href='<c:url value="/product?id=${product.id}" />'>${product.name}</a> </td>
				<td>${product.price}</td>
				<td>${product.type}</td>				
			</tr>
		</c:forEach>		
	</table>	
	
	
</body>
</html>