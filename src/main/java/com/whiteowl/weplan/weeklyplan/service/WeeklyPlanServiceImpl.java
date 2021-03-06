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

	@Override
	public void completeWeeklyPlan(
			Map<String, Object> map
	) throws Exception {
		weeklyPlanDAO.completeWeeklyPlan(map);
		
	}

	@Override
	public void notCompleteWeeklyPlan(
			Map<String, Object> map
	) throws Exception {
		weeklyPlanDAO.notCompleteWeeklyPlan(map);
	}

	@Override
	public JSONArray getWeeklyPlanListByOnlyMonth(
			Map<String, Object> map
	) throws Exception {
		return weeklyPlanDAO.getWeeklyPlanListByOnlyMonth(map);
	}

	@Override
	public void moveWeek(
			WeeklyPlanVO weeklyPlanVO
	) throws Exception {
		weeklyPlanDAO.moveWeek(weeklyPlanVO);
		
	}

	@Override
	public void addWeeklyPlanNullYearlyPlanId(
			WeeklyPlanVO weeklyPlanVO
	) throws Exception {
		weeklyPlanDAO.addWeeklyPlanNullYearlyPlanId(weeklyPlanVO);
	}

	@Override
	public JSONArray getWeeklyPlanListbyMonthAndWeek(
			Map<String, Object> map
	) throws Exception {
		return weeklyPlanDAO.getWeeklyPlanListbyMonthAndWeek(map);
	}

	@Override
	public void updateWeeklyPlanWithYearlyPlanId(
			WeeklyPlanVO weeklyPlanVO
	) throws Exception {
		weeklyPlanDAO.updateWeeklyPlanWithYearlyPlanId(weeklyPlanVO);
	}

}
