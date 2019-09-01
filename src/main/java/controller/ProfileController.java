package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import model.ChangePwRequest;
import model.LoginUser;
import model.Manufactures;
import model.Sales;
import service.ChangePwService;
import service.ManufacturesService;
import service.ProfileService;
import service.SalesService;

@Controller
@RequestMapping("/profile/")
public class ProfileController {

	ModelAndView mv = new ModelAndView();

	@Autowired
	ProfileService profileService;

	@Autowired
	ManufacturesService manufacturesService;

	@RequestMapping("e_profile")
	public ModelAndView e_profile() {
		mv.clear();
		mv.setViewName("emp/e_profile");
		return mv;
	}

	@RequestMapping("changePW")
	public ModelAndView changePW(String id, String passwd, String passwdCheck, String tel1, String tel2, String tel3)
			throws Exception {
		mv.clear();
		ChangePwRequest cpReq = new ChangePwRequest();
		cpReq.setId(id);
		cpReq.setPasswd(passwd);
		cpReq.setPasswdCheck(passwdCheck);
		cpReq.setTel(tel1 + "-" + tel2 + "-" + tel3);
		if (!cpReq.isPasswordEqualToConfirm()) {
			mv.addObject("notEq", Boolean.TRUE);
			mv.setViewName("emp/e_profile");
		} else {
			int check = profileService.updatePw(id, passwd, cpReq.getTel());
			if (check == 1) {
				mv.addObject("success", Boolean.TRUE);
				mv.setViewName("emp/e_profile");
			} else {
				mv.addObject("fail", Boolean.TRUE);
				mv.setViewName("emp/e_profile");
			}
		}
		mv.setViewName("emp/e_profile");
		return mv;
	}

	@RequestMapping("works")
	public ModelAndView works(HttpServletRequest request, Manufactures manufactures, Sales sales) {
		mv.clear();
		HttpSession session = request.getSession();
		LoginUser user = (LoginUser) session.getAttribute("user");
		String pl = "";
		if (user.getPl_num().substring(0, 1).equals("s")) {
			pl = "sale";
		} else if (user.getPl_num().substring(0, 1).equals("p")) {
			pl = "mf";
		}
		String id = (String) session.getAttribute("LOGINED_ID");

		int maxSale = profileService.getMaxSale();
		int maxMf = profileService.getMaxMf();
		List works = profileService.getWorks(id, pl);

		List<String> products = null;
		products = manufacturesService.proNums();
		String fac = manufacturesService.facNum(id);
		String pl_num = manufacturesService.plNum(id);

		mv.addObject("products", products);
		mv.addObject("maxSale", maxSale);
		mv.addObject("maxMf", maxMf);
		mv.addObject("works", works);
		mv.addObject("pl", pl);
		mv.addObject("fac", fac);
		mv.addObject("pl_num", pl_num);
		mv.setViewName("emp/e_works");
		return mv;
	}

	@RequestMapping("insertWorks")
	public ModelAndView insertwork(HttpServletRequest request, String pl, Manufactures manufactures, Sales sales) {
		mv.clear();
		HttpSession session = request.getSession();
		LoginUser user = (LoginUser) session.getAttribute("user");
		String id = (String) session.getAttribute("LOGINED_ID");

		profileService.insertWork(id, pl, manufactures, sales);
		
		int maxSale = profileService.getMaxSale();
		int maxMf = profileService.getMaxMf();
		List works = profileService.getWorks(id, pl);

		List<String> products = null;
		products = manufacturesService.proNums();
		String fac = manufacturesService.facNum(id);
		String pl_num = manufacturesService.plNum(id);

		mv.addObject("products", products);
		mv.addObject("maxSale", maxSale);
		mv.addObject("maxMf", maxMf);
		mv.addObject("works", works);
		mv.addObject("pl", pl);
		mv.addObject("fac", fac);
		mv.addObject("pl_num", pl_num);
		mv.setViewName("emp/e_works");
		return mv;
	}

}
