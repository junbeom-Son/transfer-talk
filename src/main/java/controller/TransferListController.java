package controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class TransferListController implements Controller {

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		HttpServletRequest request = (HttpServletRequest) data.get("request");
		return "/layout/transferList.jsp";
	}

}
