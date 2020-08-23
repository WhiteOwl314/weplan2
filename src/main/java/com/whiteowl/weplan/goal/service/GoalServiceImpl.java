package com.whiteowl.weplan.goal.service;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.whiteowl.weplan.goal.dao.GoalDAO;
import com.whiteowl.weplan.goal.vo.GoalVO;
import com.whiteowl.weplan.task.dao.TaskDAO;

@Service("goalService")
@Transactional(propagation = Propagation.REQUIRED)
public class GoalServiceImpl implements GoalService{
	
	@Autowired
	private GoalDAO goalDAO;

	@Override
	public List goalList(
			String member_id
	) throws Exception {
		List goalList = goalDAO.goalList(member_id);
		return goalList;
	}

	@Override
	public void addGoal(
			GoalVO goalVO
	) throws Exception {
		goalDAO.addGoal(goalVO);
	}

	@Override
	public void updateGoal(
			GoalVO goalVO
	) throws Exception {
		goalDAO.updateGoal(goalVO);
	}

	@Override
	public void updateGoalNullDate(
			GoalVO goalVO
	) throws Exception {
		goalDAO.updateGoalNullDate(goalVO);
		
	}

	@Override
	public void deleteGoal(
			Map<String, Object> map
	) throws Exception {
		
		goalDAO.deleteGoal(map);
	}

	@Override
	public JSONArray yearlyPlanList(
			Map<String, Object> map
	) throws Exception {
		
		
		return goalDAO.yearlyPlanList(map);
	}

	@Override
	public void completeGoal(
			Map<String, Object> map
	) throws Exception {
		goalDAO.completeGoal(map);
	}

	@Override
	public void addGoalNullAbsoluteValue(
			GoalVO goalVO
	) throws Exception {
		goalDAO.addGoalNullAbsoluteValue(goalVO);
		
	}

	@Override
	public JSONObject popUpGoalView(
			Map<String, Object> map
	) throws Exception {
		return goalDAO.popUpGoalView(
				map
		);
	}

	@Override
	public void updateGoalWithAbsoluteValue(GoalVO goalVO) throws Exception {
		goalDAO.updateGoalWithAbsoluteValue(goalVO);
		
	}

	@Override
	public JSONArray getGoalAllList(
			Map<String, Object> map
	) throws Exception {
		return goalDAO.getGoalAllList(map);
	}

}
