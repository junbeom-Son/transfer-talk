package service;

import dao.PlayerDAO;
import vo.PlayerVO;

public class PlayerService {
	
	PlayerDAO playerDao = new PlayerDAO();
	public int insertPlayer(PlayerVO player) {
		return playerDao.insertPlayer(player);
	}

	public PlayerVO selectPlayerById(int player_id) {
		return playerDao.selectPlayerById(player_id);
	}
}
