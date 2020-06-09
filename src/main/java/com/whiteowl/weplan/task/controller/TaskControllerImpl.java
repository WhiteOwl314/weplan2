package com.whiteowl.weplan.task.controller;


import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.whiteowl.weplan.member.service.MemberService;
import com.whiteowl.weplan.task.service.TaskService;
import com.whiteowl.weplan.task.vo.TaskVO;


@Controller("taskController")
public class TaskControllerImpl implements TaskController{
	
	@Autowired
	private TaskService taskService;
	@Autowired
	TaskVO taskVO;
	
	@Override
	@RequestMapping(value="/task/listInboxTasks.do",
						method = RequestMethod.GET)
	public ModelAndView listInboxTasks(
			HttpServletRequest request,
			HttpServletResponse response
	) throws Exception{
		String viewName = (String)request.getAttribute("viewName");
		List inboxTasksList = taskService.listInboxTasks();
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("inboxTasksList", inboxTasksList);
		return mav;
	}
	
	@Override
	@RequestMapping(value="/task/addInboxTask.do",
						method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity addInboxTask(
			HttpServletRequest request,
			HttpServletResponse response
	) throws Exception{
		request.setCharacterEncoding("utf-8");
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int importance = Integer.parseInt(request.getParameter("importance"));
		String limitDate = request.getParameter("date") + " " + /*request.getParameter("time")*/ "12:00" +":00";
		
		taskVO.setTitle(title);
		taskVO.setContent(content);
		taskVO.setImportance(importance);
		taskVO.setLimitDate(limitDate);
		
		
		String referer = request.getHeader("Referer");
		
		String message;
		ResponseEntity resEnt=null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			int taskNO = taskService.addInboxTask(taskVO);
			message = "<script>";
			message += " alert('할일이 추가되었습니다.');";
			message += " location.href='"+ referer +"'; ";
			message +=" </script>";
		    resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			message = " <script>";
			message += " alert('실패했습니다.');";
			message += " location.href='"+ referer +"'; ";
			message +=" </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt ;
	}
	

}
