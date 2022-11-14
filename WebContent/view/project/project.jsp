<%@page import="dao.ProjectDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="vo.ProjectVO"%>
<%@page import="dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% 
ArrayList<ProjectVO> projectList = (ArrayList<ProjectVO>)request.getAttribute("projectList"); 

String path = request.getContextPath();

%>

<%@ include file="../../header.jsp" %>
      <div class="sub-section">
            <div class="sub-head">
                <a href="project.jsp"><h2>작품실</h2></a>
                <a href="<%= path %>/yd-world/project/writeForm">작성하기 ></a>
            </div>
            <div class="sub-project scroll-layout">
               
              	<% for(ProjectVO project : projectList) { %>
	                <div class="project-box">
              	 <a href="<%= path %>/yd-world/project?id=<%=project.getId() %>">
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
            <link rel="stylesheet" href="<%=path %>/css/project.css">
            
<%@ include file="../../footer.jsp" %>
</body>
</html>