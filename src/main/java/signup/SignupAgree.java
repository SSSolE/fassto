package signup;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/signup/agree")
public class SignupAgree extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// 체크박스 확인 Agree
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String all_agree = request.getParameter("all_agree");				// 전체 동의
		String terms_agree = request.getParameter("terms_agree");			// 필수 동의
		String privacy_agree = request.getParameter("privacy_agree");		// 필수 동의
		String marketing_agree = request.getParameter("marketing_agree");	// 선택 동의
		
		// 전체 동의가 null 일때 필수 동의 둘 중 하나라도 null 이면 안됨
		if(all_agree == null) {
			if(terms_agree == null || privacy_agree == null) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				
				return;
			}
		}
		// 선택 동의 값 저장하기 (마케팅 수신 여부)
		// 선택 동의는 회원가입에서 필수조건으로 체크하지 않고 세션에 저장한 후 회원가입 할 때 true-false의 값을 회원정보에 저장
		// 후에 각 회원정보에서 true-false에 따라 동작하게
		HttpSession session = request.getSession();
		
		// marketing_agree 가 null일 때 (체크 x)
		if(marketing_agree == null) {
			session.setAttribute("marketing", "N");
		} else {
			// 체크했을 때
			session.setAttribute("marketing", "Y");
		}
		

		
//		session.setAttribute("marketing", marketing_agree);
		
		RequestDispatcher rd = request.getRequestDispatcher("/signup/save");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
}
