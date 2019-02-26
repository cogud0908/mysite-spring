package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.security.Auth.Role;
import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.SiteVo;


@RequestMapping("/admin")
@Controller
public class AdminController {

	@Autowired
	private SiteService siteService;

	@Auth(Role.ADMIN)
	@RequestMapping({"","/main"})
	public String main(Model model) {
		SiteVo vo = siteService.get();
		model.addAttribute("siteVo",vo);
		return "admin/main";
	}

	@Auth(Role.ADMIN)
	@RequestMapping("/board")
	public String board() {
		return "admin/board";
	}

	@Auth(Role.ADMIN)
	@RequestMapping("/guestbook")
	public String guestbook() {
		return "admin/guestbook";
	}
	
	@Auth(Role.ADMIN)
	@RequestMapping("/user")
	public String user() {
		return "admin/user";
	}
	
	@Auth(Role.ADMIN)
	@PostMapping("/main/update")
	public String update(@ModelAttribute SiteVo siteVo, Model model) {
		siteService.update(siteVo);
		SiteVo vo = siteService.get();
		
		model.addAttribute("siteVo",vo);
		
		return "redirect:/admin/main";
	}
}
