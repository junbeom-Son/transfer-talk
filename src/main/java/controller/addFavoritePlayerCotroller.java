package controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import service.PlayerService;

public class addFavoritePlayerCotroller implements Controller {

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		HttpServletRequest request = (HttpServletRequest) data.get(("request"));
		String user_id = request.getParameter("user_id");
		int player_id = Integer.parseInt(request.getParameter("player_id")); 
		PlayerService playerService = new PlayerService();
		playerService.addFavoritePlayer(user_id, player_id);
		return null;
	}

}
