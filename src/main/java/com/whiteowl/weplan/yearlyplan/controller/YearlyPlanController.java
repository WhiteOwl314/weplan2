package com.whiteowl.weplan.yearlyplan.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public interface YearlyPlanController {

	public ModelAndView yearlyPlanList(int year, HttpServletRequest request, HttpServletResponse response) throws Exception;

}
