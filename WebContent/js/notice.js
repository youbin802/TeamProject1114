$('.sub-notice-box').on('click', function(){
	var noticeId = $(this).find("#noticeId").val();
	console.log(noticeId);
	var dom = $('.big-notice');
		$.ajax({
			type:"post",
			data:{"noticeId":noticeId},
			url:"/getNotice",
			datatype:'json',
			success:function(data) {
				console.log("성공");
				const arr = data.split("===");
				const dataList = JSON.parse(arr[0]);
				console.log(dataList);
				const img = JSON.parse(arr[1]);
				
				dom.find('h2 > p').text(dataList[0].title);
				dom.find('.content').text(dataList[0].content);
				
				var imgDom = dom.find('.img-zone');
				$.each(img, function(key, val) {
					console.log(dataList[0].id);
					console.log(val.name);
					 var image = new Image();
   					 image.src =  '/noticeImage/'+dataList[0].id+'/'+val.name;
   					 imgDom.append(image);
					
				})
			},
			error:function(err) {
				alert("error:"+err);
			}
		});
    $('.big-notice').show();
})

$('.close').on('click', function(){
    $('.big-notice').hide();
})