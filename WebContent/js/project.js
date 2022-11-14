$(function () {
    let mainImg = $('.main-img>a>img') //메인 이미지
    let slideImg = $('.slide-box-img>a>img') //슬라이드 이미지
    var slideAttr;

    for (let i = 0; i < slideImg.length; i++) { // 슬라이드 이미지 클릭시 메인 이미지 변환
        $(slideImg[i]).on('click', function () {
            slideAttr = $(this).attr('src');
            console.log(slideAttr);
            $(mainImg).attr('src', slideAttr)

        })
    }

    $(slideImg).hover(function () {
        $(this).toggleClass('filter');
    }, function () {
        $(this).toggleClass('filter');
    })

    $(".slide-box-img").on('mousewheel', function (e) {
        var wheelDelta = e.originalEvent.wheelDelta; 
        if (wheelDelta > 0) { 
            console.log("up"); $(this).scrollLeft(-wheelDelta + $(this).scrollLeft()); 
        } else { 
            console.log("down"); 
            $(this).scrollLeft(-wheelDelta + $(this).scrollLeft()); 
        } 
    });

})