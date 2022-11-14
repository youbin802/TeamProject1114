package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import util.JdbcUtil;
import vo.ProjectVO;

import com.google.common.base.CharMatcher;
public class ProjectDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	final String getMaxId =  "select max(id)+1 as max from project";
	
	final String insertProject = "insert into project values(?,?,?,?,?,?)";
	final String getProject = "select * from project p, users u where p.id=? and p.writer_id= u.id";
	final String updateProject = "update project set title =?, content =?, write_date =? where id =?";
	final String deleteProject = "delete from project where id= ?";
	
	final String heartUpdate = "update project set heart = heart + 1 where id = ?";
	final String heartUpdateMinus = "update project set heart = heart - 1 where id = ?";
	final String getHeartCnt = "select heart from project where id= ?";
	
	final String getProjectList = "select p.id as id, p.title as title, u.name as name, pImg.imgName as pImg, p.heart as heart from PROJECTIMGS pImg, PROJECT p, users u where p.id = pImg.projectId  and u.id = p.writer_id order by p.id desc";
	
	final String insertProjectImgs = "insert into projectImgs(id, projectId, imgName) values( (select nvl(max(id),0)+1 from projectImgs), ?,?)";
	final String getProjectImgs = "select * from projectImgs pImgs, project p where p.id = ? and p.id= pImgs.projectId";
	final String delImgs = "delete from PROJECTIMGS where projectId = ?";
	
	public ArrayList<ProjectVO> getProjectList() {
		ArrayList<ProjectVO> list= new ArrayList<>();

		ArrayList<String>data= new ArrayList<String>();
		System.out.println("here");
		try {
			
			ProjectVO vo = null;
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(getProjectList);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int id = Integer.parseInt(rs.getString("id")); //2, 3,2
				String name = rs.getString("name"); 
				String title = rs.getString("title");
				String contentImg = rs.getString("pImg");
				int heart = rs.getInt("heart");


				int idx  = data.indexOf(Integer.toString(id));
				if(idx<0) {
					vo = new ProjectVO(id, name, title, contentImg,null,null,heart); //2,3
					list.add(vo);
				}

				data.add(Integer.toString(id)); //2,3
			}
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public ArrayList<ProjectVO> getProjectMain() {
		ArrayList<ProjectVO> list= new ArrayList<>();

		ArrayList<String>data= new ArrayList<String>();
		System.out.println("here");
		try {
			
			ProjectVO vo = null;
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(getProjectList);
			rs = pstmt.executeQuery();
			int cnt = 0;
			while(rs.next()) {
				
				cnt+=1;
				if (cnt == 3) {
					break;
				}
				int id = Integer.parseInt(rs.getString("id")); //2, 3,2
				String name = rs.getString("name"); 
				String title = rs.getString("title");
				String contentImg = rs.getString("pImg");
				int heart = rs.getInt("heart");
				



				int idx  = data.indexOf(Integer.toString(id));
				System.out.println("프로젝트"+title);
				if(idx<0) {
					vo = new ProjectVO(id, name, title, contentImg,null,null,heart); //2,3
					list.add(vo);
				}

				data.add(Integer.toString(id)); //2,3
			}
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public int deleteImgId(String projectId) {
		int n =0;
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(delImgs);
			pstmt.setString(1, projectId);
			n= pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return n;
	}
	
	public int updateProject(String projectId, String title, String content) {
		int n=0;
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(updateProject);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			LocalDateTime now = LocalDateTime.now();
			String formateNow = now.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));
			pstmt.setString(3,formateNow);
			pstmt.setString(4, projectId);
			n = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}
	
	public int updateProjectHeart(String projectId) {
		int n=0;
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(heartUpdate);
			pstmt.setString(1, projectId);
			n = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}
	
	public int getMaxId() {
		System.out.println("here");
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

	public int insertProject(String projectMaxId, String title, String content, String writerId) {
	int n = 0;
		
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(insertProject);
			pstmt.setString(1, projectMaxId);
			pstmt.setString(2,title);
			pstmt.setString(3, content);
			pstmt.setString(4,writerId);
			
			
			LocalDateTime now = LocalDateTime.now();
			String formateNow = 
					now.format(
							DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));
			pstmt.setString(5,formateNow);
			
			pstmt.setInt(6, 0);
			n = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}

	public int insertNoticeImgs(String projectMaxId, String imgName) {
		int n =0;
		try {
			con  =JdbcUtil.getConnection();
			pstmt = con.prepareStatement(insertProjectImgs);
			pstmt.setInt(1, Integer.parseInt(projectMaxId));
			pstmt.setString(2, imgName);
			n = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}
	
	public ProjectVO getProject(String id) {
		ProjectVO vo = null;
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(getProject);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int projectId = Integer.parseInt(id);
				String name = rs.getString("name"); 
				String title = rs.getString("title");
				String content = rs.getString("content");
				String writer_id = rs.getString("writer_id");
				String writer_date = rs.getString("write_date");
				int heart = rs.getInt("heart");
				vo =new ProjectVO(projectId, name, title, content, writer_id,writer_date, heart);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
	
	public ArrayList<String> getProjectImgs(String id) {
		ArrayList<String> ImgList = new ArrayList<String>();
		System.out.println("이미지 가져오기 들어옴");
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(getProjectImgs);
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

	public int getHeartCnt(String id) {
		int cnt = 0;
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(getHeartCnt);
			pstmt.setInt(1, Integer.parseInt(id));
			rs = pstmt.executeQuery();
			if(rs.next()) {
				cnt = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}

	public int updateProjectHeartMinus(String id) {
		int n=0;
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(heartUpdateMinus);
			pstmt.setString(1, id);
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
			pstmt = con.prepareStatement(deleteProject);
			pstmt.setString(1, id);
			n = pstmt.executeUpdate();
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		return n;
	}
}
