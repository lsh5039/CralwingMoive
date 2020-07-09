$(function(){
	$(".bestWrap").removeClass("active")
	$(".bestWrap").eq(0).addClass("active");
	
    
    $(".arrowLeft .fa-arrow-left").click(function(){
        var idx = $(".indicator.active").index();
        var bannerIdx = $(".bestWrap.active").index();
        if(idx==0){
            return;
        }
        $(".indicator").removeClass("active");
        $(".indicator").eq(idx-1).addClass("active");
        $(".bestWrap").removeClass("active");
        $(".bestWrap").eq(idx-1).addClass("active");
    })
    
    $(".arrowRight .fa-arrow-right").click(function(){
        var idx = $(".indicator.active").index();
        var bannerIdx = $(".bestWrap.active").index();
        if(idx==4){
            return;
        }
        $(".indicator").removeClass("active");
        $(".indicator").eq(idx+1).addClass("active");
        $(".bestWrap").removeClass("active");
        $(".bestWrap").eq(idx+1).addClass("active");
    })
    
    $(".indicator").click(function(){
    	var index = $(this).index();
    	
        $(".indicator").removeClass("active");
        $(this).addClass("active");
        $(".bestWrap").removeClass("active");
        $(".bestWrap").eq(index).addClass("active");

    })
    
    
    
    //페이징
   /* var pageMaxNum = $('#hid').val();//최대 페이지 갯 수
    var isAnimate = true;//페이지 논리변수 true면 다음 false면 최대페이지라 멈춤
    $('.movePageRight').click(function(){
    	if(!isAnimate){
    		return;
    	}
    
    	var idx = $('.pageNum.active').index();
    	
    	$('.pageNum').removeClass('active');
    	$('.pageNum').eq(idx).addClass('active');
    	
    	var current = $(".pageNum.active").index();
    	if($('.pageNum.active').html() == $('#hid').val()){
    		isAnimate = false;
    		alert('어기가 마지막 페이지입니다.')
    		
    		return;
    	}
    	if(current>4){//페이지 바꿈
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
		}
    	
    	$('.pageNum').eq(idx).trigger('click');
    })
    
    
    
    
        $('.movePageLeft').click(function(){
        	isAnimate = true;
    	var idx = $('.pageNum.active').index();
    	if(idx==1 && $('.pageNum.active').html() == 1){//페이지의 첫 장이면 이벤트 종료 
    		return;
    	}else if(idx == 1){
    		for(var i=0;i<5;i++){
				var num = Number($(".pageNum").eq(i).html());	
				num =num-5;
				
				$(".pageNum").eq(i).html(num);
				console.log("내가넣은 num : "+num)
			}
    	}
    	idx -=2;
    	$('.pageNum').removeClass('active');
    	$('.pageNum').eq(idx).addClass('active');
    	
    	$('.pageNum').eq(idx).trigger('click');
    })*/
    
    $('.pageNum').click(function(){//직접 숫자 클릭한 경우
    	var idx = $(this).index();
    	
    	$('.pageNum').removeClass('active');
    	$('.pageNum').eq(--idx).addClass('active');
    })
    
	
    
    
})