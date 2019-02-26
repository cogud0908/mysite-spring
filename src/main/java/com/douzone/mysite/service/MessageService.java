package com.douzone.mysite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.MessageDao;
import com.douzone.mysite.vo.MessageVo;

@Service
public class MessageService {

	@Autowired
	private MessageDao messageDao;

	public void message(MessageVo messageVo) {
		System.out.println(messageVo);
		messageDao.Insert(messageVo);
	}

	public List<MessageVo> getList(int no) {
		List<MessageVo> list = messageDao.getList(no);
				
		return list;
	}

	public void delete(int no) {
		messageDao.delete(no);
	}
	
	
}
