package dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

/**
 * Servlet implementation class ProjectImgUpload
 */
@MultipartConfig(
		maxFileSize = 1024*1024*50,
		maxRequestSize = 1024*1024*50*5
		)
@WebServlet("/ProjectImgUploadMod")
public class ProjectImgUploadMod extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProjectImgUploadMod() {
        super();
    }

	public String EncodingChange(String text) throws UnsupportedEncodingException {
		String Entext = new String(text.getBytes("8859_1"),"utf-8");
		return Entext;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Collection<Part> parts = request.getParts();
		ArrayList<String> ImgList = new ArrayList<String>();
		System.out.println("이미지 업로드 부분 시작");
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

	    Path path = FileSystems.getDefault().getPath("");
	    String directoryName = path.toAbsolutePath().toString();
	   
	    String realPath ="C:\\Users\\didwo\\Desktop\\PT4\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\TP5\\images\\projectImage";
		
		String projectMaxId = request.getParameter("projectId");
		String path1 = ProjectImgUploadMod.class.getResource("").getPath(); 
		String[] splitPath =path1.split("WEB-INF");

		String createFolderPath = realPath+projectMaxId;
		File Folder =new File(createFolderPath);
	
		int matchCheck = 0;

		for(Part file : parts) {
			String name = file.getSubmittedFileName();
			if(name == null) continue;
			
			// 이미지 이름만 가져오기
			String PATTERN = "([^\\s]+(\\.(?i)(jpg|png|gif|bmp))$)";
			Pattern pattern = Pattern.compile(PATTERN);
			Matcher match = pattern.matcher(name);

			if(match.find()) {
				matchCheck  = 1;

				// 체크가 true라면 이미지가 있기 때문에 폴더 안에 비우기
				if(matchCheck == 1) {
					if(Folder.exists()) {
						 try {
					            File rootDir = new File(createFolderPath);
					            FileUtils.deleteDirectory(rootDir);
					            Folder.mkdir();
					        } catch (IOException e) {
					            e.printStackTrace();
					        }
					}
					matchCheck = 2;
				}
				
				System.out.println("매치된 것 찾음 - 이미지 찾음 ");
				ImgList.add(name);
				
				File image = new File(createFolderPath + File.separator + name);
				String fe = FilenameUtils.getExtension(name);
				InputStream fis = file.getInputStream();

				FileOutputStream fos = new FileOutputStream(createFolderPath+ File.separator + name);
				byte[] buf = new byte[1024];
				int size=0;
				while((size = fis.read(buf))!= -1) {
					fos.write(buf, 0, size);
				}
				fis.close();
				fos.close();
			}
		}
		
		
		String title = request.getParameter("title");
		String content= request.getParameter("content");
		title = EncodingChange(title);
		content = EncodingChange(content);
		projectMaxId = EncodingChange(projectMaxId);
		
		ProjectDAO dao = new ProjectDAO();
		
		int n = dao.updateProject( projectMaxId ,title, content);
		
		
		try {
			if(!ImgList.isEmpty()) {
				int check = dao.deleteImgId(projectMaxId);
				if(check>0) {
					for(int i=0; i<ImgList.size(); i++) {
						System.out.println(ImgList.get(i));
						dao.insertNoticeImgs(projectMaxId, ImgList.get(i));
					}
				}				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("/yd-world/project?id="+projectMaxId);
		
	}

}
