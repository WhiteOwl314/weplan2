package com.whiteowl.weplan.monthlyplan.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

public interface MonthlyPlanController {

	public ResponseEntity addMonthlyPlan(HttpServletRequest request, HttpServletResponse response) throws Exception;

	public ResponseEntity completeMonthlyPlan(int monthlyPlanId, HttpServletRequest request, HttpServletResponse response)
			throws Exception;

	public ResponseEntity notCompleteMonthlyPlan(int monthlyPlanId, HttpServletRequest request, HttpServletResponse response)
			throws Exception;

	public String getMonthlyPlan(int monthlyPlanId, HttpServletRequest request) throws Exception;

	public ResponseEntity updateMonthlyPlan(HttpServletRequest request, HttpServletResponse response) throws Exception;

	public ModelAndView yearlyView(int year, HttpServletRequest request, HttpServletResponse response) throws Exception;

	public String getMonthlyPlanListByYear(String year, HttpServletRequest request) throws Exception;

	public String moveMonth(int id, String month, String member_id, HttpServletRequest request,
			HttpServletResponse response) throws Exception;

	public String getMonthlyPlanListByMonth(String month, HttpServletRequest request) throws Exception;



}
