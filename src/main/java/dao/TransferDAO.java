package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dbUtil.util;
import vo.TransferVO;


public class TransferDAO {
	private Connection conn;
	private Statement st;
	private PreparedStatement pst; // ?지원
	private ResultSet rs;
	private int resultCount; 
	
	CallableStatement cst;
	//신규선수등록 ..입력한 건수 리턴
		public int transferInsert(TransferVO transfer) {
			String sql = """
					insert into values(?, ?, ?, ?, ?, ?, ?)
					""";
			conn = util.getConnection();
			try {
				pst = conn.prepareStatement(sql);
				pst.setInt(1, transfer.getPlayer_id());
				pst.setString(2, transfer.getPlayer_position());
				pst.setInt(3, transfer.getTransfer_year());
				pst.setString(4, transfer.getFee());
				pst.setInt(5, transfer.getPlayer_id());
				pst.setInt(6, transfer.getPrevious_team_id());
				pst.setInt(7, transfer.getNew_team_id());
				resultCount = pst.executeUpdate(); //DML문장 실행한다. 
			} catch (SQLException e) {
				resultCount = -1;
				e.printStackTrace();
			} finally {
				util.dbDisconnect(null, pst, conn);
			}
			
			return resultCount;
		}
}
