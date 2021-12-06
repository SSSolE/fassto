package signup;

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

import util.DBMS;

/**
 * Servlet implementation class test
 */
@WebServlet("/test")
public class test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public test() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
		
			String id = "id1222@naver.com";
		try {
			
			// DB 접속 ( util.DBMS의 메서드 이용)
			Connection conn = DBMS.getConnection();
			
			// DB 이용 아이디 중복 체크
			String idsql = "SELECT * FROM memberinfo WHERE id = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(idsql);
			pstmt.setString(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			boolean chkId = rs.next();
			if(chkId) {
				out.print("yes");
			} else {
				out.print("no");
			}
			
			// 접속 종료
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (SQLException e) {
//			e.printStackTrace();
			System.out.println("SQL 예외");
		}
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
