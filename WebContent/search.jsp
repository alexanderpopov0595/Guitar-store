
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		
	<form  method="post">
		<input name="name" placeholder="Input guitar model"/>
		<input type="submit" value="Search"/>
		<input name="lowPrice"  placeholder="Input guitar's low price"/>	
		<input name="highPrice"  placeholder="Input guitar's high price"/>	
		<input list="guitarTypeList"   name="type" placeholder="Choose guitar's type"/>		
		<input type="radio" name="order" checked="true" value="higer">From higher price to lower
		<input type="radio" name="order"  value="lower">From lower price to higher
	</form>
	<!-- Кнопка сбрасывает настройки фильтра и возвращает на исходную страницу -->
	<p><a href='<c:url value="/welcome"/>'><button>"Clear filter"</button></a></p>
	
	<datalist id="guitarTypeList">
		<option value="Acoustic guitar"/>
		<option value="Acoustic bass guitar"/>
		<option value="Electric guitar"/>
		<option value="Electric bass guitar"/>
		<option value="Ukulele"/>
	</datalist>
</body>
</html>
