package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import dao.ProjectDAO;
import vo.BoardVO;
import vo.ProjectVO;

public class BoardController2 implements Controller {

	@Override
	public View process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String id = request.getParameter("id");
		
	BoardDAO boardDao =new BoardDAO();
	System.out.println("board COntroller");
	
		System.out.println("아이디"+id);
		// 작품실 페이지인 경우
		if(id == null) {	
		
			int cnt = 13;
			int size = 3;
			String pageNum = request.getParameter("p");
			if(pageNum==null) pageNum = "1";
			
			//  첫 행 번호
			int currentP = Integer.parseInt(pageNum);
			
			// 한 페이지에 보여줄 페이지 블럭 시작번호 계산
			int start = (currentP-1)*size +1;
			System.out.println("페이징");
			System.out.println(start+":"+ size);
			
			ArrayList<BoardVO> boardList = boardDao.getBoardList(start, size);
			
			// 전체 페이지 수
			int pageCnt = cnt / size + (cnt%size==0?0:1);
			int pageBlock = 4;
			// 한 페이지에 보여줄 페이지 
			int endP = start + pageBlock -1;
			if(endP > pageCnt) endP = pageCnt;
		
			ArrayList<Integer> pageData  = new ArrayList<Integer>();
			pageData.add(start);
			pageData.add(pageBlock);
			pageData.add(endP);
			pageData.add(pageCnt);
			
			for(BoardVO vo : boardList) {
				System.out.println(vo.getContent());
				System.out.println(vo.getTitle());
				System.out.println(vo.getId());
			}
			request.setAttribute("pageData", pageData);	
			request.setAttribute("boardList", boardList);			
			return new View("/view/board/board.jsp");
		} 
		else {
			System.out.println("이곳으로");
			BoardVO boardVo = boardDao.getBoard(id);
			request.setAttribute("boardVo", boardVo);
			return new View("/view/board/board_read.jsp");
		}
	}

}
