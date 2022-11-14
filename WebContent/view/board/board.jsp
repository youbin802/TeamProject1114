<%@page import="java.util.ArrayList"%>
<%@page import="vo.BoardVO"%>
<%@page import="dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
int pageSize = 10; 


String pageNum = request.getParameter("p");
if (pageNum == null){ 
	pageNum = "1";
}

int currentPage = Integer.parseInt(pageNum);


int startRow = (currentPage - 1) * pageSize + 1;
int endRow = currentPage * pageSize;
BoardDAO boardDao = new BoardDAO();
int count = 22;
ArrayList<BoardVO> boardList = boardDao.getBoardList(startRow, endRow);
%>
<style>

.pagination {
	    position: absolute;
    bottom: 0;
    left: 45%;
}
</style>
<%@ include file="../../header.jsp" %>
       <div class="sub-section">
            <div class="sub-head">
                <a href="board.html"><h2>게시판</h2></a>
                <% if(userId != null){ %>
                <a href="<%= path %>/yd-world/board/writeForm">작성하기 ></a>
            	<%} %>
            </div>

            <div class="mainpage-board-box boardpage-board-box">
            	<% for(BoardVO board : boardList) { %>
                <a href="<%= path %>/yd-world/board?id=<%=board.getId() %>">
                    <p><%= board.getTitle() %></p>
                    <p><%= board.getWriter_date() %></p>
                </a>
                <% } %>
            </div>
       <ul class="pagination">
		
					<%	
						if(count > 0){
					
							int pageCount = count / pageSize + (count%pageSize == 0 ? 0 : 1);
						
							int pageBlock = 10;
					
							int startPage = ((currentPage-1)/pageBlock)*pageBlock+1;
							int endPage = startPage + pageBlock - 1;
							
							
							if(endPage > pageCount){
								endPage = pageCount;
							}
							
							if(startPage > pageBlock){
					%>
								     <li class="page-item">
					                    <a class="page-link" href="<%= path %>/yd-world/board?p=<%= startPage - 10 %>" aria-label="Previous">
					                      <span>이전</span>
					                    </a>
					                  </li>

					<%			
							}
							
							for(int i=startPage; i <= endPage; i++){ // 페이지 블록 번호
								if(i == currentPage){ // 현재 페이지에는 링크를 설정하지 않음
					%>               <li class="page-item"><a class="page-link"><%= i %></a></li>
								
					<%									
								}else{ // 현재 페이지가 아닌 경우 링크 설정
					%>               <li class="page-item"><a class="page-link" href="<%= path %>/yd-world/board?p=<%=i%>"><%= i %></a></li>
				
					<%	
								}
							} 
							
							if(endPage < pageCount){ // 현재 블록의 마지막 페이지보다 페이지 전체 블록수가 클경우 다음 링크 생성
					%>
								<a href="<%= path %>/yd-world/board?p=<%=startPage + 10 %>">[다음]</a>
					<%			
							}
						}
					%>
		  </ul>
        </div>
            <link rel="stylesheet" href="css/board.css">
            
<%@ include file="../../footer.jsp" %>
</body>
</html>