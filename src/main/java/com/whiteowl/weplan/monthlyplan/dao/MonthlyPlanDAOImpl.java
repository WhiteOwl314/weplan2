package com.whiteowl.weplan.monthlyplan.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.whiteowl.weplan.monthlyplan.vo.MonthlyPlanVO;

@Repository("monthlyPlanDAO")
public class MonthlyPlanDAOImpl implements MonthlyPlanDAO{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void addMonthlyPlan(
			MonthlyPlanVO monthlyPlanVO
	) throws DataAccessException {
		
		int id = sqlSession.selectOne(
				"mapper.monthlyPlan.selectNewMonthlyPlan_NO");
		monthlyPlanVO.setId(id);
		
		sqlSession.insert("mapper.monthlyPlan.addMonthlyPlan", monthlyPlanVO);
	}


}
