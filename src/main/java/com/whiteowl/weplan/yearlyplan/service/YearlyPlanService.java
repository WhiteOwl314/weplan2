package com.whiteowl.weplan.yearlyplan.service;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.whiteowl.weplan.yearlyplan.vo.YearlyPlanVO;

public interface YearlyPlanService {

	public List yearlyPlanList(Map<String, Object> map) throws Exception;

	public void addYearlyPlan(YearlyPlanVO yearlyPlanVO)throws Exception;

	public void updateYearlyPlan(YearlyPlanVO yearlyPlanVO) throws Exception;

	public void deleteYearlyPlan(Map<String, Object> map) throws Exception;

	public void completeYearlyPlan(Map<String, Object> map) throws Exception;

	public JSONArray monthlyPlanList(Map<String, Object> map) throws Exception;

	public JSONArray getMonthList(Map<String, Object> map) throws Exception;

	public void addYearlyPlanNullGoalId(YearlyPlanVO yearlyPlanVO) throws Exception;

	public void updateYearlyPlanWithGoalId(YearlyPlanVO yearlyPlanVO) throws Exception;

	JSONObject popUpYearlyPlanView(Map<String, Object> map) throws Exception;

	public JSONArray getYearlyPlanAllList(Map<String, Object> map) throws Exception;

}
