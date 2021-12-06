package util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBMS {
	
	public static Connection getConnection() {
		try {
			// DB접속 클래스
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/maria");
//			DataSource ds = (DataSource) ic.lookup("jdbc/maria");
			
			Connection conn = ds.getConnection();
			
			return conn;
		} catch(NamingException e) {
//			e.printStackTrace();
			System.out.println("Connection Pool 관련 예외 발생");
		} catch (SQLException e) {
//			e.printStackTrace();
			System.out.println("Conncetion 관련 예외 발생");
		}
		// 예외 발생 시 null 리턴
		return null;
	}
}
