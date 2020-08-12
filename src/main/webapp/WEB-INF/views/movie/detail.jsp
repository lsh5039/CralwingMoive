<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.contentBox{width:1000px; height:200px; margin: 0 auto; overflow:auto;}
.mainImg{text-align:center;}
.mainImg > img{width:50%; height:50%;}
.detailTitle{font-size:48px; font-weight:bold; text-align:center}	
.smallTitle{font-size:36px; text-align:center;}
</style>
</head>
<body>
	<h1 class="detailTitle">${one.title }</h1>
	<input type="hidden" value="${one.pk }">
	<div class="mainImg"><img src="${largeImg }"><br></div>
	
	<p class="smallTitle">${title }</p>
	<div  class="contentBox">
		줄거리 : ${content }
	</div>


</body>
</html>