<%@page import="dao.NoticeDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src = "http://code.jquery.com/jquery-latest.js"></script>
<script>
    var cnt = 1;
    function fn_addFile(){
        $("#d_file").append("<br>" + "<input type='file' name='file" + cnt + "' />");
        cnt++;
    }
    
</script>

<%@ include file="header.jsp" %>
<%
	NoticeDAO noticeDao = new NoticeDAO();
	int noticeMaxId= noticeDao.getMaxId();
%>
    <h1>파일 업로드 하기</h1>
    <form method="post" action=ImgUpload02 enctype="multipart/form-data">
        <label>아이디 : </label>
         	<input type="text" name="noticeId" value="<%= noticeMaxId %>"><br>
            <input type="text" name="writerId" value="<%= userId %>"><br>
       
            <input type="button" value="파일 추가" onClick="fn_addFile()"><br>
            
            <div id="d_file">
            
            </div>
            
            <label>설명</label>
            <textarea rows="5" cols="10" name="content"></textarea>
            <button>업로드</button>
    </form>
</body>
</html>

<%@ include file="footer.jsp" %>