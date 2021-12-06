package signup;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/signup/id")
public class SignupId extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	// 파라미터 ID 확인
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("id");
		
		// null이거나 공백일 경우 에러
		if(userId == null || userId.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			
			return;
		}
		
		// 문자열 내 공백 제거(null이 아니어야 가능)
		if(userId != null) {
			userId = userId.replaceAll(" ", "");
		}
		
		// 이메일 형식 패턴
		// naver.com 이나 google.co.kr 같은 형식은 체크되나 실제 존재하는 이메일인지는 체크하지 않음 -> zzz.zzz 로 입력해도 맞는 형식으로 체크 됨
		String pattern = "^[a-zA-Z0-9]*@[a-zA-Z]*+.[a-zA-Z]([.a-zA-Z]).*$";
		
		// 이메일 형식 체크
		if(!userId.matches(pattern)) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			
			return;
		}
		// ID 문제가 없으면 PW로 이동
		RequestDispatcher rd = request.getRequestDispatcher("/signup/pw");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
}
