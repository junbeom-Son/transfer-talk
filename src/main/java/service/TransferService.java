package service;

import java.util.List;

import dao.TeamDAO;
import dao.TransferDAO;
import vo.TeamVO;
import vo.TransferVO;

public class TransferService {
	TransferDAO transferDao = new TransferDAO();
	TeamDAO teamDao = new TeamDAO();
	TeamService teamService = new TeamService();
	
	/**
	 * TransferVO를 받아 저장하는 기능 (db에 최종팀이 들어온 데이터의 이적전 팀과 같을때만 저장)
	 * @param transfer
	 * @return 저장 성공 시 1, 실패시 0
	 * 작성자 : 한진
	 */
	public int insertTransfer(TransferVO transfer) {
		//팀 id 추가
		TeamVO prevTeam = teamService.selectTeamByTeamName(transfer.getPrevious_team().getTeam_name());
		TeamVO currentTeam = teamService.selectTeamByTeamName(transfer.getNew_team().getTeam_name());
		transfer.getPrevious_team().setTeam_id(prevTeam.getTeam_id());
		transfer.getNew_team().setTeam_id(currentTeam.getTeam_id());
		
		// 신규선수가 아닐 때, db마지막팀과 입력받은데이터에 떠나는팀이 일치하는지
		TransferVO lastTransfer = selectByLastTransfer(transfer.getPlayer().getPlayer_id()); 
		String lastTeam = lastTransfer.getNew_team().getTeam_name(); 
		if(lastTeam.equals(transfer.getPrevious_team().getTeam_name())) {
			return transferDao.insertTransfer(transfer);
		}
		return 0;
	}
	
	/**
	 * *****Null return 가능*****
	 * playerId를 받아 아이디에 해당하는 TransferVO 리턴 
	 * @param playerId
	 * @return 해당하는 id가 있으면 TransferVO, 없으면 null return
	 * 작성자 : 한진
	 */
	public TransferVO selectByLastTransfer(int playerId) {
		return transferDao.selectByLastTransfer(playerId); 
	}
	
	public int insertTransfers(List<TransferVO> transfers) {
		int result = 0;
		for (TransferVO transfer : transfers) {
			result += insertTransfer(transfer);
		}
		return result;
	}
}
