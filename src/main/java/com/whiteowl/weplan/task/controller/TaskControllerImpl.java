package com.whiteowl.weplan.task.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
	

}
