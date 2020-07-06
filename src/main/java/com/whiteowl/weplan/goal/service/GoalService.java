package com.whiteowl.weplan.goal.service;

import java.util.List;

import com.whiteowl.weplan.goal.vo.GoalVO;

public interface GoalService {

	public List goalList(String member_id) throws Exception;

	public void addGoalNullDate(GoalVO goalVO) throws Exception;

	public void addGoal(GoalVO goalVO) throws Exception;

}
