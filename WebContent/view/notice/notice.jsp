<%@page import="dao.NoticeDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="vo.NoticeVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    ArrayList<NoticeVO> noticeList = (ArrayList<NoticeVO>)request.getAttribute("noticeList");
    ArrayList<String> noticeType= (ArrayList<String>)request.getAttribute("noticeType");
    String path = request.getContextPath();
%>
    


<%@ include file="../../header.jsp" %>
    
        <div class="sub-section">
            <div class="sub-head">
                <a href="notice.html">
                    <h2>공지모음실</h2>
                    <a href="<%= path %>/yd-world/notice/writeForm">작성하기 ></a>
                </a>
            </div>
            <div class="d-flex mb-3">
          	  <% for(String selectText : noticeType) { %>
            <a href="/yd-world/notice?type=<%=selectText%>"><button type="button" class="btn btn-dark mr-2"><%= selectText %></button></a>
              <% }  %>
            </div>
            <div class="sub-notice scroll-layout">
             <% for(NoticeVO notice : noticeList) { %>
                <div class="sub-notice-box">
                	<input type="hidden" id="noticeId" value=<%= notice.getId() %> >
                    <img src="/noticeImage/<%=notice.getId() +"/"+ notice.getContent() %>" alt="project-sample1">
                    <p><%= notice.getTitle() %></p>
                      <form action="/noticeDelete" method="POST">
	                      <input type="hidden" name="noticeId" id="noticeId" value="<%= notice.getId() %>">
	                      <button>삭제하기</button>
						</form>
					<a href="<%= path %>/yd-world/notice/updateForm?id=<%=notice.getId()%>"><button>수정하기</button></a>
                </div>
     		  <% }  %>

            </div>
        </div>
    </div>
    <div class="big-notice">
        <div class="notice-pop">
            <h2>
               <p> 이것은 제목입니다.</p>
                <div class="close">
                    X
                </div>
            </h2>
            <p class="content"><span>내용</span></p>
            <div class="img-zone">
            </div>
          
        </div>
    </div>
    <link rel="stylesheet" href="<%=path %>/css/notice.css">
    <script src="<%=path %>/js/jquery-3.6.0.min.js"></script>
    <script src="<%=path %>/js/notice.js"></script>
    
<%@ include file="../../footer.jsp" %>