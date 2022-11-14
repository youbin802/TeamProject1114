<%@page import="dao.NoticeDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%
	NoticeDAO noticeDao = new NoticeDAO();
	int noticMaxId = noticeDao.getMaxId();
%>

	<form action="NoticeImgUpload" method="POST" enctype="multipart/form-data">
	  <input type="text" name="noticMaxId" value="<%= noticMaxId %>">
		<input type="text" name="userId" value="<%= userId %>">
		<input name="title" placeholder="제목">
		<input name="content" placeholder="설명">
		

<a href="notice_load.jsp">파일 다운로드 Ddos 공격 차단</a>
		<label>이미지/파일 업로드</label>
		<input type="file" name="file" class="form-control" id="imgs" multiple>
		<button type="submit" class="btn">작성</button>
		
	</form>
</body>
</html>