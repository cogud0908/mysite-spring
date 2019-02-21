package com.douzone.mysite.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.douzone.mysite.exception.UserDaoException;
import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join() {
		return "/user/join";
	}

	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(@ModelAttribute UserVo userVo) {
		userService.join(userVo);
		return "redirect:/user/joinsuccess";
	}

	@RequestMapping("/joinsuccess")
	public String joinSuccess() {
		return "/user/joinsuccess";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST) 
	public ModelAndView login(HttpSession session, @ModelAttribute UserVo userVo) {
		UserVo loginUser = userService.login(userVo);
		
		ModelAndView mav = new ModelAndView();
		if(loginUser == null)
		{
			mav.addObject("result","fail");
			mav.setViewName("/user/login");
		}
		else {
			session.setAttribute("loginuser", loginUser);
			mav.setViewName("redirect:/");
		}
		return mav;
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET) 
	public String login() {
		return "/user/login";
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		if(session != null && session.getAttribute("loginuser") != null)
		{
			// logout 처리
			session.removeAttribute("loginuser");
			session.invalidate();
		}
		return "redirect:/";
	}
	
	@GetMapping(value="/modify")
	public String modify(HttpSession session) {
		UserVo loginUser = (UserVo)session.getAttribute("loginuser");

		if(loginUser == null) {
			return "redirect:/";
		}
		
		return "/user/modify";
	}
	
	@PostMapping(value="/modify")
	public String modify(HttpSession session, @ModelAttribute UserVo userVo) {
		UserVo loginUser = (UserVo)session.getAttribute("loginuser");

		if(loginUser == null) {
			return "redirect:/";
		}
		userService.modify(userVo);
		session.removeAttribute("loginuser");
		session.invalidate();
		
		return "/user/modifysuccess";
	}
	
	@GetMapping("/modifysuccess")
	public String modifySuccess() {
		return "/user/modifysuccess";
	}
}