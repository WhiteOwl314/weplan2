package com.whiteowl.weplan.weeklyplan.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.whiteowl.weplan.weeklyplan.vo.WeeklyPlanVO;

@Repository("weeklyPlanDAO")
public class WeeklyPlanDAOImpl implements WeeklyPlanDAO{
	@Autowired
	private SqlSession sqlSession;

	@Override
	public JSONArray getweeklyPlanListByMonth(
			Map<String, Object> map
	) throws DataAccessException {
		JSONArray ja = new JSONArray();
		
		List<WeeklyPlanVO> weeklyPlanList = sqlSession.selectList(
				"mapper.weeklyPlan.getweeklyPlanListByMonth",
				map
		);
		
		for (WeeklyPlanVO weeklyPlanVO : weeklyPlanList) {
			JSONObject data = new JSONObject();
			
			int id = weeklyPlanVO.getId();
			String title = weeklyPlanVO.getTitle();
			String content = weeklyPlanVO.getContent();
			String isCompleted = weeklyPlanVO.getIsCompleted();
			int importance = weeklyPlanVO.getImportance();
			String month = weeklyPlanVO.getMonth();
			int week = weeklyPlanVO.getWeek();
			int yearlyPlan_id = weeklyPlanVO.getYearlyPlan_id();
			
			data.put("id", id);
			data.put("title", title);
			data.put("content", content);
			data.put("isCompleted", isCompleted);
			data.put("importance", importance);
			data.put("month", month);
			data.put("week", week);
			data.put("yearlyPlan_id", yearlyPlan_id);
			
			ja.add(data);
		}
		return ja;
	}

}
