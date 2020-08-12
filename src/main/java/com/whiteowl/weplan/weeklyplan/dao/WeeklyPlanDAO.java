package com.whiteowl.weplan.weeklyplan.dao;

import java.util.Map;

import org.json.simple.JSONArray;
import org.springframework.dao.DataAccessException;

import com.whiteowl.weplan.weeklyplan.vo.WeeklyPlanVO;

public interface WeeklyPlanDAO {

	public JSONArray getweeklyPlanListByMonth(Map<String, Object> map) throws DataAccessException;

	public void addWeeklyPlan(WeeklyPlanVO weeklyPlanVO) throws DataAccessException;

}
