package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import dao.ProjectDAO;
import vo.BoardVO;
import vo.ProjectVO;

public class BoardController implements Controller {

	@Override
	public View process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String id = request.getParameter("id");
	System.out.println(id);
	
	String regEx = "[0-9]";

	
	 
	BoardDAO boardDao =new BoardDAO();
	
		System.out.println("아이디"+id);
		// 작품실 페이지인 경우
		if(id == null) {			
			return new View("/view/board/board.jsp");
		} 
		else {
			String match = "[^0-9]";
			id = id.replaceAll(match, "");
			System.out.println("get요청 받은 아이디22 ===>"+ id);			
			BoardVO boardVo = boardDao.getBoard(id);
			request.setAttribute("boardVo", boardVo);
			
			
			return new View("/view/board/board_read.jsp");
		}
	}

}
