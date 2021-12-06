package signup;

import java.io.IOException;
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

@WebServlet("/signup/save")
public class SignupSave extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// 파라미터들을 저장할 클래스 MemberInfo (DB연동 전 임시)
//	public static Set<MemberInfo> memberTable;
//		
//	@Override
//	public void init() throws ServletException {
//		memberTable = new HashSet<>();
//	}
//	
//	public Set<MemberInfo> getMemberTable() {
//		return memberTable;
//	}
       
	// 입력한 파라미터가 모두 형식에 맞을경우 아이디 중복 체크 후 저장
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("id");
		String userPw = request.getParameter("pw");
		String name = request.getParameter("name");
		
		// 아이디 중복 체크 (DB연동 전 임시)
//		boolean exist = false;
//		for(MemberInfo memberInfo : memberTable) {
//			String savedId = memberInfo.getId();
//			
//			exist = savedId.equals(userId);
//			
//			if(exist) {
//				break;
//			}	
//		}
//		if(exist) {
//			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//			
//			return;
//		}
		
		// ChkList에서 마케팅 수신여부 (선택 동의) 값 가져오기
		HttpSession session = request.getSession();
		String marketing = (String) session.getAttribute("marketing");
		
		try {
			
			// DB 접속 ( util.DBMS의 메서드 이용)
			Connection conn = DBMS.getConnection();
			
			// DB 이용 아이디 중복 체크
			String idsql = "SELECT * FROM memberinfo WHERE id = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(idsql);
			pstmt.setString(1, userId);
			
			ResultSet rs = pstmt.executeQuery();
			
			boolean chkId = rs.next();
			
			if(chkId) {
				// 중복일 경우
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				
				return;
				
			} else {
				// 중복아닐 경우 DB에 저장
				
				// 회원가입 쿼리 ( 추가는 INSERT INTO)
				String sql = "INSERT INTO memberinfo(id, pw, name, marketing) VALUES(?, ?, ?, ?)";
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, userId);
				pstmt.setString(2, userPw);
				pstmt.setString(3, name);
				pstmt.setString(4, marketing);
				
//				ResultSet rs = pstmt.executeQuery();
				
				int result = pstmt.executeUpdate();
				
				// 회원가입 성공 여부 판단
				if(result == 1) {
					// 성공일 때 (excuteUpdate의 리턴값이 1일 때)
					response.setStatus(HttpServletResponse.SC_CREATED);
				} else {
					// 실패일 때 (리턴값이 2 일 때?)
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				}
			}
			
			// 접속 종료
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (SQLException e) {
//			e.printStackTrace();
			System.out.println("SQL 예외");
		}
	
		// (DB연동 전 임시)
		// MemberInfo 타입의 memberInfo 객체 생성 각 변수 값 저장
//		MemberInfo memberInfo = new MemberInfo();
//		memberInfo.setId(userId);
//		memberInfo.setPw(userPw);
//		memberInfo.setName(name);
//		memberInfo.setMarketing(marketing);
		
		// 회원 정보 저장 (DB연동 전 임시)
		// 키를 아이디로사용, 저장할 데이터는 memberInfo 객체
//		memberTable.add(memberInfo);
		
		// 입력 값 테스트
//		PrintWriter out = response.getWriter();
//		
//		out.print(memberInfo.getId());
//		out.print(memberInfo.getPw());
//		out.print(memberInfo.getName());
//		out.print(memberInfo.getMarketing());
//		
//		out.close();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
}
