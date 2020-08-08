package com.whiteowl.weplan.monthlyplan.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.whiteowl.weplan.monthlyplan.dao.MonthlyPlanDAO;
import com.whiteowl.weplan.monthlyplan.vo.MonthlyPlanVO;

@Repository("monthlyPlanService")
public class MonthlyPlanServiceImpl implements MonthlyPlanService{
	
	@Autowired
	private MonthlyPlanDAO monthlyPlanDAO;

	@Override
	public void addMonthlyPlan(
			MonthlyPlanVO monthlyPlanVO
	) throws Exception {
		monthlyPlanDAO.addMonthlyPlan(monthlyPlanVO);
	}

}
