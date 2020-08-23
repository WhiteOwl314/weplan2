package com.whiteowl.weplan.yearlyplan.dao;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.dao.DataAccessException;

import com.whiteowl.weplan.yearlyplan.vo.YearlyPlanVO;

public interface YearlyPlanDAO {

	public List yearlyPlanList(Map<String, Object> map) throws DataAccessException;

	public void addYearlyPlan(YearlyPlanVO yearlyPlanVO) throws DataAccessException;

	public JSONObject popUpYearlyPlanView(Map<String, Object> map) throws DataAccessException;

	public void updateYearlyPlan(YearlyPlanVO yearlyPlanVO) throws DataAccessException;

	public void deleteYearlyPlan(Map<String, Object> map) throws DataAccessException;

	public void completeYearlyPlan(Map<String, Object> map) throws DataAccessException;

	public JSONArray monthlyPlanList(Map<String, Object> map) throws DataAccessException;

	public JSONArray getMonthList(Map<String, Object> map) throws DataAccessException;

	public void addYearlyPlanNullGoalId(YearlyPlanVO yearlyPlanVO) throws DataAccessException;

	public void updateYearlyPlanWithGoalId(YearlyPlanVO yearlyPlanVO) throws DataAccessException;

	public JSONArray getYearlyPlanAllList(Map<String, Object> map) throws DataAccessException;  

}
