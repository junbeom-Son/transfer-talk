package controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import service.UserService;

public class LoginCheckController implements Controller {
	@Override
	public String execute(Map<String, Object> data) {
		String page = "";
		if (data.get("method").equals("GET")) {
			page = "login.jsp";
		} else {
			HttpServletRequest request = (HttpServletRequest) data.get(("request"));
			String user_id = request.getParameter("user_id");
			String user_pw = request.getParameter("user_pw");
			UserService userService = new UserService();
			int loginResult = userService.loginCheck(user_id, user_pw);
			String path = request.getContextPath();
			if (loginResult != 0) {
				//로그인 성공시 메인페이지 이동
				HttpSession session = request.getSession();
				session.setAttribute("loginUser", loginResult);
				page = "redirect:" + path + "/index.jsp";
			} else {
				//로그인 실패
				page = "redirect:"+ path +"/login/loginPage";
			}
		}
		return page;
	}
}