package dao;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class noticeDelete
 */
@WebServlet("/noticeDelete")
public class noticeDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public noticeDelete() {
        super();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id= request.getParameter("noticeId");
		System.out.println("삭제로 들어옴");
		System.out.println(id);
		NoticeDAO dao = new NoticeDAO();
		System.out.println("delete");
		int n = dao.delete(id);
		
		
		response.sendRedirect("/yd-world/notice");
	}

}
