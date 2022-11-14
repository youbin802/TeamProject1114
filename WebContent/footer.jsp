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
     
</body>
</html>