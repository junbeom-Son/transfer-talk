package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dbUtil.util;
import vo.TeamVO;
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
	 * 작성자 : 한진
	 */
	public int insertTransfer(TransferVO transfer) {
		String sql = """
				insert into transfer (player_position, transfer_year, fee, player_id, age, previous_team_id, new_team_id) 
						values(?, ?, ?, ?, ?, ?, ?)
				""";
		conn = util.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, transfer.getPlayer_position());
			pst.setInt(2, transfer.getTransfer_year());
			pst.setString(3, transfer.getFee());
			pst.setInt(4, transfer.getPlayer().getPlayer_id());
			pst.setInt(5, transfer.getAge());
			pst.setInt(6, transfer.getPrevious_team().getTeam_id());
			pst.setInt(7, transfer.getNew_team().getTeam_id());
			resultCount = pst.executeUpdate(); 
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
    * 작성자 : 한진
    */
	public TransferVO selectByLastTransfer(int player_id) {
		TransferVO transfer = null;
		String sql = "select * from transfer join team on (transfer.new_team_id = team.team_id) where player_id = "+ player_id +" ORDER BY transfer_id DESC LIMIT 1";
		conn = util.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				transfer = new TransferVO();
				transfer.setTransfer_id(rs.getInt("transfer_id"));
				transfer.setPlayer_position(rs.getString("player_position"));
				transfer.setTransfer_year(rs.getInt("transfer_year"));
				transfer.setFee(rs.getString("fee"));
				
				TeamVO team = new TeamVO();
				team.setTeam_id(rs.getInt("team_id"));
				team.setTeam_name(rs.getString("team_name"));
				transfer.setNew_team(team);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.dbDisconnect(rs, st, conn);
		}
		return transfer;
	}
}
