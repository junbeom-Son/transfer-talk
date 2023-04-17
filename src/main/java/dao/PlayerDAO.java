package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dbUtil.util;
import vo.PlayerVO;

public class PlayerDAO {
	private Connection conn;
	private Statement st;
	private PreparedStatement pst; // ?지원
	private ResultSet rs;
	private int resultCount; 
	
	/**
	 * 1. player_id, player_name insert 하는것
	 * @param player
	 * @return 저장 성공 시 1, 실패시 0
	 */
	public int insertPlayer(PlayerVO player) {
		String sql = """
				insert into player(player_id, player_name) values (?, ?)
				""";
		conn = util.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, player.getPlayer_id());
			pst.setString(2, player.getPlayer_name());
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
    * player_id를 받아 아이디에 해당하는 PlayerVO 리턴 
    * @param player_id
    * @return 해당하는 아이디가 있으면 PlayerVO, 없으면 null return
	 */
	public PlayerVO selectPlayerById(int player_id) {
		String sql ="""
				select Player_id
				from player 
				where Player_id = ? 
				""";
		
		PlayerVO player = null;
		conn = util.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			rs = st.executeQuery(sql);
			player = new PlayerVO();
			while (rs.next()) {
				player.setPlayer_id(player_id);
				player.setPlayer_name(rs.getString("player_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.dbDisconnect(null, pst, conn);
		}
		return player;
	}
}
