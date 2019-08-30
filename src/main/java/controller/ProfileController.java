package controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import model.ChangePwRequest;
import model.LoginUser;
import service.ChangePwService;
import service.ProfileService;

@Controller
@RequestMapping("/profile/")
public class ProfileController {

	ModelAndView mv = new ModelAndView();

	@Autowired
	ProfileService profileService;

	@RequestMapping("e_profile")
	public ModelAndView e_profile() {
		mv.clear();
		mv.setViewName("emp/e_profile");
		return mv;
	}

	@RequestMapping("changePW")
	public ModelAndView changePW(String id, String passwd, String passwdCheck, String tel1, String tel2, String tel3) throws Exception {
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
			if (check ==1) {
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

}
