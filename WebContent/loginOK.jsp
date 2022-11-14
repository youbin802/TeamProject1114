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
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		UserDAO userDao = new UserDAO();
	    UserVO vo =  userDao.login(id, pw);
	    
	    if(vo==null) {
	 %>
	 		<script>
	 			alert("로그인 실패");
	 			location.go(-1);
	 		</script> 
	 <%   
	    } else {
	    	
	 %>
	 	<script>
	 			alert("로그인 성공");
	 			location.href="index.jsp";
	 		</script>
	 <% 
		    HttpSession user_session = request.getSession();
	    	user_session.setAttribute("id", vo.getId());
		    user_session.setAttribute("name", vo.getName());
		    user_session.setAttribute("deparment", vo.getDeparment());
		    user_session.setAttribute("grade_number", vo.getGrade_number());
		    user_session.setAttribute("class_number", vo.getClass_number());
		    user_session.setAttribute("profile_img", vo.getProfile_img());

		    user_session.setAttribute("identity", vo.getIdentity());
	    }
	    
	%>
</body>
</html>