package com.whiteowl.weplan.monthlyplan.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.whiteowl.weplan.monthlyplan.vo.MonthlyPlanVO;

public interface MonthlyPlanDAO {

	public void addMonthlyPlan(MonthlyPlanVO monthlyPlanVO) throws DataAccessException;


}
