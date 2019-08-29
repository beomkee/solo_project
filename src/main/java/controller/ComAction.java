package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.msk.Action;

import model.Notice;
import service.ComService;

public class ComAction extends Action {

	ComService comService = new ComService();
	
	public String plGET(HttpServletRequest request, HttpServletResponse res) throws Exception {
		
		HttpSession session = request.getSession();
		String pl = (String) session.getAttribute("pl");
		String id = (String) session.getAttribute("LOGINED_ID");
		
		return "/concept-master/content/emp/e_pl_info.jsp";
	}

	public String facGET(HttpServletRequest request, HttpServletResponse res) throws Exception {
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("LOGINED_ID");
		String f_num = comService.getFNum(id);
		
		int pageSize = 10;
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null || pageNum == "") {
			pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum);
		int count = comService.getFacMfCount(f_num);
		int startRow = (currentPage - 1) * pageSize;
		int endRow = currentPage * pageSize;
		if (count < endRow) {
			endRow = count;
		}
		int number = count - ((currentPage - 1) * pageSize);

		List f_mfs = comService.getFMf(id, startRow, pageSize);

		int bottomLine = 3;
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		int startPage = 1 + (currentPage - 1) / bottomLine * bottomLine;
		int endPage = startPage + bottomLine - 1;
		if (endPage > pageCount) {
			endPage = pageCount;
		}
		
		request.setAttribute("f_num", f_num);
		request.setAttribute("f_mfs", f_mfs);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("bottomLine", bottomLine);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("number", number);
		
		return "/concept-master/content/emp/e_f_info.jsp";
	}
}
