package com.whiteowl.weplan.task.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

import com.whiteowl.weplan.task.vo.TaskVO;

public interface TaskController {

	ModelAndView listInboxTasks(
			HttpServletRequest request, 
			HttpServletResponse response
	) throws Exception;

	ResponseEntity addInboxTask(HttpServletRequest request, HttpServletResponse response) throws Exception;

	ModelAndView viewTask(int taskNO, HttpServletRequest request, HttpServletResponse response) throws Exception;

	public ModelAndView weeklyView(HttpServletRequest request, HttpServletResponse response) throws Exception;

	public String getTaskListByMonthAndWeek(HttpServletRequest request) throws Exception;

	String popUpGetTask(int taskId, HttpServletRequest request) throws Exception;

}
