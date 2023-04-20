package controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import service.PlayerService;
import service.TransferService;
import vo.PlayerVO;
import vo.TransferVO;

/**
 * 특정 플레이어 상세페이지 컨트롤러
 * @param player_id(선수아이디)
 * @return PlayerDetail.jsp로 forward
 * 작성자 : 서준호
 */
public class PlayerInfoController implements Controller {

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		HttpServletRequest request = (HttpServletRequest) data.get("request");
		TransferService  transferservice = new TransferService();
		int playerId = Integer.parseInt(request.getParameter("playerId"));		
		TransferVO transfer = transferservice.selectPlayerDetailById(playerId);
		request.setAttribute("PlayerDetail", transfer);
			
		return "PlayerDetail.jsp";
	}
}
