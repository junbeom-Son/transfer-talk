package service;

import dao.LeagueDAO;
import dao.TeamDAO;
import vo.LeagueVO;
import vo.TeamVO;

public class TeamAndLeagueService {
	LeagueDAO leagueDao = new LeagueDAO();
	TeamDAO teamDao = new TeamDAO();
	

	public String insertTeam(TeamVO team) {
		//팀이 db에 없을 경우
		TeamVO selectTeam = selectTeamByTeamName(team.getTeam_name());
		if(selectTeam == null) {
			//리그가 db에 없을 경우
			if(selectLeagueByLeagueName(team.getLeague().getLeague_name()) == null) {
				insertLeague(team.getLeague());//리그테이블 추가
				//team에 리그아이디 넣기
				LeagueVO league = selectLeagueByLeagueName(team.getLeague().getLeague_name());
				team.getLeague().setLeague_id(league.getLeague_id());
			}else {
				team.getLeague().setLeague_id(selectTeam.getLeague().getLeague_id());
			}
			teamDao.insertTeam(team);
			return "팀추가";
		}
		return "추가없음";
	}
	
	public int insertLeague(LeagueVO league) {
		return leagueDao.insertLeague(league);
	}
	
	public LeagueVO selectLeagueByLeagueName(String league_name) {
		return leagueDao.selectLeagueByLeagueName(league_name);
	}
	public TeamVO selectTeamByTeamName(String team_name) {
		return teamDao.selectTeamByTeamName(team_name);
	}
}
