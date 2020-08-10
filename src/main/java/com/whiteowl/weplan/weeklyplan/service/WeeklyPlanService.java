package com.whiteowl.weplan.weeklyplan.service;

import java.util.Map;

import org.json.simple.JSONArray;

public interface WeeklyPlanService {

	public JSONArray getweeklyPlanListByMonth(Map<String, Object> map) throws Exception;

}
