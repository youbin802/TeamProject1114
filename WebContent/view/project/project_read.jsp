<%@page import="java.util.ArrayList"%>
<%@page import="vo.ProjectVO"%>
<%@page import="dao.ProjectDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../header.jsp" %>

<%
	ProjectVO projectVo = (ProjectVO) request.getAttribute("projectVo");
	ArrayList<String> ImgList = (ArrayList<String>)request.getAttribute("ImgList"); 
	int projectId =projectVo.getId();
	String path = request.getContextPath();
	
%>
        <script src=<%=path %>/js/jquery-3.6.0.min.js"></script>
        <script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="<%=path %>/js/project_read.js"></script>
        <link rel="stylesheet" href="<%=path %>/css/project_read.css">
        
        <div class="sub-section">
            <div class="sub-head">
                <a href="/yd-world/project">
                    <h2>작품실</h2>
                </a>
            </div>
            <input type="hidden" name="projectId" id="projectId" value="<%= projectId %>">
        <div class="project-contain">
                <div class="project-img-box col">
                    <div class="main-img">
                        <i class="fa fa-solid fa-angle-left leftBtn btn"></i>
                        <a href="#" class="col"><img src="/projectImage/<%=projectId+"/"+ ImgList.get(0) %>" alt="project-sample1"></a>
                        <i class="fa fa-solid fa-angle-right rightBtn btn"></i>
                    </div>
                    <div class="slide-img">
                        <!-- 메인 이미지 밑에 있는 슬라이드 이미지 -->
                        <div class="slide-box-img row-cols-3">
                           <% for(int i =0; i<ImgList.size(); i++) { 
                        System.out.print( ImgList.get(i)+"/n");
                        
                        %>
                        	<a href="#" class="col"><img src="/projectImage/<%=projectId+"/"+ ImgList.get(i) %>" alt="project-sample1"></a>
                   <%} %>
                        </div>
                    </div>
                </div>
  

                <div class="project-explain-box">
                    <div class="head-explain">
                        <div class="min-p">
                            <h3><%= projectVo.getTitle() %></h3>
                            <div class="good-wrap">
                                <i class="fa fa-heart" style="color: #000;"></i>
                                <form action="" name="heartCnt">
                                    <p class="text-center"><%= projectVo.getHeart() %></p>
                                </form>
                            </div>
                        </div>
                        <div class="d-flex justify-content-between">
	                        <p><%= projectVo.getName() + " "+ projectVo.getWriter_date() %></p>
	                          <% if(userId.equals(projectVo.getWriter_id()))  { %>
	                        <a href=<%=path + "/yd-world/project/updateForm?id=" +projectVo.getId() %>><button>수정하기</button></a>
	                        <form action="/projectDelete" method="POST">
	                           <input type="hidden" name="projectId" id="projectId" value="<%= projectId %>">
	                        	<button>삭제하기</button>
							</form>	                       
	                        <% } %>
                        </div>
                    </div>
                    <div class="explain-text-box">
	                        <%= projectVo.getContent() %>
                    </div>
                </div>
            </div>
        </div>

<%@ include file="../../footer.jsp" %>