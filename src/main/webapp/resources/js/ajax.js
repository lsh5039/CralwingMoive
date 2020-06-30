$(function(){
	$(".movePageLeft").click(function(){
		var current = $(".pageNum.active").index();
		if(current-2<0){
			return
		}
		current-=2;
		
		$(".pageNum").eq(current).trigger('click');
	})
	$(".movePageRight").click(function(){
		var current = $(".pageNum.active").index();
		/*if(current>4){
			return
		}*/
		
		if(current>4){
			var pageMax = $(".pageNum.active").html();
			
			
			for(var i=0;i<5;i++){
				var num = Number($(".pageNum").eq(i).html());	
				num =num+5;
				//$(".pageNum").eq(i).remove();
				$(".pageNum").eq(i).html(num);
				console.log("내가넣은 num : "+num)
			}
			$(".pageNum").removeClass("active");
			$(".pageNum").eq(0).addClass("active")
			$(".pageNum").eq(0).trigger("click");
		}
		$(".pageNum").eq(current).trigger('click');
		
		
	})
	
	$(".pageNum").eq(0).addClass("active");
	$('.pageNum').on('click', function(){
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
				
				$(".movieItems").remove();
				for(var i=0;i<data.movieList.length;i++){
					var ajaxData = '<tr class="movieItems" >'
										+'<td>'+data.movieList[i].title+'</td>'
										+'<td>+<img src='+data.movieList[i].img+'></td>'
										+'<td>'+data.movieList[i].num+'</td>'
										+'<td>'+data.movieList[i].num2+'</td>'
										+'<td><button type="button" class="btn btn-dark" onclick=goDetail('+data.movieList[i].pk+')>자세히</button></td>'
										+'<td><button type="button" class="btn btn-dark">리뷰&댓글</button></td>'
									+'</tr>';
						$(".tables").append(ajaxData);			
						
				}
				
				
				
				
			},
			error:function(data){
				console.log("실패");
			}
		})
	})
})