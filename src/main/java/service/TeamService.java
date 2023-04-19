package service;

import java.util.Set;

import dao.TeamDAO;
import vo.TeamVO;

public class TeamService {
	TeamDAO teamDao = new TeamDAO();
	LeagueService leagueService = new LeagueService();
	

	public int insertTeam(TeamVO team) {
		//팀이 db에 없을 경우
		TeamVO selectTeam = selectTeamByTeamName(team.getTeam_name());
		if(selectTeam == null) {
			int leagueId = leagueService.selectLeagueByLeagueName(team.getLeague().getLeague_name()).getLeague_id();
			team.getLeague().setLeague_id(leagueId);
			return teamDao.insertTeam(team);
		}
		return 0;
	}
	
	public TeamVO selectTeamByTeamName(String team_name) {
		return teamDao.selectTeamByTeamName(team_name);
	}
	
	public int insertTeams(Set<TeamVO> teams) {
		int result = 0;
		for (TeamVO team : teams) {
			result += insertTeam(team);
		}
		return result;
	}
}
