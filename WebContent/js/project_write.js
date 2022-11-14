// 이미지 정보들을 담을 배열
var sel_files = [];

$(document).ready(function () {
    $('.upload-form').on('change', handleImgFileSelect);
});

// function fileUploadAction(){
//     console.log("fileUploadAction");
//     $('.upload-form').trigger('click');
// }

function handleImgFileSelect(e) {
    // console.log("실행");
    // 이미지 정보들을 초기화
    sel_files = [];
    $('.project-main-img').empty();

    var files = e.target.files;
    var filesArr = Array.prototype.slice.call(files);

    var index = 0;
    var slideImg = [];
    // var mainImg = [];

    filesArr.forEach(f => {
        if (!f.type.match("image.*")) {
            alert("확장자는 이미지 확장자만 가능합니다.");
            return;
        }
        sel_files.push(f);

        var reader = new FileReader();
        reader.onload = function (e) {
            var mainHtml = "<img src=\"" + e.target.result + "\" data-file='" + f.name + "' class='selProductFile'>";
            var html = "<div class='project-img-slide-images-box'><div class='close'>X</div><img src=\"" + e.target.result + "\" data-file='" + f.name + "' class='selProductFile'></div>";
            index++;
            var mainImgSW = $('.project-main-img').width();
            slideImg += html;

            $('.project-main-img').prepend(mainHtml);
            $('.project-img-slide').show();
            $('.project-main-img img').css({
                width: `${mainImgSW}px`,
                height: '75%'
            });
            
            $('.project-img-slide-images').prepend(slideImg);

            for (let i = 0; i < sel_files.length; i++) {
                $(`.project-main-img>img`).eq(i + 1).hide();
            }
            slideImg = [];

            $('.selProductFile').on('click', function () {
                var src = $(this).attr('src');
                console.log(src);
                $('.project-main-img img').attr('src', src);
                if($('.project-main-img img').length == 0){
                    $('.project-main-img').prepend(html)
                    $('.project-main-img img').attr('src', src);
                }
            })

            $('.project-img-slide-images-box .close').on('click', function(){
                $(this).parent('.project-img-slide-images-box').remove();
            })

            // $('.project-img-slide-images')
        }

        reader.readAsDataURL(f);
    });
}

$(function(){
    $(".project-img-slide-images").on('mousewheel', function (e) {
        var wheelDelta = e.originalEvent.wheelDelta; 
        if (wheelDelta > 0) {
            $(this).scrollLeft(-wheelDelta + $(this).scrollLeft()); 
        } else { 
            $(this).scrollLeft(-wheelDelta + $(this).scrollLeft()); 
        } 
    });
})
