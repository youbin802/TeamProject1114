<%@page import="java.util.ArrayList"%>
<%@page import="vo.BoardVO"%>
<%@page import="dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	BoardDAO boardDao =new BoardDAO();
	
	
	int cnt = 10;
	int size = 3;
	String pageNum = request.getParameter("p");
	if(pageNum==null) pageNum = "1";
	
	int currentP = Integer.parseInt(pageNum);
	int start = (currentP-1)*size +1;
	
	ArrayList<BoardVO> boardList = boardDao.getBoardList(start, size);
	
	int pageCnt = cnt / size + (cnt%size==0?0:1);
	int pageBlock = 4;
	int endP = start + pageBlock -1;
	if(endP > pageCnt) endP = pageCnt;
%>

<%@ include file="../../header.jsp" %>
       <div class="sub-section">
            <div class="sub-head">
                <a href="board.html"><h2>게시판</h2></a>
                <% if(userId != null){ %>
                <a href="board_write.jsp">작성하기 ></a>
            	<%} %>
            </div>

            <div class="mainpage-board-box boardpage-board-box">
            	<% for(BoardVO board : boardList) { %>
            
                <a href="/board_read.jsp?id=<%=board.getId() %>">
                    <p><%= board.getTitle() %></p>
                    <p><%= board.getWriter_date() %></p>
                </a>
                <% } %>
            </div>
            <nav aria-label="Page navigation board-pagination">
                <ul class="pagination">
                <% if(start>pageBlock){ %>
                  <li class="page-item">
                    <a class="page-link" href="board.jsp?p=<%= start - pageBlock %>" aria-label="Previous">
                      <span aria-hidden="true">&laquo;</span>
                    </a>
                  </li>
                  <% } %>
                  
                  <% for(int i=start; i<=endP; i++) { %>
                  <li class="page-item"><a class="page-link" href="board.jsp?p=<%=i%>"><%= i %></a></li>
                  <% } %>
                  
                  <% if(endP < pageCnt){ %>
                  <li class="page-item">
                    <a class="page-link" href="board.jsp?p=<%= start-pageBlock %>" aria-label="Next">
                      <span aria-hidden="true">&raquo;</span>
                    </a>
                  </li>
                  <% } %>
                </ul>
              </nav>
        </div>
            <link rel="stylesheet" href="css/board.css">
            
<%@ include file="../../footer.jsp" %>
</body>
</html>