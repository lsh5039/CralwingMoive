$(function(){
	$(document).on('click', '.pageNum', function(){
	var pageNum = $(this).html();
		$.ajax({
			method:"POST",
			url:"/movie/board.do?start="+pageNum+"&end="+10,
			dataType:"JSON",
			succes:function(data){
				console.log("data : "+data)
			},
			error:function(data){
				console.log("실패"+data);
			}
		})
	})
})