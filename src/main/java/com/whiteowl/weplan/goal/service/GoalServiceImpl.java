package com.whiteowl.weplan.goal.service;

import java.util.List;

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
	public void addGoalNullDate(
			GoalVO goalVO
	) throws Exception {
		goalDAO.addGoalNullDate(goalVO);
	}

	@Override
	public void addGoal(
			GoalVO goalVO
	) throws Exception {
		goalDAO.addGoal(goalVO);
	}

	@Override
	public JSONObject popUpGoalView(
			int goal_id
	) throws Exception {
		return goalDAO.popUpGoalView(
				goal_id
				);
	}

	@Override
	public void updateGoal(
			GoalVO goalVO
	) throws Exception {
		goalDAO.updateGoal(goalVO);
	}

}
