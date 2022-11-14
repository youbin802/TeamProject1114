<%@page import="dao.ProjectDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="vo.ProjectVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../header.jsp" %>
<%
	ProjectVO projectVo = (ProjectVO) request.getAttribute("projectVo");
	ArrayList<String> ImgList = (ArrayList<String>)request.getAttribute("ImgList"); 
	int projectId =projectVo.getId();
	String path = request.getContextPath();
%>
<script src="<%= path %>/js/project_write.js"></script>

<link rel="stylesheet" href="<%= path %>/css/project_write.css">
        <script src=js/jquery-3.6.0.min.js"></script>
        <script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="<%= path %>/js/project_read.js"></script>
        <link rel="stylesheet" href="<%= path %>/css/project_read.css">

     <div class="sub-section">
            <div class="sub-head">
                <a href="project.jsp"><h2>작품실</h2></a>
            </div>
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
                	<form action="/ProjectImgUploadMod" method="POST" enctype="multipart/form-data">
	                    <input type="text" name="projectId" value="<%= projectId %>">
	                    <div class="head-explain">
	                        <div class="min-p">
	                            <div class="mb-3">
	                                <label class="form-label">작품 제목</label>
	                                <input type="text" class="input" name="title" placeholder="제목을 입력하세요" value="<%= projectVo.getTitle() %>">
	                            </div>
	                        </div>
	                    </div>
	                    <div class="explain-text-box scroll-layout">
	                        <textarea class="form-control" rows="15" name="content"><%= projectVo.getContent() %></textarea>
	                    </div>
	                    <div class="explain-submit">
	                        <div>
	                            <label class="form-label">이미지 업로드</label>
	                            <input type="file" name="file" class="form-control upload-form" id="exampleFormControlInput2" multiple>
	                        </div>
	                        <button type="submit" class="btn">작성</button>
	                    </div>
                    </form>
                </div>
            </div>
        </div>

<%@ include file="../../footer.jsp" %>