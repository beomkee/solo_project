package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

}
