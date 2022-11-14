package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoticeUpdateFormController implements Controller {

	@Override
	public View process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("수정 공지 로 들어옴");
		String id = request.getParameter("id");
		request.setAttribute("id", id);
		return new View("/view/notice/notice_mod.jsp");
	}

}
