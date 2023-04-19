package service;

import java.util.Set;

import dao.LeagueDAO;
import vo.LeagueVO;

public class LeagueService {
	LeagueDAO leagueDao = new LeagueDAO();
	
	public int insertLeague(LeagueVO league) {
		LeagueVO selectLeague = selectLeagueByLeagueName(league.getLeague_name());
		if(selectLeague == null) {
			return leagueDao.insertLeague(league);
		}
		return 0;
	}
	
	public LeagueVO selectLeagueByLeagueName(String league_name) {
		return leagueDao.selectLeagueByLeagueName(league_name);
	}
	
	public int insertLeagues(Set<LeagueVO> leagues) {
		int result = 0;
		for (LeagueVO league : leagues) {
			result += insertLeague(league);
		}
		return result;
	}
}
