package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.GalleryDao;
import com.douzone.mysite.vo.GalleryVo;

@Service
public class GalleryService {

	@Autowired
	private GalleryDao galleryDao;
	
	public void insert(GalleryVo galleryVo) {
		galleryDao.insert(galleryVo);
	}

	public List<GalleryVo> getList() {
		List<GalleryVo> list = galleryDao.getList();
		return list;
	}

	public void delete(int no) {
		galleryDao.delete(no);
	}
	

}
