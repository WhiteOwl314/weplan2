package com.whiteowl.weplan.monthlyplan.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.sun.xml.internal.ws.api.message.HeaderList;
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

	@Override
	public void completeMonthlyPlan(
			Map<String, Object> map
	) throws Exception {
		monthlyPlanDAO.completeMonthlyPlan(map);
	}

	@Override
	public void notCompleteMonthlyPlan(
			Map<String, Object> map
	) throws Exception {
		monthlyPlanDAO.notCompleteMonthlyPlan(map);
		
	}

	@Override
	public JSONObject getMonthlyPlan(
			Map<String, Object> map
	) throws Exception {
		
		return monthlyPlanDAO.getMonthlyPlan(map);
	}

	@Override
	public void updateMonthlyPlan(
			MonthlyPlanVO monthlyPlanVO
	) throws Exception {
		monthlyPlanDAO.updateMonthlyPlan(monthlyPlanVO);
	}

	@Override
	public List yearlyView(
			Map<String, Object> map
	) throws Exception {
		String year = map.get("year").toString();
		String member_id = (String) map.get("member_id");
		List monthlyPlanListList = new ArrayList();
		
		for(int i=0; i<12; i++) {
			Map<String, Object> tempMap = new HashMap<String, Object>();
			tempMap.put("member_id", member_id);

			String month = "";
			
			if((i+1)<10) {
				month = "0" + Integer.toString(i+1);
			} else {
				month = Integer.toString(i+1);
			}
			
			String fullMonth = year + "-" + month;
			tempMap.put("month", fullMonth);
			
			List<Map<String, String>> monthlyPlanList = 
					monthlyPlanDAO.monthlyPlanListByMonth(tempMap);
			monthlyPlanListList.add(monthlyPlanList);
		}
		return monthlyPlanListList;
	}

	@Override
	public JSONArray getMonthlyPlanListByYear(
			Map<String, Object> map
	) throws Exception {

		return monthlyPlanDAO.getMonthlyPlanListByYear(map);
	}

	@Override
	public void addMonthlyPlanNullYearlyId(
			MonthlyPlanVO monthlyPlanVO
	) throws Exception {
		monthlyPlanDAO.addMonthlyPlanNullYearlyId(monthlyPlanVO);
	}

}
