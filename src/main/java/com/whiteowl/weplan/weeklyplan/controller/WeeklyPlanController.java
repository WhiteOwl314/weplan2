package com.whiteowl.weplan.weeklyplan.controller;

import javax.servlet.http.HttpServletRequest;

public interface WeeklyPlanController {

	public String getweeklyPlanListByMonth(int yearlyPlanId, String month, HttpServletRequest request) throws Exception;


}
