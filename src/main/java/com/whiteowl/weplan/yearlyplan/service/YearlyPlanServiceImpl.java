package com.whiteowl.weplan.yearlyplan.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.whiteowl.weplan.yearlyplan.dao.YearlyPlanDAO;
import com.whiteowl.weplan.yearlyplan.vo.YearlyPlanVO;

@Repository("yearlyPlanService")
public class YearlyPlanServiceImpl implements YearlyPlanService{
	
	@Autowired
	private YearlyPlanDAO yearlyPlanDAO;

	@Override
	public List yearlyPlanList(
			Map<String, Object> map
	) throws Exception {
		return yearlyPlanDAO.yearlyPlanList(
				map
			);
		
	}

	@Override
	public void addYearlyPlan(
			YearlyPlanVO yearlyPlanVO
	) throws Exception {
		yearlyPlanDAO.addYearlyPlan(yearlyPlanVO);
	}

	@Override
	public JSONObject popUpYearlyPlanView(
			int yearlyPlan_id
	) throws Exception {
		return yearlyPlanDAO
				.popUpYearlyPlanView(yearlyPlan_id);
	}

	@Override
	public void updateYearlyPlan(
			YearlyPlanVO yearlyPlanVO
	) throws Exception {
		yearlyPlanDAO.updateYearlyPlan(yearlyPlanVO);
	}

	@Override
	public void deleteYearlyPlan(
			Map<String, Object> map
	) throws Exception {
		yearlyPlanDAO.deleteYearlyPlan(map);
	}

}
