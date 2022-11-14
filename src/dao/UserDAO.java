package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Request;

import util.JdbcUtil;
import vo.UserVO;

public class UserDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	final String get_user = "select * from users where id=? and password=?";
	
	public UserVO login(String id,String pw) {
		UserVO vo =null;
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(get_user);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String user_id = rs.getString("id");
				String name = rs.getString("name");
				String deparment = rs.getString("deparment");
				String grade_number  = rs.getString("grade_number");
				String class_number = rs.getString("class_number");
				String profile_img  = rs.getString("profile_img");
				int identity =rs.getInt("identity");
				vo = new UserVO(user_id, name, deparment, grade_number, class_number, profile_img, identity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
	
	public UserVO userData(HttpServletRequest request) {
		UserVO vo =new UserVO();
		HttpSession user_session = request.getSession();
		
		String id = (String) user_session.getAttribute("id");
		String name = (String) user_session.getAttribute("name");
		String prpfile = (String) user_session.getAttribute("profile_img");
//		System.out.println(name);
//		int identity = (int) user_session.getAttribute("identity");
		
	
//		vo.setIdentity(identity);
		vo.setName(name);
		vo.setId(id);
		vo.setProfile_img(prpfile);
		return vo;
	}
}
