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
import vo.LeagueVO;
import vo.TeamVO;


public class TeamDAO {
	private Connection conn;
	private Statement st;
	private PreparedStatement pst; // ?지원
	private ResultSet rs;
	private int resultCount; 
	
	CallableStatement cst;
	
	/**
	 * team_name, league_id를 insert 하는것
	 * @param team
	 * @return 저장 성공 시 1, 실패시 0
	 * 작성자 : 한진
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
    * 작성자 : 한진
    */
	public TeamVO selectTeamByTeamName(String team_name) {
		TeamVO team = null;
		String sql = """
				select * from team join league on (team.league_id = league.league_id) where team_name = ?
				""";
		conn = util.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, team_name);
			rs = pst.executeQuery();
			while(rs.next()) {
				team = new TeamVO();
				team.setTeam_id(rs.getInt("team_id"));
				team.setTeam_name(team_name);
				
				LeagueVO league = new LeagueVO();
				league.setLeague_id(rs.getInt("league_id"));
				league.setLeague_name(rs.getString("league_name"));
				league.setLeague_country(rs.getString("country_name"));
				
				team.setLeague(league);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.dbDisconnect(rs, pst, conn);
		}
		return team;
	}
	
	/**
	 * league_name를 받아 모든 team_name 데이터를 리턴
	 * @param league_name
	 * @return team_name
	 * 작성자 : 한진
	 */
	public List<String> selectTeamsByleague(String league_name) {
		List<String> teams = new ArrayList<>();
		String sql = """
				select distinct(team_name) 
				from team
				join league on (team.league_id = league.league_id) 
				where league_name = ?
				""";
		conn = util.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, league_name);
			rs = pst.executeQuery();
			while(rs.next()) {
				teams.add(rs.getString("team_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.dbDisconnect(null, pst, conn);
		}
		return teams;
	}
}
