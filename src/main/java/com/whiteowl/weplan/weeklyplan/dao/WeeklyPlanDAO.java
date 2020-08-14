package com.whiteowl.weplan.weeklyplan.dao;

import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.dao.DataAccessException;

import com.whiteowl.weplan.weeklyplan.vo.WeeklyPlanVO;

public interface WeeklyPlanDAO {

	public JSONArray getweeklyPlanListByMonth(Map<String, Object> map) throws DataAccessException;

	public void addWeeklyPlan(WeeklyPlanVO weeklyPlanVO) throws DataAccessException;

	public JSONObject popUpWeeklyPlanView(Map<String, Object> map)throws DataAccessException;

	public void updateWeeklyPlan(WeeklyPlanVO weeklyPlanVO) throws DataAccessException;

	public void completeWeeklyPlan(Map<String, Object> map) throws DataAccessException;

	public void notCompleteWeeklyPlan(Map<String, Object> map) throws DataAccessException;

	public JSONArray getWeeklyPlanListByOnlyMonth(Map<String, Object> map) throws DataAccessException;

	public void moveWeek(WeeklyPlanVO weeklyPlanVO) throws DataAccessException;

	public void addWeeklyPlanNullYearlyPlanId(WeeklyPlanVO weeklyPlanVO) throws DataAccessException; 

}
