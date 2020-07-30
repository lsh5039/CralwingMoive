$(function(){
	$(".movePageLeft").click(function(){
		var current = $(".pageNum.active").index();
		if(current-2<0){
			return
		}
		current-=2;
		
		$(".pageNum").eq(current).trigger('click');
	})
	$('.movePageRight').click(function(){//오른쪽 화살표클릭시
		var current = $('.pageNum.active').index();
		if($('.pageNum.active').html() == $('#hid').val()){//최대 페이지시
			alert('마지막 페이지입니다.')
			return;
		}else if(current > 4){
			var pageMax = $(".pageNum.active").html();
			
			
			for(var i=0;i<5;i++){
				var num = Number($(".pageNum").eq(i).html());	
				num =num+5;
				
				$(".pageNum").eq(i).html(num);
				console.log("내가넣은 num : "+num)
			}
			$(".pageNum").removeClass("active");
			$(".pageNum").eq(0).addClass("active")
			$(".pageNum").eq(0).trigger("click");
		
    	
   
		}else{
			$('.pageNum').removeClass('active');
			$('.pageNum').eq(current).addClass('active');
			$('.pageNum').eq(current).trigger('click');
		}
	})
	
	$(".pageNum").eq(0).addClass("active");
	
	
	$('.pageNum').on('click', function(){//페이지 이동시 ajax
	var pageNum = $(this).html();
		pageNum--;
	var idx	=$(this).index();
	$(".pageNum").removeClass("active");
	$(".pageNum").eq(idx-1).addClass("active");
		$.ajax({
			method:"POST",
			url:"/movie/board.do?start="+pageNum+"&end="+10,
			dataType:"json",
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			success :function(data){
				
				$(".itemBoxDesc").remove();
				for(var i=0;i<data.movieList.length;i++){
					var ajaxData = '<div class="itemBoxDesc">'
										+'<p class="descTitle">'+data.movieList[i].title+'</p>'
										+'<p class="descThum"><img src="'+data.movieList[i].img+'"></p>'
										+'<p class="descNum1">'+data.movieList[i].num+'</p>'
										+'<p class="descNum2">'+data.movieList[i].num2+'</p>'
										+'<div class="descBtn">'
										+	'<button type="button" class="btn btn-dark" onclick="goDetail('+data.movieList[i].pk+')">자세히</button>'
							            +	'<button type="button" class="btn btn-dark" onclick="goComment('+data.movieList[i].pk+')">리뷰&댓글</button>'							           
										+'</div>'
									+'</div>';
						$(".itemBoxTop").append(ajaxData);
				}
				$('.descBtn > .btn').style({
					margin:'10px'
				});
				
				
				
				
				
			},
			error:function(data){
				console.log("실패");
			}
		})
	})
})