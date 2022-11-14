package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.NoticeDAO;
import vo.NoticeVO;

public class NoticeController implements Controller {

	@Override
	public View process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String type = request.getParameter("type");
	    NoticeDAO noticeDao = new NoticeDAO();

			if(id != null) {
				NoticeVO noticeVo = noticeDao.getNotice(id);
				

				ArrayList<String> ImgList = noticeDao.getNoticeImgs(id);
				request.setAttribute("noticeVo", noticeVo);
				return new View("/view/notice/notice_read.jsp");

			} else if (type!=null) {
				System.out.println(type);
				if(type.equals("all")) {
					System.out.println("모두 가져오기 ======");
					ArrayList<NoticeVO> noticeList = noticeDao.getNoticeList(null);
					ArrayList<String> noticeType = noticeDao.getNoticeType();
					
					request.setAttribute("noticeList", noticeList);	
					request.setAttribute("noticeType", noticeType);	
					return new View("/view/notice/notice.jsp");
				}else {
					
					System.out.println("타입이 있다==================");
					ArrayList<NoticeVO> noticeList = noticeDao.getNoticeList(type);
					ArrayList<String> noticeType = noticeDao.getNoticeType();
					
					request.setAttribute("noticeList", noticeList);	
					request.setAttribute("noticeType", noticeType);						
					
					return new View("/view/notice/notice.jsp");
				}
			}
			
			
			else {

				System.out.println("타입이 없다==================");
				ArrayList<NoticeVO> noticeList = noticeDao.getNoticeList(null);
				ArrayList<String> noticeType = noticeDao.getNoticeType();
				
				request.setAttribute("noticeList", noticeList);	
				request.setAttribute("noticeType", noticeType);	
				return new View("/view/notice/notice.jsp");
			}
	}

}
