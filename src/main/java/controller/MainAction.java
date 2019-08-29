package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.msk.Action;

import dao.UserDao;
import model.Factory;
import model.LoginUser;
import model.Manufactures;
import model.Message;
import model.Notice;
import model.Sales;
import model.SendEmail;
import service.EmailService;
import service.MainService;
import service.ManufacturesService;
import service.ProfileService;
import service.SalesService;

public class MainAction extends Action {

	MainService mainService = new MainService();

	public String mainUserGET(HttpServletRequest request, HttpServletResponse res) throws Exception {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("LOGINED_ID");
		
		List<Notice> notices = mainService.getNotice();
		int lastNotice = mainService.lastNotice();
		int lastWort = mainService.lastWork(id);
		int lastEmail = mainService.lastEmail(id);
		int unReadMs = mainService.unReadMs(id);
		
		EmailService emailService = new EmailService();
		List<SendEmail> emails = emailService.getEmails(id);
		
		ProfileService profileService = new ProfileService();
		List<Message> mss = profileService.getMessages(id);		
		
		List works = mainService.getWorks(id);
		String pl = mainService.getPl(id);
		session.setAttribute("pl", pl);
		
		request.setAttribute("works", works);
		request.setAttribute("emails", emails);
		request.setAttribute("notices", notices);
		request.setAttribute("mss", mss);
		request.setAttribute("lastNotice", lastNotice);
		request.setAttribute("lastWort", lastWort);
		request.setAttribute("lastEmail", lastEmail);
		request.setAttribute("unReadMs", unReadMs);

		return "/concept-master/content/main_e.jsp";
	}

	public String mainManagerGET(HttpServletRequest request, HttpServletResponse res) throws Exception {

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("LOGINED_ID");
		
		List<Notice> notices = mainService.getNotice();
		request.setAttribute("notices", notices);

		List<LoginUser> emps = mainService.getMainEmp(id);
		request.setAttribute("emps", emps);
		
		int myEmps = mainService.getMyEmps(id);
		request.setAttribute("myEmps", myEmps);
		
		int unReadMs = mainService.unReadMs(id);
		request.setAttribute("unReadMs", unReadMs);
		
		int lastNotice = mainService.lastNotice();
		int lastEmail = mainService.lastEmail(id);
		request.setAttribute("lastNotice", lastNotice);
		request.setAttribute("lastEmail", lastEmail);
		
		Factory fac = mainService.getFac(id);
		request.setAttribute("fac", fac);
		
		return "/concept-master/content/main_m.jsp";
	}

	public String mainPartnerGET(HttpServletRequest request, HttpServletResponse res) throws Exception {

		return "/concept-master/content/main_p.jsp";
	}

	public String mainAdminGET(HttpServletRequest request, HttpServletResponse res) throws Exception {

		return "/concept-master/content/main_a.jsp";
	}

}
