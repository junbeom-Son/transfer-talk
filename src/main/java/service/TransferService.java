package service;

import dao.TransferDAO;
import vo.TransferVO;

public class TransferService {
	TransferDAO transferDao = new TransferDAO();
	public String transferInsert(TransferVO transfer) {
		int result = transferDao.transferInsert(transfer);
		return result > 0 ? "입력성공" : "입력실패";
	}
}
