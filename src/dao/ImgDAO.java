package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import util.JdbcUtil;

public class ImgDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	final String insertImg = "insert into ProjectImgs values ((select max(projectImgId)+1 as max from ProjectImgs),?,?,?)";

	
	public int insertImg(int projectId, String imgName, String imgFe) {
		int n=0;
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(insertImg);
			pstmt.setInt(1, projectId);
			pstmt.setString(2, imgName);
			pstmt.setString(3, imgFe);
			n = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}
}
