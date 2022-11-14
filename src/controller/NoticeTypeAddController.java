package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.HonorDAO;
import dao.NoticeDAO;

public class NoticeTypeAddController implements Controller {

	@Override
	public View process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("여기 들어옴");
		
		String type = request.getParameter("type");
		System.out.println(type);
		NoticeDAO dao =new NoticeDAO();
		int contains = dao.containsCheck(type);
		if(contains == 0) {
			int id = dao.getMaxSelectId();
			String id2 = String.valueOf(id);
			int n= dao.insertSelect(id2, type);
				
			return new View("/yd-world/notice?type="+type);
		}
		else {
			return new View("/yd-world/notice");
		}
	}

}
