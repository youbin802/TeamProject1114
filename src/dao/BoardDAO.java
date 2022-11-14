package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import util.JdbcUtil;
import vo.BoardVO;
import vo.ReplyVO;

public class BoardDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	

	final String getBoards = "select * from board order by write_date desc";
	final String getBoardList = "select * from board order by write_date desc limit ?,?";
	final String getReadBoard = "select * from board b, users u where b.id = ? and b.writer_id = u.id";
	
	final String insertReply = "insert into reply(id, content, board_id, writer_id, write_date) values( (select nvl(max(id),0)+1 from reply), ?,?,?,?)";
	final String getReplys  = "select r.id as id, r.content as content, r.board_id as board_id, r.writer_id as writer_id, u.name as writer_name, r.write_date as write_date\r\n"
			+ "from reply r, users u\r\n"
			+ "where board_id =? and r.writer_id = u.id order by write_date desc";

	final String insertBoard = "insert into board(id,title, content,  writer_id, write_date) values( (select nvl(max(id),0)+1 from board), ?,?,?,?)";
	final String updateBoard = "update board set title=?, content =?, write_date =? where id =? ";
	final String deleteBoard = "delete from board where id= ?";
	
	final String getBoardLimit = "SELECT * FROM (SELECT A.*, ROWNUM AS RNUM FROM (SELECT * FROM board ORDER BY id asc) A WHERE ROWNUM <= ?) WHERE RNUM >= ?";
	final String getBoardLimit2= 	 "select * from "
			+ "(select rownum rn, no, name, passwd, subject, content, reg_date, readcount, ip from "
			+ "(select * from exboard order by no desc)) where rn between ? and ?";
	
	final String modReply ="update reply set content =?, write_date =? where id = ?";
	final String delReply = "delete from reply where id= ?";



	public int insertBoard(BoardVO vo) {
		int n = 0;
		
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(insertBoard);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3,vo.getWriter_id());
			
			LocalDateTime now = LocalDateTime.now();
			String formateNow = now.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));
			pstmt.setString(4,formateNow);
			n = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}
	
	public BoardVO getBoard(String id) {
		BoardVO vo =null;
		try {
			con  =JdbcUtil.getConnection();
			pstmt= con.prepareStatement(getReadBoard);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int voId = Integer.parseInt(id);
				String name = rs.getString("name"); 
				String title = rs.getString("title");
				String content = rs.getString("content");
				String writer_id = rs.getString("writer_id");
				String writer_date = rs.getString("write_date");
				vo = new BoardVO(name, voId, title, content, writer_id, writer_date);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			}
		return vo;
	}
	
	public ArrayList<BoardVO> getBoardList(int start, int size) {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		System.out.println("게시판 리스트 가져오는 곳");
		System.out.println(start +"===="+size);
		try {
			con  =JdbcUtil.getConnection();
			pstmt= con.prepareStatement(getBoardLimit);
			pstmt.setInt(2, start);
			pstmt.setInt(1, size);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int id = Integer.parseInt(rs.getString("id"));

				System.out.println(id);
				
				String title = rs.getString("title");
				String content = rs.getString("content");
				String writer_id = rs.getString("writer_id");
				String writer_date = rs.getString("write_date");
				BoardVO vo = new BoardVO("none", id, title, content, writer_id, writer_date);
				System.out.println("게시판 아이디 "+id);
				list.add(vo);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			}
		return list;
	}
	
	public ArrayList<BoardVO> getBoardMain() {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		System.out.println("here");
		try {
			con  =JdbcUtil.getConnection();
			pstmt= con.prepareStatement(getBoards);

			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int id = Integer.parseInt(rs.getString("id"));

				System.out.println(id);
				
				String title = rs.getString("title");
				String content = rs.getString("content");
				String writer_id = rs.getString("writer_id");
				String writer_date = rs.getString("write_date");
				BoardVO vo = new BoardVO("none", id, title, content, writer_id, writer_date);
				list.add(vo);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			}
		return list;
	}
	
//	replay dao
	
	public int insertReply(String boardId, String content, String userId) {
		int n = 0;
		
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(insertReply);
			pstmt.setString(1, content);
			pstmt.setString(2, boardId);
			pstmt.setString(3,userId);
			
			LocalDateTime now = LocalDateTime.now();
			String formateNow = now.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));
			pstmt.setString(4,formateNow);
			n = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}
	
	public ArrayList<ReplyVO> getReplyList(String boardId) {
		ArrayList<ReplyVO> list = new ArrayList<ReplyVO>();
		System.out.println("here");
		System.out.println("here");
		try {
			con  =JdbcUtil.getConnection();
			pstmt= con.prepareStatement(getReplys);
			pstmt.setInt(1, Integer.parseInt(boardId));
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int id = Integer.parseInt(rs.getString("id"));
				String content = rs.getString("content");
				String writer_id = rs.getString("writer_id");
				String writer_date = rs.getString("write_date");

				String writer_name = rs.getString("writer_name");
				ReplyVO vo = new ReplyVO(id, content, id, writer_id, writer_date, writer_name);
				list.add(vo);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			}
		return list;
	}
	
	public int updateBoard(String boardId, String title, String content) {
		int n=0;
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(updateBoard);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			LocalDateTime now = LocalDateTime.now();
			String formateNow = now.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));
			pstmt.setString(3,formateNow);
			pstmt.setString(4, boardId);
			n = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}

	public int delete(String id) {
		int n =0;
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(deleteBoard);
			pstmt.setString(1, id);
			n = pstmt.executeUpdate();
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		return n;
	}

	public int modReply(String replyId, String content) {
		int n=0;
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(modReply);
			pstmt.setString(1, content);
			LocalDateTime now = LocalDateTime.now();
			String formateNow = now.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));
			pstmt.setString(2,formateNow);
			pstmt.setString(3, replyId);
			n = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}

	public int delReply(String replyId) {
		int n=0;
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(delReply);
			pstmt.setString(1, replyId);
			n = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}
}
