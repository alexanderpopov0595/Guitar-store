<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
	<h2>Edit guitar</h2>
	
	<form method="post" enctype="multipart/form-data">	
	<!-- Default vales -->
		<input type="hidden" value="${product.id}" name="id"/>
		<input type="hidden" value="${user.id}" name="user_id"/>
		<input name="name" value="${product.name}"/><br><br>
		<input name="price" value="${product.price}"/><br><br>
		<input name="type" value="${product.type}"/><br><br>
		<input name="description" value="${product.description}" /><br><br>	
		<input type="hidden" name="imagePath" value="${product.imagePath}"/>
		<p>
			<img src=${product.imagePath}>	
			<input type="file" name="image" id="image"/><br><br>
		</p>
	
		<input type="submit" value="Send" />
			
	</form>
	<!-- Ссылка на удаление товара -->
	<p><a href='<c:url value="/deleteProduct?id=${product.id}"/>'>Delete product</a></p>

</body>
</html>