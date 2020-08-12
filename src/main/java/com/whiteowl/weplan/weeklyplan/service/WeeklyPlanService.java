package com.whiteowl.weplan.weeklyplan.service;

import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.whiteowl.weplan.weeklyplan.vo.WeeklyPlanVO;

public interface WeeklyPlanService {

	public JSONArray getweeklyPlanListByMonth(Map<String, Object> map) throws Exception;

	public void addWeeklyPlan(WeeklyPlanVO weeklyPlanVO) throws Exception;

	public JSONObject popUpWeeklyPlanView(Map<String, Object> map) throws Exception;

	public void updateWeeklyPlan(WeeklyPlanVO weeklyPlanVO) throws Exception;

}
