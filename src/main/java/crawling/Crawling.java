package crawling;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import service.LeagueService;
import service.PlayerService;
import vo.LeagueVO;
import vo.PlayerVO;
import vo.TeamVO;
import vo.TransferVO;

public class Crawling {
	private static int FIRST_YEAR = 1994; // 시작은 1992
	private static int CUR_YEAR = 2023; //test 목적으로 변경, 추후 2023으로 복귀
	
	private Set<PlayerVO> players;
	private List<TransferVO> transfers;
	private Set<TeamVO> teams;
	private Set<LeagueVO> leagues;
	private Map<String, String> leagueNames = new HashMap<>();
	private Map<String, String> leagueCodes = new HashMap<>();
	
	private int curYear;
	
	private PlayerService playerService = new PlayerService();
	private LeagueService leagueService = new LeagueService();
	
	/**
	 * 크롤링 시작 메서드
	 * 연도 -> 시즌(여름, 겨울) -> 팀 -> in -> out 순으로 순회 : 먼저 발생한 순서대로 데이터를 저장하기 위해서
	 * 시즌 코드 s, w 순으로 crawlBySeason 호출
	 * @throws IOException
	 * 작성자 : 손준범
	 */
	public void crawl() throws IOException {
		initializeDefaultLeagueNames();
		initializeContryCodes(leagueCodes);
		for (int year = FIRST_YEAR; year <= CUR_YEAR; ++year) {
			curYear = year;
			System.out.println(year + "*************");
			crawlBySeason('s');
			crawlBySeason('w');			
		}
	}
	
	/**
	 * 국가명 → 해당 국가 리그명으로 변환
	 * map 리스트에 키값이 없을경우 국가명+league로 변환
	 * 작성자 : 김창겸
	 */
	private void initializeDefaultLeagueNames() {
		leagueNames.put("England", "Premier_League");
		leagueNames.put("Spain", "LaLiga");
		leagueNames.put("Germany", "BundesLiga");
		leagueNames.put("Italy", "Serie_A");
		leagueNames.put("France", "Ligue_1");
		leagueNames.put("Brazil", "Breasileirao");
		leagueNames.put("Argentina", "Superliga_Argentina");
		leagueNames.put("Netherlands", "Eredivisie");
		leagueNames.put("Portugal", "Primeira_Liga");
		leagueNames.put("United States", "MLS");
		leagueNames.put("Republic of Korea", "K리그");
		leagueNames.put("Scotland", "Scotland Premiership");
	}
	
	/**
	 * 시즌별 크롤링 하기 위해 url을 만드는 메서드
	 * 각 시즌별로 국가명의 url코드를 사용하며 반복해서 url 생성후, 크롤링 진행
	 * 크롤링한 데이터를 팀별로 수집하기 위해 transferByTheTeam호출
	 * @param season
	 * @throws IOException
	 * 작성자 : 손준범
	 */
	public void crawlBySeason(char season) throws IOException {
		
		for (String leagueName : leagueCodes.keySet()) {
			String leagueCode = leagueCodes.get(leagueName);
			String URL = "https://www.transfermarkt.com/" + leagueName + "/transfers/wettbewerb/"+
					leagueCode + "/plus/?saison_id=" + curYear + "&s_w=" + season
							+ "&leihe=1&intern=0&intern=1";
			System.out.println(URL);
			Document doc = Jsoup.connect(URL).maxBodySize(0).get();
			Elements teamElements = doc.select(".show-for-small ~ .box");
			
			players = new HashSet<>();
			transfers = new ArrayList<>();
			teams = new HashSet<>();
			leagues = new HashSet<>();
			for (int i = 0; i < teamElements.size(); i++) {
				transferByTheTeam(teamElements.get(i));
			}
//			시즌별로 저장한 정보 service에 저장하기 위한 호출 코드 입력 하기
			
//			playerService.insertPlayers(players);
			leagueService.insertLeagues(leagues);
		}
	}
	
	/**
	 * 국가별 코드를 매핑하기 위한 Map 초기화
	 * 국가별 코드는 크롤링을 위한 url에서 사용
	 * @param countryCodes
	 * 작성자 : 손준범
	 */
	public void initializeContryCodes(Map<String, String> countryCodes) {
		countryCodes.put("premier-league", "GB1");
		countryCodes.put("laliga", "ES1");
		countryCodes.put("bundesliga", "L1");
		countryCodes.put("serie-a", "IT1");
		countryCodes.put("ligue-1", "FR1");
	}
	
