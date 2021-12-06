package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import signup.MemberInfo;
import signup.SignupSave;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("loginId");
		String pw = request.getParameter("loginPw");
		
		PrintWriter out = response.getWriter();
		
		if(id == null || pw == null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			
			out.print("아이디 또는 비밀번호를 입력해 주세요.");
			
			return;
		}
		
		// 로그인 먼저 실행하면 회원가입된 정보가 없으므로 null에러 생김 -> 회원가입 먼저 하나 하던지 try문
		Set<MemberInfo> memberTable = SignupSave.memberTable;
		
		for(MemberInfo memberInfo : memberTable) {
			String nthMemberId = memberInfo.getId();
			String nthMemberPw = memberInfo.getPw();
			

			if(id.equals(nthMemberId) && pw.equals(nthMemberPw)) {
				response.setStatus(HttpServletResponse.SC_OK);
				
				out.print("로그인 성공");
				
				return;
			}
		}
		response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		
		out.print("아이디 또는 비밀번호를 확인해 주세요.");
		
		out.close();
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
