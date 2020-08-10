package com.whiteowl.weplan.weeklyplan.dao;

import java.util.Map;

import org.json.simple.JSONArray;
import org.springframework.dao.DataAccessException;

public interface WeeklyPlanDAO {

	public JSONArray getweeklyPlanListByMonth(Map<String, Object> map) throws DataAccessException;

}
