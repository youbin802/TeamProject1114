package dao;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class delReply
 */
@WebServlet("/delReply")
public class delReply extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public delReply() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("삭제 함수 들어옴");
		String replyId = request.getParameter("replyId");

		BoardDAO dao =new BoardDAO();
		int n = dao.delReply(replyId);
		String res = (n>0) ? "true" : "false";

		response.getWriter().print(res);

		response.getWriter().append("Served at: ").append(request.getContextPath());

	}

}
