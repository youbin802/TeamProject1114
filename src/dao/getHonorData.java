package dao;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import vo.HonorVO;

@WebServlet("/getHonorData")
public class getHonorData extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public getHonorData() {
        super();

    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("getHonorData in");
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String now = request.getParameter("now");
		System.out.println(now);
		HonorDAO dao = new HonorDAO();
		HonorVO vo = dao.getHonor(Integer.parseInt(now));
		
		JSONArray jarr = new JSONArray();
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		json.put("id", vo.getId());
		json.put("content", vo.getContent());
		json.put("writer_id", vo.getWriter_id());
		json.put("writer_date", vo.getWriter_date());
		jarr.add(json);

		System.out.println(jarr);
		System.out.println("여기서 로딩 다시22222222");
		out.print(jarr.toString());
		
	}

}
