package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FileLoad
 */
@WebServlet("/FileLoad")
public class FileLoad extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileLoad() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");  
		System.out.println("파일 로딩 서블릿으로 옴");
		// 파일 업로드된 경로
	
		String filename = request.getParameter("filename");
		String noticeId = request.getParameter("id");
		PrintWriter out = response.getWriter();
//		String savePath = "C:\\\\Users\\\\user\\\\Desktop\\\\teamproject1107\\\\.metadata\\\\.plugins\\\\org.eclipse.wst.server.core\\\\tmp0\\\\wtpwebapps\\\\TeamProject\\\\images\\\\noticeImage\\\\" + noticeId;
		//String savePath = "C:\\Users\\user\\Desktop\\teamproject1107\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\TeamProject\\images\\noticeImage\\"+ noticeId;
		String savePath = "C:\\Users\\youbi\\Desktop\\TP1108\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\TeamProject\\images\\noticeImage\\"+ noticeId;
		System.out.println(savePath);
		
		// 실제 내보낼 파일명
		String orgfilename = filename;    
		

		InputStream in = null;
		OutputStream os = null;
		File file = null;
		boolean skip = false;
		String client = ""; 

		try{ 
		    // 파일을 읽어 스트림에 담기
		    try{
		        file = new File(savePath, filename);
		        in = new FileInputStream(file);

		        System.out.println("파일 읽기"+in);
		    }catch(FileNotFoundException fe){

		        System.out.println("성공못함");
		        skip = true;
		    }
		    System.out.println("");
		     
		    client = request.getHeader("User-Agent");
		    // 파일 다운로드 헤더 지정
		    response.reset() ;
		    response.setContentType("application/octet-stream");
		    response.setHeader("Content-Description", "JSP Generated Data"); 

		    if(!skip){

		        // IE
		        if(client.indexOf("MSIE") != -1){
		            response.setHeader ("Content-Disposition", "attachment; filename="+new String(orgfilename.getBytes("KSC5601"),"ISO8859_1"));

		        }else{
		            // 한글 파일명 처리
		            orgfilename = new String(orgfilename.getBytes("utf-8"),"iso-8859-1");

		            response.setHeader("Content-Disposition", "attachment; filename=\"" + orgfilename + "\"");
		            response.setHeader("Content-Type", "application/octet-stream; charset=utf-8");
		        } 
		         
		        response.setHeader ("Content-Length", ""+file.length() );
		   
		        os = response.getOutputStream();
		        byte b[] = new byte[(int)file.length()];
		        int leng = 0;
		         
		        while( (leng = in.read(b)) > 0 ){
		            os.write(b,0,leng);
		        }

		    }else{
		        response.setContentType("text/html;charset=UTF-8");
		        out.println("<script language='javascript'>alert('파일을 찾을 수 없습니다');history.back();</script>");

		    }
		     
		    in.close();
		    os.close();

		}catch(Exception e){
		  e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
