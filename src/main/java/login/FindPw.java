package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import signup.MemberInfo;
import signup.SignupSave;

@WebServlet("/find/pw")
public class FindPw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		
		String id = request.getParameter("findId");
		String name = request.getParameter("findName");
		
		PrintWriter out = response.getWriter();
		
		if(id == null || name == null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			
			out.print("아이디 또는 이름을 입력해주세요.");
			
			return;
		}
		
		Set<MemberInfo> memberTable = SignupSave.memberTable;
		
		for(MemberInfo memberInfo : memberTable) {
			String nthMemberId = memberInfo.getId();
			String nthMemberName = memberInfo.getName();
			String nthMemberPw = memberInfo.getPw();
			out.print(nthMemberId);
			out.print(nthMemberName);
			out.print(nthMemberPw);
			out.print("0000");
			
			if(id.equals(nthMemberId) && name.equals(nthMemberName)) {
				response.setStatus(HttpServletResponse.SC_OK);
				
				Random random = new Random();
				
				StringBuffer tempPw = new StringBuffer();
				// 임시 비밀번호 10자리로 지정
				for(int i=0; i<10; i++) {
					// 숫자,영문대문자,영문소문자 3개지를 랜덤으로
					int j = random.nextInt(3);
					switch(j) {
					// j가 0일 때 숫자 출력		// 0~9
					case 0:
						tempPw.append((random.nextInt(10)));
						break;
					// j가 1일 때 소문자 출력		// 아스키코드 97~122 -> a-z
					case 1:
						tempPw.append((char) ((int) (random.nextInt(26))+97 ));
						break;
					// j가 2일 때 대문자 출력		// 아스키코드 65~90 -> A-Z
					case 2:
						tempPw.append((char) ((int) (random.nextInt(26))+65 ));
						break;
					}
				}
				
				String pw = tempPw.toString();
				
				out.print("임시 비밀번호는" + pw + "입니다.");
				
				memberInfo.setPw(pw);
				
				out.print(memberInfo.getId());
				out.print(memberInfo.getName());
				out.print(memberInfo.getPw());
				
				return;
			}
		}
		response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		
		out.print("아이디 또는 이름을 확인해 주세요.");
		out.print(id);
		out.print(name);
		
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}
}
