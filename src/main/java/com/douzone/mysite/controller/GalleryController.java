package com.douzone.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.vo.GalleryVo;
import com.douzone.mysite.security.Auth;
import com.douzone.mysite.security.Auth.Role;
import com.douzone.mysite.service.FileuploadService;
import com.douzone.mysite.service.GalleryService;

@Controller
@RequestMapping("/gallery")
public class GalleryController {

	@Autowired
	private GalleryService galleryService;

	@Autowired
	private FileuploadService fileuploadService;
	
	@RequestMapping("")
	public String index(Model model) {
		List<GalleryVo> list = galleryService.getList();
		model.addAttribute("list", list);
		return "gallery/index";
	}

	@Auth(Role.ADMIN)
	@RequestMapping("/upload")
	public String upload(
		@ModelAttribute GalleryVo galleryVo,
		@RequestParam(value="image-url") MultipartFile multipartFile) {
		
		System.out.println(multipartFile);
		
		String profile = fileuploadService.restore(multipartFile);
		galleryVo.setImage_url(profile);
		
		galleryService.insert(galleryVo);
		
		return "redirect:/gallery";
	}
	
	@Auth(Role.ADMIN)
	@RequestMapping("/delete")
	public String delete(@RequestParam(value="no",required=true) String no) {
		
		galleryService.delete(Integer.parseInt(no));
		
		return "redirect:/gallery";
	}

}
