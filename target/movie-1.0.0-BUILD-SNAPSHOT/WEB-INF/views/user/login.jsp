<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<style>
.loginWrap{width:1000px; margin:0 auto;}
#msg{color:red;}
</style>
</head>
<body>

<div class="loginWrap">
	<form action="/user/login.do" method="post" >
		<input type="text" name="id" placeholder="id">
		<input type="password" name="pw" placeholder="pw">
		<button class="btn btn-primary">로그인</button>
	</form>
	<p id="msg">${msg }</p>
</div>

</body>
</html>