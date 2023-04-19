package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/") //기본주소 : /transferTalk
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		
		Controller controller = null;
		Map<String, Object> data = new HashMap<>();
		data.put("method", request.getMethod());
		data.put("request", request);
		data.put("response", response);
		System.out.println(path);
		switch (path) {
		case "/transfer/league":
			controller = new TransferInfoController();
			break;
		case "/player/search":
			controller = new AllplayerInfoController();
			break;
		case "/player/{id}":
			controller = new PlayerInfoController();
			break;
		case "/transfer/top_transfer_fee":
			controller = new RankerController();
			break;
			
//		case "/site-result/changePhoto.do":
//			controller = new ChangePhotoController();
//			break;
		}
		
		String page = null;
		try {
			page = controller.execute(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		if (page.indexOf("redirect:") >= 0) {
//			response.sendRedirect(page.substring(9));
//		} else if(page.indexOf("download") >= 0) {
//			response.getWriter().append("download OK");
//		} else if (page.indexOf("responseBody:")>=0) {
//			response.getWriter().append(page.substring(13));
//		}
//		else {
//			RequestDispatcher rd = request.getRequestDispatcher(page);
//			rd.forward(request, response);
//		}
	}

	

}
