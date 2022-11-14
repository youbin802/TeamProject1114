$(function () {
    let mainImg = $('.main-img>a>img') //메인 이미지
    let slideImg = $('.slide-box-img>a>img') //슬라이드 이미지
    var slideAttr;
    var sno = 0;
    var mainSrc;
    $(slideImg[0]).addClass('filter');

    for (let i = 0; i < slideImg.length; i++) { // 슬라이드 이미지 클릭시 메인 이미지 변환
        $(slideImg[i]).on('click', function () {
            $(slideImg).removeClass('filter')
            slideAttr = $(this).attr('src');
            $(mainImg).attr('src', slideAttr)
            $(this).addClass('filter')
        })
    }

    $('.leftBtn').on('click', function () {
        sno--;
        if (sno < 0) {
            sno = slideImg.length - 1;
            mainSlide(sno)
        } else {
            mainSlide(sno)
        }
    })

    $('.rightBtn').on('click', function(){
        sno++;
        if(sno > slideImg.length-1){
            sno = 0;
            mainSlide(sno)
        }else{
            mainSlide(sno)
        }
    })

    function mainSlide(sno) {
        mainSrc = $(slideImg[sno]).attr('src')
        $(mainImg).attr('src', mainSrc)
        $(slideImg).not($(slideImg[sno])).removeClass('filter')
        $(slideImg[sno]).addClass('filter')
    }

    //호버시 이미지 filter해주는건데 개인적으로 조금 안예쁜거 같아서 뺐어요
    // $(slideImg).hover(function () {
    //     $(this).toggleClass('down-filter');
    // }, function () {
    //     $(this).toggleClass('down-filter');
    // })

    $(".slide-box-img").on('mousewheel', function (e) {
        var wheelDelta = e.originalEvent.wheelDelta;
        if (wheelDelta > 0) {
            console.log("up"); $(this).scrollLeft(-wheelDelta + $(this).scrollLeft());
        } else {
            console.log("down");
            $(this).scrollLeft(-wheelDelta + $(this).scrollLeft());
        }
    });

    var heartTF = false // 하트가 눌렀는지 안눌렀는지 판단
    var heartCnt = parseInt($('.good-wrap p').text());
    $('.min-p .good-wrap i').on('click', function () {
		console.log("click");
        if (!heartTF) {
            heartCnt++;
            var id = $("#projectId").val();
            $(this).css('color', '#d44e4e')
             $.ajax({
                 type: "post",
           	     url: "/projectHeart",
                 dataType: "dataType",
                 success: function (data) {
                     $('.good-wrap p').text(heartCnt)
                 }
             });
            heartTF = true
        } else {
            $(this).css('color', '#000')
            $('.good-wrap p').text(--heartCnt)
            heartTF = false
        }
    })

})