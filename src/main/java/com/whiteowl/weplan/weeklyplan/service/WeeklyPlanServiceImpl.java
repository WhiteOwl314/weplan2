package com.whiteowl.weplan.weeklyplan.service;

import java.util.Map;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.whiteowl.weplan.weeklyplan.dao.WeeklyPlanDAO;

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

}
