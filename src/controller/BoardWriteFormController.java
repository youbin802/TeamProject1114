package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardWriteFormController implements Controller {

	@Override
	public View process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return new View("/view/board/board_write.jsp");
	}

}
