<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.mainImg{text-align:center;}
.mainImg > img{width:50%; height:50%;}	
</style>
</head>
<body>
	<div class="mainImg"><img src="${largeImg }"><br></div>
	detail page<br>
	${one.pk }
	${one.title }<br>
	소제목 : ${title }<br>
	줄거리 : ${content }<br>


</body>
</html>