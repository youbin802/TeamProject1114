<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../header.jsp" %>
<%
	String path = request.getContextPath();
%>

    <link rel="stylesheet" href="<%= path %>/css/honor.css">
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script> 
    <script src="<%= path %>/js/turn.js"></script>
   <div class="sub-section">
            <div class="sub-head">
                <a href="notice.html">
                    <h2>명예의 전당</h2>
                    <a id="write">작성하기 ></a>
                </a>
            </div>
           <div class="sub-notice">
           	    <div id="book">
			        <div class="cover"><h1>제목</h1></div>
			    </div>
			    <div class="controls">
			        <button id="prev" class="btn btn-success text-white">이전</button>
			        <button id="next" class="btn btn-success text-white">다음</button>
			    </div>
           	
    	   </div>
    </div>
               <input type="hidden" name="userId" id="userId" value="<%= userId %>">

    <script>


var numberOfPages = 1000; 

var nowPage =0;
var userId = $("#userId").val();
var honorId;
console.log("유저 아이디====================", userId);

function addPage(page, book, list,reply, type) {
  if (!book.turn('hasPage', page)) {
	  console.log("add page 실행 함수");
	  console.log(page);

	  	 
    var element = $('<div />', {'class': 'page '+((page%2==0) ? 'odd' : 'even'), 'id': 'page-'+page}).html('<i class="loader"></i>');
    book.turn('addPage', element, page);

    setTimeout(function(){
    	if(type == "first") {
    		console.log("첫 번쨰");
    		console.log(list);
	   		var id = list[0].id;
			var content = list[0].content;
			var writer_id = list[0].writer_id;
			var writer_date = list[0].writer_date;
			console.log(id+content);
			nowNoticeId = id;
			
			console.log("구간2");
			console.log(userId, writer_id);
			if(userId == writer_id) {
				honorId = id;
				console.log(honorId+"");
				element.html('<div class="data"><div class="text-area"><div><small>박유빈   '+writer_date+'</small></div><a id="modHonor">수정하기</a></div><div class="bar-div"></div><p>'+content+'</p></div>');
			}else {
				element.html('<div class="data"><div class="text-area"><div><small>박유빈   '+writer_date+'</small></div></div><div class="bar-div"></div><p>'+content+'</p></div>');
			}
			
			element.find("#modHonor").click(function() {
				console.log("글수정하22는 ㄱ놋========");
				$(".modalMod").find("#honorIdInput").val(honorId);
				$(".modalMod").find(".contentTextArea").val(content);
				$(".modalMod").show()
			}) 		
    	}else {
    		console.log("두 번쨰");
    		console.log(reply);
    		$.each(reply, function(key, val) {
    			var content= val.content;
    			var writerId = val.writerId;
    			var replyId = val.replyId;
    			var date = val.date;
    			var name = val.name;
    			console.log("함수 돌기");
    			console.log(content);
    			var div = document.createElement('div');
    			div.className = 'reply-div';
                div.innerHTML= `<div class="reply-head">
                    <div class="profile-img">
			                    <img src="semi-imgs/profile.png" alt="">
			                </div>
			                <p class="name">홍길동</p>
			                <p class="date">dsf</p>
			            </div>
					   </div>
			            <div class="reply-bottom d-flex justify-content-between align-items-center">
			                <p></p>
			                <div class="d-flex">
			                    <button class="btn btn-success text-white" style="width: 60px;">수정</button>
			                    <button class="btn btn-success text-white mx-2" style="width: 60px;">삭제</button>
                </div>`;
                $(div).find(".name").text(name);
	    		$(div).find(".date").text(date);
	    		$(div).find(".reply-bottom > p").text(content);
	    		element.append(div);
                
    		})
    	}
    	
    }, 1000);
  }
}

function getHonorData(now) {
	var dataList = [];
	  $.ajax({
			type:"post",
			data:{"now":now},
			url:"/getHonorData",
			datatype:"json",
			success:function(data) {
				
				list = JSON.parse(data);
				console.log("명전 성공");
				console.log(list);
			},	async : false,
			error:function(err) {
				console.log("error"+err);
			}
	  })
	  console.log(list);
	  return list;
}


$(window).ready(function(){
  $('#book').turn({
            acceleration: true,
            pages: numberOfPages,
            elevation: 50,
            gradients: !$.isTouch,
            when: {
              turning: function(e, page, view) { 
            	  console.log(page, view);
          
            	  if(nowPage !=0) {
            		  
            	  var list = getHonorData(nowPage);
            	  console.log(" 현재페이지"+nowPage);
            	  console.log(list);
            	  
            	  
            	  var replyList = loadReply(nowPage);
            	  console.log("댓글 리스트 가져오기");
            	  console.log(replyList);
                // 가져온 길이만큼 돌리기
                 var range = $(this).turn('range', page);
            	  }
                
                addPage(view[0], $(this), list,replyList, 'first');
                addPage(view[1], $(this), list,replyList, 'second');

                // for문으로 돌며 그 페이지 수에 addpage로 페이지 생성
                 // for (page = range[0]; page<=range[1]; page++)  {
                   // addPage(page, $(this), list);
                
                 // }
    

              },

              turned: function(e, page) {
                $('#page-number').val(page);
              }
            }
          });
	
	  $('#number-pages').html(numberOfPages);  
	});
$( document ).ready( function() {
	$("#prev").click(function() {
		nowPage = nowPage -1;
	      $('#book').turn('previous');
	})
	$("#next").click(function() {
		nowPage =nowPage+1;
	      $('#book').turn('next');
	})
});

$( document ).ready( function() {
	$("#write").click(function() {
		console.log("글작성하e는곳============="+userId);
		$(".modal-write").find("#userIdInput").val(userId);

		$(".modal-write").show();
	})
	$(".close").click(function() {
		$(".modal-write").hide();
	})

});
function loadReply(now) {
	console.log("댓글 가져오는 함수 z"+now);
	var list = null;
	$.ajax({
		type:"post",
		data:{"id":now},
		url:"/getHonorReply",
		datatype:'json',
		success:function(data) {
			list = JSON.parse(data);
			console.log(list);
			console.log("성공");
		},	async : false,
		error:function(err) {
			console.log("error"+err);
		}
	});
	console.log("리스트 정상?"+list);
	return list;
}


    </script>
<%@ include file="../../footer.jsp" %>