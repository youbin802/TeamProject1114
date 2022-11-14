<%@page import="org.apache.catalina.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

    <div class="login-popup">
        <div class="login-popup-content">
            <div class="close">
                X
            </div>
            <form action="loginOK.jsp" method="post">
                <input type="text" placeholder="아이디" class="form-control" name="id">
                <input type="text" placeholder="비밀번호" class="my-2 form-control" name="pw">
                <button type="submit" class="btn btn-success my-1">로그인</button>
            </form>
        </div>
    </div>
         <div class="modal modal-write" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title">명예의 전당 글 작성하기</h5>
              <button type="button" class="btn btn-primary close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <form action="/honorMod" method="post">
                <div class="modal-body">
                <input type="hidden" name="userId" id="userIdInput">
                <textarea id="w3review"  name="content" rows="4" cols="60"></textarea>
                </div>
                <div class="modal-footer">
                <button type="submit" class="btn btn-primary">작성</button>
                </div>
            </form>
          </div>
        </div>
      </div>
        <div class="modal modalMod" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title">명예의 전당 글 작성하기</h5>
              <button type="button" class="btn btn-primary close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <form action="/honorMod" method="post">
                <div class="modal-body">
                <input type="hidden" name="userId" id="userIdInput">
                <input type="text" name="honorId" id="honorIdInput">
                    <textarea id="w3review" class="contentTextArea" name="content" rows="4" cols="60"></textarea>
                </div>
                <div class="modal-footer">
                <button type="submit" class="btn btn-primary">작성</button>
                </div>
            </form>
          </div>
        </div>
      </div>
</body>
</html>