<%@page import="java.util.ArrayList"%>
<%@page import="vo.BoardVO"%>
<%@page import="dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
ArrayList<Integer> pageData = (ArrayList<Integer>)request.getAttribute("pageData");
ArrayList<BoardVO> boardList = (ArrayList<BoardVO>)request.getAttribute("boardList");

String path = request.getContextPath();

int start = pageData.get(0);
int pageBlock = pageData.get(1);
int endP = pageData.get(2);
int pageCnt = pageData.get(3);
System.out.println("페이지 정보");
System.out.println("start====>"+start+"pageBlock====>"+pageBlock+"endP====>"+endP+"pageCnt====>"+pageCnt);
%>

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
                <% if(start> pageBlock){ %>
                  <li class="page-item">
                    <a class="page-link" href="<%= path %>/yd-world/board?p=<%= start - pageBlock %>" aria-label="Previous">
                      <span>뒤</span>
                    </a>
                  </li>
                  <% } %>
                  
                  <% for(int i=start; i<=endP; i++) { %>
                  <li class="page-item"><a class="page-link" href="<%= path %>/yd-world/board?p=<%=i%>"><%= i %></a></li>
                  <% } %>
                  
                  <% if(endP < pageCnt){ %>
                  <li class="page-item">
                    <a class="page-link" href="<%= path %>/yd-world/board?p=<%= start+pageBlock %>" aria-label="Next">
                      <span>&raquo;</span>
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