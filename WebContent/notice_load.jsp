<%@page import="java.io.FileNotFoundException"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.InputStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
request.setCharacterEncoding("UTF-8");  
// 파일 업로드된 경로
String root = request.getSession().getServletContext().getRealPath("/images/noticeImage/");


String savePath = root + "6";
// 서버에 실제 저장된 파일명
String filename = "housewarming_party.txt" ;    
// 실제 내보낼 파일명
String orgfilename = "housewarming_party.txt" ;      

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
%>
</body>
</html>