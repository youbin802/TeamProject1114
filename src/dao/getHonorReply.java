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
import vo.HonorReplyVO;
import vo.HonorVO;
import vo.ReplyVO;


@WebServlet("/getHonorReply")
public class getHonorReply extends HttpServlet {
	private static final long serialVersionUID = 1L;
        

    public getHonorReply() {
        super();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		String honorId = request.getParameter("id");
		System.out.println("서블릿에서 댓글 아이디zzzz"+honorId);
		HonorDAO dao = new HonorDAO();
		ArrayList<HonorReplyVO> list = dao.getReplyList(honorId);
		PrintWriter out = response.getWriter();
		JSONArray jarr = new JSONArray();
		System.out.println("명전 댓글 리스트 가져오는 함수 들어옴");
		
		
		
		
		
		for(HonorReplyVO vo : list) {
			JSONObject json = new JSONObject();
			System.out.println(vo.getContent());
			json.put("name", vo.getName());
			json.put("writerId", vo.getWriterId());
			json.put("replyId", vo.getReplyId());
			json.put("content",vo.getContent());
			json.put("date", vo.getDate());
			jarr.add(json);
		}
		System.out.println(jarr.toString());
		out.print(jarr.toString());
		
	}

}
