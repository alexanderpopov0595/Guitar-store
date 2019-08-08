<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2>Add new guitar</h2>
	<!-- Форма для записи параметров добавляемой гитары -->
	<form method="post" enctype="multipart/form-data">	
		
		<!-- Метка для поля "Модель" -->
		<label for="name">Model</label>
		<p><input  name="name"  id="name" required  placeholder="Input guitar's model"/></p>
		
		<!-- Метка для поля "Цена" -->
		<label for="price" >Price</label>
		<p><input  name="price" " id="price"  required placeholder="Input guitar's price"/></p>
		
		<!-- Метка для поля ввода типа гитары-->
		<label for="type">Guitar's type</label>
		<!-- Само поле ввода представляет собой список -->
		<p><input list="guitarTypeList"  required name="type" placeholder="Choose guitar's type"/></p>	
		
		<!-- Метка для описания-->
		
		<label for="description">Guitar's description</label>
		<p><textarea name="description" id="description" max=200 placeholder="Input guitar's description (200 symbols)"></textarea></p>
		
		<!-- Мета для загрузка изображения гитары -->
		<label for="image">Guitar's image</label>
		<p><input type="file" name="image" id="image"/></p>		
		
		<!-- Кнопки отправки и отмены ввода -->
		<p>
			<input type="submit" value="Add guitar" />
			<button type="reset">Cancel</button>
		</p>
	</form>
	
	<!-- Лист с перечислением типов гитар -->
	<datalist id="guitarTypeList">
		<option value="Acoustic guitar"/>
		<option value="Acoustic bass guitar"/>
		<option value="Electric guitar"/>
		<option value="Electric bass guitar"/>
		<option value="Ukulele"/>
	</datalist>
	
</body>
</html>