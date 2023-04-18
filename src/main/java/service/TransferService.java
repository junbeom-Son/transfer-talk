package service;

import dao.TeamDAO;
import dao.TransferDAO;
import vo.TeamVO;
import vo.TransferVO;

public class TransferService {
	TransferDAO transferDao = new TransferDAO();
	TeamDAO teamDao = new TeamDAO();
	TeamAndLeagueService teamService = new TeamAndLeagueService();
	
	/**
	 * TransferVO를 받아 저장하는 기능 (신규선수이거나 db에 최종팀이 들어온 데이터의 이적전 팀과 같을때만 저장)
	 * @param transfer
	 * @return 저장 성공 시 1, 실패시 0
	 */
	public int insertTransfer(TransferVO transfer) {
		//팀 id 추가
		TeamVO prevTeam = teamService.selectTeamByTeamName(transfer.getPrevious_team().getTeam_name());
		TeamVO currentTeam = teamService.selectTeamByTeamName(transfer.getNew_team().getTeam_name());
		transfer.getPrevious_team().setTeam_id(prevTeam.getTeam_id());
		transfer.getNew_team().setTeam_id(currentTeam.getTeam_id());
		
		//해당 선수의 마지막 이적데이터
		TransferVO lastTransfer = selectByLastTransfer(transfer.getPlayer().getPlayer_id()); 
		if(lastTransfer==null) { // 신규 선수인지
			return transferDao.insertTransfer(transfer);
		}else { // 신규선수가 아닐 때, db마지막팀과 입력받은데이터에 떠나는팀이 일치하는지
			String lastTeam = lastTransfer.getNew_team().getTeam_name(); 
			if(lastTeam.equals(transfer.getPrevious_team().getTeam_name())) {
				return transferDao.insertTransfer(transfer);
			}
		}
		return 0;
	}
	
	/**
	 * *****Null return 가능*****
	 * player_id를 받아 아이디에 해당하는 TransferVO 리턴 
	 * @param player_id
	 * @return 해당하는 id가 있으면 TransferVO, 없으면 null return
	 */
	public TransferVO selectByLastTransfer(int playerId) {
		return transferDao.selectByLastTransfer(playerId); 
	}
}
