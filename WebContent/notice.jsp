<%@page import="dao.NoticeDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="vo.NoticeVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    NoticeDAO noticeDao = new NoticeDAO();
	ArrayList<NoticeVO> noticeList = noticeDao.getNoticeList();
%>
    


<%@ include file="header.jsp" %>
    
        <div class="sub-section">
            <div class="sub-head">
                <a href="notice.html">
                    <h2>공지모음실</h2>
                    <a href="noticetest.jsp">작성하기 ></a>
                </a>
            </div>
            <div class="sub-notice scroll-layout">
             <% for(NoticeVO notice : noticeList) { %>
                <div class="sub-notice-box">
                    <img src="/noticeImage/<%=notice.getId() +"/"+ notice.getContent() %>" alt="project-sample1">
                </div>
     		  <% }  %>

            </div>
        </div>
    </div>
    <div class="big-notice">
        <div class="notice-pop">
            <h2>
                이것은 제목입니다.
                <div class="close">
                    X
                </div>
            </h2>
            <p><span>내용</span></p>
            <img src="./semi-imgs/pop_0717s.jpg" alt="">
        </div>
    </div>
    <link rel="stylesheet" href="css/notice.css">
    <script src="./js/jquery-3.6.0.min.js"></script>
    <script src="./js/notice.js"></script>
    
<%@ include file="footer.jsp" %>