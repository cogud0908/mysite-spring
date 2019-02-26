package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.repository.GuestbookDao;
import com.douzone.mysite.service.GuestbookService;
import com.douzone.mysite.vo.GuestbookVo;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	@RequestMapping({"","/list"})
	public String list(Model model) {
		
		model.addAttribute("list", guestbookService.getList());
				
		return "/guestbook/list";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String add(GuestbookVo vo) {
		guestbookService.insert(vo);
		
		return "redirect:/guestbook";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String deleteform(@RequestParam(value="no", required=false) Long no, Model model) {
		model.addAttribute("no", no);
		return "/guestbook/delete";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(GuestbookVo vo) {
		guestbookService.delete(vo);
		
		return "redirect:/guestbook";
	}
	
	@GetMapping(value="/ajax")
	public String ajaxList() {
		return "/guestbook/index-ajax";
	}
}
