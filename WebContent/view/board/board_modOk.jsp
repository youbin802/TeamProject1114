<%@page import="vo.BoardVO"%>
<%@page import="dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
request.setCharacterEncoding("utf-8");
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	String boardId = request.getParameter("boardId");
	BoardDAO boardDao = new BoardDAO();
	
	int n = boardDao.updateBoard(boardId, title, content);
	   if(n<0) {
			 %>
			 		<script>
			 			alert("작성 실패");
			 			location.go(-1);
			 		</script> 
			 <%   
			    } else {
			    	
			 %>
			 	<script>
			 			alert("작성 성공");
			 			location.href="board.jsp";
			 		</script>
			 <%}%>
%>
</body>
</html>