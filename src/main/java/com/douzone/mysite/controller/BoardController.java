package com.douzone.mysite.controller;

import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.security.AuthUser;
import com.douzone.mysite.security.Auth.Role;
import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.service.MessageService;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.MessageVo;
import com.douzone.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@Autowired
	private MessageService messageService;
	
	@RequestMapping({"","/list"})
	public ModelAndView list(
			@RequestParam(value="search", required=false) String search, 
			@RequestParam(value="page", required=false) String page) {
		if(page == null) page = "1";
		if(search == null) search = "";
		
		ModelAndView mav = new ModelAndView();
		
		Map<String, Object> resultMap = boardService.list(search ,Integer.parseInt(page));
		mav.addAllObjects(resultMap);
		mav.setViewName("board/list");
		
		return mav;
	}
	
	@GetMapping(value="/view")
	public ModelAndView view(@RequestParam(value="no", required=false) String no) {
				
		BoardVo boardVo = boardService.view(Integer.parseInt(no));
		List<MessageVo> result = messageService.getList(Integer.parseInt(no));
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo",boardVo);
		mav.addObject("m_list",result);
		mav.setViewName("board/view");
		
		return mav;
	}
	
	@Auth(Role.USER)
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String delete(@RequestParam(value="no", required=false) String no) {
		
		boardService.delete(Integer.parseInt(no));
		
		return "redirect:/board";
	}
	
	@Auth(Role.USER)
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String write(@AuthUser UserVo loginuser) {
				
		return "board/write";
	}
	
	@Auth(Role.USER)
	@Transactional
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(HttpSession session, @ModelAttribute BoardVo boardVo,
			@RequestParam(value="reply", required=false) String flag,
			@RequestParam(value="no", required=false, defaultValue = "") String no) {
		UserVo loginUser = (UserVo)session.getAttribute("loginuser");
		boardVo.setUser_no(loginUser.getNo());
		System.out.println(loginUser.getNo());
		
		if(flag == null) {
			if(boardVo.getTitle().equals("")) {
				return "redirect:/board";
			}
			boardService.insert(boardVo);
		} else {
			if(boardVo.getTitle().equals("")) {
				return "redirect:/board";
			}
			boardService.replyInsert(boardVo, Integer.parseInt(no));
		}
				
		return "redirect:/board";
	}
	
	@Auth(Role.USER)
	@GetMapping(value="/modify")
	public ModelAndView modfiy(@RequestParam(value="no", required=false) String no, HttpSession session) {
		UserVo loginuser = (UserVo)session.getAttribute("loginuser");
		ModelAndView mav = new ModelAndView();
		
		BoardVo vo = boardService.view(Integer.parseInt(no));
		
		if(loginuser.getNo() != vo.getUser_no())
		{
			mav.setViewName("redirect:/board");
			return mav;
		}
		
		vo.setNo(Integer.parseInt(no));
		mav.addObject("modifyboard",vo);		
		mav.setViewName("board/modify");
		
		return mav;
	}
	
	@Auth(Role.USER)
	@PostMapping(value="/modify")
	public String modfiy(@ModelAttribute BoardVo boardVo) {
		System.out.println(boardVo);
		boardService.update(boardVo);
						
		return "redirect:/board";
	}
	
	@Auth(Role.USER)
	@GetMapping(value="/reply")
	public ModelAndView reply(@RequestParam(value="no", required=false) String no, HttpSession session) {
		
		ModelAndView mav = new ModelAndView();
		
		BoardVo vo = boardService.reply(Integer.parseInt(no));
		
		mav.addObject("reply",vo);
		mav.setViewName("board/write");
		
		return mav;
	}
	
	@Auth(Role.USER)
	@PostMapping(value="/message")
	public ModelAndView message(@ModelAttribute MessageVo messageVo, HttpSession session) {
		UserVo loginuser = (UserVo)session.getAttribute("loginuser");
		ModelAndView mav = new ModelAndView();
		messageVo.setUser_no(loginuser.getNo());
		messageVo.setName(loginuser.getName());
		
		messageService.message(messageVo);
		
		mav.addObject("no",messageVo.getBoard_no());
		mav.setViewName("redirect:/board/view");
		
		return mav;
	}
	
	@Auth(Role.USER)
	@PostMapping(value="/deletemessage")
	public ModelAndView deleteMessage(@RequestParam(value="message_no", required=false) String message_no,
			@RequestParam(value="board_no", required=false) String board_no) {
		
		ModelAndView mav = new ModelAndView();
		
		messageService.delete(Integer.parseInt(message_no));
		
		mav.addObject("no",Integer.parseInt(board_no));
		mav.setViewName("redirect:/board/view");

		return mav;
	}
	
	@PostMapping(value="/search")
	public ModelAndView search(@RequestParam(value="search", required=false) String search) {
		
		ModelAndView mav = new ModelAndView();
				
		mav.addObject("search",search);
		mav.setViewName("redirect:/board/list");

		return mav;
	}
}
