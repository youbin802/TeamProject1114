<%@page import="vo.BoardVO"%>
<%@page import="dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file= "../../header.jsp" %>
<%
System.out.println("들어옴");
String path = request.getContextPath();
BoardVO boardVo = (BoardVO) request.getAttribute("boardVo");

System.out.println(boardVo.getContent());
int boardId = boardVo.getId();
%>

<link rel="stylesheet" href="<%=path %>/css/board_read.css">
<input type="hidden" id="writerId" value = <%= userId %>>
        <div class="sub-section">
            <div class="sub-head">
                <a href="board_read.html">
                    <h2>게시글 읽기</h2>
                </a>
            </div>
            <!-- 게시글 영역 -->
            <div class="board-content-box">
                <h3 class="title"><%= boardVo.getTitle() %></h3>
                <div class="board-head">
                    <div>
                        <div class="profile-img">
                            <img src="semi-imgs/profile.png" alt="">
                        </div>
                        <p class="name"><%= boardVo.getName() %></p>
                    </div>
                    <div class="d-flex">
                        <p class="date mx-2"><%= boardVo.getWriter_date() %></p>
                        <% if(userId.equals(boardVo.getWriter_id()))  { %>
                        <a href=<%="/yd-world/board/updateForm?id=" +boardVo.getId() %>><button>수정하기</button></a>
                        <form action="/boardDelete" method="POST">
	                      <input type="hidden" name="boardId" id="boardId" value="<%= boardId %>">
	                      <button>삭제하기</button>
						</form>	 
                        <% } %>
                    </div>
                </div>
                <hr>
                <div class="board-content">
                    <%= boardVo.getContent() %>
                </div>
            </div>
            <!-- 게시글 댓글 영역 -->
            <div class="sub-head">
                <a href="#">
                    <h2>댓글</h2>
                </a>
            </div>
            <div class="board-reply-box">
                <!-- 여러 댓글 -->
                <div class="reply-content scroll-layout">
                   
                </div>
                <!-- 댓글 작성 -->
                <form name="form">
	                <div class="reply-write d-flex justify-content-between">
	                	<input type="hidden" name="boardId" value="<%=boardVo.getId() %>" class="boardIdText" >
	                	<input type="hidden" name="userId" value="<%=userId %>"  >
	                    <input type="text" class="form-control" name="content" placeholder="댓글을 쓰세요!">
	                    <button type="button" class="btn btn-success text-white" onclick="replyBtn()" style="width: 100px; margin-left: 5px;">작성</button>
	                </div>
	                </form>
                
            </div>
        </div>
<script src="<%=path %>/js/jquery-3.6.0.min.js"></script>
<script src="<%=path %>/js/board_read.js"></script>    

<script>
	
	

</script>
<%@ include file= "../../footer.jsp" %>