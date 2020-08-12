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
			int yearly_plan_id = weeklyPlanVO.getYearly_plan_id();
			
			data.put("id", id);
			data.put("title", title);
			data.put("content", content);
			data.put("isCompleted", isCompleted);
			data.put("importance", importance);
			data.put("month", month);
			data.put("week", week);
			data.put("yearly_plan_id", yearly_plan_id);
			
			ja.add(data);
		}
		return ja;
	}

	@Override
	public void addWeeklyPlan(
			WeeklyPlanVO weeklyPlanVO
	) throws DataAccessException {
		
		int id = sqlSession.selectOne(
				"mapper.weeklyPlan.selectNewWeeklyPlan_NO");
		weeklyPlanVO.setId(id);
		
		sqlSession.selectList(
				"mapper.weeklyPlan.addWeeklyPlan",
				weeklyPlanVO
		);
	}

}
