package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.DBMS;

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
		
		// 로그인 먼저 실행하면 회원가입된 정보가 없으므로 null에러 생김 -> 회원가입 먼저 하나 하던지 try문 - db연동 전 임시
//		Set<MemberInfo> memberTable = SignupSave.memberTable;
//		
//		for(MemberInfo memberInfo : memberTable) {
//			String nthMemberId = memberInfo.getId();
//			String nthMemberPw = memberInfo.getPw();
			
		
		// DB 연동
		try {
			
			// DB 접속 ( util.DBMS의 메서드 이용)
			Connection conn = DBMS.getConnection();
			
			// 회원가입 여부 체크(아이디)
			String loginsql = "SELECT * FROM memberinfo WHERE id = ? AND pw = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(loginsql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			ResultSet rs = pstmt.executeQuery();
			
			boolean isLogin = rs.next();
			
			if(isLogin) {
				// 아이디가 있을 경우
				
				// 저장된 비밀번호를 불러와 입력값과 비교
				HttpSession session = request.getSession();
				
				session.setAttribute("isLogin", true);
				session.setAttribute("id", id);
				session.setMaxInactiveInterval(600);	// 세션 유지시간 600초
			} else {
				// 가입된 아이디가 없을 경우
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			}
			
//			response.sendRedirect("/FASSTO/index.html");	// 메인페이지로 이동 ajax로 처리가능하면 변경할 것
			
			// 접속 종료
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (SQLException e) {
//			e.printStackTrace();
			System.out.println("SQL 예외");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
