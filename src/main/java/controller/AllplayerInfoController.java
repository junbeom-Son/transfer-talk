package controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import service.PlayerService;
import vo.PlayerVO;

/**
 * 검색하는 이름에 해당하는 모든 선수 조회
 * PlayerService에 playerName을 매개변수로 하여 %이름%에 해당하는 selectPlayerAll 메서드 필요
 * @param playerName(선수이름)
 * @return PlayerAll.jsp로 forward
 * 작성자 : 서준호
 */
public class AllplayerInfoController implements Controller {

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		HttpServletRequest request = (HttpServletRequest) data.get("request");
		PlayerService service = new PlayerService();
		String playerName = request.getParameter("playerName");		
		PlayerVO player = service.selectPlayerByName(playerName);
		request.setAttribute("PlayerAll", player);
				
		return "PlayerAll.jsp";
	}
}