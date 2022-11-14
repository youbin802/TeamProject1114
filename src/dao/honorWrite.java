package dao;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.BoardVO;

/**
 * Servlet implementation class honorWrite
 */
@WebServlet("/honorWrite")
public class honorWrite extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public honorWrite() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		System.out.println("here");
		String content = request.getParameter("content");
		String userId = request.getParameter("userId");
		String noticMaxId = request.getParameter("noticMaxId");
		HonorDAO dao =new HonorDAO();
	
		int n = dao.insertHonor(noticMaxId,content, userId);
		System.out.println(n);
		
		response.sendRedirect("/yd-world/honor");
	}

}
