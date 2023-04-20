package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dbUtil.util;
import vo.PlayerVO;
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
	/**
	 *
	 * playerId를 받아 아이디에 해당하는 TransferVO리턴
	 * transfer, player 테이블의 모든 정보 조회
	 * @param playerId
	 *
	 * 작성자 : 서준호
	 */
	public List<TransferVO> selectPlayerDetailById(int playerId) {
		
		String sql = "select * from transfer where player_id = ?";
		List<TransferVO> transfers = new ArrayList<>();
		conn = util.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, playerId);
			rs = pst.executeQuery();
			while(rs.next()) {
				TransferVO transfer = new TransferVO();
				transfer.setTransfer_id(rs.getInt("transfer_id"));
				transfer.setPlayer_position(rs.getString("player_position"));
				transfer.setTransfer_year(rs.getInt("transfer_year"));
				transfer.setFee(rs.getString("fee"));
				transfer.setAge(rs.getInt("age"));
				
				PlayerVO player = new PlayerVO();
				player.setPlayer_id(rs.getInt("player_id"));
				player.setPlayer_name(rs.getString("player_name"));
				transfer.setPlayer(player);
				
				TeamVO previousteam = new TeamVO();
				previousteam.setTeam_name(rs.getString("team_name"));
				transfer.setPrevious_team(previousteam);
				
				TeamVO newteam = new TeamVO();
				newteam.setTeam_name(rs.getString("team_name"));
				transfer.setNew_team(newteam);
				transfers.add(transfer);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.dbDisconnect(null, pst, conn);
		}
		return transfers;
	}
	/**
	 * 
	 * leagueName를 받아 아이디에 해당하는 TransferVO리턴
	 * league, team, transfer 테이블 조인
	 * leagueName을 통해 transfer 테이블의 모든 정보 조회
	 * @param leagueName
	 *
	 * 작성자 : 서준호
	 */
	public List<TransferVO> selectTransferAll(String leagueName) {
		String sql = "select * from transfer "
				+ "join team on (transfer.new_team_id = team.team_id) "
				+ "join league on (team.league_id = league.league_id) "
				+ "where league_name = ?";
		List<TransferVO> transfers = new ArrayList<>();
		conn = util.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, leagueName);
			rs = pst.executeQuery();
			while(rs.next()) {
				TransferVO transfer = new TransferVO();
				transfer.setTransfer_id(rs.getInt("transfer_id"));
				transfer.setPlayer_position(rs.getString("player_position"));
				transfer.setTransfer_year(rs.getInt("transfer_year"));
				transfer.setFee(rs.getString("fee"));
				transfer.setAge(rs.getInt("age"));
				
				PlayerVO player = new PlayerVO();
				player.setPlayer_id(rs.getInt("player_id"));
				player.setPlayer_name(rs.getString("player_name"));
				transfer.setPlayer(player);
				
				TeamVO previousteam = new TeamVO();
				previousteam.setTeam_name(rs.getString("team_name"));
				transfer.setPrevious_team(previousteam);
				
				TeamVO newteam = new TeamVO();
				newteam.setTeam_name(rs.getString("team_name"));
				transfer.setNew_team(newteam);
				transfers.add(transfer);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.dbDisconnect(null, pst, conn);
		}
		return transfers;
	}
	/**
	 * year을 받아 연도에 해당하는 TransferVO리턴
	 * 연봉 상위 5위 선수조회
	 * transfer, team테이블 조인
	 * @param year
	 * @return player_id, player_name, team_name, transfer_id, player_position, fee
	 * 작성자 : 서준호
	 */
	public List<TransferVO> selectTransferTop5(int year) {
		String sql = "select * from transfer join team on (transfer.new_team_id = team.team_id) where transfer_year = ? ORDER BY fee DESC LIMIT 5";
		List<TransferVO> transfers = new ArrayList<>();
		conn = util.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, year);
			rs = pst.executeQuery();
			while(rs.next()) {
				TransferVO transfer = new TransferVO();
				transfer.setTransfer_id(rs.getInt("transfer_id"));
				transfer.setPlayer_position(rs.getString("player_position"));
				transfer.setFee(rs.getString("fee"));
				
				PlayerVO player = new PlayerVO();
				player.setPlayer_id(rs.getInt("player_id"));
				player.setPlayer_name(rs.getString("player_name"));
				transfer.setPlayer(player);
				
				TeamVO newteam = new TeamVO();
				newteam.setTeam_name(rs.getString("team_name"));
				transfer.setNew_team(newteam);
				transfers.add(transfer);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.dbDisconnect(null, pst, conn);
		}
		return transfers;
	}
}