	/**
	 * 팀별로 이적정보를 저장하기 위한 메소드
	 * 해당 팀에 들어오는 정보와, 팀에서 나가는 이적을 구분해서 메서드 구분
	 * 해당 팀에 들어오는 정보는 transferToTheTeam()메서드 호출
	 * 해당 팀에서 나가는 정보는 transferFromTheTeam()메서드 호출
	 * @param teamElement
	 * 작성자 : 손준범
	 */
	public void transferByTheTeam(Element teamElement) { // in, out에 해당 팀을 넣어주기
		String teamName = teamElement.select("h2 > a").get(1).text();
		transferToTheTeam(teamElement.select(".responsive-table").get(0).select("table > tbody > tr"), teamName);
		if (teamElement.select(".responsive-table").size() > 1) {
			transferFromTheTeam(teamElement.select(".responsive-table").get(1).select("table > tbody > tr"), teamName);			
		}
	}
	
	/**
	 * 해당 팀에 들어온 선수들 정보로 데이터 수집
	 * @param inPlayers
	 * @param newTeamName
	 * 작성자 : 손준범
	 */
	public void transferToTheTeam(Elements inPlayers, String newTeamName) {
		for (int j = 0; j < inPlayers.size(); j++) {
			Elements idElements = inPlayers.get(j).select("td > .di > .show-for-small > a");
			if (idElements.size() == 0) {
				continue;
			}
			String idStr = idElements.get(0).attr("href");
			int player_id = Integer.parseInt(idStr.substring(idStr.lastIndexOf("/") + 1, idStr.length()));
			String player_name = inPlayers.get(j).select("td > .di > .show-for-small > a").get(0).text();
			String ageStr = inPlayers.get(j).select("td").get(1).text().replaceAll("[^0-9]", "");
			int age = 0;
			if (!ageStr.equals("")) {
				age = Integer.parseInt(ageStr);
			}
			String nation = null;
			if (inPlayers.get(j).select("td > img").size() > 0) {
				nation = inPlayers.get(j).select("td > img").get(0).attr("title");
			}
			
			if (inPlayers.get(j).select("td").size() < 3) {
				System.out.println(player_name + "doesn't have a position******");
			}
			String position = inPlayers.get(j).select("td").get(3).text();
			String previousTeamName = null;
			if (inPlayers.get(j).select("td.zentriert > a > img").size() > 0) {
				previousTeamName = inPlayers.get(j).select("td.zentriert > a > img").get(0).attr("title");
			}
			String previousLeagueName = inPlayers.get(j).select("td.verein-flagge-transfer-cell > img").size() == 0 ? 
					"-" : inPlayers.get(j).select("td.verein-flagge-transfer-cell > img").get(0).attr("title");
			if(leagueNames.get(previousLeagueName) == null) {
				leagueNames.put(previousLeagueName, previousLeagueName + "_league");
			}
			if (inPlayers.get(j).select("td.rechts > a").size() == 0) {
				System.out.println(inPlayers.get(j));				
			}
			String fee = inPlayers.get(j).select("td.rechts > a").get(0).text();

			PlayerVO player = makePlayer(player_id, player_name);
			players.add(player);
			
			LeagueVO league = makeLeagueVO(leagueNames.get(previousLeagueName), previousLeagueName); 
            leagues.add(league);
			
//			TeamVO previousTeam = makeTeamVO(previousTeamName, league);
//			TeamVO newTeam = makeTeamVO(newTeamName, league);
//            teams.add(previousTeam);
			
//            TransferVO transfer = makeTransferVO(age, fee, player, position, previousTeam, newTeam);
//            transfers.add(transfer);
		}
		
	}
	
