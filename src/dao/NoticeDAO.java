package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import util.JdbcUtil;
import vo.BoardVO;
import vo.NoticeVO;
import vo.ProjectVO;

public class NoticeDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	final String insertNotice = "insert into notice values(?,?,?,?,?,?)";
	final String getMaxId =  "select max(id)+1 as max from notice";
	final String getNoticeList ="select n.id as id, n.title as title, u.name as name, nImg.imgName as nImg \r\n"
			+ "from noticeImgs nImg, notice n, users u\r\n"
			+ "where n.id = nImg.noticeId  and u.id = n.writer_id order by n.id desc";
	
	final String getNoticeTypeList ="select n.id as id, n.title as title, u.name as name, nImg.imgName as nImg \r\n"
			+ "from noticeImgs nImg, notice n, users u\r\n"
			+ "where n.id = nImg.noticeId  and u.id = n.writer_id and n.notice_type = ? order by n.id desc";
	final String insertNoticeImgs = "insert into noticeImgs(id, noticeId, imgName) values( (select nvl(max(id),0)+1 from noticeImgs), ?,?)";
	
	final String getNotice = "select * from notice where id = ?";
	
	final String getNoticeImgs ="select * from noticeImgs nImgs, notice n where n.id = ? and n.id= nImgs.noticeId";
	
	final String getNoticeType = "select * from noticeType";
	
	final String delNotice = "delete from notice where id= ?";
	
	final String updateNotice = "update notice set notice_type = ?, title= ?, content =?, write_date =? where id =?";
	final String delImgs = "delete from noticeImgs where noticeId = ?";
	final String mainNotice = "select n.id as id, n.content as content, nImg.imgname as img\r\n"
			+ "from notice n, noticeImgs nImg where n.id = nImg.noticeId";
	
	public ArrayList<String> getNoticeType() {
		ArrayList<String> noticeType = new ArrayList<String>();

		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(getNoticeType);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String selection = rs.getString("selection");
				noticeType.add(selection);
				System.out.println(selection+"여기 이것들");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("끝");
		return noticeType;
	}
	
	public NoticeVO getNotice(String id) {
		NoticeVO vo =null;
		try {
			con  =JdbcUtil.getConnection();
			pstmt= con.prepareStatement(getNotice);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				System.out.println("다오에서 실행한다");
				String type = rs.getString("notice_type");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String writer_id = rs.getString("writer_id");
				String writer_date = rs.getString("write_date");
				System.out.println(id+""+ title+""+content+""+ writer_id+""+ writer_date);
				vo = new NoticeVO(id, type,title, content, writer_id, writer_date);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			}
		return vo;
	}
	
	public ArrayList<String> getNoticeImgs(String id) {
		ArrayList<String> ImgList = new ArrayList<String>();

		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(getNoticeImgs);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String imgName = rs.getString("imgname");
				ImgList.add(imgName);
				System.out.println(imgName+"여기 이미지");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("끝");
		return ImgList;
	}
	
	public int getMaxId() {
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
		return id;
	}
	
	public int insertNoticeImgs(String noticeId, String imgName) {
		int n =0;
		try {
			con  =JdbcUtil.getConnection();
			pstmt = con.prepareStatement(insertNoticeImgs);
			pstmt.setInt(1, Integer.parseInt(noticeId));
			pstmt.setString(2, imgName);
			n = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}
	
	
	
	public int insertNotice(String title, String content, String writerId, String noticeMaxId, String selectText) {
		int n=0;
		try {
			con  =JdbcUtil.getConnection();
			pstmt = con.prepareStatement(insertNotice);
			pstmt.setInt(1 , Integer.parseInt(noticeMaxId));
			pstmt.setString(2, selectText);
			pstmt.setString(3, title);
			pstmt.setString(4, content);
			pstmt.setString(5, writerId);
		

			LocalDateTime now = LocalDateTime.now();
			String formateNow = 
					now.format(
							DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));
			pstmt.setString(6,formateNow);
			n = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}
	
	public ArrayList<NoticeVO> getNoticeListMain() {
		ArrayList<NoticeVO> list= new ArrayList<>();

		ArrayList<String>data= new ArrayList<String>();
		System.out.println("here");
		try {
			
			NoticeVO vo = null;
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(mainNotice);
			rs = pstmt.executeQuery();
			int cnt = 0;
			while(rs.next()) {
				
				cnt+=1;
				if (cnt == 3) {
					break;
				}
				int id = Integer.parseInt(rs.getString("id")); //2, 3,2
				String content = rs.getString("content");
				String img = rs.getString("img");


				int idx  = data.indexOf(Integer.toString(id));
				
				if(idx<0) {
					vo = new NoticeVO(rs.getString("id"), null, img, content,null,null);
					list.add(vo);
				}

				data.add(Integer.toString(id)); //2,3
			}
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	

	public ArrayList<NoticeVO> getNoticeList(String type) {
		ArrayList<NoticeVO> list= new ArrayList<>();

		ArrayList<String>data= new ArrayList<String>();
		try {
			
			NoticeVO vo = null;
			con = JdbcUtil.getConnection();
			if (type != null) {
				pstmt = con.prepareStatement(getNoticeTypeList);
				pstmt.setString(1, type);
			} else {
				System.out.println("여기로 들어옴");
				pstmt = con.prepareStatement(getNoticeList);
			}
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String id = rs.getString("id"); //2, 3,2
				String name = rs.getString("name"); 
				String title = rs.getString("title");
				String nImg = rs.getString("nImg");

				int idx  = data.indexOf(id);
				if(idx<0) {
					vo = new NoticeVO(id, null, title, nImg, null, null);
					System.out.println(title+nImg);
					list.add(vo);
				}
			

				data.add(id); //2,3
			}
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public int delete(String id) {
		int n =0;
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(delNotice);
			pstmt.setString(1, id);
			n = pstmt.executeUpdate();
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		return n;
	}

	public int updateNotice(String noticeId, String title, String content, String selectText) {
		int n=0;
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(updateNotice);
			pstmt.setString(1, selectText);
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			LocalDateTime now = LocalDateTime.now();
			String formateNow = now.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));
			pstmt.setString(4,formateNow);
			pstmt.setString(5, noticeId);
			n = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}

	public int deleteImgId(String noticeId) {
		int n =0;
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(delImgs);
			pstmt.setString(1, noticeId);
			n= pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return n;
	}
}
