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
import vo.NoticeVO;
import vo.ReplyVO;

/**
 * Servlet implementation class getNotice
 */
@WebServlet("/getNotice")
public class getNotice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getNotice() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String noticeId = request.getParameter("noticeId");
		System.out.println("공지 가져오기 함수 in");
		System.out.println(noticeId);
		NoticeDAO noticeDao = new NoticeDAO();
		NoticeVO vo = noticeDao.getNotice(noticeId);
		

		ArrayList<String> ImgList = noticeDao.getNoticeImgs(noticeId);
		JSONArray Noticejarr = new JSONArray();
		JSONObject njson = new JSONObject();

		System.out.println( vo.getId()+""+ vo.getTitle()+""+ vo.getContent()+""+ vo.getWriter_id()+""+ vo.getWriter_date());
		njson.put("id", vo.getId());
		njson.put("title", vo.getTitle());
		njson.put("content", vo.getContent());
		njson.put("writer_id", vo.getWriter_id());
		njson.put("writer_date", vo.getWriter_date());
		Noticejarr.add(njson);
		
		JSONArray Imgjarr = new JSONArray();
		
		System.out.println(ImgList);
		for(int i =0; i<ImgList.size(); i++) {
			System.out.println("역디ㅏ여기");
			System.out.println(ImgList.get(i));
			JSONObject json = new JSONObject();
			json.put("name", ImgList.get(i));
			Imgjarr.add(json);

		}
		


		response.setCharacterEncoding("utf-8");
		System.out.println("여-===================================기");
		System.out.println(Imgjarr.toString());
		System.out.println(Noticejarr.toString());
		System.out.println(Noticejarr.toString()+"==="+Imgjarr.toString());

		
		
//		response.getWriter().print(jarr.toJSONObject(jarr));
		PrintWriter out = response.getWriter();
		out.print(Noticejarr.toString()+"==="+Imgjarr.toString());
	}

}
