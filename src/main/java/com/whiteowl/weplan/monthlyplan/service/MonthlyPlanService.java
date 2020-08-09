package com.whiteowl.weplan.monthlyplan.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.whiteowl.weplan.monthlyplan.vo.MonthlyPlanVO;

public interface MonthlyPlanService {

	void addMonthlyPlan(MonthlyPlanVO monthlyPlanVO) throws Exception;

	public void completeMonthlyPlan(Map<String, Object> map) throws Exception;

	public void notCompleteMonthlyPlan(Map<String, Object> map) throws Exception;


}
