package dbUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class util {
	//1.DB연결
		public static Connection getConnection() {
			Connection conn = null;
			String url = "jdbc:mysql://192.168.0.101/transfer_talk";
			String userid = "root";
			String pass="1234";
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection(url, userid, pass);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return conn;
		}
		
		//2.자원반납
		public static void dbDisconnect(ResultSet rs, Statement st, Connection conn) {
			try {
				if(rs != null) rs.close();
				if(st != null) st.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
}
