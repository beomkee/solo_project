package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import model.LoginUser;
import service.MainService;

@Controller
@RequestMapping("/")
public class MainController {

	ModelAndView mv = new ModelAndView();

	@Autowired
	MainService mainservice;

	@RequestMapping("main")
	public ModelAndView main(String div) throws Exception {
		mv.clear();
		if (div.equals("1")) {
			mv.setViewName("main_e");
		}else {
			mv.setViewName("main_m");
		}
		return mv;
	}
	
	@RequestMapping("login")
	public ModelAndView login() throws Exception {
		mv.clear();
		mv.setViewName("../login/login");
		return mv;
	}

	@RequestMapping("loginPro")
	public ModelAndView loginPro(LoginUser loginUser, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		mv.clear();
		int check = mainservice.userCheck(loginUser);
		mv.addObject("check", check);
		if (check == 1) {
			LoginUser user = new LoginUser();
			user = mainservice.getUser(loginUser);
			String division = "";
			String loginId = user.getId();
			if (loginId.substring(0, 1).equals("1")) {
				division = "1";
				mv.setViewName("main_e");
			} else {
				division = "2";
				mv.setViewName("main_m");
			}
			session.setAttribute("user", user);
			session.setAttribute("LOGINED_ID", loginId);
			session.setAttribute("division", division);
		} else {
			mv.setViewName("../login/login");
		}
		return mv;
	}

	@RequestMapping("leftMenu")
	@ResponseBody
	public List leftMenu(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		String div = (String) session.getAttribute("division");
		System.out.println("div: "+div);
		List menuList = mainservice.getLeftMenus(div);
		return menuList;
	}

}
