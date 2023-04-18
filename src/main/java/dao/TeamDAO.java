package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dbUtil.util;
import vo.LeagueVO;
import vo.TeamVO;
import vo.TransferVO;


public class TeamDAO {
	private Connection conn;
	private Statement st;
	private PreparedStatement pst; // ?지원
	private ResultSet rs;
	private int resultCount; 
	
	CallableStatement cst;
	
	/**
	 * team_id, team_name, league_id를 insert 하는것
	 * @param team
	 * @return 저장 성공 시 1, 실패시 0
	 */
	public int insertTeam(TeamVO team) {
		String sql = """
				insert into team (team_name, league_id) values(?, ?)
				""";
		conn = util.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, team.getTeam_name());
			pst.setInt(2, team.getLeague().getLeague_id());
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
    * team_name를 받아 아이디에 해당하는 TeamVO 리턴 
    * @param team_name
    * @return 해당하는 아이디가 있으면 TeamVO, 없으면 null return
    */
	public TeamVO selectByTeamName(String team_name) {
		TeamVO team = null;
		String sql = "select * from team where team_name = " + team_name;
		conn = util.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				team = new TeamVO();
				team.setTeam_id(rs.getInt("team_id"));
				team.setTeam_name(team_name);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.dbDisconnect(rs, st, conn);
		}
		return team;
	}
}
