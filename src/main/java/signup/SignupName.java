package signup;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/signup/name")
public class SignupName extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    // 파라미터 name 확인
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		
		// name 이 비어있거나 2글자 미만일 경우
		if( (name == null || name.isEmpty()) || name.length()<2) {

			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			
			return;
		}		
		
		RequestDispatcher rd = request.getRequestDispatcher("/signup/agree");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
}
