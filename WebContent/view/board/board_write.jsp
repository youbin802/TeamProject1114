<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../header.jsp" %>

     <div class="sub-section">
            <div class="sub-head">
                <a href="board.html"><h2>게시글 작성</h2></a>
            </div>
                <form action="/boardWrite" method="post">
                <input type="hidden" class="form-control" name="userId" value="<%= userId %>">
                    <div class="mb-3">
                        <label>제목</label>
                        <input type="text" class="form-control" name="title">
                    </div>
                    <div class="mb-3">
                        <label>내용</label>
                        <textarea class="form-control" rows="5" name="content"></textarea>
                    </div>
                    <div class="mb-3">
                        <label>작성자</label>
                        <input type="text" class="form-control" value="<%= name %>" readonly="readonly">
                    </div>
                    <div class="col-auto">
                        <button type="submit" class="btn">작성</button>
                    </div>
                </form>
        </div>
        
<%@ include file="../../footer.jsp" %>
        