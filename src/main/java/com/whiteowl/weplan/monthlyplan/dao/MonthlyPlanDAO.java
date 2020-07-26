package com.whiteowl.weplan.monthlyplan.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

public interface MonthlyPlanDAO {

	public List monthlyPlanList(Map<String, Object> map) throws DataAccessException;

}
