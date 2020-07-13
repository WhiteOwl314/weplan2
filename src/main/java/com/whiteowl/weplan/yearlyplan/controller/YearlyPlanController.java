package com.whiteowl.weplan.yearlyplan.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

public interface YearlyPlanController {

	public ModelAndView yearlyPlanList(int year, HttpServletRequest request, HttpServletResponse response) throws Exception;

	public ResponseEntity addYearlyPlan(HttpServletRequest request, HttpServletResponse response) throws Exception;

	public String popUpYearlyPlanView(int yearlyPlan_id) throws Exception;

	public ResponseEntity updateYearlyPlan(HttpServletRequest request, HttpServletResponse response) throws Exception;

	public ResponseEntity deleteYearlyPlan(int yearlyPlan_id, HttpServletRequest request, HttpServletResponse response)
			throws Exception;

}
