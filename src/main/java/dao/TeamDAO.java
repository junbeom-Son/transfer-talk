package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dbUtil.util;
import vo.TeamVO;


public class TeamDAO {
	private Connection conn;
	private Statement st;
	private PreparedStatement pst; // ?지원
	private ResultSet rs;
	private int resultCount; 
	
	CallableStatement cst;
	
	public TeamVO selectByTeamName(int team_id) {
		TeamVO team = null;
		String sql = """
				select * from team 
						 where team_id = ?;
				""";
		conn = util.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1,team_id);
			rs = pst.executeQuery();
			team = new TeamVO(rs.getInt(1),rs.getString(2),rs.getInt(3));

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.dbDisconnect(rs, st, conn);
		}
		return team;
	}
}
