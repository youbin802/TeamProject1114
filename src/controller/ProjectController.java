package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProjectDAO;
import vo.ProjectVO;

public class ProjectController implements Controller {

	@Override
	public View process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		ProjectDAO projectDao = new ProjectDAO();
		
		// 작품실 페이지인 경우
		if(id == null) {
			ArrayList<ProjectVO> projectList = projectDao.getProjectList();
			
			request.setAttribute("projectList", projectList);			
			return new View("/view/project/project.jsp");
		} 
		else {
			ProjectVO projectVo = projectDao.getProject(id);
			ArrayList<String> ImgList = projectDao.getProjectImgs(id);
			
			request.setAttribute("projectVo", projectVo);
			request.setAttribute("ImgList", ImgList);
			return new View("/view/project/project_read.jsp");
		}
		
	}

}
