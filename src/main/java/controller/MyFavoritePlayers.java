package controller;

import java.util.Map;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import vo.UserVO;

public class MyFavoritePlayers implements Controller {

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		HttpServletRequest request = (HttpServletRequest) data.get(("request"));
		HttpSession session = request.getSession();
		UserVO loginUser = (UserVO)session.getAttribute("loginUser");
		
		return null;
	}

}
