package com.whiteowl.weplan.monthlyplan.service;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.dao.DataAccessException;

import com.whiteowl.weplan.monthlyplan.vo.MonthlyPlanVO;

public interface MonthlyPlanService {

	void addMonthlyPlan(MonthlyPlanVO monthlyPlanVO) throws Exception;

	public void completeMonthlyPlan(Map<String, Object> map) throws Exception;

	public void notCompleteMonthlyPlan(Map<String, Object> map) throws Exception;

	public JSONObject getMonthlyPlan(Map<String, Object> map) throws Exception;

	public void updateMonthlyPlan(MonthlyPlanVO monthlyPlanVO) throws Exception;

	public List yearlyView(Map<String, Object> map) throws Exception;

	public JSONArray getMonthlyPlanListByYear(Map<String, Object> map) throws Exception;

	public void addMonthlyPlanNullYearlyId(MonthlyPlanVO monthlyPlanVO) throws Exception;

	void moveMonth(MonthlyPlanVO monthlyPlanVO) throws Exception;

	public JSONArray getMonthlyPlanListByMonth(Map<String, Object> map) throws Exception;

	void updateMonthlyPlanWithYearlyPlanId(MonthlyPlanVO monthlyPlanVO) throws Exception; 


}
