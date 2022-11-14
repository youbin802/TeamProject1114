package dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import vo.ReplyVO;

@WebServlet("/getReplyList")
public class getReplyList extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public getReplyList() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String boardId = request.getParameter("boardId");
		System.out.println("댓글 가져오기 함수 in");
		System.out.println(boardId);
		BoardDAO dao =new BoardDAO();
		ArrayList<ReplyVO> list = dao.getReplyList(boardId);
		JSONArray jarr = new JSONArray();
		
		JSONObject replyArr = new JSONObject();
		for(ReplyVO vo : list) {
			JSONObject json = new JSONObject();
			System.out.println(vo.getContent());
			json.put("id", vo.getId());
			json.put("content", vo.getContent());
			json.put("writer_id", vo.getWriter_id());
			json.put("writer_date", vo.getWriter_date());
			json.put("writer_name", vo.getWriter_name());
			jarr.add(json);
		}
		response.setCharacterEncoding("utf-8");
		System.out.println("여기");
		System.out.println(jarr.toJSONObject(jarr));
		System.out.println(jarr.toString());
		System.out.println(jarr);
		
//		response.getWriter().print(jarr.toJSONObject(jarr));
		PrintWriter out = response.getWriter();
		out.print(jarr.toString());
//		response.getWriter().append("Servedat:").append(request.getContextPath());
	}
}
