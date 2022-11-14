package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import util.JdbcUtil;
import vo.BoardVO;
import vo.HonorReplyVO;
import vo.HonorVO;
import vo.ReplyVO;

public class HonorDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	final String getHonor = "SELECT * FROM (SELECT A.*, ROWNUM AS RNUM FROM \r\n" + 
			"(SELECT * FROM honor  ORDER BY id asc) A WHERE ROWNUM <= ?) WHERE RNUM >= ?";
	final String getMaxId =  "select max(id)+1 as max from honor";
	final String getReply = "select h.id as honorId, u.name as name, u.id as writer_id, h.id as reply_id,h.content as content, h.write_date as write_date from HonorReply h, users u where h.honor_id = ? and u.id= h.writer_id";
	final String insertHonor = "insert into honor values (?,?,?,?)";
	final String honorMod = "update honor set content =? , writer_date = ? where id =?";
	public HonorVO getHonor(int now) {
		HonorVO vo =null;
		try {
			con  =JdbcUtil.getConnection();
			pstmt= con.prepareStatement(getHonor);
			
			pstmt.setInt(1, now);
			pstmt.setInt(2, now);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int id =rs.getInt("id");
				String content = rs.getString("content");
				String writer_id = rs.getString("writer_id");
				String writer_date = rs.getString("writer_date");
				vo = new HonorVO(id, content, writer_id, writer_date);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			}
		return vo;
	}
	
	public HonorVO getHonorReply(int now) {
		HonorVO vo =null;
		try {
			con  =JdbcUtil.getConnection();
			pstmt= con.prepareStatement(getHonor);
			
			pstmt.setInt(1, now);
			pstmt.setInt(2, now);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int id =rs.getInt("id");
				String content = rs.getString("content");
				String writer_id = rs.getString("writer_id");
				String writer_date = rs.getString("writer_date");
				vo = new HonorVO(id, content, writer_id, writer_date);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			}
		return vo;
	}
	
	public ArrayList<HonorReplyVO> getReplyList(String honorId) {
		ArrayList<HonorReplyVO> list = new ArrayList<HonorReplyVO>();
		try {
			System.out.println("댓글 아이디 ===============>"+honorId);
			con  =JdbcUtil.getConnection();
			pstmt= con.prepareStatement(getReply);
			pstmt.setInt(1, Integer.parseInt(honorId));
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				HonorReplyVO vo = null;
				String name = rs.getString("name");
				String writerId = rs.getString("writer_id");
				int replyId = rs.getInt("reply_id");
				String content = rs.getString("content");
				String writer_date = rs.getString("write_date");
				vo = new HonorReplyVO(Integer.parseInt(honorId), name, writerId, replyId, content, writer_date);
				list.add(vo);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			}
		return list;
	}
	
	public int getMaxId() {
		System.out.println("겟믹스 컴온 =============");
		int id = 0;
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(getMaxId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				id = rs.getInt("max");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("이거라구욧!!!!!!!!!!!!!"+id);
		return id;
	}

	public int insertHonor(String maxid, String content, String userId) {
		int n = 0;
		
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(insertHonor);
			pstmt.setString(1, maxid);
			pstmt.setString(2, content);
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

	public int honorMod(String honorId, String content) {
		int n=0;
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(honorMod);
			pstmt.setString(1, content);
			LocalDateTime now = LocalDateTime.now();
			String formateNow = now.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));
			pstmt.setString(2,formateNow);
			pstmt.setString(3, honorId);
			n = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}
}
