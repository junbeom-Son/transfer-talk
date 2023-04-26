package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dbUtil.util;
import vo.UserVO;

public class UserDAO {
	private Connection conn;
	private Statement st;
	private PreparedStatement pst; // ?지원
	private ResultSet rs;
	private int resultCount; 

	/**
	 * 아이디 중복 체크
	 * @param userId
	 * @return 1 -> 아이디 이미 존재, 0 -> 아이디 없음
	 */
	public int registerCheck(String userId) {
		UserVO user = null;
		String sql = "select * from users where user_id = ?";
		resultCount = 0;
		conn = util.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, userId);
			rs = pst.executeQuery();
			resultCount = rs.getRow();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.dbDisconnect(rs, pst, conn);
		}
		return resultCount;
	}

}
