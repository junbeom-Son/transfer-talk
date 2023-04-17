package crawling;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import vo.LeagueVO;
import vo.PlayerVO;
import vo.TeamVO;
import vo.TransferVO;

public class Crawling {
	public void Crawl() throws IOException {
		String currentLeague = "England";
		String URL = "https://www.transfermarkt.com/premier-league/transfers/wettbewerb/GB1/saison_id/2022";
//		https://www.transfermarkt.com/premier-league/transfers/wettbewerb/GB1/plus/?saison_id=2022&s_w=s&leihe=1&intern=0&intern=1
//		https://www.transfermarkt.com/laliga/transfers/wettbewerb/ES1/plus/?saison_id=2022&s_w=s&leihe=1&intern=0&intern=1
		Document doc = Jsoup.connect(URL).get();
		Elements teamElements = doc.select(".show-for-small ~ .box");
		List<PlayerVO> players = new ArrayList<>();
		List<TransferVO> transfers = new ArrayList<>();
		List<TeamVO> teams = new ArrayList<>();
		List<LeagueVO> leagues = new ArrayList<>();
		for (int i = 0; i < teamElements.size(); i++) {
			String teamName = teamElements.get(i).select("h2 > a").get(1).text();
			Elements inPlayers = teamElements.get(i).select(".responsive-table").get(0).select("table > tbody > tr");
			Elements outPlayers = teamElements.get(i).select(".responsive-table").get(0).select("table > tbody > tr");

			// in
			for (int j = 0; j < inPlayers.size(); j++) {
				String idStr = inPlayers.get(j).select("td > .di > .show-for-small > a").get(0).attr("href");
				String player_id = idStr.substring(idStr.lastIndexOf("/") + 1, idStr.length());
				String player_name = inPlayers.get(j).select("td > .di > .show-for-small > a").get(0).text();
				String age = inPlayers.get(j).select("td").get(1).text();
				String nation = inPlayers.get(j).select("td > img").get(0).attr("title");
				String position = inPlayers.get(j).select("td").get(3).text();
				String previousTeam = inPlayers.get(j).select("td.zentriert > a > img").get(0).attr("title");
				String previousLeague = inPlayers.get(j).select("td.verein-flagge-transfer-cell > img").size() == 0 ? "-"
						: inPlayers.get(j).select("td.verein-flagge-transfer-cell > img").get(0).attr("title");
				String fee = inPlayers.get(j).select("td.rechts > a").get(0).text();

				PlayerVO player = new PlayerVO();
				player.setPlayer_id(Integer.parseInt(player_id));
				player.setPlayer_name(player_name);
				players.add(player);
				
				LeagueVO league = new LeagueVO();
	            league.setLeague_name(previousLeague);
	            league.setLeague_country(nation);
	            leagues.add(league);
				
				TeamVO team = new TeamVO();
	            team.setTeam_name(previousTeam);
	            team.setLeague(league);
	            teams.add(team);
				
				TransferVO transfer = new TransferVO();
				transfer.setAge(Integer.parseInt(age));
				transfer.setFee(fee);
				transfer.setPlayer(player);
				transfer.setPlayer_position(position);
				transfer.setPrevious_team(team);
//				transfer.setTransfer_year(j);
//				현재 팀 및 국가 설정
			}

			// out
			for (int j = 0; j < outPlayers.size(); j++) {
				String outStr = outPlayers.get(j).select("td > .di > .show-for-small > a").get(0).attr("href");
				String player_id = outStr.substring(outStr.lastIndexOf("/") + 1, outStr.length());
				String player_name = outPlayers.get(j).select("td > .di > .show-for-small > a").get(0).text();
				String age = outPlayers.get(j).select("td").get(1).text();
				String nation = outPlayers.get(j).select("td > img").get(0).attr("title");
				String position = outPlayers.get(j).select("td").get(3).text();
				String newTeam = outPlayers.get(j).select("td.zentriert > a > img").get(0).attr("title");
				String newLeague = outPlayers.get(j).select("td.verein-flagge-transfer-cell > img").size() == 0 ? "-"
						: inPlayers.get(j).select("td.verein-flagge-transfer-cell > img").get(0).attr("title");
				String fee = outPlayers.get(j).select("td.rechts > a").get(0).text();
				PlayerVO player = new PlayerVO();
				player.setPlayer_id(Integer.parseInt(player_id));
				player.setPlayer_name(player_name);
				players.add(player);
				
				LeagueVO league = new LeagueVO();
	            league.setLeague_name(newLeague);
	            league.setLeague_country(nation);
	            leagues.add(league);
				
				TeamVO team = new TeamVO();
	            team.setTeam_name(newTeam);
	            team.setLeague(league);
	            teams.add(team);
				
				TransferVO transfer = new TransferVO();
				transfer.setAge(Integer.parseInt(age));
				transfer.setFee(fee);
				transfer.setPlayer(player);
				transfer.setPlayer_position(position);
				transfer.setPrevious_team(team);
//				transfer.setTransfer_year(j);
//				현재 팀 및 국가 설정
			}
		}
	}
}