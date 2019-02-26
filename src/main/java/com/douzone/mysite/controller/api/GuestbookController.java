package com.douzone.mysite.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.mysite.dto.JSONResult;
import com.douzone.mysite.repository.GuestbookDao;
import com.douzone.mysite.service.GuestbookService;
import com.douzone.mysite.vo.GuestbookVo;

@Controller("guestbookApicontroller")
@RequestMapping("/api/guestbook")
public class GuestbookController {

	@Autowired
	private GuestbookService GuestbookService;
	
	@ResponseBody
	@RequestMapping("/ajax")
	public JSONResult ajaxList(@RequestParam(value="p", required = true) String page) {
		List<GuestbookVo> list = GuestbookService.ajaxList(Integer.parseInt(page));
								
		return JSONResult.success(list);
	}
	
	@ResponseBody
	@RequestMapping("/ajax/insert")
	public JSONResult ajaxInsert(@ModelAttribute GuestbookVo guestbookVo) {
		GuestbookVo vo = GuestbookService.ajaxInsert(guestbookVo);
		
		return JSONResult.success(vo);
	}
	
	@ResponseBody
	@RequestMapping("/ajax/delete")
	public JSONResult ajaxDelete(@ModelAttribute GuestbookVo guestbookVo) {
		
		GuestbookService.delete(guestbookVo);
		
		return JSONResult.success(true);
	}
}
