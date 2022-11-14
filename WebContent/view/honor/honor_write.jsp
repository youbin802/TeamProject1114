<%@page import="dao.HonorDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../header.jsp" %>
<%
HonorDAO honorDao = new HonorDAO();
int noticMaxId = honorDao.getMaxId(); 
%>
	<form action="/honorWrite" method="POST">
			<input type="text" name="noticMaxId" value="<%= noticMaxId %>">
		<input type="text" name="userId" value="<%= userId %>">
		<input name="content"  placeholder="설명">
		<button type="submit" class="btn">작성</button>
	</form>
</body>
</html>