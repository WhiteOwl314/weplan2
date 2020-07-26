package com.whiteowl.weplan.monthlyplan.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

public interface MonthlyPlanService {

	public List monthlyPlanList(Map<String, Object> map) throws Exception;

}
