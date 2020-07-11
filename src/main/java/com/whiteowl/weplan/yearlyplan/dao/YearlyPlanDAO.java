package com.whiteowl.weplan.yearlyplan.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

public interface YearlyPlanDAO {

	public List yearlyPlanList(Map<String, Object> map) throws DataAccessException;

}
