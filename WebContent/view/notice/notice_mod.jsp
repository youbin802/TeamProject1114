<%@page import="vo.NoticeVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.NoticeDAO"%>
<%@page import="java.util.regex.Pattern"%>
<%@page import="java.util.regex.Matcher"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../header.jsp" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<%
System.out.println("수정 공지 로 들어옴");
	String id = request.getParameter("id");
	NoticeDAO noticeDao = new NoticeDAO();
	NoticeVO noticeVo = noticeDao.getNotice(id);
	

	ArrayList<String> ImgList = noticeDao.getNoticeImgs(id);
	
	String root = request.getSession().getServletContext().getRealPath("/images/noticeImage");
	ArrayList<String> noticeType= noticeDao.getNoticeType();


	String savePath = root ;
	String testFile = savePath + "\\7\\housewarming_party.txt";
	
%>
<a href="notice_load.jsp">파일 다운로드 Ddos 공격 차단</a>

	<form action="/NoticeImgUploadMod" method="POST" enctype="multipart/form-data">
	  <input type="text" name="noticeId" value="<%= noticeVo.getId()%>">
		<input type="text" name="userId" value="<%=noticeVo.getWriter_id()   %>">
		<input name="title" placeholder="제목" value="<%=noticeVo.getTitle() %>">
		<input name="content"  placeholder="설명" value="<%= noticeVo.getContent() %>">
				<label>이미지/파일 업로드</label>
		<input type="file" name="file" class="form-control" id="imgs" multiple>
		<input name="selectText" id="selectText" type="text">
		<select class="form-select" size="3" aria-label="size 3 select example" style="width: 243px;" id="select">
		   	<option selected>공지 종류를 선택하세요</option>
		   <% for(String selectText : noticeType) { %>
		     
		  	<option value="<%= selectText%>"><%= selectText%></option>
		   <% }  %>
		</select>
		<button>수정하기</button>
	</form>
<p><%= noticeVo.getType() %></p>
<p><%= noticeVo.getId() %></p>
<p><%= noticeVo.getTitle() %></p>
<p><%= noticeVo.getContent() %></p>
<p><%= noticeVo.getWriter_date() %></p>
<p><%= noticeVo.getWriter_id() %></p>
<p><%= noticeVo.getWriter_id() %></p>

<% for(int i =0; i<ImgList.size(); i++) {
	String PATTERN = "([^\\s]+(\\.(?i)(jpg|png|))$)";
	Pattern pattern = Pattern.compile(PATTERN);
	String name2 = ImgList.get(i);
	Matcher match = pattern.matcher(name);
	

	if(match.find()) {%>
	  <a href="/FileLoad?filename=<%= name%>&id=<%=noticeVo.getId() %>" class="col" download><img src="/noticeImage/<%=id+"/"+name2 %>" alt="project-sample1" width="100" height="100"></a>
	<% }else{ %>
	<a href="/FileLoad?filename=<%= name%>&id=<%=noticeVo.getId() %>" download><%= name2 %><button type="button">다운로드</button></a>
<% 		

	}
	
} %>
                   
                   
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>           
     	<script>
	$(document).ready(function(){
		$("#select").change(function(){
			console.log("값변경테스트: " + $(this).val());
			$("#selectText").val($(this).val());

		});
	 });
	</script>              
       <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>            
                   
                   
                   
                   
</body>
</html>