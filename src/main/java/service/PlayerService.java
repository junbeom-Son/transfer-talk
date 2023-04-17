package service;

import dao.PlayerDAO;
import vo.PlayerVO;

public class PlayerService {
	
	PlayerDAO playerDao = new PlayerDAO();
	
	/**
	 * PlayerVO를 받아 저장하는 기능
	 * @param player
	 * @return 저장 성공 시 1, 실패시 0
	 */
	public int insertPlayer(PlayerVO player) {
		return playerDao.insertPlayer(player);
	}

	/**
	 * *****Null return 가능*****
	 * player_id를 받아 아이디에 해당하는 PlayerVO 리턴 
	 * @param player_id
	 * @return 해당하는 아이디가 있으면 PlayerVO, 없으면 null return
	 */
	public PlayerVO selectPlayerById(int player_id) {
		return playerDao.selectPlayerById(player_id);
	}
}
