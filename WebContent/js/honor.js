// $(function() {
//     $('.chat-btn').on('click', function(){ // 댓글 보기 클릭 이벤트 
//         if($(this).next().css('display') == 'none'){
//             $(this).next().stop().slideToggle(1000)
//             $(this).children('a').text('댓글닫기')
//             console.log($(this).next()); 
//             console.log($('.chat-btn').next())
//         }
//     })
// })
const log = console.log;
const dom = $(".chat-btn")
$(".h-card > .chat-btn").each(function(idx,item) {
    let flag = false;
    $(item).on('click', function(){ // 댓글 보기 클릭 이벤트 
        if(!flag) { //true일때
            $(this).next().stop().slideDown(1000)
            $(this).children('a').text('댓글닫기');
            // $(this).parents('.h-card').find(".chat").css('display','block')
            
            flag = true;
        } else {
            $(this).next().stop().slideUp(1000);
            $(this).children('a').text('댓글보기');
            // $(this).parents('.h-card').find(".chat").css('display','none')
            flag = false;
        }
        
        // flag == true ? flag = true : flag = false;
        log(idx, flag);
        
    })
})