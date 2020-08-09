package com.whiteowl.weplan.monthlyplan.dao;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.dao.DataAccessException;

import com.whiteowl.weplan.monthlyplan.vo.MonthlyPlanVO;

public interface MonthlyPlanDAO {

	public void addMonthlyPlan(MonthlyPlanVO monthlyPlanVO) throws DataAccessException;

	public void completeMonthlyPlan(Map<String, Object> map) throws DataAccessException;

	public void notCompleteMonthlyPlan(Map<String, Object> map) throws DataAccessException;

	public JSONObject getMonthlyPlan(Map<String, Object> map) throws DataAccessException;

	public void updateMonthlyPlan(MonthlyPlanVO monthlyPlanVO) throws DataAccessException;


}
