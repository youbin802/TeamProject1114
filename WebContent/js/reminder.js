$('.reminder-container>.reminder-card').on('click', function(){
    $('.pop-wrap').show();
   
    var img = $(this).find('img')
    var imgText = img.attr('alt')
    var imgSrc = img.attr('src')
   
    $('.pop h2>span').text(imgText);
    $('.pop>.pop-img>img').attr('src', imgSrc);
})
 
$('.close').on('click', function(){
    $('.pop-wrap').hide();
})
