<%@page import="vo.UserVO"%>
<%@page import="dao.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="/bootstrap-5.1.3-dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="/bootstrap-5.1.3-dist/css/bootstrap.css">
  
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/youbin.css">
</head>
<body>
<%

	UserDAO dao = new UserDAO();
	UserVO vo = dao.userData(request);

	String name = vo.getName();
	String profile_img =vo.getProfile_img();
	String userId = vo.getId();
	int identity = vo.getIdentity();
	
%>

    <div class="container">
        <header>
            <div class="logo">
				<a href="/"><img src="image/menu_project02.png" alt=""></a>
            </div>

            <nav>
                <a href="board.jsp">
                    <img src="<%= request.getContextPath() %>/image/menu_project02.png" alt="">
                </a>
                <a href="#">
                    <img src="<%= request.getContextPath() %>/image/menu_project02.png" alt="">
                </a>
                <a href="#">
                    <img src="<%= request.getContextPath() %>/image/menu_notice02.png" alt="">
                </a>
                <a href="#">
                    <img src="<%= request.getContextPath() %>/image/menu_honor02.png" alt="">
                </a>
            </nav>

            <div class="footer-profile-nav">
                <div class="profile-img">
                <%if(name==null || name.isEmpty()) {%>
                    <img src="<%= request.getContextPath() %>/semi-imgs/profile.png" alt="">
                          <%} else {%>
                    <img src="imgs/<%= profile_img %>.jpg" alt="" witdh=50 height=50 style="border-radius: 50%;">
	  <%} %>
                </div>
                <!-- 임시 -> 로그아웃 아이콘으로 변경 해야 함 -->
                <%if(name==null || name.isEmpty()) {%>	
                <div class="login">
                        로그인
                </div>       	
                <%} else {%>
                 <div class="logout">
                 	<%= name %><br>
                    <a href="logout.jsp">
                        로그아웃
                    </a>
                </div>
                <%} %>
         
            </div>
        </header>
        
            <script src="js/jquery-3.6.0.min.js"></script>
    <script src="js/login.js"></script>
           <script src="./js/index.js"></script>