	/**
	 * 해당 팀에서 나가는 선수들 정보로 데이터 수집
	 * @param outPlayers
	 * @param previousTeamName
	 * 작성자 : 손준범
	 */
	public void transferFromTheTeam(Elements outPlayers, String previousTeamName) {
		for (int j = 0; j < outPlayers.size(); j++) {
			Elements idElements = outPlayers.get(j).select("td > .di > .show-for-small > a");
			if (idElements.size() == 0) {
				continue;
			}
			String outStr = idElements.get(0).attr("href");
			int player_id = Integer.parseInt(outStr.substring(outStr.lastIndexOf("/") + 1, outStr.length()));
			String player_name = outPlayers.get(j).select("td > .di > .show-for-small > a").get(0).text();
			String ageStr = outPlayers.get(j).select("td").get(1).text().replaceAll("[^0-9]", "");
			int age = 0;
			if (!ageStr.equals("")) {
				age = Integer.parseInt(ageStr);
			}
			
			String nation = null;
			if (outPlayers.get(j).select("td > img").size() > 0) {
				nation = outPlayers.get(j).select("td > img").get(0).attr("title");
			}
			if (outPlayers.get(j).select("td").size() < 3) {
				System.out.println(player_name + "doesn't have a position******");
				System.out.println(outPlayers.get(j).select("td"));
			}
			String position = outPlayers.get(j).select("td").get(3).text();
			String newTeamName = null;
			if (outPlayers.get(j).select("td.zentriert > a > img").size() > 0) {
				newTeamName = outPlayers.get(j).select("td.zentriert > a > img").get(0).attr("title");
			}
			String newLeagueName = outPlayers.get(j).select("td.verein-flagge-transfer-cell > img").size() == 0 ? "-"
					: outPlayers.get(j).select("td.verein-flagge-transfer-cell > img").get(0).attr("title");
			if(leagueNames.get(newLeagueName) == null) {
				leagueNames.put(newLeagueName, newLeagueName + "_league");
			}
			
			if (outPlayers.get(j).select("td.rechts > a").size() == 0) {

				System.out.println(outPlayers.get(j));
			}
			String fee = outPlayers.get(j).select("td.rechts > a").get(0).text();
			PlayerVO player = makePlayer(player_id, player_name);
			players.add(player);
			
			LeagueVO league = makeLeagueVO(leagueNames.get(newLeagueName), newLeagueName);
			leagues.add(league);
//			
//			TeamVO previousTeam = makeTeamVO(previousTeamName, league);
//			TeamVO newTeam = makeTeamVO(newTeamName, league);
//            teams.add(newTeam);
			
//			TransferVO transfer = makeTransferVO(age, fee, player, position, previousTeam, newTeam);
//			transfers.add(transfer);
		}
	}
	
	/**
	 * PlayerVO 오브젝트 생성 메서드
	 * @param player_id
	 * @param player_name
	 * @return PlayerVO
	 * 작성자 : 손준범
	 */
	private PlayerVO makePlayer(int player_id, String player_name) {
		PlayerVO player = new PlayerVO();
		player.setPlayer_id(player_id);
		player.setPlayer_name(player_name);
		return player;
	}
	
	/**
	 * LeagueVO 오브젝트 생성 메서드
	 * @param leagueName
	 * @param nation
	 * @return LeagueVO
	 * 작성자 : 손준범
	 */
	private LeagueVO makeLeagueVO(String leagueName, String nation) {
		LeagueVO league = new LeagueVO();
		league.setLeague_name(leagueName);
        league.setLeague_country(nation);
        return league;
	}
	
	/**
	 * TeamVO 오브젝트 생성 메서드
	 * @param teamName
	 * @param league
	 * @return TeamVO
	 * 작성자 : 손준범
	 */
	private TeamVO makeTeamVO(String teamName, LeagueVO league) {
		TeamVO team = new TeamVO();
		team.setTeam_name(teamName);
		team.setLeague(league);
		return team;
	}
	
	/**
	 * Transfer 오브젝트 생성 메서드
	 * @param age
	 * @param fee
	 * @param player
	 * @param position
	 * @param previousTeam
	 * @param newTeam
	 * @return TransferVO
	 * 작성자 : 손준범
	 */
	private TransferVO makeTransferVO(int age, String fee, PlayerVO player, String position, TeamVO previousTeam, TeamVO newTeam) {
		TransferVO transfer = new TransferVO();
		transfer.setAge(age);
		transfer.setFee(fee);
		transfer.setPrevious_team(previousTeam);
		transfer.setNew_team(newTeam);
		transfer.setPlayer(player);
		transfer.setPlayer_position(position);
		transfer.setTransfer_year(this.curYear);
		return transfer;
	}
}