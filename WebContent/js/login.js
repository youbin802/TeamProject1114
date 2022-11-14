$('.login').on('click', function(){
    $('.login-popup').show();
})

$('.login-popup .close').on('click', function(){
	console.log("click");
    $('.login-popup').hide();
})