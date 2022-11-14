package dao;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class projectDelete
 */
@WebServlet("/projectDelete")
public class projectDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public projectDelete() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id= request.getParameter("projectId");
		System.out.println("삭제로 들어옴");
		System.out.println(id);
		ProjectDAO dao =new ProjectDAO();
		System.out.println("delete");
		int n = dao.delete(id);
		
		
		response.sendRedirect("/yd-world/project");
	}

}
