package com.whiteowl.weplan.monthlyplan.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

public interface MonthlyPlanController {

	public ResponseEntity addMonthlyPlan(HttpServletRequest request, HttpServletResponse response) throws Exception;


}
