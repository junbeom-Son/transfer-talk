package service;

import dao.TeamDAO;
import dao.TransferDAO;
import vo.TeamVO;
import vo.TransferVO;

public class TransferService {
	TransferDAO transferDao = new TransferDAO();
	TeamDAO teamDao = new TeamDAO();
	
	public int insertTransfer(TransferVO transfer) {
		int result = 0;
		// 신규 선수인지
		TransferVO lastTransfer = this.selectByLastTransfer(transfer.getPlayer_id()); 
		// db마지막팀과 입력받은데이터에 떠나는팀이 일치하는지
		TeamVO lastTeam = teamDao.selectByTeamName(lastTransfer.getPrevious_team_id()); 
		
		//입력성공 -> result : 1
		if(lastTransfer==null || lastTeam.getTeam_id() == transfer.getPrevious_team_id()) {
			result = transferDao.insertTransfer(transfer);
		}
		return result;
	}
	
	public TransferVO selectByLastTransfer(int playerId) {
		return transferDao.selectByLastTransfer(playerId); 
	}
}
