package controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
		int year = Integer.parseInt(request.getParameter("year"));		
		List<TransferVO> transfer = service.selectTransferTop5(year);
		request.setAttribute("transferTop5", transfer);
				
		return "transferTop5.jsp";
	}
}
