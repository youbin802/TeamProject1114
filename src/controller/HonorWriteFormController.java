package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HonorWriteFormController implements Controller {

	@Override
	public View process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return new View("/view/honor/honor_write.jsp");
	}

}
