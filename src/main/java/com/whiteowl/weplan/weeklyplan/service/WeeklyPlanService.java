package com.whiteowl.weplan.weeklyplan.service;

import java.util.Map;

import org.json.simple.JSONArray;

import com.whiteowl.weplan.weeklyplan.vo.WeeklyPlanVO;

public interface WeeklyPlanService {

	public JSONArray getweeklyPlanListByMonth(Map<String, Object> map) throws Exception;

	public void addWeeklyPlan(WeeklyPlanVO weeklyPlanVO) throws Exception;

}
