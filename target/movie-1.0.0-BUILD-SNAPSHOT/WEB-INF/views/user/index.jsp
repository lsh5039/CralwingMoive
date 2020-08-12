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
<link href="https://fonts.googleapis.com/css2?family=Lemonada:wght@700&family=Passion+One:wght@700;900&display=swap" rel="stylesheet">
<!--font-family: 'Lemonada', cursive;  -->
	
</head>
<body>

 <header>
       <div class="head">
        <h1 class="title">Movie Comment</h1>
        <div class="headUser">
        <c:if test="${loginUser.name  ne null }">
        	<a href="/login.do">로그아웃</a>
            <a href="/join.do">메시지</a>
        </c:if>
        <c:if test="${loginUser.name eq null }">
        	<a href="/user/login.do">로그인</a>
            <a href="/join.do">회원가입</a>
        </c:if>
            
        </div>
        <div class="logo">
            <a href="#"><img src="/resources/img/logo.jpg" alt="로고" id="logoImg"></a>
        </div>
       </div>
       
       <div class="myInfoWrap">
            	<div class="profile">
            		<img src="/resources/img/noProfile.jpg">
            	</div>
            	<p class="nickName">${loginUser.name }</p>
            	<c:if test="${loginUser.name eq null }">
            		<p class="nickName">비회원</p>
            	</c:if>
            	<c:if test="${loginUser.name ne null }">
            		<p class="nickName">${loginUser.name }</p>
            	</c:if>
            	<div class="infoBox">
            		<button class="btn btn-primary">&nbsp 내 정보 &nbsp</button>
            		<button class="btn btn-primary">프로필변경</button>
            	</div>
       </div> 
    
 </header>
 		<!-- <form method="post" action="/user/profile.do">
       		<input type="file">
       		<input type="submit" value="업로드">
       </form>
  -->      
    <aside>
    	<h2 class="bannerTitle">TOP FIVE</h2>
            <div class="bannerWrap">
            
            
            
            <c:forEach items="${movieBest5 }" var="best"  varStatus="idx">
          	
            	<div class="bestWrap active">
            		<div class="bestItemWrap">
            	
            			<c:choose>
            				<c:when test="${idx.index == 0 }">
            					<img src="${mainImg[0]}" class="bannerItems active" alt="${best.title }">
            					<div class="detailBtn">
			            			<button type="button" class="btn btn-warning mainBtn" onclick="goDetail(${best.pk})">&nbsp 자세히 &nbsp</button>
			            			<button type="button" class="btn btn-warning mainBtn">리뷰&댓글</button>
			            		</div>	
            				</c:when>
            				<c:when test="${idx.index == 1 }">
            					<img src="${mainImg[1]}" class="bannerItems active" alt="${best.title }">
            					<div class="detailBtn">
			            			<button type="button" class="btn btn-warning mainBtn" onclick="goDetail(${best.pk})">&nbsp 자세히 &nbsp</button>
			            			<button type="button" class="btn btn-warning mainBtn">리뷰&댓글</button>
			            		</div>	
            				</c:when>
            				<c:when test="${idx.index == 2 }">
            					<img src="${mainImg[2]}" class="bannerItems active" alt="${best.title }">
            					<div class="detailBtn">
			            			<button type="button" class="btn btn-warning mainBtn" onclick="goDetail(${best.pk})">&nbsp 자세히 &nbsp</button>
			            			<button type="button" class="btn btn-warning mainBtn">리뷰&댓글</button>
			            		</div>	
            				</c:when>
            				<c:when test="${idx.index == 3 }">
            					<img src="${mainImg[3]}" class="bannerItems active" alt="${best.title }">
            					<div class="detailBtn">
			            			<button type="button" class="btn btn-warning mainBtn" onclick="goDetail(${best.pk})">&nbsp 자세히 &nbsp</button>
			            			<button type="button" class="btn btn-warning mainBtn">리뷰&댓글</button>
			            		</div>	
            				</c:when>
            				<c:when test="${idx.index == 4 }">
            					<img src="${mainImg[4]}" class="bannerItems active" alt="${best.title }">
            					<div class="detailBtn">
			            			<button type="button" class="btn btn-warning mainBtn" onclick="goDetail(${best.pk})">&nbsp 자세히 &nbsp</button>
			            			<button type="button" class="btn btn-warning mainBtn">리뷰&댓글</button>
			            		</div>	
            				</c:when>
            			
            			
            			</c:choose>
            			
            			
	            		
	            		
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
		<div class="menuWrap">
			<button class="btn btn-primary" onclick="rateDesc()">평점순</button>
			<button class="btn btn-primary" onclick="voteDesc()">투표순</button>
		</div>
	<main>
	<%-- 	<div class="mainWrap">
			<table border="1" class="tables">
				<tr class="tableTh">
					<th>제목</th>
					<th>영화</th>
					<th>평점</th>
					<th>투표자 수</th>
					<th>여러분의 의견을 적어주세요</th>
				</tr>
				<c:forEach items="${movieList}" var="vo">
					<tr class="movieItems" >
						<td class="itemTitle">${vo.title }</td>
						<td><img src="${vo.img }"></td>
						<td>${vo.num }</td>
						<td>${vo.num2 }</td>
						<td><button type="button" class="btn btn-dark">자세히</button></td>
						<td><button type="button" class="btn btn-dark">리뷰&댓글</button></td>
					</tr>
					
				</c:forEach>
			</table>
		</div> --%>
		
		<div class="itemBoxWrap">
			<div class="itemBoxTop">
				<p>제목</p>
				<p>썸네일</p>
				<p>평점</p>
				<p>투표자 수</p>
				<p>여러분의 의견을 공유해주세요~!</p>
			</div>
		
		</div>
		<c:forEach items="${movieList}" var="vo">
		<div class="itemBoxDesc">
		
	
			<p class="descTitle">${vo.title }</p>
			<p class="descThum"><img src="${vo.img }"></p>
			<p class="descNum1">${vo.num }</p>
			<p class="descNum2">${vo.num2 }</p>
			<div class="descBtn">

				<button type="button" class="btn btn-dark" onclick="goDetail(${vo.pk})">&nbsp 자세히 &nbsp</button>
	            <button type="button" class="btn btn-dark" onclick="goComment(${vo.pk})">리뷰&댓글</button>
	           
			</div>
		</div>
		</c:forEach>

	</main>
	<input type="text" value="${pageMaxNum }" id="hid">
	<div class="movePageWrap">
	<div class="movePage">
		<span class="movePageLeft"><i class="fas fa-arrow-left" id="pageBtn"></i></span>	
		<c:forEach var="i" begin="1" end="5" step="1">
			<span class="pageNum" style="width:50px">${i }</span>
		</c:forEach>
		<span class="movePageRight" ><i class="fas fa-arrow-right" id="pageBtn"></i></span>
	</div>
	</div>
	
	
   <script>
   	//영화 자세히, 리뷰&댓글 버튼 클릭시 제어///
    function goDetail(pk){
    	location.href="/movie/goDetail.do?pk="+pk;
    }
    
    
    
    function goComment(pk){
    	location.href="/movie/goComment.do?pk="+pk;
    }
    
    
    </script>

</body>
</html>