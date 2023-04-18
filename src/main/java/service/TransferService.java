package service;

import dao.TeamDAO;
import dao.TransferDAO;
import vo.TransferVO;

public class TransferService {
	TransferDAO transferDao = new TransferDAO();
	TeamDAO teamDao = new TeamDAO();
	
	/**
	 * TransferVO를 받아 저장하는 기능
	 * @param transfer
	 * @return 저장 성공 시 1, 실패시 0
	 */
	public int insertTransfer(TransferVO transfer) {
		int result = 0;
		// 신규 선수인지
		TransferVO lastTransfer = selectByLastTransfer(transfer.getPlayer().getPlayer_id()); 
		// db마지막팀과 입력받은데이터에 떠나는팀이 일치하는지
		String lastTeam = lastTransfer.getPrevious_team().getTeam_name(); 
		
		//입력성공 -> result : 1
		if(lastTransfer==null || lastTeam.equals(transfer.getPrevious_team().getTeam_name())) {
			result = transferDao.insertTransfer(transfer);
		}
		return result;
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
