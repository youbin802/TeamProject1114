package dao;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/boardUpdate")
public class boardUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public boardUpdate() {
        super();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String boardId = request.getParameter("boardId");
		BoardDAO boardDao = new BoardDAO();
		
		int n = boardDao.updateBoard(boardId, title, content);
		response.sendRedirect("/yd-world/board?id="+boardId);
	}

}
