package com.douzone.mysite.controller;

import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
	public ModelAndView list(@RequestParam(value="search", required=false) String search, @RequestParam(value="page", required=false) String page) {
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
	
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String delete(@RequestParam(value="no", required=false) String no) {
		
		boardService.delete(Integer.parseInt(no));
		
		return "redirect:/board";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String write(HttpSession session) {
		UserVo loginUser = (UserVo)session.getAttribute("loginuser");
		if(loginUser == null) {
			return "redirect:/board";
		}
		
		return "board/write";
	}
	
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
	
	@GetMapping(value="/modify")
	public ModelAndView modfiy(@RequestParam(value="no", required=false) String no, HttpSession session) {
		UserVo loginUser = (UserVo)session.getAttribute("loginuser");
		ModelAndView mav = new ModelAndView();
		if(loginUser == null) {
			mav.setViewName("redirect:/board");
			return mav;
		}
		
		BoardVo vo = boardService.view(Integer.parseInt(no));
		
		vo.setNo(Integer.parseInt(no));
		mav.addObject("modifyboard",vo);		
		mav.setViewName("board/modify");
		
		return mav;
	}
	
	@PostMapping(value="/modify")
	public String modfiy(@ModelAttribute BoardVo boardVo) {
		
		boardService.update(boardVo);
						
		return "redirect:/board";
	}
	
	@GetMapping(value="/reply")
	public ModelAndView reply(@RequestParam(value="no", required=false) String no, HttpSession session) {
		
		UserVo loginUser = (UserVo)session.getAttribute("loginuser");
		ModelAndView mav = new ModelAndView();
		if(loginUser == null) {
			mav.setViewName("redirect:/board");
			return mav;
		}
		BoardVo vo = boardService.reply(Integer.parseInt(no));
		
		mav.addObject("reply",vo);
		mav.setViewName("board/write");
		
		return mav;
	}
	
	@PostMapping(value="/message")
	public ModelAndView message(@ModelAttribute MessageVo messageVo, HttpSession session) {
		UserVo loginUser = (UserVo)session.getAttribute("loginuser");
		ModelAndView mav = new ModelAndView();
				
		if(loginUser == null) {
			mav.setViewName("redirect:/board");
			return mav;
		}
		
		messageVo.setUser_no(loginUser.getNo());
		messageVo.setName(loginUser.getName());
				
		messageService.message(messageVo);
				
		mav.addObject("no",messageVo.getBoard_no());
		mav.setViewName("redirect:/board/view");
		
		return mav;
	}
	
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
