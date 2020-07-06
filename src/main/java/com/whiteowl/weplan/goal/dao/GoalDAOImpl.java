package com.whiteowl.weplan.goal.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.whiteowl.weplan.goal.vo.GoalVO;

@Repository("goalDAO")
public class GoalDAOImpl implements GoalDAO{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List goalList(
			String member_id
	) throws DataAccessException {
		List<GoalVO> goalList = 
				sqlSession.selectList(
						"mapper.goal.goalList",
						member_id
				);
		return goalList;
	}

}
