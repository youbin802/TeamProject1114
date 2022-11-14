<%@page import="vo.UserVO"%>
<%@page import="dao.UserDAO"%>
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
	UserDAO dao = new UserDAO();
	UserVO vo = dao.userData(request);
	String name = vo.getName();
	String profile_img =vo.getProfile_img();
	
%>

	<a href="loginForm.jsp">로그인하기</a>
	<a href="project_write.jsp">글작성하기</a>
	---메인페이지---W
	<hr>
	마이페이지
	이름  : <%= name %>
	<img src="imgs/<%= profile_img %>.jpg" alt="" witdh=50 height=50 style="border-radius: 50%;">
	
	
	
</body>
</html>