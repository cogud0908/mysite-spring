package com.douzone.mysite.exception;


import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.douzone.mysite.dto.JSONResult;
import com.fasterxml.jackson.databind.ObjectMapper;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(UserDaoException.class)
	public void handlerExcpetion(HttpServletRequest request, HttpServletResponse response ,Exception e) throws ServletException, IOException {
		// 1. 로깅작업
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		//LOG.error(errors.toString());
				
		// 2. 시스템 오류 안내 페이지 전환
//		ModelAndView mav = new ModelAndView();
//		
//		mav.addObject("uri", request.getRequestURI());
//		mav.addObject("exception",errors.toString());
//		mav.setViewName("error/exception");
//		return mav;
		
		String accept = request.getHeader("accept");
		if( accept.matches(".*application/json.*") ) { // JSON 응답
			response.setStatus(HttpServletResponse.SC_OK);
			
			OutputStream out = response.getOutputStream();
						
			out.write(new ObjectMapper().writeValueAsString(JSONResult.fail(errors.toString())).getBytes("UTF-8"));
			out.flush();
			out.close();
			
		} else { // HTML 응답
			request.setAttribute("uri", request.getRequestURI());
			request.setAttribute("exception", errors.toString());
			request.getRequestDispatcher("/WEB-INF/vies/error/exception.jsp").forward(request, response);
		}
		
	}
}
