<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화 리뷰 사이트</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="/resources/js/index.js"></script>
<script src="/resources/js/ajax.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet" href="/resources/css/reset.css">

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">
<link rel="stylesheet" href="/resources/css/index.css">

	
</head>
<body>

 <header>
       <div class="head">
        <h1 class="title">영화 리뷰 사이트</h1>
        <div class="headUser">
            <a href="/login.do">로그인</a>
            <a href="/join.do">회원가입</a>
        </div>
        <div class="logo">
            
            <a href="#"><img src="https://placehold.it/100x100" alt="로고"></a>
        </div>
       </div>
        
    </header>
       
    <aside class="bannerWrap">
            <div class="banner">
            <c:forEach items="${movieBest5 }" var="best">
            	<div class="bestWrap active">
            		<div class="bestItemWrap">
            		
            			<img src="${best.img }" class="bannerItems active" alt="${best.title }">
	            		
	            		
	            		<table class="bestItems">
	            			<tr>
	            				<th>평점</th>
	            				<th>투표자</th>
	            				<th>제목</th>
	            			</tr>
	            			<tr  height="120px" class="itemsDes"> 
	            				<td>${best.num }</td>
	            				<td>${best.num2 }</td>
	            				<td>${best.title }</td>
	            			</tr>
	            		</table>
	            		
	            		<div class="detailBtn">
	            			<button type="button" class="btn btn-dark">자세히</button>
	            			<button type="button" class="btn btn-dark">리뷰&댓글</button>
	            		</div>
            		</div>
            	</div>
            </c:forEach>
                
            </div>
            <div class="indicatorWrap">
                <div class="indicator active"></div>
                <div class="indicator"></div>
                <div class="indicator"></div>
                <div class="indicator"></div>
                <div class="indicator"></div>
            </div>
            <div class="arrowLeft">
                <i class="fas fa-arrow-left"></i>
            </div>
            <div class="arrowRight">
                <i class="fas fa-arrow-right"></i>
            </div>
    </aside>
        
    <h2 class="subTitle">현재 상영 영화</h2>

	<main>
		<div class="mainWrap">
			<table border="1">
				<tr>
					<th>제목</th>
					<th>영화</th>
					<th>평점</th>
					<th>투표자 수</th>
					<th>여러분의 의견을 적어주세요</th>
				</tr>
				<c:forEach items="${movieList}" var="vo">
					<tr class="movieItems" >
						<td>${vo.title }</td>
						<td><img src="${vo.img }"></td>
						<td>${vo.num }</td>
						<td>${vo.num2 }</td>
						<td><button type="button" class="btn btn-dark">자세히</button></td>
						<td><button type="button" class="btn btn-dark">리뷰&댓글</button></td>
					</tr>
					
				</c:forEach>
			</table>


		</div>


	</main>
	<input type="hidden" value="${pageMax }">
	<div class="movePageWrap">
	<div class="movePage">
		<span class="movePageLeft"><i class="fas fa-arrow-left"></i></span>	
		<c:forEach var="i" begin="1" end="5" step="1">
			<span class="pageNum">${i }</span>
		</c:forEach>
		<span class="movePageRight"><i class="fas fa-arrow-right"></i></span>
	</div>
	</div>
	
	<script>
/* 	$(function(){
		$(document).on('click', '.pageNum', function(){
		var pageNum = $(this).html();
			$.ajax({
				type:"POST",
				url:"/movie/board.do?start="+(pageNum-1)*10+"&end="+(pageNum)*10,
				dataType:"JSON",
				succes:function(data){
					console.log(data);			
				}
			})
		})
	}) */
	</script>




</body>
</html>