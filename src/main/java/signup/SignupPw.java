package signup;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/signup/pw")
public class SignupPw extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// 파라미터 PW 확인  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userPw = request.getParameter("pw");

		// 문자열 내 공백 제거(null이 아니어야 가능)
		if(userPw != null) {
			userPw = userPw.replaceAll(" ", "");
		}
		
		// 비밀번호 패턴 -> 길이 8~20, 숫자포함, 영문자포함(대소문자구별x), 특스문자 포함하는 정규식
		String pattern = "^.*(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!?@#$%^&*+=])(?=^.{8,20}$).*$";
		
		// userPW가 null 이거나 비어있거나 패턴과 같지 않을 경우
		if( (userPw == null || userPw.isEmpty()) || !userPw.matches(pattern)) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			
			return;
		}
		
		// 확인 완료한 userPw를 전달하기 위해 세션 pw에 저장
		HttpSession session = request.getSession();
		session.setAttribute("pw", userPw);
		
		// Pw 문제가 없으면 chkPw로 이동
		RequestDispatcher rd = request.getRequestDispatcher("/signup/chkpw");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
}
