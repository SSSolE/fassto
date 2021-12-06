package signup;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/signup/chkpw")
public class SignupChkPw extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	// 파라미터 PW 와 ChkPw가 같은지 확인
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// MembershipPw 서블릿에서 세션에 저장했던 pw를 불러와서 String 타입의 userPw에 저장
		// 세션에 저장되는 데이터 타입은 Object타입 이므로 형변환이 필요
		HttpSession session = request.getSession();
		String userPw = (String) session.getAttribute("pw");
		
		// String userPw = request.getParameter("pw");
		String chkPw = request.getParameter("chkPw");
		
		// 앞에서 공백을 제거한 userPw와 똑같이 입력해야 하므로 여기서는 공백제거를 안해줌
		
		// chkPw가 null 이거나 비어있거나 userPw와 같지 않을 경우
		if( (chkPw == null || chkPw.isEmpty()) || !chkPw.equals(userPw)) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			
			return;
		}
		
		// chkPw 문제 없으면 name으로 이동
		RequestDispatcher rd = request.getRequestDispatcher("/signup/name");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
}
