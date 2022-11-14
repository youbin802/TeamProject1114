package dao;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.BoardVO;


@WebServlet("/boardWrite")
public class boardWrite extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public boardWrite() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		System.out.println("here");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String userId = request.getParameter("userId");
		BoardDAO boardDao = new BoardDAO();
		BoardVO boardVo = new BoardVO("none",0, title, content, userId, "none");
		System.out.println(title+""+ content+""+ userId);
		int n = boardDao.insertBoard(boardVo);
		System.out.println(n);
		
		response.sendRedirect("/yd-world/board");
	}

}
