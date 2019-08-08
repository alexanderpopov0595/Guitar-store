<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${product.name}</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
	
	<!-- Таблица с товаром -->
	<table>
		<caption>${product.name}  <a href='<c:url value="/edit?id=${product.id}" />'>${edit}</a> </caption>
		<!-- Столбцы -->
		<tr><th>Image</th><th>Information</th></tr>
			<td><img src=${product.imagePath}></td>							
			<td>
				<div>
					<p>Seller: ${product.user.firstName} ${product.user.lastName}</p>
					<p>Seller's phone: ${product.user.telephone}</p>
					<p>Name: ${product.name}</p>
					<p>Price ${product.price}</p>
					<p>Type: ${product.type}</p>
					<p>Description</p>
					<p>${product.description}</p>	
				</div>
			</td>
	</table>	
<!-- ссылка на главную страницу -->
	<p><a href='<c:url value="/welcome"/>'>Main page</a></p>
</body>
</html>

<!-- Information ${message} -->
<!-- message= <a href='<c:url value="/edit?id=${user.id}" />'>${product.name}</a> -->
<!-- if user.id==product.user.id -->