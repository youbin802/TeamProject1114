package myutil;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;

public class MultiPart {
	List items; // 입력 데이터 항목들로 구성된 List

	// 생성자
	public MultiPart(HttpServletRequest request) throws Exception {
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		items = upload.parseRequest(request);

	}

	// 주어진 이름에 해당하는 데이터 값을 리턴하는 메서드
	public String getParameter(String fieldName) throws UnsupportedEncodingException {
		for (int cnt = 0; cnt < items.size(); cnt++) {
			FileItem item = (FileItem) items.get(cnt);
			if (item.getFieldName().equals(fieldName))
				return item.getString("UTF-8");
		}
		return null;

	}

	// 주어진 이름에 해당하는 업로드 파일의 경로명을 리턴하는 메서드
	public String getFilePath(String fieldName) throws UnsupportedEncodingException {
		for (int cnt = 0; cnt < items.size(); cnt++) {
			FileItem item = (FileItem) items.get(cnt);
			if (item.getFieldName().equals(fieldName))
				return item.getName();
		}
		return null;
	}

	// 주어진 이름에 해당하는 업로드 파일의 이름을 리턴하는 메서드
	public String getFileName(String fieldName) throws UnsupportedEncodingException {
		String path = getFilePath(fieldName);
		int index1 = path.lastIndexOf("/");
		int index2 = path.lastIndexOf("\\");
		int index = 0;

		if (index1 > index2)
			index = index1;
		else
			index = index2;

		if (index < 0)
			return path;
		else
			return path.substring(index + 1);
	}

	// 주어진 이름에 해당하는 업로드 파일을 저장하는 메서드
	public void saveFile(String fieldName, String path) throws Exception {
		for (int cnt = 0; cnt < items.size(); cnt++) {
			FileItem item = (FileItem) items.get(cnt);
			if (item.getFieldName().equals(fieldName)) {
				if (!item.isFormField()) {
					item.write(new File(path));
					return;
				}
			}
		}
	}
	
	// 다중 파일 업로드 (반환값은 업로드된 파일 이름 리스트)
	public ArrayList<String> saveFiles(String fieldName, String path) throws Exception {
		ArrayList<String> uploadedFileNames = new ArrayList();		
		Iterator itr = items.iterator();
	      while (itr.hasNext()) {
	           FileItem item = (FileItem) itr.next();
	           if (item.isFormField()) {
	           } else {
	              String itemName = item.getName();
	              uploadedFileNames.add("/teamPriject/imgs/" + itemName);
	              File savedFile = new File(path + itemName);
	              System.out.println(savedFile+"/자바 이미지 업로드");
	              item.write(savedFile);	
	              System.out.println(item+"아이템 내용");
	           }
	      }
	   return uploadedFileNames;
	}
	
	// 모든 넘겨받은 parameter들 반환하는 메소드
	public ArrayList<String> getAllParameter() throws UnsupportedEncodingException {
		ArrayList<String> params = new ArrayList<String>();
		for (int cnt = 0; cnt < items.size(); cnt++) {				
			FileItem item = (FileItem) items.get(cnt);
			params.add(item.getFieldName());					
		}
		return params;

	}

}