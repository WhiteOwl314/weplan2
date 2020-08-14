package com.whiteowl.weplan.weeklyplan.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

public interface WeeklyPlanController {

	public String getweeklyPlanListByMonth(int yearlyPlanId, String month, HttpServletRequest request) throws Exception;

	public ResponseEntity addWeeklyPlan(HttpServletRequest request, HttpServletResponse response) throws Exception;

	public String popUpWeeklyPlanView(int weekly_plan_id, HttpServletRequest request) throws Exception;

	public ResponseEntity updateWeeklyPlan(HttpServletRequest request, HttpServletResponse response) throws Exception;

	public ResponseEntity completeWeeklyPlan(int weeklyPlanId, HttpServletRequest request, HttpServletResponse response)
			throws Exception;

	public ResponseEntity notCompleteWeeklyPlan(int weeklyPlanId, HttpServletRequest request, HttpServletResponse response)
			throws Exception;

	public ModelAndView monthlyView(String month, HttpServletRequest request, HttpServletResponse response) throws Exception;

	public String getWeeklyPlanListByOnlyMonth(HttpServletRequest request) throws Exception;

	public String moveWeek(HttpServletRequest request, HttpServletResponse response) throws Exception;


}
