<%@page import="vo.NoticeVO"%>
<%@page import="dao.NoticeDAO"%>
<%@page import="dao.BoardDAO"%>
<%@page import="vo.BoardVO"%>
<%@page import="org.jsoup.select.Elements"%>
<%@page import="org.jsoup.Jsoup"%>
<%@page import="dao.ProjectDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="vo.ProjectVO"%>
<%@page import="org.jsoup.nodes.Document"%>
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
	Document doc = Jsoup.connect("https://yy-h.goesn.kr/yy-h/main.do").get();
	Elements posts = doc.body().getElementsByClass("meal_list");
	String[] menu = posts.text().split(" ");
	

	//System.out.print(posts.text().replaceAll("\\(.*\\)", ""));
//	String[] menu = posts.text().split("\\(.*\\)");

	ProjectDAO projectDao = new ProjectDAO();
	ArrayList<ProjectVO> projectList = projectDao.getProjectMain();

	String path = request.getContextPath();

	
	BoardDAO boardDao = new BoardDAO();
	int start = 0;
	int size= 0;
	ArrayList<BoardVO> boardList = boardDao.getBoardList(start, size);
	
	NoticeDAO noticeDao = new NoticeDAO();
	ArrayList<NoticeVO> noticeList = noticeDao.getNoticeListMain();
	
%>
<%@ include file="header.jsp" %>
        <div class="main-section">
            <div class="main-card-contain">
                <div class="main-card">
                    <a href="<%= path %>/yd-world/board"><h2>게시판</h2></a>
                    <div class="view-box">
                        <div class="mainpage-board-box">
                        
                            <a href="#">
                                <p>수학 시험범위 아는사람</p>
                                <p>2022.02.02 10:20</p>
                            </a>
                  
                        </div>
                    </div>
                </div>
                <div class="main-card">
                       <a href="<%= path %>/yd-world/project"><h2>작품실</h2></a>
                    <div class="view-box main-project">
                           
              	<% for(ProjectVO project : projectList) { %>
	                <div class="project-box">
              	 <a href="project_read.jsp?id=<%=project.getId() %>">
	                    <img src="/projectImage/<%=project.getId() +"/"+ project.getContent() %>" alt="project-sample1"></a>
	                    <div class="project-text">
	                        <p><%= project.getTitle() %></p>
	                        <div class="min-p">
	                            <p><%= project.getName() %></p>
	                            <div class="good-wrap">
	                                <i class="fa fa-heart" style="color:#d44e4e;"></i>
	                                <p class="text-center"><%= project.getHeart() %></p>
	                            </div>
	                        </div>
	                    </div>
                </a>
	                </div>
           
              <% }  %>
           
                  
                  
                  
                    </div>
                </div>
            </div>
            <div class="main-card-contain">
                <div class="main-card">
                    <a href="<%= path %>/yd-world/notice"><h2>공지모음실</h2></a>
                    <div class="view-box main-notice">
                       <% for(NoticeVO notice : noticeList) { %>
                        <div class="notice-box">
                            <img src="/noticeImage/<%= notice.getId() %>/<%= notice.getTitle() %>" alt="">
                            <div class="notice-text">
                                <p><%= notice.getContent() %></p>
                                <button class="btn btn-outline-info">자세히 보기</button>
                            </div>
                        </div>
                         <% }  %>
                    </div>
                </div>
                <div class="main-card">
                    <a href="/yd-world/honor"><h2>명예의 전당</h2></a>
                    <div class="view-box">
                        
                    </div>
                </div>
                <div class="main-card lunch-card">
                    <a href="#"><h2>급식2</h2></a>
                    <div class="view-box">
                        <ul class="lunch-box">
                        <%
                        	for(int i = 0; i < menu.length; i++) {
                        		if(i%2==0) {
                        		   out.print(menu[i]);	
                        		    out.println("<br>");
                       			}
                       }

                        %>
                        </ul>
                        <div class="lunch-img">
							  <img src="<%= path %>/imgs/sale2.jpg" alt="project-sample1" style="width:100%"></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="./js/index.js"></script>
       
<%@ include file="footer.jsp" %>
</body>
</html>