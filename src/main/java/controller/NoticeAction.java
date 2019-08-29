package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.msk.Action;

import model.Notice;
import service.NoticeService;

public class NoticeAction extends Action {

	NoticeService noticeService = new NoticeService();

	public String nListGET(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int pageSize = 10;
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null || pageNum == "") {
			pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum);
		int count = noticeService.getNoticeCount();
		int startRow = (currentPage - 1) * pageSize;
		int endRow = currentPage * pageSize;
		if (count < endRow) {
			endRow = count;
		}
		int number = count - ((currentPage - 1) * pageSize);

		List<Notice> notices = noticeService.getNoticeList(startRow, pageSize);

		int bottomLine = 3;
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		int startPage = 1 + (currentPage - 1) / bottomLine * bottomLine;
		int endPage = startPage + bottomLine - 1;
		if (endPage > pageCount) {
			endPage = pageCount;
		}

		request.setAttribute("notices", notices);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("bottomLine", bottomLine);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("number", number);

		return "/concept-master/content/notice/noticeList.jsp";
	}

	public String nWriteGET(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int maxNum = noticeService.getMaxNum();
		String pageNum = request.getParameter("pageNum");

		request.setAttribute("maxNum", maxNum);
		request.setAttribute("pageNum", pageNum);

		return "/concept-master/content/notice/noticeWrite.jsp";
	}

	public String nContentGET(HttpServletRequest request, HttpServletResponse res) throws Exception {

		int nNum = Integer.parseInt(request.getParameter("nNum"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		int maxNum = noticeService.getMaxNum();
		int minNum = noticeService.getMinNum();

		Notice notice = new Notice();
		notice = noticeService.getContent(nNum);

		int pre = noticeService.preNotice(nNum);
		int next = noticeService.nextNotice(nNum);
		
		request.setAttribute("pre", pre);
		request.setAttribute("next", next);
		request.setAttribute("minNum", minNum);
		request.setAttribute("maxNum", maxNum);
		request.setAttribute("notice", notice);
		request.setAttribute("pageNum", pageNum);

		return "/concept-master/content/notice/noticeContent.jsp";
	}

	public String modifyGET(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int nNum = Integer.parseInt(request.getParameter("nNum"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));

		Notice notice = new Notice();
		notice = noticeService.getContent(nNum);

		request.setAttribute("notice", notice);
		request.setAttribute("pageNum", pageNum);

		return "/concept-master/content/notice/noticeUpdate.jsp";
	}

	public String writePOST(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String writer = request.getParameter("writer");
		String n_title = request.getParameter("n_title");
		String n_content = request.getParameter("n_content");

		noticeService.writeNotice(writer, n_title, n_content);

		return "/concept-master/content/notice/noticeUpdatePro.jsp";
	}

	public String nUpdatePOST(HttpServletRequest request, HttpServletResponse res) throws Exception {

		String n_num = request.getParameter("n_num");
		String n_title = request.getParameter("n_title");
		String n_content = request.getParameter("n_content");

		noticeService.updateNotice(n_num, n_title, n_content);

		return "/concept-master/content/notice/noticeUpdatePro.jsp";
	}

	public String nDeleteGET(HttpServletRequest request, HttpServletResponse res) throws Exception {
		
		String n_num = request.getParameter("nNum");
		System.out.println(n_num);
		noticeService.deleteNotice(n_num);
		
		return "/concept-master/content/notice/noticeUpdatePro.jsp";
	}
}
