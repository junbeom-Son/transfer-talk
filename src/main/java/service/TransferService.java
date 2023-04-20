package service;

import java.util.List;
import java.util.Queue;

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
		
		
		
		// 신규 선수 이거나
		// 마지막 이적의 새팀과, 현재 이적 기록의 이전팀의 팀이 같을 때 insert 진행
		TransferVO lastTransfer = selectByLastTransfer(transfer.getPlayer().getPlayer_id());
		if (lastTransfer == null ||
				lastTransfer.getNew_team().getTeam_name().
				equals(transfer.getPrevious_team().getTeam_name())) { 
			return transferDao.insertTransfer(transfer);
		}
		
//		System.out.println(lastTransfer.getNew_team().getTeam_name().
//				equals(transfer.getPrevious_team().getTeam_name()));
		
		
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
	
	public int insertTransfers(Queue<TransferVO> transfers) {
		int result = 0;
		while (!transfers.isEmpty()) {
			TransferVO transfer = transfers.poll();
			insertTransfer(transfer);
//			if (insertTransfer(transfer) == 0) {
//				transfers.add(transfer);
//			} else {
//				++result;
//			}
		}
		return result;
	}
	/**
	 * Null return 가능
	 * playerId를 받아 아이디에 해당하는 TransferVO 리턴
	 * @param playerId
	 * @return 해당하는 id가 있으면 TransferVO, 없으면 null return
	 * 작성자 : 서준호
	 */
	public List<TransferVO> selectPlayerDetailById(int playerId) {
		return transferDao.selectPlayerDetailById(playerId); 
	}
	/**
	 *  Null return 가능
	 * leagueName를 받아 아이디에 해당하는 TransferVO 리턴
	 * @param leagueName
	 * @return 해당하는 leagueName이 있으면 TransferVO, 없으면 null return
	 * 작성자 : 서준호
	 */
	//연도, 시즌 조건은 아직 추가 안함
	public List<TransferVO> selectTransferAll(String leagueName) {
		return transferDao.selectTransferAll(leagueName);
	}
	/**
	 * year을 받아 연도에 해당하는 TransferVO 리턴
	 * @param year
	 * 작성자 : 서준호
	 */
	public List<TransferVO> selectTransferTop5(int year) {
		return transferDao.selectTransferTop5(year);
	}
}
