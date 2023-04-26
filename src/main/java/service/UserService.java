package service;

import dao.UserDAO;

public class UserService {

	private UserDAO userDao = new UserDAO();
	
	public int registerCheck(String userId) {
		return userDao.registerCheck(userId);
	}

}
