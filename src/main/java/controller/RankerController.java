package controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

import service.TransferService;
import vo.TransferVO;

/**
 * 특정년도 이적료 상위5 조회 컨트롤러
 * TransferService에 year을 매개변수로 하는 상위5의 fee를 조회하는 selectTransferTop5 메서드 필요
 * @param year(특정년도)
 * @return transferTop5.jsp로 forward
 * 작성자 : 서준호
 */
public class RankerController implements Controller {

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		HttpServletRequest request = (HttpServletRequest) data.get("request");
		TransferService service = new TransferService();
		String leagueName = request.getParameter("league").replace("%20", " ");
		String teamName = request.getParameter("team").replace("%20", " ");
		String year = request.getParameter("year");
		boolean top5 = request.getParameter("top5").equals("true");
		List<TransferVO> transfers = service.selectTransfers(year, leagueName, teamName, top5);
		ObjectMapper objectMapper = new ObjectMapper();
//		if (top5) {
//			return "responseBody:" + objectMapper.writeValueAsString(transfers);
//		}
//		else {
//			request.setAttribute("transfers", transfers);
//			return "/layout/transferList.jsp";
//		}
		return "responseBody:" + objectMapper.writeValueAsString(transfers);
	}
}
