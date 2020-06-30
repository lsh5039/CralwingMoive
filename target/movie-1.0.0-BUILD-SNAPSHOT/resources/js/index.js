$(function(){
	$(".bestWrap").removeClass("active")
	$(".bestWrap").eq(0).addClass("active");
	
    
    $(".fa-arrow-left").click(function(){
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
    
    $(".fa-arrow-right").click(function(){
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
    
})