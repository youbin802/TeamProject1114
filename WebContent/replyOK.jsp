<%@page import="vo.ReplyVO"%>
<%@page import="dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
	<%
		String content = request.getParameter("content");
		int boardId = Integer.parseInt(request.getParameter("boardId"));
		
		
		BoardDAO boardDao = new BoardDAO();
		int n = boardDao.insertReply(boardId, content, userId);
		
		
	
	%>
<%@ include file="footer.jsp" %>