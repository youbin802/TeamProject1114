<%@page import="vo.NoticeVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.NoticeDAO"%>
<%@page import="java.util.regex.Pattern"%>
<%@page import="java.util.regex.Matcher"%>

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
	String id = request.getParameter("id");
	NoticeDAO noticeDao = new NoticeDAO();
	NoticeVO noticeVo = noticeDao.getNotice(id);
	

	ArrayList<String> ImgList = noticeDao.getNoticeImgs(id);
	
	String root = request.getSession().getServletContext().getRealPath("/images/noticeImage");


	String savePath = root ;
	String testFile = savePath + "\\7\\housewarming_party.txt";
	
%>
<a href="notice_load.jsp">파일 다운로드 Ddos 공격 차단</a>
<p><%= noticeVo.getType() %></p>
<p><%= noticeVo.getId() %></p>
<p><%= noticeVo.getTitle() %></p>
<p><%= noticeVo.getContent() %></p>
<p><%= noticeVo.getWriter_date() %></p>
<p><%= noticeVo.getWriter_id() %></p>
<p><%= noticeVo.getWriter_id() %></p>
			<a href="<%= request.getContextPath() %>/yd-world/notice/updateForm?id=<%=noticeVo.getId()%>"><button>수정하기</button></a>
<% for(int i =0; i<ImgList.size(); i++) {
	String PATTERN = "([^\\s]+(\\.(?i)(jpg|png|))$)";
	Pattern pattern = Pattern.compile(PATTERN);
	String name = ImgList.get(i);
	Matcher match = pattern.matcher(name);
	

	if(match.find()) {%>
	  <a href="/FileLoad?filename=<%= name%>&id=<%=noticeVo.getId() %>" class="col" download><img src="/noticeImage/<%=id+"/"+name %>" alt="project-sample1" width="100" height="100"></a>
	<% }else{ %>
	<a href="/FileLoad?filename=<%= name%>&id=<%=noticeVo.getId() %>" download><%= name %><button type="button">다운로드</button></a>
<% 		

	}
	
} %>
                   
                   
                   
                   
                   
                   
                   
                   
                   
</body>
</html>