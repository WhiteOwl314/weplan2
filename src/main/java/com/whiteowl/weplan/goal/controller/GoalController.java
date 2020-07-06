package com.whiteowl.weplan.goal.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public interface GoalController {

	public ModelAndView goalList(HttpServletRequest request, HttpServletResponse response) throws Exception;

}
