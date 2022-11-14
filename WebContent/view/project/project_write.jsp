<%@page import="dao.ProjectDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	ProjectDAO projectDao = new ProjectDAO();
	int projectMaxId = projectDao.getMaxId();
%>

<%@ include file="../../header.jsp" %>
<script src="./js/project_write.js"></script>
<link rel="stylesheet" href="<%= path %>/css/project_write.css">
     <div class="sub-section">
            <div class="sub-head">
                <a href="project.jsp"><h2>작품실</h2></a>
            </div>
            <div class="project-contain">
                <div class="project-img-box">
                    <div class="project-main-img">
                        
                    </div>
                    <div class="project-img-slide">
                        <div class="project-img-slide-images">
                            
                        </div>
                    </div>
                </div>
                <div class="project-explain-box">
                	<form action="/ProjectImgUpload" method="POST" enctype="multipart/form-data">
	                    <input type="hidden" name="projectMaxId" value="<%= projectMaxId %>">
	                    <input type="hidden" name="userId" value="<%= userId %>">
	                    <div class="head-explain">
	                        <div class="min-p">
	                            <div class="mb-3">
	                                <label class="form-label">작품 제목</label>
	                                <input type="text" class="input" name="title" placeholder="제목을 입력하세요">
	                            </div>
	                        </div>
	                    </div>
	                    <div class="explain-text-box scroll-layout">
	                        <textarea class="form-control" rows="15" name="content"></textarea>
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