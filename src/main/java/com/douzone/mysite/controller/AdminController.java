package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.security.Auth.Role;
import com.douzone.mysite.service.FileuploadService;
import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.SiteVo;


@RequestMapping("/admin")
@Controller
public class AdminController {

	@Autowired
	private FileuploadService fileuploadService;
	
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
	public String update(@ModelAttribute SiteVo siteVo, Model model,
			@RequestParam(value="upload-profile") MultipartFile multipartFile) {
//		siteService.update(siteVo);
//		SiteVo vo = siteService.get();
//		
//		model.addAttribute("siteVo",vo);
		
		String profile = fileuploadService.restore(multipartFile);
		siteVo.setProfile(profile);
		
		siteService.update(siteVo);
		
		return "redirect:/admin/main";
	}
	
	
}
