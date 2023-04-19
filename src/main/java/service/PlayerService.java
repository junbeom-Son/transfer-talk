package service;

import java.util.Set;

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
		if (selectPlayerById(player.getPlayer_id()) == null) {
			return playerDao.insertPlayer(player);
		}
		return 0;
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
	
	public void insertPlayers(Set<PlayerVO> players) {
		for (PlayerVO player : players) {
			insertPlayer(player);
		}
	}
}
