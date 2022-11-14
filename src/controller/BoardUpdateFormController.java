package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import vo.BoardVO;

public class BoardUpdateFormController implements Controller {

	@Override
	public View process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String boardId = request.getParameter("id");
		BoardDAO boardDao = new BoardDAO();
		BoardVO boardVo = boardDao.getBoard(boardId);
		
		request.setAttribute("boardVo", boardVo);
		return new View("/view/board/board_modify.jsp");
	}

}
