package com.whiteowl.weplan.weeklyplan.service;

import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.whiteowl.weplan.weeklyplan.dao.WeeklyPlanDAO;
import com.whiteowl.weplan.weeklyplan.vo.WeeklyPlanVO;

@Repository("weeklyPlanService")
public class WeeklyPlanServiceImpl implements WeeklyPlanService{
	
	
	@Autowired
	private WeeklyPlanDAO weeklyPlanDAO;

	@Override
	public JSONArray getweeklyPlanListByMonth(
			Map<String, Object> map
	) throws Exception {
		return weeklyPlanDAO.getweeklyPlanListByMonth(map);
	}

	@Override
	public void addWeeklyPlan(
			WeeklyPlanVO weeklyPlanVO
	) throws Exception {
		weeklyPlanDAO.addWeeklyPlan(weeklyPlanVO);
	}

	@Override
	public JSONObject popUpWeeklyPlanView(
			Map<String, Object> map
	) throws Exception {
		return weeklyPlanDAO.popUpWeeklyPlanView(map);
	}

	@Override
	public void updateWeeklyPlan(
			WeeklyPlanVO weeklyPlanVO
	) throws Exception {
		weeklyPlanDAO.updateWeeklyPlan(weeklyPlanVO);
	}

}
