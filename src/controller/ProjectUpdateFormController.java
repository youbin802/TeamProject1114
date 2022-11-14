package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProjectDAO;
import vo.ProjectVO;

public class ProjectUpdateFormController implements Controller {

	@Override
	public View process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String projectId = request.getParameter("id");
		ProjectDAO projectDao = new ProjectDAO();
		ProjectVO projectVo = projectDao.getProject(projectId);
		
		ArrayList<String> ImgList = projectDao.getProjectImgs(projectId);
		
		request.setAttribute("projectVo", projectVo);
		request.setAttribute("ImgList", ImgList);
		return new View("/view/project/project_modify.jsp");
	}

}
