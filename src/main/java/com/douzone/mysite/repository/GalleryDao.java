package com.douzone.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.GalleryVo;

@Repository
public class GalleryDao {

	@Autowired
	private SqlSession sqlSession;

	public void insert(GalleryVo galleryVo) {
		sqlSession.insert("gallery.insert",galleryVo);
	}

	public List<GalleryVo> getList() {
		List<GalleryVo> list = sqlSession.selectList("gallery.getlist");
		return list;
	}

	public void delete(int no) {
		sqlSession.delete("gallery.delete",no);
	}
	
	
}
