$(function(){
	
	$(window).scroll(function () { 
		var scrollValue = $(document).scrollTop();
		if(scrollValue>1200){
			$(".head").css({
				position:'fixed',
				top:'0px',
				zIndex:"999"
			})
		} else{
			$(".head").css({
				position:''
			})
		}
	});

	
	
	
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
    
    
    $('.pageNum').click(function(){//직접 숫자 클릭한 경우
    	var idx = $(this).index();
    	
    	$('.pageNum').removeClass('active');
    	$('.pageNum').eq(--idx).addClass('active');
    })
    
    $("#updPro").click(function(){
    	$(".proFile").trigger("click");
    })
    
	
    
    
})