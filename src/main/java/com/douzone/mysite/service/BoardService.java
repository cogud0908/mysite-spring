package com.douzone.mysite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.BoardDao;
import com.douzone.mysite.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;
	
	public Map<String, Object> list(String search, int page) {
				
		int totalcount = boardDao.getCount(search);
		int totalpage;
		int pagecount;
		
		if(totalcount % 10 == 0)
		{
			totalpage = (totalcount / 10);
			pagecount = 10;
		}
		else
		{
			totalpage = (totalcount / 10) + 1;
			pagecount = totalcount % 10;
		}
		
		int start_page = (page / 6)*5 + 1;
		int end_page = start_page + 4;
		
		if(totalpage < end_page)
			end_page = totalpage;
		
		int[] pageList = {page, start_page, end_page, totalpage, pagecount};
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("search", search);
		map.put("page", (page-1)*10);
		
		List<BoardVo> list = boardDao.getList(map);
		
		map.clear();
		map.put("list", list);
		map.put("pagelist",pageList);
		map.put("search", search);
		
		return map;
	}

	public void insert(BoardVo boardVo) {
		boardDao.insert(boardVo);
	}
	
	public void replyInsert(BoardVo boardVo, int no) {		
		boardDao.ReplyInsert(boardVo, no);
	}

	public void delete(int no) {
		boardDao.delete(no);
	}

	public BoardVo view(int no) {
		BoardVo vo = boardDao.getBoard(no);
		return vo;
	}

	public void update(BoardVo boardVo) {
		boardDao.update(boardVo);
	}

	public BoardVo reply(int no) {
		BoardVo vo = boardDao.getReply(no);
		return vo;
	}
}
