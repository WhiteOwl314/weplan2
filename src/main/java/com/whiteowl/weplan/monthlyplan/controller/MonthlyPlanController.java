package com.whiteowl.weplan.monthlyplan.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public interface MonthlyPlanController {

	public ModelAndView monthlyPlanList(int month, HttpServletRequest request, HttpServletResponse response) throws Exception;

}
