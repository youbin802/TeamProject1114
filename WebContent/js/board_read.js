var replyInput; //댓글을 input창으로 변환
var compution = false
const btns = $('.reply-bottom .btn:even')

for (const btn of btns) {
    $(btn).on('click', function () {
        if (!compution) {
            replyInput = $(this).parents('.reply-bottom').children('p');
            replyInput.html(`<input type="text" class="replyCorrection form-control" value="${replyInput.text()}">`)
            $(this).text('확인')
            $(btns).not(this).attr('disabled', true)
            compution = !compution
        } else {
            replyCorrectionVal();
            $(this).text('수정')
            $(btns).not(this).attr('disabled', false)
            compution = !compution
        }
    })
}

	function replyBtn() {
		console.log("댓글작성 d");
		let boardId = document.form.boardId.value;
		let content = document.form.content.value;
		let userId = document.form.userId.value;
		console.log(boardId);
		$.ajax({
			type:"get",
			data:{"boardId":boardId,"content":content,"userId":userId},
			url:"/InsertReply",
			datatype:'json',
			success:function(data) {
				console.log(data);
				loadReply();
			},
			error:function(err) {
				console.log("error"+data);
				alert("error:"+error);
			}
		});
		
	}

function replyCorrectionVal() {
    var text = $('.replyCorrection').val();
    replyInput.text(text);
    $('.replyCorrection').remove();
}

function modAjax(replyId,content ){
		$.ajax({
			type:"get",
			data:{"replyId":replyId,"content":content},
			url:"/modReply",
			datatype:'json',
			success:function(data) {
				console.log(data);
				loadReply();
			},
			error:function(err) {
				alert("error:"+err);
			}
		});
}
function delAjax(id) {
	$.ajax({
			type:"get",
			data:{"replyId":id},
			url:"/delReply",
			datatype:'json',
			success:function(data) {
				console.log(data);
				loadReply();
			},
			error:function(err) {
				alert("error:"+err);
			}
		});
}
function getModVal(input) {
	console.log("inininini");
	console.log(input+input.val());
	return input.val();
}
loadReply();

	// 댓글 ajax 부분
	function loadReply() {
		console.log("댓글 가져오는 함수 z");
		let boardId = document.form.boardId.value;
		console.log(boardId);
		
		
		$.ajax({
			type:"post",
			data:{"boardId":boardId},
			url:"/getReplyList",
			datatype:'json',
			success:function(data) {
				var modlVal = null;
				var list = JSON.parse(data);
				console.log(list);
				$(".reply-content").html("");
				$.each(list, function(key, val) {
					console.log("here");
	              	console.log(val);
	              	
	               var writerId = val['writer_id'];
	               var replyId = val['id'];
	                if($("#writerId").val()==val['writer_id']) {
						 var div = document.createElement('div');
	                div.innerHTML= `<div class="reply-head">
	                                        <div class="profile-img">
	                                            <img src="semi-imgs/profile.png" alt="">
	                                        </div>
	                                    
	                                        <p class="name"></p>
	                                        <p class="date"></p>
	                                   
	                                    </div>
	                           
	                                    <div class="reply-bottom d-flex justify-content-between align-items-center">
	                                    	<input type="text" id="modInput" style="display:none;">
	                                        <p></p>
	                                        <div class="show-form" style="display:flex;">
	                                            <button class="btn btn-success text-white" id="mod" style="width: 60px;">수정</button>
	                                            <button class="btn btn-success text-white mx-2" id="del" style="width: 60px;">삭제</button>
	                                        </div>
	                                        
	                                         <div class="mod-form" style="display:none !important; display:flex;">
	                                            <button class="btn btn-success text-white" id="mod" style="width: 60px;">완료</button>
	                                            <button class="btn btn-success text-white mx-2" id="cancle" style="width: 60px;">취소</button>
	                                        </div>
	                                    </div>
	                              
	                `;
	                $(div).find("#del").click(function() {
						delAjax(replyId);
					});
	                console.log(writerId,replyId);
	               $(div).find("#mod").click(function() {
						console.log("수정 클릭");
						console.log($(div).find(".reply-bottom > p").text());
						//modAjax(replyId,$(div).find(".reply-bottom > p").text());
						$(div).find(".reply-bottom > p").css('display','none')
						$(div).find(".reply-bottom > input").val(val['content']);
						$(div).find(".reply-bottom > input").css('display','block');
						$(div).find(".reply-bottom > .mod-form").css('display','block');
						$(div).find(".reply-bottom > .show-form").css('display','none');
						
						$(div).find(".reply-bottom > .mod-form > #cancle").click(function() {
							$(div).find(".reply-bottom > p").css('display','block')
							$(div).find(".reply-bottom > input").css('display','none');
							$(div).find(".reply-bottom > .mod-form").css('display','none');
							$(div).find(".reply-bottom > .show-form").css('display','block');
						})
						var TextCh = "";
						 $( document ).ready( function() {
							console.log("입력준비");
							$(div).find(".reply-bottom > input").keyup(function() {
					
								console.log("감지중3"+$(this).val());
								TextCh = $(this).val();
								console.log(TextCh);
							})
						});
						$( document ).ready( function() {
							console.log("클릭준비");
							$(div).find(".reply-bottom > .mod-form > #mod").click(function() {
								console.log("클릭준비=====>");
								console.log(TextCh)
								getModVal($(div).find(".reply-bottom > input"));
								console.log("입력받은 거"+TextCh);
								modAjax(replyId,TextCh);
							})
						});
						
					})
					} else {
						 var div = document.createElement('div');
	                div.innerHTML= `<div class="reply-head">
	                                        <div class="profile-img">
	                                            <img src="semi-imgs/profile.png" alt="">
	                                        </div>
	                                        <p class="name"></p>
	                                        <p class="date"></p>
	                                    </div>
	                           
	                                    <div class="reply-bottom d-flex justify-content-between align-items-center">
	                                        <p></p>
	                                    </div>
	                              
	                `;
					}
	               
	              
	                $(div).find(".name").text(val['writer_name']);
	                
	                $(div).find(".date").text(val['writer_date']);
	                $(div).find(".reply-bottom > p").text(val['content']);
	                
	                $(".reply-content").append(div);
	                console.log("완료");
	            })
				
			
	            

			},
			error:function(err) {
				console.log("error"+data);
				alert("error:"+error);
			}
		});
		
	}

