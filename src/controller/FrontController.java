package controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/yd-world/*")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Controller> controllerMap = new HashMap<String, Controller>();
    

    public FrontController() {
        super();
    }


	public void init(ServletConfig config) throws ServletException {
		controllerMap.put("/", new MainController());
		controllerMap.put("/yd-world/project", new ProjectController());
		controllerMap.put("/yd-world/project/writeForm", new ProjectWriteFormController());
		controllerMap.put("/yd-world/project/updateForm", new ProjectUpdateFormController());
		

		controllerMap.put("/yd-world/board", new BoardController());
		controllerMap.put("/yd-world/board/writeForm", new BoardWriteFormController());
		controllerMap.put("/yd-world/board/updateForm", new BoardUpdateFormController());
		
		controllerMap.put("/yd-world/notice", new NoticeController());
		controllerMap.put("/yd-world/notice/writeForm", new NoticeWriteFormController());
		controllerMap.put("/yd-world/notice/updateForm", new NoticeUpdateFormController());
	

		controllerMap.put("/yd-world/honor", new HonorController());
		controllerMap.put("/yd-world/honor/writeForm", new HonorWriteFormController());
	}


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		System.out.println("여기2");
		
		String requestURI = request.getRequestURI();
		System.out.println(requestURI);
		String contextPath = request.getContextPath();
		String path = requestURI.substring(contextPath.length());
		
		Controller controller = controllerMap.get(path);
		System.out.println(controller);
		
		if (controller == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		View view = controller.process(request, response);
		view.render(request, response);
	}

}
