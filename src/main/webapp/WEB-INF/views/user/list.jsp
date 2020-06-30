<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<style>
	td,table,tr,th{border:1px solid #333; border-collapse:collapse; text-align: center;}
	#msg{color:red;}

</style>
<meta charset="UTF-8">
<title>회원 목록</title>
</head>
<body>
	<table>
		<tr>
			<th>회원번호</th>
			<th>아이디</th>
			<th>비밀번호</th>
			<th>이름</th>
			<th>성별</th>
			<th>이메일</th>
			<th>연락처</th>
			<th>회원관리</th>
		</tr>
		<c:forEach items="${list }" var="vo">
		<c:if test="${vo.gender eq 'M' }">
			<c:set var="gender" value="남자"></c:set>
		</c:if>
		<c:if test="${vo.gender eq 'F' }">
		<c:set var="gender" value="여자"></c:set>
		</c:if>
		<tr>
			<td>${vo.pk }</td>
			<td>${vo.id }</td>
			<td>${vo.pw }</td>
			<td>${vo.name }</td>
			<td>${gender }</td>
			<td>${vo.email }</td>
			<td>${vo.phone }</td>
			<td><button class="btn btn-primary" onclick="goDel(${vo.pk})">삭제</button></td>
			<td></td>
		</tr>
		</c:forEach>
		
		
	</table>
	<p id="msg">${msg }</p>
	<script>
		function goDel(pk){
			location.href="/user/del.do?pk="+pk;
		}
	
	
	</script>
	
	
</body>
</html>