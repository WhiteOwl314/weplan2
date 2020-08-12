package com.whiteowl.weplan.weeklyplan.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

public interface WeeklyPlanController {

	public String getweeklyPlanListByMonth(int yearlyPlanId, String month, HttpServletRequest request) throws Exception;

	public ResponseEntity addWeeklyPlan(HttpServletRequest request, HttpServletResponse response) throws Exception;

	public String popUpWeeklyPlanView(int weekly_plan_id, HttpServletRequest request) throws Exception;

	public ResponseEntity updateWeeklyPlan(HttpServletRequest request, HttpServletResponse response) throws Exception;


}
