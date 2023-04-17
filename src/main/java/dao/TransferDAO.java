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
	
	/**
	 * TransferVO에 맞게 insert 하는것
	 * @param transfer
	 * @return 저장 성공 시 1, 실패시 0
	 */
	public int insertTransfer(TransferVO transfer) {
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
	
	/**
	*****Null return 가능*****
    * player_id를 받아 아이디에 해당하는 TransferVO 리턴 
    * @param player_id
    * @return 해당하는 아이디가 있으면 TransferVO, 없으면 null return
    */
	public TransferVO selectByLastTransfer(int playerId) {
		TransferVO player = null;
		String sql = """
				select * from transfer 
				 		where player_id = ? ORDER BY transfer_id DESC LIMIT 1
				""";
		conn = util.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1,playerId);
			rs = pst.executeQuery();
			player = new TransferVO(rs.getInt(1), rs.getString(2),rs.getInt(3),rs.getString(4),playerId,rs.getInt(6),rs.getInt(7));

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.dbDisconnect(rs, st, conn);
		}
		return player;
	}
}
