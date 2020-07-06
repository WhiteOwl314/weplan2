package com.whiteowl.weplan.goal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.whiteowl.weplan.goal.dao.GoalDAO;

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

}
