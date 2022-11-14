package util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProjectDAO;

/**
 * Servlet implementation class projectHeart
 */
@WebServlet("/projectHeart")
public class projectHeart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public projectHeart() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      request.setCharacterEncoding("utf-8");
	      response.setContentType("text/html; charset=utf-8");
	      PrintWriter out = response.getWriter();
	      String id = request.getParameter("id");
	      ProjectDAO dao = new ProjectDAO();
	      int n= dao.updateProjectHeart(id);
	      
	      if(n>0) {
	    	  int heartCnt = dao.getHeartCnt(id);
	    	  out.print(heartCnt);
	      } else {
	    	  out.print("false");
	      }
	      

//		doGet(request, response);
	}

}
