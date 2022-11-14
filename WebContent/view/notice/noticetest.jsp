<%@page import="dao.NoticeDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>

<%@ include file="../../header.jsp" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<%
	NoticeDAO noticeDao = new NoticeDAO();
	int noticMaxId = noticeDao.getMaxId();
    ArrayList<String> noticeType= noticeDao.getNoticeType();
%>

	<form action="/NoticeImgUpload" method="POST" enctype="multipart/form-data">
	  <input type="text" name="noticMaxId" value="<%= noticMaxId %>">
		<input type="text" name="userId" value="<%= userId %>">
		<input name="title" placeholder="제목">
		<input name="content"  placeholder="설명">
		
		<input name="selectText" id="selectText" type="text">
		<select class="form-select" size="3" aria-label="size 3 select example" style="width: 243px;" id="select">
		   	<option selected>공지 종류를 선택하세요</option>
		   <% for(String selectText : noticeType) { %>
		     
		  	<option value="<%= selectText%>"><%= selectText%></option>
		   <% }  %>
		</select>
		

<a href="notice_load.jsp">파일 다운로드 Ddos 공격 차단</a>
		<label>이미지/파일 업로드</label>
		<input type="file" name="file" class="form-control" id="imgs" multiple>
		<button type="submit" class="btn">작성</button>
		
	</form>
	<script>
	$(document).ready(function(){
		$("#select").change(function(){
			console.log("값변경테스트: " + $(this).val());
			$("#selectText").val($(this).val());

		});
	 });
	</script>
</body>
</html>