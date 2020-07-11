package com.whiteowl.weplan.yearlyplan.service;

import java.util.List;
import java.util.Map;

import com.whiteowl.weplan.yearlyplan.vo.YearlyPlanVO;

public interface YearlyPlanService {

	public List yearlyPlanList(Map<String, Object> map) throws Exception;

	public void addYearlyPlan(YearlyPlanVO yearlyPlanVO)throws Exception;

}
